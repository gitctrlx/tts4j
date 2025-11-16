# tts4j

Minimal text-to-speech for Java. SSML in, MP3 out. Streaming over WebSocket, no cloud SDKs.

## Features

- **Simple API**. `TTSService.sendText` with a tiny callback interface.
- **SSML builder**. Set `voice`, `rate`, `volume`, and `outputFormat`.
- **Streaming synthesis**. Writes audio as it arrives; default MP3 output.
- **Zero ceremony**. Only OkHttp + SLF4J under the hood.

## Install

Build once and use from your local Maven repository.

- Windows
  - `gradlew.bat publishToMavenLocal`
- macOS/Linux
  - `./gradlew publishToMavenLocal`

Then depend on it:

- Gradle (Kotlin DSL)

```kotlin
repositories { mavenLocal(); mavenCentral() }
dependencies { implementation("io.gitctrlx:tts4j:0.1.0") }
```

- Maven

```xml
<dependency>
  <groupId>io.gitctrlx</groupId>
  <artifactId>tts4j</artifactId>
  <version>0.1.0</version>
</dependency>
```

Requires JDK 8+.

## Quickstart

```java
TTSService tts = new TTSService();

SSML ssml = SSML.builder()
    .synthesisText("Hello, world")
    .voice(VoiceEnum.en_US_AriaNeural)
    .outputFormat(OutputFormat.audio_24khz_48kbitrate_mono_mp3)
    .outputFile("C:\\hello_test") // produces C:\\hello_test.mp3
    .build();

tts.sendText(ssml, new TTSService.TTSCallback() {
  @Override public void onSuccess(String filePath) { System.out.println(filePath); }
  @Override public void onError(Exception e) { e.printStackTrace(); }
});

// waits for the in-flight synthesis to finish, then closes resources
tts.close();
```

## API at a glance

- **`TTSService`**. Manages the WebSocket and synthesis lifecycle.
- **`SSML`**. Builder for `synthesisText`, `voice`, `rate`, `volume`, `outputFormat`, `outputFile`.
- **`VoiceEnum`**. Curated voices (Chinese and US English, among others).
- **`OutputFormat`**. Common audio formats; MP3 variants recommended.

## Notes

- Provide `outputFile` without an extension; the library appends the correct suffix.
- One synthesis at a time. Calls queue via internal idling.
- Logging via `slf4j-simple` (configure as desired).
- Uses the Edge/Bing ReadAloud WebSocket endpoints under the hood; availability and policies may change.
