package com.depakto.classes.help.api;

public enum TextMessageTargetMode {
    CLIENT(1),
    CHANNEL(2),
    SERVER(3);
    
    private int i;

    private TextMessageTargetMode(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
