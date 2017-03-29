package com.depakto.classes.help.api;

public enum LogLevel {
    ERROR(1),
    WARNING(2),
    DEBUG(3),
    INFO(4);
    
    private int i;

    private LogLevel(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
