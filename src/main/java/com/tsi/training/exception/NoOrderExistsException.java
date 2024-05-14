package com.tsi.training.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoOrderExistsException
        extends RuntimeException {

    private String message;

    public NoOrderExistsException() {}

    public NoOrderExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}