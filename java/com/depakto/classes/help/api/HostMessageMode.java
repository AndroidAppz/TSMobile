package com.depakto.classes.help.api;

public enum HostMessageMode {
    LOG(1),
    MODAL(2),
    MODAL_QUIT(3),
    UNKNOWN(-1);
    
    private int i;

    private HostMessageMode(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
