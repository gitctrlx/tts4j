package io.gitctrlx.service;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.util.Tools;

import java.io.Serializable;
import java.util.Optional;

/**
 * SpeechConfig类用于配置语音输出格式。
 * The SpeechConfig class is used to configure the speech output format.
 */
public class SpeechConfig implements Serializable {

    /**
     * 配置模式模板，包括时间戳、内容类型、路径和具体配置内容。
     * Configuration pattern template including timestamp, content type, path, and specific configuration content.
     */
    public static final String CONFIG_PATTERN = "X-Timestamp:%s\r\n" +
            "Content-Type:application/json; charset=utf-8\r\n" +
            "Path:speech.config\r\n" +
            "\r\n" +
            "{\"context\":{\"synthesis\":{\"audio\":{\"metadataoptions\":{\"sentenceBoundaryEnabled\":\"false\",\"wordBoundaryEnabled\":\"true\"},\"outputFormat\":\"%s\"}}}}";

    private OutputFormat outputFormat;

    /**
     * 构造函数，使用指定的输出格式进行初始化。如果输出格式为空，则使用默认值。
     * Constructor that initializes with the specified output format. If the output format is null, the default value is used.
     *
     * @param outputFormat 语音输出格式 The speech output format.
     */
    private SpeechConfig(OutputFormat outputFormat) {
        this.outputFormat = Optional
                .ofNullable(outputFormat)
                .orElse(OutputFormat.audio_24khz_48kbitrate_mono_mp3);
    }

    /**
     * 静态工厂方法，返回一个使用指定输出格式的SpeechConfig实例。
     * Static factory method that returns a SpeechConfig instance with the specified output format.
     *
     * @param outputFormat 语音输出格式 The speech output format.
     * @return SpeechConfig实例 An instance of SpeechConfig.
     */
    public static SpeechConfig of(OutputFormat outputFormat) {
        return new SpeechConfig(outputFormat);
    }

    /**
     * 获取当前的输出格式。
     * Gets the current output format.
     *
     * @return 当前的输出格式 The current output format.
     */
    public OutputFormat getOutputFormat() {
        return outputFormat;
    }

    /**
     * 设置新的输出格式。
     * Sets a new output format.
     *
     * @param outputFormat 新的语音输出格式 The new speech output format.
     */
    public void setOutputFormat(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    /**
     * 返回当前配置的字符串表示形式。
     * Returns the string representation of the current configuration.
     *
     * @return 当前配置的字符串表示 The string representation of the current configuration.
     */
    @Override
    public String toString() {
        return String.format(CONFIG_PATTERN, Tools.date(), outputFormat.getValue());
    }
}
