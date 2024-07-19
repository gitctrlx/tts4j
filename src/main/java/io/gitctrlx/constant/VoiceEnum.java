package io.gitctrlx.constant;

/**
 * VoiceEnum 枚举类定义了文本到语音转换中的各种语音选择。
 * The VoiceEnum enum defines various voice options for text-to-speech conversion.
 * <p>
 * 文档参考：<a href="https://speech.platform.bing.com/consumer/speech/synthesize/readaloud/voices/list?trustedclienttoken=6A5AA1D4EAFF4E9FB37E23D68491D6F4">...</a>
 */
public enum VoiceEnum {

    // 中文
    zh_HK_HiuGaaiNeural("zh-HK-HiuGaaiNeural", "女", "zh-HK"),
    zh_HK_HiuMaanNeural("zh-HK-HiuMaanNeural", "女", "zh-HK"),
    zh_HK_WanLungNeural("zh-HK-WanLungNeural", "男", "zh-HK"),
    zh_CN_XiaoxiaoNeural("zh-CN-XiaoxiaoNeural", "女", "zh-CN"),
    zh_CN_XiaoyiNeural("zh-CN-XiaoyiNeural", "女", "zh-CN"),
    zh_CN_YunjianNeural("zh-CN-YunjianNeural", "男", "zh-CN"),
    zh_CN_YunxiNeural("zh-CN-YunxiNeural", "男", "zh-CN"),
    zh_CN_YunxiaNeural("zh-CN-YunxiaNeural", "男", "zh-CN"),
    zh_CN_YunyangNeural("zh-CN-YunyangNeural", "男", "zh-CN"),
    zh_CN_liaoning_XiaobeiNeural("zh-CN-liaoning-XiaobeiNeural", "女", "zh-CN-liaoning"),
    zh_TW_HsiaoChenNeural("zh-TW-HsiaoChenNeural", "女", "zh-TW"),
    zh_TW_YunJheNeural("zh-TW-YunJheNeural", "男", "zh-TW"),
    zh_TW_HsiaoYuNeural("zh-TW-HsiaoYuNeural", "女", "zh-TW"),
    zh_CN_shaanxi_XiaoniNeural("zh-CN-shaanxi-XiaoniNeural", "女", "zh-CN-shaanxi"),

    // 英语 (美国)
    en_US_AvaMultilingualNeural("en-US-AvaMultilingualNeural", "女", "en-US"),
    en_US_AndrewMultilingualNeural("en-US-AndrewMultilingualNeural", "男", "en-US"),
    en_US_EmmaMultilingualNeural("en-US-EmmaMultilingualNeural", "女", "en-US"),
    en_US_BrianMultilingualNeural("en-US-BrianMultilingualNeural", "男", "en-US"),
    en_US_AvaNeural("en-US-AvaNeural", "女", "en-US"),
    en_US_AndrewNeural("en-US-AndrewNeural", "男", "en-US"),
    en_US_EmmaNeural("en-US-EmmaNeural", "女", "en-US"),
    en_US_BrianNeural("en-US-BrianNeural", "男", "en-US"),
    en_US_AnaNeural("en-US-AnaNeural", "女", "en-US"),
    en_US_AriaNeural("en-US-AriaNeural", "女", "en-US"),
    en_US_ChristopherNeural("en-US-ChristopherNeural", "男", "en-US"),
    en_US_EricNeural("en-US-EricNeural", "男", "en-US"),
    en_US_GuyNeural("en-US-GuyNeural", "男", "en-US"),
    en_US_JennyNeural("en-US-JennyNeural", "女", "en-US"),
    en_US_MichelleNeural("en-US-MichelleNeural", "女", "en-US"),
    en_US_RogerNeural("en-US-RogerNeural", "男", "en-US"),
    en_US_SteffanNeural("en-US-SteffanNeural", "男", "en-US");

    private final String shortName;
    private final String gender;
    private final String locale;

    /**
     * 构造函数，初始化语音枚举值
     * Constructor to initialize the voice enum values
     *
     * @param shortName 语音短名称 The short name of the voice
     * @param gender    性别 The gender of the voice
     * @param locale    语言环境 The locale of the voice
     */
    VoiceEnum(String shortName, String gender, String locale) {
        this.shortName = shortName;
        this.gender = gender;
        this.locale = locale;
    }

    /**
     * 获取语音短名称
     * Gets the short name of the voice
     *
     * @return 语音短名称 The short name of the voice
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 获取语音的性别
     * Gets the gender of the voice
     *
     * @return 性别 The gender of the voice
     */
    public String getGender() {
        return gender;
    }

    /**
     * 获取语音的语言环境
     * Gets the locale of the voice
     *
     * @return 语言环境 The locale of the voice
     */
    public String getLocale() {
        return locale;
    }
}
