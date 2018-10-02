package com.epam.jd12.exception;

public class DataFileNotFoundException extends DataFileException {

    public DataFileNotFoundException() {
    }

    public DataFileNotFoundException(String message) {
        super(message);
    }

    public DataFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFileNotFoundException(Throwable cause) {
        super(cause);
    }
}
