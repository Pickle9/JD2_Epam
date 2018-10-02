package com.epam.jd12.exception;

import java.io.IOException;

public class DataFileException extends IOException {

    public DataFileException() {
    }

    public DataFileException(String message) {
        super(message);
    }

    public DataFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFileException(Throwable cause) {
        super(cause);
    }
}
