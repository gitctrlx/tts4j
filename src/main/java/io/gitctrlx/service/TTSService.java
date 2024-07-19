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
 * TTSService 类用于处理文本到语音的转换服务。
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
    private TTSCallback callback; // 回调接口

    public TTSService(){}

    /**
     * WebSocket 监听器
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
     * TTSCallback 接口定义了语音合成的回调方法。
     * The TTSCallback interface defines callback methods for text-to-speech synthesis.
     */
    public interface TTSCallback {
        void onSuccess(String filePath);
        void onError(Exception e);
    }

    /**
     * 发送文本进行语音合成
     * Sends text for speech synthesis
     *
     * @param ssml SSML文本 The SSML text
     * @param callback 回调接口 The callback interface
     */
    public void sendText(SSML ssml, TTSCallback callback) {
        this.callback = callback; // 初始化回调
        while (synthesising) {
            log.info("[INFO] Idling while waiting for the previous speech synthesis (空转等待上一个语音合成)");
            Tools.sleep(1);
        }
        synthesising = true;
        if (Objects.nonNull(ssml.getOutputFormat()) && !ssml.getOutputFormat().equals(outputFormat)) {
            sendConfig(ssml.getOutputFormat());
        }
        log.debug("ssml:{}", ssml);
        if (!getOrCreateWs().send(ssml.toString())) {
            callback.onError(new TtsException("[ERROR] Failed to send speech synthesis request... (语音合成请求发送失败...)"));
            synthesising = false;
            return;
        }
        currentText = ssml.getSynthesisText();
        outputFileName = ssml.getOutputFileName();
    }

    /**
     * 关闭语音合成服务
     * Closes the text-to-speech service
     */
    public void close() {
        while (synthesising) {
            log.info("[INFO] Idling while waiting for speech synthesis...(空转等待语音合成...)");
            Tools.sleep(1);
        }
        if (Objects.nonNull(ws)) {
            ws.close(1000, "bye");
        }
        if (Objects.nonNull(okHttpClient)) {
            okHttpClient.dispatcher().executorService().shutdown();   // 清除并关闭线程池
            okHttpClient.connectionPool().evictAll();                 // 清除并关闭连接池
        }
    }

    /**
     * 获取或创建 WebSocket 连接
     * Gets or creates a WebSocket connection
     *
     * @return WebSocket 连接 The WebSocket connection
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
     * 获取 OkHttpClient 实例
     * Gets the OkHttpClient instance
     *
     * @return OkHttpClient 实例 The OkHttpClient instance
     */
    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .pingInterval(20, TimeUnit.SECONDS) // 设置 PING 帧发送间隔
                    .build();
        }
        return okHttpClient;
    }

    /**
     * 发送语音配置
     * Sends the speech configuration
     *
     * @param outputFormat 输出格式 The output format
     */
    private void sendConfig(OutputFormat outputFormat) {
        SpeechConfig speechConfig = SpeechConfig.of(outputFormat);
        log.debug("audio config:{}", speechConfig);
        if (!getOrCreateWs().send(speechConfig.toString())) {
            throw TtsException.of("[ERROR] Failed to configure the speech output format (语音输出格式配置失败!)");
        }
        this.outputFormat = speechConfig.getOutputFormat();
    }

    /**
     * 写入音频文件
     * Writes the audio file
     *
     * @param format 音频格式 The audio format
     * @param data 音频数据 The audio data
     * @param fileName 文件名 The file name
     * @return 音频文件的绝对路径 The absolute path of the audio file
     */
    private String writeAudio(OutputFormat format, ByteString data, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            // 将 ByteString 转换为字节数组 Convert ByteString to byte array
            byte[] audioBuffer = data.toByteArray();

            // 获取文件后缀 Get file suffix
            File outputAudioFile = getAudioFile(format, fileName);

            // 创建文件输出流并写入音频数据 Create file output stream and write audio data
            fileOutputStream = new FileOutputStream(outputAudioFile);
            fileOutputStream.write(audioBuffer);
            fileOutputStream.flush();

            // 返回音频文件的绝对路径 Return the absolute path of the audio file
            return outputAudioFile.getAbsolutePath();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw TtsException.of("[ERROR] Audio file write error (音频文件写出异常): " + e.getMessage());

        } finally {
            // 确保文件输出流在完成时关闭 Ensure the file output stream is closed upon completion
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    log.error("[ERROR] Failed to close FileOutputStream (关闭文件输出流失败): " + e.getMessage(), e);
                }
            }
        }
    }


    private static File getAudioFile(OutputFormat format, String fileName) {
        String[] split = format.getValue().split("-");
        String suffix = split[split.length - 1];

        // 生成完整的输出文件名 Generate full output file name
        String outputFileName = fileName + "." + suffix;
        File outputAudioFile = new File(outputFileName);

        // 如果文件已存在，先删除 If file already exists, delete it first
        if (outputAudioFile.exists()) {
            if (!outputAudioFile.delete()) {
                throw new TtsException("[ERROR] Failed to delete existing file (删除现有文件失败): " + outputFileName);
            }
        }
        return outputAudioFile;
    }

}
