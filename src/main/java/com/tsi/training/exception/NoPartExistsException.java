package com.tsi.training.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
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