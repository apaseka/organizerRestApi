package com.example.organizer.exception;

public class DataException extends RuntimeException {

private String error;

    public DataException(String message) {
        super(message);
    }

    public String getError() {
        return error;
    }
}
