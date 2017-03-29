package com.depakto.classes.help.api;

public enum ReasonIdentifier {
    REASON_KICK_CHANNEL(4),
    REASON_KICK_SERVER(5);
    
    private int i;

    private ReasonIdentifier(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
