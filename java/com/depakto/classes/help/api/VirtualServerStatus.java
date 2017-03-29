package com.depakto.classes.help.api;

public enum VirtualServerStatus implements Property {
    ONLINE("online"),
    OFFLINE("offline"),
    DEPLOY_RUNNING("deploy running"),
    BOOTING_UP("booting up"),
    SHUTTING_DOWN("shutting down"),
    VIRTUAL_ONLINE("virtual online"),
    UNKNOWN("unknown");
    
    private String name;

    private VirtualServerStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
