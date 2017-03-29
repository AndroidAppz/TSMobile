package com.depakto.classes.help.api.exception;

public class TSUnknownEventException extends TSException {
    private static final long serialVersionUID = -9179157153557357715L;

    public TSUnknownEventException(String event) {
        super(event + " [Please report this exception to the developer!]");
    }
}
