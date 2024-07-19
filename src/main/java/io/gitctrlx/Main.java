package io.gitctrlx;

import io.gitctrlx.constant.OutputFormat;
import io.gitctrlx.constant.VoiceEnum;
import io.gitctrlx.service.SSML;
import io.gitctrlx.service.TTSService;

public class Main {
    public static void main(String[] args) {
        TTSService ts = new TTSService();

        SSML ssml = SSML.builder()
                .outputFormat(OutputFormat.audio_24khz_48kbitrate_mono_mp3)
                .synthesisText("Test Text")
                .outputFile("C:\\hello_test") // File Path, e.g "C:\\hello_test", it will save hello_test.mp3 at C:\\
                .voice(VoiceEnum.zh_CN_XiaoxiaoNeural)
                .build();

        new Thread(() -> {
            ts.sendText(ssml, new TTSService.TTSCallback() {
                @Override
                public void onSuccess(String filePath) {
                    // When the request is successful, do whatever you want to do here
                    System.out.println(filePath);
                }

                @Override
                public void onError(Exception e) {
                    // When an error occurs, you can handle it here
                    e.printStackTrace();
                }
            });
        }).start();

        ts.close();
    }
}