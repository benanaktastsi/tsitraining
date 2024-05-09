package com.tsi.training.exception;

public class NoPartExistsException
        extends RuntimeException {

    private String message;

    public NoPartExistsException() {}

    public NoPartExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}