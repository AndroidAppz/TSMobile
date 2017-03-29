package com.depakto.classes.help.api;

public enum TokenType {
    SERVER_GROUP(0),
    CHANNEL_GROUP(1);
    
    private int i;

    private TokenType(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
