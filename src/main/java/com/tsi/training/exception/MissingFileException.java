package com.tsi.training.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MissingFileException extends RuntimeException {

    private String message;

    public MissingFileException() {}

    public MissingFileException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
