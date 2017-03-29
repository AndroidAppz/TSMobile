package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CUse extends Command {
    public CUse(int id) {
        this(id, -1);
    }

    public CUse(int id, int port) {
        super("use");
        if (id > 0) {
            add(new KeyValueParam("sid", id));
        }
        if (port > 0) {
            add(new KeyValueParam("port", port));
        }
    }
}
