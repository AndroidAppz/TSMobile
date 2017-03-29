package com.depakto.classes.help;

import com.depakto.classes.help.TSQuery.FloodRate;
import java.util.logging.Level;

public class TSConfig {
    private boolean debugToFile = false;
    public FloodRate floodRate = FloodRate.DEFAULT;
    private String host = null;
    private Level level = Level.WARNING;
    private String password = null;
    private int queryPort = 25639;
    private String username = null;

    public TSConfig setHost(String host) {
        this.host = host;
        return this;
    }

    String getHost() {
        return this.host;
    }

    public TSConfig setQueryPort(int queryPort) {
        this.queryPort = queryPort;
        return this;
    }

    int getQueryPort() {
        return this.queryPort;
    }

    public TSConfig setFloodRate(FloodRate rate) {
        this.floodRate = rate;
        return this;
    }

    FloodRate getFloodRate() {
        return this.floodRate;
    }

    public TSConfig setDebugLevel(Level level) {
        this.level = level;
        return this;
    }

    Level getDebugLevel() {
        return this.level;
    }

    public TSConfig setLoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

    public TSConfig setDebugToFile(boolean debugToFile) {
        this.debugToFile = debugToFile;
        return this;
    }

    boolean getDebugToFile() {
        return this.debugToFile;
    }
}
