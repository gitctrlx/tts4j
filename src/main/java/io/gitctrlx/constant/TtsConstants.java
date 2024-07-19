package io.gitctrlx.constant;

/**
 * TtsConstants接口定义了文本到语音转换服务的常量。
 * The TtsConstants interface defines constants for the text-to-speech conversion service.
 */
public interface TtsConstants {

    /**
     * 受信任的客户端令牌
     * Trusted client token
     */
    String TRUSTED_CLIENT_TOKEN = "6A5AA1D4EAFF4E9FB37E23D68491D6F4";

    /**
     * 语音列表URL
     * URL for the voice list
     * <p>
     * 参考：<a href="https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list?trustedclienttoken=6A5AA1D4EAFF4E9FB37E23D68491D6F4">...</a>
     * Reference: <a href="https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list?trustedclienttoken=6A5AA1D4EAFF4E9FB37E23D68491D6F4">...</a>
     */
    String VOICE_LIST_URL = "https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list";

    /**
     * Edge浏览器语音WebSocket URL
     * WebSocket URL for Edge browser speech
     * <p>
     * 参考：wss://speech.platform.bing.com/consumer/speech/synthesize/readaloud/edge/v1?TrustedClientToken=6A5AA1D4EAFF4E9FB37E23D68491D6F4&ConnectionId=a6c52f201673b44b73fede7a6de9def2
     * Reference: wss://speech.platform.bing.com/consumer/speech/synthesize/readaloud/edge/v1?TrustedClientToken=6A5AA1D4EAFF4E9FB37E23D68491D6F4&ConnectionId=a6c52f201673b44b73fede7a6de9def2
     */
    String EDGE_SPEECH_WSS = "wss://speech.platform.bing.com/consumer/speech/synthesize/readaloud/edge/v1";

    /**
     * Edge浏览器语音WebSocket的来源URL
     * Origin URL for Edge browser speech WebSocket
     */
    String EDGE_SPEECH_ORIGIN = "chrome-extension://jdiccldimpdaibmpdkjnbmckianbfold";

    /**
     * Edge用户代理字符串
     * User agent string for Edge
     */
    String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.44";

    /**
     * 音频流开始传输标记
     * Marker for the start of audio stream transmission
     */
    String TURN_START = "turn.start";

    /**
     * 音频流结束传输标记
     * Marker for the end of audio stream transmission
     */
    String TURN_END = "turn.end";

    /**
     * 音频数据流标志头
     * Header for the start of audio data stream
     */
    String AUDIO_START = "Path:audio\r\n";

    /**
     * 音频内容类型
     * MIME type for audio content
     */
    String AUDIO_CONTENT_TYPE = "Content-Type:audio";
}
