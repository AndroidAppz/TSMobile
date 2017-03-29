package com.depakto.classes.help.api.exception;

public class TSException extends RuntimeException {
    private static final long serialVersionUID = 7167169981592989359L;

    public TSException(String msg) {
        super(msg);
    }

    public TSException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
