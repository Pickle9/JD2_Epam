package by.epam.htp12.exception;

public class ReadDataFileException extends Exception {

    public ReadDataFileException() {
        super();
    }

    public ReadDataFileException(String message) {
        super(message);
    }

    public ReadDataFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadDataFileException(Throwable cause) {
        super(cause);
    }

    protected ReadDataFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
