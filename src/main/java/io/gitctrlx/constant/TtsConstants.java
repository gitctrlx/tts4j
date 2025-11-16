package io.gitctrlx.constant;

/**
 * The TtsConstants interface defines constants for the text-to-speech conversion service.
 */
public interface TtsConstants {

    /**
     * Trusted client token
     */
    String TRUSTED_CLIENT_TOKEN = "6A5AA1D4EAFF4E9FB37E23D68491D6F4";

    /**
     * URL for the voice list
     * <p>
     * Reference: <a href="https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list?trustedclienttoken=6A5AA1D4EAFF4E9FB37E23D68491D6F4">...</a>
     */
    String VOICE_LIST_URL = "https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list";

    /**
     * WebSocket URL for Edge browser speech
     * <p>
     * Reference: wss://speech.platform.bing.com/consumer/speech/synthesize/readaloud/edge/v1?TrustedClientToken=6A5AA1D4EAFF4E9FB37E23D68491D6F4&ConnectionId=a6c52f201673b44b73fede7a6de9def2
     */
    String EDGE_SPEECH_WSS = "wss://speech.platform.bing.com/consumer/speech/synthesize/readaloud/edge/v1";

    /**
     * Origin URL for Edge browser speech WebSocket
     */
    String EDGE_SPEECH_ORIGIN = "chrome-extension://jdiccldimpdaibmpdkjnbmckianbfold";

    /**
     * User agent string for Edge
     */
    String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.44";

    /**
     * Marker for the start of audio stream transmission
     */
    String TURN_START = "turn.start";

    /**
     * Marker for the end of audio stream transmission
     */
    String TURN_END = "turn.end";

    /**
     * Header for the start of audio data stream
     */
    String AUDIO_START = "Path:audio\r\n";

    /**
     * MIME type for audio content
     */
    String AUDIO_CONTENT_TYPE = "Content-Type:audio";
}
