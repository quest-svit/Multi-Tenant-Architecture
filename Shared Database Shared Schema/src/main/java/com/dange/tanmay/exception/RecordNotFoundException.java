package com.dange.tanmay.exception;

public class RecordNotFoundException extends Exception{

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable t) {
        super(message, t);
    }

}
