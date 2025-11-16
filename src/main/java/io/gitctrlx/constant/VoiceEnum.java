package io.gitctrlx.constant;

/**
 * The VoiceEnum enum defines various voice options for text-to-speech conversion.
 * <p>
 * Reference: <a href="https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list?trustedclienttoken=6A5AA1D4EAFF4E9FB37E23D68491D6F4">...</a>
 */
public enum VoiceEnum {

    // Chinese
    zh_HK_HiuGaaiNeural("zh-HK-HiuGaaiNeural", "Female", "zh-HK"),
    zh_HK_HiuMaanNeural("zh-HK-HiuMaanNeural", "Female", "zh-HK"),
    zh_HK_WanLungNeural("zh-HK-WanLungNeural", "Male", "zh-HK"),
    zh_CN_XiaoxiaoNeural("zh-CN-XiaoxiaoNeural", "Female", "zh-CN"),
    zh_CN_XiaoyiNeural("zh-CN-XiaoyiNeural", "Female", "zh-CN"),
    zh_CN_YunjianNeural("zh-CN-YunjianNeural", "Male", "zh-CN"),
    zh_CN_YunxiNeural("zh-CN-YunxiNeural", "Male", "zh-CN"),
    zh_CN_YunxiaNeural("zh-CN-YunxiaNeural", "Male", "zh-CN"),
    zh_CN_YunyangNeural("zh-CN-YunyangNeural", "Male", "zh-CN"),
    zh_CN_liaoning_XiaobeiNeural("zh-CN-liaoning-XiaobeiNeural", "Female", "zh-CN-liaoning"),
    zh_TW_HsiaoChenNeural("zh-TW-HsiaoChenNeural", "Female", "zh-TW"),
    zh_TW_YunJheNeural("zh-TW-YunJheNeural", "Male", "zh-TW"),
    zh_TW_HsiaoYuNeural("zh-TW-HsiaoYuNeural", "Female", "zh-TW"),
    zh_CN_shaanxi_XiaoniNeural("zh-CN-shaanxi-XiaoniNeural", "Female", "zh-CN-shaanxi"),

    // English (US)
    en_US_AvaMultilingualNeural("en-US-AvaMultilingualNeural", "Female", "en-US"),
    en_US_AndrewMultilingualNeural("en-US-AndrewMultilingualNeural", "Male", "en-US"),
    en_US_EmmaMultilingualNeural("en-US-EmmaMultilingualNeural", "Female", "en-US"),
    en_US_BrianMultilingualNeural("en-US-BrianMultilingualNeural", "Male", "en-US"),
    en_US_AvaNeural("en-US-AvaNeural", "Female", "en-US"),
    en_US_AndrewNeural("en-US-AndrewNeural", "Male", "en-US"),
    en_US_EmmaNeural("en-US-EmmaNeural", "Female", "en-US"),
    en_US_BrianNeural("en-US-BrianNeural", "Male", "en-US"),
    en_US_AnaNeural("en-US-AnaNeural", "Female", "en-US"),
    en_US_AriaNeural("en-US-AriaNeural", "Female", "en-US"),
    en_US_ChristopherNeural("en-US-ChristopherNeural", "Male", "en-US"),
    en_US_EricNeural("en-US-EricNeural", "Male", "en-US"),
    en_US_GuyNeural("en-US-GuyNeural", "Male", "en-US"),
    en_US_JennyNeural("en-US-JennyNeural", "Female", "en-US"),
    en_US_MichelleNeural("en-US-MichelleNeural", "Female", "en-US"),
    en_US_RogerNeural("en-US-RogerNeural", "Male", "en-US"),
    en_US_SteffanNeural("en-US-SteffanNeural", "Male", "en-US");

    private final String shortName;
    private final String gender;
    private final String locale;

    /**
     * Constructor to initialize the voice enum values
     *
     * @param shortName The short name of the voice
     * @param gender    The gender of the voice
     * @param locale    The locale of the voice
     */
    VoiceEnum(String shortName, String gender, String locale) {
        this.shortName = shortName;
        this.gender = gender;
        this.locale = locale;
    }

    /**
     * Gets the short name of the voice
     *
     * @return The short name of the voice
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Gets the gender of the voice
     *
     * @return The gender of the voice
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the locale of the voice
     *
     * @return The locale of the voice
     */
    public String getLocale() {
        return locale;
    }
}
