package com.depakto.classes.help.api.event;

public enum TSEventType {
    SERVER("server"),
    CHANNEL("any"),
    TEXT_SERVER("textserver"),
    TEXT_CHANNEL("textchannel"),
    TEXT_PRIVATE("textprivate"),
    SERVER_GROUP("notifyservergrouppermlist"),
    CHANNEL_GROUP("notifychannelgrouppermlist");
    
    private String name;

    private TSEventType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
