package io.gitctrlx.service;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.constant.VoiceEnum;
import io.gitctrlx.util.Tools;

import java.io.Serializable;
import java.util.Optional;

/**
 * SSML类用于配置语音合成标记语言 (SSML) 的相关设置。
 * The SSML class is used to configure settings related to Speech Synthesis Markup Language (SSML).
 */
public class SSML implements Serializable {

    /**
     * SSML 模板，包括请求ID、内容类型、时间戳、路径和SSML内容。
     * SSML template including request ID, content type, timestamp, path, and SSML content.
     */
    public static final String SSML_PATTERN = "X-RequestId:%s\r\n" +
            "Content-Type:application/ssml+xml\r\n" +
            "X-Timestamp:%sZ\r\n" +
            "Path:ssml\r\n" +
            "\r\n" +
            "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='%s'>\r\n" +
            "<voice name='%s'>\r\n" +
            "<prosody pitch='+0Hz' rate='%s' volume='%s'>" +
            "%s" +
            "</prosody>" +
            "</voice>" +
            "</speak>";

    /**
     * 语音合成文本
     * Text for speech synthesis
     */
    private final String synthesisText;

    /**
     * 语音角色
     * Voice role
     */
    private final VoiceEnum voice;

    /**
     * 语速
     * Rate of speech
     * 以相对值表示：以相对数字或百分比表示。数字表示原始速率的倍数，百分比表示相对变化。
     * Represented as a relative value: as a relative number or percentage. Numbers indicate a multiple of the original rate, and percentages indicate relative change.
     */
    private final String rate;

    /**
     * 音量
     * Volume
     * 以绝对值或相对值表示。绝对值为0.0到100.0之间的数字，相对值可以是相对数字或百分比。
     * Represented as an absolute or relative value. Absolute values are numbers between 0.0 and 100.0, and relative values can be relative numbers or percentages.
     */
    private final String volume;

    /**
     * 文件格式
     */
    private final OutputFormat outputFormat;

    /**
     * 输出文件名
     */
    private final String outputFileName;

    /**
     * 构造函数，使用指定参数初始化SSML对象。
     * Constructor that initializes the SSML object with the specified parameters.
     *
     * @param synthesisText  语音合成文本 Text for speech synthesis
     * @param voice          语音角色 Voice role
     * @param rate           语速 Rate of speech
     * @param volume         音量 Volume
     * @param outputFormat   输出格式 Output format
     * @param outputFileName 输出文件名 Output file name
     */
    private SSML(String synthesisText, VoiceEnum voice, String rate, String volume, OutputFormat outputFormat, String outputFileName) {
        this.synthesisText = synthesisText;
        this.voice = voice;
        this.rate = rate;
        this.volume = volume;
        this.outputFormat = outputFormat;
        this.outputFileName = outputFileName;
    }

    /**
     * 静态工厂方法，返回一个SSMLBuilder实例。
     * Static factory method that returns an instance of SSMLBuilder.
     *
     * @return SSMLBuilder实例 An instance of SSMLBuilder.
     */
    public static SSMLBuilder builder() {
        return new SSMLBuilder();
    }

    /**
     * 获取语音合成文本。
     * Gets the synthesis text.
     *
     * @return 语音合成文本 The synthesis text.
     */
    public String getSynthesisText() {
        return synthesisText;
    }

    /**
     * 获取输出格式。
     * Gets the output format.
     *
     * @return 输出格式 The output format.
     */
    public OutputFormat getOutputFormat() {
        return outputFormat;
    }

    /**
     * 获取输出文件名。
     * Gets the output file name.
     *
     * @return 输出文件名 The output file name.
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * 返回当前SSML配置的字符串表示形式。
     * Returns the string representation of the current SSML configuration.
     *
     * @return 当前SSML配置的字符串表示 The string representation of the current SSML configuration.
     */
    @Override
    public String toString() {
        return String.format(SSML_PATTERN,
                Tools.getRandomId(),
                Tools.date(),
                Optional.ofNullable(voice).orElse(VoiceEnum.zh_CN_XiaoxiaoNeural).getLocale(),
                Optional.ofNullable(voice).orElse(VoiceEnum.zh_CN_XiaoxiaoNeural).getShortName(),
                Optional.ofNullable(rate).orElse("+0%"),
                Optional.ofNullable(volume).orElse("+0%"),
                synthesisText
        );
    }

    /**
     * SSMLBuilder类用于构建SSML对象。
     * The SSMLBuilder class is used to build SSML objects.
     */
    public static class SSMLBuilder {
        private String synthesisText;
        private VoiceEnum voice;
        private String rate;
        private String volume;
        private OutputFormat outputFormat;
        private String outputFileName;

        /**
         * 设置语音合成文本。
         * Sets the synthesis text.
         *
         * @param synthesisText 语音合成文本 The synthesis text.
         * @return SSMLBuilder实例 An instance of SSMLBuilder.
         */
        public SSMLBuilder synthesisText(String synthesisText) {
            this.synthesisText = synthesisText;
            return this;
        }

        /**
         * 设置语音角色。
         * Sets the voice role.
         *
         * @param voice 语音角色 The voice role.
         * @return SSMLBuilder实例 An instance of SSMLBuilder.
         */
        public SSMLBuilder voice(VoiceEnum voice) {
            this.voice = voice;
            return this;
        }

        /**
         * 设置语速。
         * Sets the rate of speech.
         *
         * @param rate 语速 The rate of speech.
         * @return SSMLBuilder实例 An instance of SSMLBuilder.
         */
        public SSMLBuilder rate(String rate) {
            this.rate = rate;
            return this;
        }

        /**
         * 设置音量。
         * Sets the volume.
         *
         * @param volume 音量 The volume.
         * @return SSMLBuilder实例 An instance of SSMLBuilder.
         */
        public SSMLBuilder volume(String volume) {
            this.volume = volume;
            return this;
        }

        /**
         * 设置输出文件名。
         * Sets the output file name.
         *
         * @param outputFileName 输出文件名 The output file name.
         * @return SSMLBuilder实例 An instance of SSMLBuilder.
         */
        public SSMLBuilder outputFileName(String outputFileName) {
            this.outputFileName = outputFileName;
            return this;
        }

        /**
         * 设置输出格式。
         * Sets the output format.
         *
         * @param outputFormat 输出格式 The output format.
         * @return SSMLBuilder实例 An instance of SSMLBuilder.
         */
        public SSMLBuilder outputFormat(OutputFormat outputFormat) {
            this.outputFormat = outputFormat;
            return this;
        }

        /**
         * 构建SSML对象。
         * Builds the SSML object.
         *
         * @return SSML对象 An instance of SSML.
         */
        public SSML build() {
            return new SSML(this.synthesisText, this.voice, this.rate, this.volume, this.outputFormat, this.outputFileName);
        }
    }
}
