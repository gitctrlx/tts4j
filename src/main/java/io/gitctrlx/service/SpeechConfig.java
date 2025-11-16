package io.gitctrlx.service;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.util.Tools;

import java.io.Serializable;
import java.util.Optional;

/**
 * The SpeechConfig class is used to configure the speech output format.
 */
public class SpeechConfig implements Serializable {

    /**
     * Configuration pattern template including timestamp, content type, path, and specific configuration content.
     */
    public static final String CONFIG_PATTERN = "X-Timestamp:%s\r\n" +
            "Content-Type:application/json; charset=utf-8\r\n" +
            "Path:speech.config\r\n" +
            "\r\n" +
            "{\"context\":{\"synthesis\":{\"audio\":{\"metadataoptions\":{\"sentenceBoundaryEnabled\":\"false\",\"wordBoundaryEnabled\":\"true\"},\"outputFormat\":\"%s\"}}}}";

    private OutputFormat outputFormat;

    /**
     * Constructor that initializes with the specified output format. If the output format is null, the default value is used.
     *
     * @param outputFormat The speech output format.
     */
    private SpeechConfig(OutputFormat outputFormat) {
        this.outputFormat = Optional
                .ofNullable(outputFormat)
                .orElse(OutputFormat.audio_24khz_48kbitrate_mono_mp3);
    }

    /**
     * Static factory method that returns a SpeechConfig instance with the specified output format.
     *
     * @param outputFormat The speech output format.
     * @return An instance of SpeechConfig.
     */
    public static SpeechConfig of(OutputFormat outputFormat) {
        return new SpeechConfig(outputFormat);
    }

    /**
     * Gets the current output format.
     *
     * @return The current output format.
     */
    public OutputFormat getOutputFormat() {
        return outputFormat;
    }

    /**
     * Sets a new output format.
     *
     * @param outputFormat The new speech output format.
     */
    public void setOutputFormat(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    /**
     * Returns the string representation of the current configuration.
     *
     * @return The string representation of the current configuration.
     */
    @Override
    public String toString() {
        return String.format(CONFIG_PATTERN, Tools.date(), outputFormat.getValue());
    }
}
