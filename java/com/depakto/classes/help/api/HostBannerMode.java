package com.depakto.classes.help.api;

public enum HostBannerMode {
    NO_ADJUST(0),
    IGNORE_ASPECT(1),
    KEEP_ASPECT(2),
    UNKNOWN(-1);
    
    private int i;

    private HostBannerMode(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
