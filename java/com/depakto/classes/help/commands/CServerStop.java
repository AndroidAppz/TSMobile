package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerStop extends Command {
    public CServerStop(int id) {
        super("serverstop");
        add(new KeyValueParam("sid", id));
    }
}
