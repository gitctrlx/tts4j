package io.gitctrlx.service;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.constant.VoiceEnum;
import io.gitctrlx.util.Tools;

import java.io.Serializable;
import java.util.Optional;

/**
 * The SSML class is used to configure settings related to Speech Synthesis Markup Language (SSML).
 */
public class SSML implements Serializable {

    /**
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
     * Text for speech synthesis
     */
    private final String synthesisText;

    /**
     * Voice role
     */
    private final VoiceEnum voice;

    /**
     * Rate of speech
     * Represented as a relative value: as a relative number or percentage. Numbers indicate a multiple of the original rate, and percentages indicate relative change.
     */
    private final String rate;

    /**
     * Volume
     * Represented as an absolute or relative value. Absolute values are numbers between 0.0 and 100.0, and relative values can be relative numbers or percentages.
     */
    private final String volume;

    /**
     * Output format
     */
    private final OutputFormat outputFormat;

    /**
     * Output file name
     */
    private final String outputFileName;

    /**
     * Constructor that initializes the SSML object with the specified parameters.
     *
     * @param synthesisText  Text for speech synthesis
     * @param voice          Voice role
     * @param rate           Rate of speech
     * @param volume         Volume
     * @param outputFormat   Output format
     * @param outputFileName Output file name
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
     * Static factory method that returns an instance of SSMLBuilder.
     *
     * @return An instance of SSMLBuilder.
     */
    public static SSMLBuilder builder() {
        return new SSMLBuilder();
    }

    /**
     * Gets the synthesis text.
     *
     * @return The synthesis text.
     */
    public String getSynthesisText() {
        return synthesisText;
    }

    /**
     * Gets the output format.
     *
     * @return The output format.
     */
    public OutputFormat getOutputFormat() {
        return outputFormat;
    }

    /**
     * Gets the output file name.
     *
     * @return The output file name.
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * Returns the string representation of the current SSML configuration.
     *
     * @return The string representation of the current SSML configuration.
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
         * Sets the synthesis text.
         *
         * @param synthesisText The synthesis text.
         * @return An instance of SSMLBuilder.
         */
        public SSMLBuilder synthesisText(String synthesisText) {
            this.synthesisText = synthesisText;
            return this;
        }

        /**
         * Sets the voice role.
         *
         * @param voice The voice role.
         * @return An instance of SSMLBuilder.
         */
        public SSMLBuilder voice(VoiceEnum voice) {
            this.voice = voice;
            return this;
        }

        /**
         * Sets the rate of speech.
         *
         * @param rate The rate of speech.
         * @return An instance of SSMLBuilder.
         */
        public SSMLBuilder rate(String rate) {
            this.rate = rate;
            return this;
        }

        /**
         * Sets the volume.
         *
         * @param volume The volume.
         * @return An instance of SSMLBuilder.
         */
        public SSMLBuilder volume(String volume) {
            this.volume = volume;
            return this;
        }

        /**
         * Sets the output file name.
         *
         * @param outputFileName The output file name.
         * @return An instance of SSMLBuilder.
         */
        public SSMLBuilder outputFile(String outputFileName) {
            this.outputFileName = outputFileName;
            return this;
        }

        /**
         * Sets the output format.
         *
         * @param outputFormat The output format.
         * @return An instance of SSMLBuilder.
         */
        public SSMLBuilder outputFormat(OutputFormat outputFormat) {
            this.outputFormat = outputFormat;
            return this;
        }

        /**
         * Builds the SSML object.
         *
         * @return An instance of SSML.
         */
        public SSML build() {
            return new SSML(this.synthesisText, this.voice, this.rate, this.volume, this.outputFormat, this.outputFileName);
        }
    }
}
