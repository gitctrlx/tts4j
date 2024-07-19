package io.gitctrlx.exceptions;

/**
 * TtsException类用于处理文本到语音转换中的异常情况。
 * The TtsException class is used to handle exceptions in text-to-speech conversion.
 */
public class TtsException extends RuntimeException {

    /**
     * 使用指定的错误信息初始化TtsException实例。
     * Initializes a new instance of the TtsException class with the specified error message.
     *
     * @param message 错误信息 The error message.
     */
    public TtsException(String message) {
        super(message);
    }

    /**
     * 使用指定的错误信息和原因初始化TtsException实例。
     * Initializes a new instance of the TtsException class with the specified error message and cause.
     *
     * @param message 错误信息 The error message.
     * @param cause   导致此异常的原因 The cause of this exception.
     */
    public TtsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 静态工厂方法，创建并返回一个具有指定错误信息的TtsException实例。
     * Static factory method that creates and returns a new instance of TtsException with the specified error message.
     *
     * @param message 错误信息 The error message.
     * @return TtsException实例 A new instance of TtsException.
     */
    public static TtsException of(String message) {
        return new TtsException(message);
    }

    /**
     * 静态工厂方法，创建并返回一个具有指定错误信息和原因的TtsException实例。
     * Static factory method that creates and returns a new instance of TtsException with the specified error message and cause.
     *
     * @param message 错误信息 The error message.
     * @param cause   导致此异常的原因 The cause of this exception.
     * @return TtsException实例 A new instance of TtsException.
     */
    public static TtsException of(String message, Throwable cause) {
        return new TtsException(message, cause);
    }
}
