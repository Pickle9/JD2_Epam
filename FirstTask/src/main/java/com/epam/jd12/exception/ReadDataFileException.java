package com.epam.jd12.exception;

public class ReadDataFileException extends Exception {

    public ReadDataFileException() {
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
}
