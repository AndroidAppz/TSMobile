package com.depakto.classes.help.api.exception;

public class TSUnknownErrorException extends TSException {
    private static final long serialVersionUID = -8458508268312921713L;

    public TSUnknownErrorException(String error) {
        super(error + " [Please report this exception to the developer!]");
    }
}
