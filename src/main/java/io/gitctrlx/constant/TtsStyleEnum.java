package io.gitctrlx.constant;

/**
 * The TtsStyleEnum enum defines various speaking styles and roles for text-to-speech conversion.
 * <p>
 * Reference: <a href="https://learn.microsoft.com/en-us/azure/cognitive-services/speech-service/speech-synthesis-markup-voice#speaking-styles-and-roles">...</a>
 */
public enum TtsStyleEnum {
    /**
     * Promote products or services with an upbeat and energetic tone
     */
    advertisement_upbeat("advertisement_upbeat"),
    /**
     * Express warmth and friendliness with a higher pitch and volume, often engaging and likable
     */
    affectionate("affectionate"),
    /**
     * Express anger and disgust
     */
    angry("angry"),
    /**
     * Use an enthusiastic and casual tone for digital assistants
     */
    assistant("assistant"),
    /**
     * Speak with a calm and composed attitude, with a more uniform tone, pitch, and rhythm
     */
    calm("calm"),
    /**
     * Express a relaxed and casual tone
     */
    chat("chat"),
    /**
     * Express a positive and cheerful tone
     */
    cheerful("cheerful"),
    /**
     * Provide support with a friendly and enthusiastic tone
     */
    customerservice("customerservice"),
    /**
     * Lower pitch and volume to express a melancholic and depressed tone
     */
    depressed("depressed"),
    /**
     * Express disdain and complaint, showing displeasure and contempt
     */
    disgruntled("disgruntled"),
    /**
     * Narrate documentaries in a relaxed, engaging, and informative style, suitable for documentaries, expert commentary, and similar content
     */
    documentary_narration("documentary-narration"),
    /**
     * Express uncertainty and hesitation when the speaker feels uncomfortable
     */
    embarrassed("embarrassed"),
    /**
     * Express care and understanding
     */
    empathetic("empathetic"),
    /**
     * Express admiration when you desire what others have
     */
    envious("envious"),
    /**
     * Express optimism and hopefulness, as if something good has happened and the speaker is very pleased
     */
    excited("excited"),
    /**
     * Express fear and nervousness with a higher pitch, higher volume, and faster speech rate, indicating tension and anxiety
     */
    fearful("fearful"),
    /**
     * Express a pleasant, pleasing, and warm tone, sounding sincere and caring
     */
    friendly("friendly"),
    /**
     * Express a gentle, polite, and pleasant tone with a lower pitch and volume
     */
    gentle("gentle"),
    /**
     * Express a warm and hopeful tone, sounding like something good is about to happen to the speaker
     */
    hopeful("hopeful"),
    /**
     * Express emotions in a lyrical and slightly melancholic manner
     */
    lyrical("lyrical"),
    /**
     * Read content in a professional and objective tone
     */
    narration_professional("narration-professional"),
    /**
     * Read content in a soothing and pleasant tone
     */
    narration_relaxed("narration-relaxed"),
    /**
     * Narrate news in a formal and professional tone
     */
    newscast("newscast"),
    /**
     * Deliver general news in a casual tone
     */
    newscast_casual("newscast-casual"),
    /**
     * Deliver news in a formal, confident, and authoritative tone
     */
    newscast_formal("newscast-formal"),
    /**
     * Express emotions and rhythm when reading poetry
     */
    poetry_reading("poetry-reading"),
    /**
     * Express sadness
     */
    sad("sad"),
    /**
     * Express a serious and commanding tone, with a typically stiff voice and less relaxed rhythm
     */
    serious("serious"),
    /**
     * Express a loud and shouting tone. The speaker's voice is usually stiff and the rhythm is not as relaxed
     */
    shouting("shouting"),
    /**
     * Comment on sports events in a relaxed and entertaining tone
     */
    sports_commentary("sports_commentary"),
    /**
     * Comment on exciting sports moments in a fast-paced and energetic tone
     */
    sports_commentary_excited("sports_commentary_excited"),
    /**
     * Speak very softly, with a quiet and gentle tone
     */
    whispering("whispering"),
    /**
     * Express extreme fear with a fast speech rate and trembling voice, sounding unstable and frantic
     */
    terrified("terrified"),
    /**
     * Express a cold and unfeeling tone
     */
    unfriendly("unfriendly");

    // Enum value
    private final String value;

    /**
     * Constructor to initialize the speaking style
     *
     * @param value The value of the speaking style
     */
    TtsStyleEnum(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the speaking style
     *
     * @return The value of the speaking style
     */
    public String getValue() {
        return value;
    }
}
