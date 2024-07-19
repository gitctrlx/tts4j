package io.gitctrlx.constant;

/**
 * TtsStyleEnum 枚举类定义了文本到语音转换中的各种说话风格和角色。
 * The TtsStyleEnum enum defines various speaking styles and roles for text-to-speech conversion.
 * <p>
 * 文档参考：<a href="https://learn.microsoft.com/zh-cn/azure/cognitive-services/speech-service/speech-synthesis-markup-voice#speaking-styles-and-roles">...</a>
 */
public enum TtsStyleEnum {
    /**
     * 用兴奋和精力充沛的语气推广产品或服务
     * Promote products or services with an upbeat and energetic tone
     */
    advertisement_upbeat("advertisement_upbeat"),
    /**
     * 以较高的音调和音量表达温暖而亲切的语气。说话者处于吸引听众注意力的状态。说话者的个性往往是讨喜的
     * Express warmth and friendliness with a higher pitch and volume, often engaging and likable
     */
    affectionate("affectionate"),
    /**
     * 表达生气和厌恶的语气
     * Express anger and disgust
     */
    angry("angry"),
    /**
     * 数字助理用的是热情而轻松的语气
     * Use an enthusiastic and casual tone for digital assistants
     */
    assistant("assistant"),
    /**
     * 以沉着冷静的态度说话。语气、音调和韵律与其他语音类型相比要统一得多
     * Speak with a calm and composed attitude, with a more uniform tone, pitch, and rhythm
     */
    calm("calm"),
    /**
     * 表达轻松随意的语气
     * Express a relaxed and casual tone
     */
    chat("chat"),
    /**
     * 表达积极愉快的语气
     * Express a positive and cheerful tone
     */
    cheerful("cheerful"),
    /**
     * 以友好热情的语气为客户提供支持
     * Provide support with a friendly and enthusiastic tone
     */
    customerservice("customerservice"),
    /**
     * 调低音调和音量来表达忧郁、沮丧的语气
     * Lower pitch and volume to express a melancholic and depressed tone
     */
    depressed("depressed"),
    /**
     * 表达轻蔑和抱怨的语气。 这种情绪的语音表现出不悦和蔑视
     * Express disdain and complaint, showing displeasure and contempt
     */
    disgruntled("disgruntled"),
    /**
     * 用一种轻松、感兴趣和信息丰富的风格讲述纪录片，适合配音纪录片、专家评论和类似内容
     * Narrate documentaries in a relaxed, engaging, and informative style, suitable for documentaries, expert commentary, and similar content
     */
    documentary_narration("documentary-narration"),
    /**
     * 在说话者感到不舒适时表达不确定、犹豫的语气
     * Express uncertainty and hesitation when the speaker feels uncomfortable
     */
    embarrassed("embarrassed"),
    /**
     * 表达关心和理解
     * Express care and understanding
     */
    empathetic("empathetic"),
    /**
     * 当你渴望别人拥有的东西时，表达一种钦佩的语气
     * Express admiration when you desire what others have
     */
    envious("envious"),
    /**
     * 表达乐观和充满希望的语气。似乎发生了一些美好的事情，说话人对此非常满意
     * Express optimism and hopefulness, as if something good has happened and the speaker is very pleased
     */
    excited("excited"),
    /**
     * 以较高的音调、较高的音量和较快的语速来表达恐惧、紧张的语气。说话人处于紧张和不安的状态
     * Express fear and nervousness with a higher pitch, higher volume, and faster speech rate, indicating tension and anxiety
     */
    fearful("fearful"),
    /**
     * 表达一种愉快、怡人且温暖的语气。听起来很真诚且满怀关切
     * Express a pleasant, pleasing, and warm tone, sounding sincere and caring
     */
    friendly("friendly"),
    /**
     * 以较低的音调和音量表达温和、礼貌和愉快的语气
     * Express a gentle, polite, and pleasant tone with a lower pitch and volume
     */
    gentle("gentle"),
    /**
     * 表达一种温暖且渴望的语气。听起来像是会有好事发生在说话人身上
     * Express a warm and hopeful tone, sounding like something good is about to happen to the speaker
     */
    hopeful("hopeful"),
    /**
     * 以优美又带感伤的方式表达情感
     * Express emotions in a lyrical and slightly melancholic manner
     */
    lyrical("lyrical"),
    /**
     * 以专业、客观的语气朗读内容
     * Read content in a professional and objective tone
     */
    narration_professional("narration-professional"),
    /**
     * 为内容阅读表达一种舒缓而悦耳的语气
     * Read content in a soothing and pleasant tone
     */
    narration_relaxed("narration-relaxed"),
    /**
     * 以正式专业的语气叙述新闻
     * Narrate news in a formal and professional tone
     */
    newscast("newscast"),
    /**
     * 以通用、随意的语气发布一般新闻
     * Deliver general news in a casual tone
     */
    newscast_casual("newscast-casual"),
    /**
     * 以正式、自信和权威的语气发布新闻
     * Deliver news in a formal, confident, and authoritative tone
     */
    newscast_formal("newscast-formal"),
    /**
     * 在读诗时表达出带情感和节奏的语气
     * Express emotions and rhythm when reading poetry
     */
    poetry_reading("poetry-reading"),
    /**
     * 表达悲伤语气
     * Express sadness
     */
    sad("sad"),
    /**
     * 表达严肃和命令的语气。说话者的声音通常比较僵硬，节奏也不那么轻松
     * Express a serious and commanding tone, with a typically stiff voice and less relaxed rhythm
     */
    serious("serious"),
    /**
     * 表达大声喊叫的语气。 说话者的声音通常比较僵硬，节奏也不那么轻松
     * Express a loud and shouting tone. The speaker's voice is usually stiff and the rhythm is not as relaxed
     */
    shouting("shouting"),
    /**
     * 用轻松有趣的语气播报体育赛事
     * Comment on sports events in a relaxed and entertaining tone
     */
    sports_commentary("sports_commentary"),
    /**
     * 用快速且充满活力的语气播报体育赛事精彩瞬间
     * Comment on exciting sports moments in a fast-paced and energetic tone
     */
    sports_commentary_excited("sports_commentary_excited"),
    /**
     * 说话非常柔和，发出的声音小且温柔
     * Speak very softly, with a quiet and gentle tone
     */
    whispering("whispering"),
    /**
     * 表达一种非常害怕的语气，语速快且声音颤抖。听起来说话人处于不稳定的疯狂状态
     * Express extreme fear with a fast speech rate and trembling voice, sounding unstable and frantic
     */
    terrified("terrified"),
    /**
     * 表达一种冷淡无情的语气
     * Express a cold and unfeeling tone
     */
    unfriendly("unfriendly");

    // 枚举值
    // Enum value
    private final String value;

    /**
     * 构造函数，初始化说话风格
     * Constructor to initialize the speaking style
     *
     * @param value 说话风格的值 The value of the speaking style
     */
    TtsStyleEnum(String value) {
        this.value = value;
    }

    /**
     * 获取说话风格的值
     * Gets the value of the speaking style
     *
     * @return 说话风格的值 The value of the speaking style
     */
    public String getValue() {
        return value;
    }
}
