package io.gitctrlx.service;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.constant.TtsConstants;
import io.gitctrlx.exceptions.TtsException;
import io.gitctrlx.util.Tools;

import okhttp3.*;
import okio.Buffer;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * The TTSService class handles text-to-speech conversion services.
 */
public class TTSService {

    public static final Logger log = LoggerFactory.getLogger(TTSService.class);
    private volatile OutputFormat outputFormat;
    private volatile String outputFileName;
    private volatile boolean synthesising;
    private volatile String currentText;
    private final Buffer audioBuffer = new Buffer();
    private OkHttpClient okHttpClient;
    private WebSocket ws;
    private TTSCallback callback; // Callback interface

    public TTSService(){}

    /**
     * WebSocket listener
     */
    protected WebSocketListener webSocketListener = new WebSocketListener() {
        @Override
        public void onClosed( WebSocket webSocket, int code,  String reason) {
            super.onClosed(webSocket, code, reason);
            log.debug("onClosed:" + reason);
            TTSService.this.ws = null;
            synthesising = false;
        }

        @Override
        public void onClosing( WebSocket webSocket, int code,  String reason) {
            super.onClosing(webSocket, code, reason);
            log.debug("onClosing:" + reason);
            TTSService.this.ws = null;
            synthesising = false;
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            log.error("WebSocket onFailure", t);
            if (response != null) {
                log.error("Response: " + response.toString());
            }
            TTSService.this.ws = null;
            synthesising = false;
            if (callback != null) {
                callback.onError(new TtsException(t.getMessage(), t));
            }
        }

        @Override
        public void onMessage( WebSocket webSocket,  String text) {
            super.onMessage(webSocket, text);
            if (text.contains(TtsConstants.TURN_START)) {
                audioBuffer.clear();
            } else if (text.contains(TtsConstants.TURN_END)) {
                if (outputFileName == null || outputFileName.isEmpty()) {
                    outputFileName = (currentText.length() < 6 ? currentText : currentText.substring(0, 5)).replaceAll("[</|*。?\" >\\\\]", "") + Tools.localDateTime();
                }
                String absolutePath = writeAudio(outputFormat, audioBuffer.readByteString(), outputFileName);
                synthesising = false;
                outputFileName = null;
                if (callback != null) {
                    callback.onSuccess(absolutePath);
                }
            }
        }

        @Override
        public void onMessage( WebSocket webSocket,  ByteString bytes) {
            super.onMessage(webSocket, bytes);
            int audioIndex = bytes.lastIndexOf(TtsConstants.AUDIO_START.getBytes(StandardCharsets.UTF_8)) + TtsConstants.AUDIO_START.length();
            boolean audioContentType = bytes.lastIndexOf(TtsConstants.AUDIO_CONTENT_TYPE.getBytes(StandardCharsets.UTF_8)) + TtsConstants.AUDIO_CONTENT_TYPE.length() != -1;
            if (audioIndex != -1 && audioContentType) {
                try {
                    audioBuffer.write(bytes.substring(audioIndex));
                } catch (Exception e) {
                    log.error("[ERROR] onMessage Error," + e.getMessage(), e);
                }
            }
        }
    };

    /**
     * The TTSCallback interface defines callback methods for text-to-speech synthesis.
     */
    public interface TTSCallback {
        void onSuccess(String filePath);
        void onError(Exception e);
    }

    /**
     * Sends text for speech synthesis
     *
     * @param ssml The SSML text
     * @param callback The callback interface
     */
    public void sendText(SSML ssml, TTSCallback callback) {
        this.callback = callback; // Initialize callback
        while (synthesising) {
            log.info("[INFO] Idling while waiting for the previous speech synthesis");
            Tools.sleep(1);
        }
        synthesising = true;
        if (Objects.nonNull(ssml.getOutputFormat()) && !ssml.getOutputFormat().equals(outputFormat)) {
            sendConfig(ssml.getOutputFormat());
        }
        log.debug("ssml:{}", ssml);
        if (!getOrCreateWs().send(ssml.toString())) {
            callback.onError(new TtsException("[ERROR] Failed to send speech synthesis request..."));
            synthesising = false;
            return;
        }
        currentText = ssml.getSynthesisText();
        outputFileName = ssml.getOutputFileName();
    }

    /**
     * Closes the text-to-speech service
     */
    public void close() {
        while (synthesising) {
            log.info("[INFO] Idling while waiting for speech synthesis...");
            Tools.sleep(1);
        }
        if (Objects.nonNull(ws)) {
            ws.close(1000, "bye");
        }
        if (Objects.nonNull(okHttpClient)) {
            okHttpClient.dispatcher().executorService().shutdown();   // Clear and shut down the thread pool
            okHttpClient.connectionPool().evictAll();                 // Clear and close the connection pool
        }
    }

    /**
     * Gets or creates a WebSocket connection
     *
     * @return The WebSocket connection
     */
    private synchronized WebSocket getOrCreateWs() {
        if (Objects.nonNull(ws)) {
            return ws;
        }

        String url = TtsConstants.EDGE_SPEECH_WSS + "?Retry-After=200&TrustedClientToken=" + TtsConstants.TRUSTED_CLIENT_TOKEN + "&ConnectionId=" + Tools.getRandomId();
        String origin = TtsConstants.EDGE_SPEECH_ORIGIN;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", TtsConstants.UA)
                .addHeader("Origin", origin)
                .build();
        ws = getOkHttpClient().newWebSocket(request, webSocketListener);
        sendConfig(outputFormat);
        return ws;
    }

    /**
     * Gets the OkHttpClient instance
     *
     * @return The OkHttpClient instance
     */
    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .pingInterval(20, TimeUnit.SECONDS) // Set PING frame interval
                    .build();
        }
        return okHttpClient;
    }

    /**
     * Sends the speech configuration
     *
     * @param outputFormat The output format
     */
    private void sendConfig(OutputFormat outputFormat) {
        SpeechConfig speechConfig = SpeechConfig.of(outputFormat);
        log.debug("audio config:{}", speechConfig);
        if (!getOrCreateWs().send(speechConfig.toString())) {
            throw TtsException.of("[ERROR] Failed to configure the speech output format");
        }
        this.outputFormat = speechConfig.getOutputFormat();
    }

    /**
     * Writes the audio file
     *
     * @param format The audio format
     * @param data The audio data
     * @param fileName The file name
     * @return The absolute path of the audio file
     */
    private String writeAudio(OutputFormat format, ByteString data, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            // Convert ByteString to byte array
            byte[] audioBuffer = data.toByteArray();

            // Get file suffix
            File outputAudioFile = getAudioFile(format, fileName);

            // Create file output stream and write audio data
            fileOutputStream = new FileOutputStream(outputAudioFile);
            fileOutputStream.write(audioBuffer);
            fileOutputStream.flush();

            // Return the absolute path of the audio file
            return outputAudioFile.getAbsolutePath();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw TtsException.of("[ERROR] Audio file write error: " + e.getMessage());

        } finally {
            // Ensure the file output stream is closed upon completion
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    log.error("[ERROR] Failed to close FileOutputStream: " + e.getMessage(), e);
                }
            }
        }
    }


    private static File getAudioFile(OutputFormat format, String fileName) {
        String[] split = format.getValue().split("-");
        String suffix = split[split.length - 1];

        // Generate full output file name
        String outputFileName = fileName + "." + suffix;
        File outputAudioFile = new File(outputFileName);

        // If file already exists, delete it first
        if (outputAudioFile.exists()) {
            if (!outputAudioFile.delete()) {
                throw new TtsException("[ERROR] Failed to delete existing file: " + outputFileName);
            }
        }
        return outputAudioFile;
    }

}
