package com.depakto.classes.help.api.exception;

public class TSCommandFailedException extends TSException {
    private static final long serialVersionUID = 8179203326662268882L;

    public TSCommandFailedException(Throwable c) {
        super("An error occurred while sending a command to the help server", c);
    }
}
