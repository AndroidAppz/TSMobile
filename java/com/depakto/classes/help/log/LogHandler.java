package com.depakto.classes.help.log;

import java.io.File;
import java.text.DateFormat;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {
    private static final DateFormat format = DateFormat.getInstance();
    private boolean debugToFile;
    private File log;

    public LogHandler(boolean debugToFile) {
        this.debugToFile = debugToFile;
    }

    public void close() throws SecurityException {
    }

    public void flush() {
    }

    public void publish(LogRecord record) {
        if (record.getLevel().intValue() < Level.WARNING.intValue()) {
            System.out.println("[DEBUG--] " + record.getMessage());
        } else {
            System.err.println("[DEBUG] [" + record.getLevel() + "] " + record.getMessage());
        }
    }
}
