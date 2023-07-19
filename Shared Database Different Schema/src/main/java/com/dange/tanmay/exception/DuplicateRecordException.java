package com.dange.tanmay.exception;

public class DuplicateRecordException extends Exception {
    public DuplicateRecordException(String message) {
        super(message);
    }

    public DuplicateRecordException(String message, Throwable t) {
        super(message, t);
    }
}
