package com.depakto.classes.help.api.exception;

public class TSConnectionFailedException extends TSException {
    private static final long serialVersionUID = 6849777544299282019L;

    public TSConnectionFailedException(Throwable c) {
        super("An error occurred while sending a command to a help server", c);
    }
}
