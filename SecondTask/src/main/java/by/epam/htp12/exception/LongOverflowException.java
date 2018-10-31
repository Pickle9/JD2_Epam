package by.epam.htp12.exception;

public class LongOverflowException extends Exception {

    public LongOverflowException() {
        super();
    }

    public LongOverflowException(String message) {
        super(message);
    }

    public LongOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public LongOverflowException(Throwable cause) {
        super(cause);
    }

    protected LongOverflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
