package io.gitctrlx.constant;

/**
 * 输出格式枚举类，定义了文本到语音转换的各种音频输出格式。
 * Enum class for output formats, defining various audio output formats for text-to-speech conversion.
 * <p>
 * 文档参考：<a href="https://learn.microsoft.com/zh-cn/azure/cognitive-services/speech-service/rest-text-to-speech?tabs=streaming#audio-outputs">...</a>
 */
public enum OutputFormat {
    /**
     * 音频输出格式。统一输出为MP3，其他格式可能无法解码（本项目中未实现）。
     * Audio output format. Unified output as MP3, other formats may not be decodable.
     */
    // amr
    amr_wb_16000hz("amr-wb-16000hz"),

    // audio
    audio_16khz_16bit_32kbps_mono_opus("audio-16khz-16bit-32kbps-mono-opus"),
    audio_16khz_32kbitrate_mono_mp3("audio-16khz-32kbitrate-mono-mp3"),
    audio_16khz_64kbitrate_mono_mp3("audio-16khz-64kbitrate-mono-mp3"),
    audio_16khz_128kbitrate_mono_mp3("audio-16khz-128kbitrate-mono-mp3"),
    audio_24khz_16bit_24kbps_mono_opus("audio-24khz-16bit-24kbps-mono-opus"),
    audio_24khz_16bit_48kbps_mono_opus("audio-24khz-16bit-48kbps-mono-opus"),
    audio_24khz_48kbitrate_mono_mp3("audio-24khz-48kbitrate-mono-mp3"), // 推荐 Recommended
    audio_24khz_96kbitrate_mono_mp3("audio-24khz-96kbitrate-mono-mp3"),
    audio_24khz_160kbitrate_mono_mp3("audio-24khz-160kbitrate-mono-mp3"),
    audio_48khz_96kbitrate_mono_mp3("audio-48khz-96kbitrate-mono-mp3"),
    audio_48khz_192kbitrate_mono_mp3("audio-48khz-192kbitrate-mono-mp3"),

    // ogg
    ogg_16khz_16bit_mono_opus("ogg-16khz-16bit-mono-opus"),
    ogg_24khz_16bit_mono_opus("ogg-24khz-16bit-mono-opus"),
    ogg_48khz_16bit_mono_opus("ogg-48khz-16bit-mono-opus"),


    // raw
    raw_8khz_8bit_mono_alaw("raw-8khz-8bit-mono-alaw"),
    raw_8khz_8bit_mono_mulaw("raw-8khz-8bit-mono-mulaw"),
    raw_8khz_16bit_mono_pcm("raw-8khz-16bit-mono-pcm"),
    raw_16khz_16bit_mono_pcm("raw-16khz-16bit-mono-pcm"),
    raw_16khz_16bit_mono_truesilk("raw-16khz-16bit-mono-truesilk"),
    raw_22050hz_16bit_mono_pcm("raw-22050hz-16bit-mono-pcm"),
    raw_24khz_16bit_mono_pcm("raw-24khz-16bit-mono-pcm"),
    raw_24khz_16bit_mono_truesilk("raw-24khz-16bit-mono-truesilk"),
    raw_44100hz_16bit_mono_pcm("raw-44100hz-16bit-mono-pcm"),
    raw_48khz_16bit_mono_pcm("raw-48khz-16bit-mono-pcm"),

    // webm
    webm_16khz_16bit_mono_opus("webm-16khz-16bit-mono-opus"),
    webm_24khz_16bit_24kbps_mono_opus("webm-24khz-16bit-24kbps-mono-opus"),
    webm_24khz_16bit_mono_opus("webm-24khz-16bit-mono-opus");

    // 枚举值
    // Enum value
    private final String value;

    /**
     * 构造函数，初始化输出格式。
     * Constructor to initialize the output format.
     *
     * @param value 输出格式的值 The value of the output format.
     */
    OutputFormat(String value) {
        this.value = value;
    }

    /**
     * 获取输出格式的值。
     * Gets the value of the output format.
     *
     * @return 输出格式的值 The value of the output format.
     */
    public String getValue() {
        return value;
    }
}
