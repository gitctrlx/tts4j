package io.gitctrlx.exceptions;

/**
 * The TtsException class is used to handle exceptions in text-to-speech conversion.
 */
public class TtsException extends RuntimeException {

    /**
     * Initializes a new instance of the TtsException class with the specified error message.
     *
     * @param message The error message.
     */
    public TtsException(String message) {
        super(message);
    }

    /**
     * Initializes a new instance of the TtsException class with the specified error message and cause.
     *
     * @param message The error message.
     * @param cause   The cause of this exception.
     */
    public TtsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Static factory method that creates and returns a new instance of TtsException with the specified error message.
     *
     * @param message The error message.
     * @return A new instance of TtsException.
     */
    public static TtsException of(String message) {
        return new TtsException(message);
    }

    /**
     * Static factory method that creates and returns a new instance of TtsException with the specified error message and cause.
     *
     * @param message The error message.
     * @param cause   The cause of this exception.
     * @return A new instance of TtsException.
     */
    public static TtsException of(String message, Throwable cause) {
        return new TtsException(message, cause);
    }
}
