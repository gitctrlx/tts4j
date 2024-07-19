package io.gitctrlx;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.constant.VoiceEnum;
import io.gitctrlx.service.SSML;
import io.gitctrlx.service.TTSService;

public class Main {
    public static void main(String[] args) {
        TTSService ts = new TTSService();
//        ts.setBaseSavePath("d:\\"); // 设置保存路径
        SSML ssml = SSML.builder()
                .outputFormat(OutputFormat.audio_24khz_48kbitrate_mono_mp3)
                .synthesisText("你好")
                .outputFileName("你好")
                .voice(VoiceEnum.zh_CN_XiaoxiaoNeural)
                .build();
        new Thread(() -> {
            ts.sendText(ssml, new TTSService.TTSCallback() {
                @Override
                public void onSuccess(String filePath) {
                    // 播放音频
                    System.out.println(filePath);
                }

                @Override
                public void onError(Exception e) {
                    // 输出错误信息
                    e.printStackTrace();
                }
            });
        }).start();

        ts.close();
    }
}