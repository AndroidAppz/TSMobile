package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientFind extends Command {
    public CClientFind(String pattern) {
        super("clientfind");
        add(new KeyValueParam("pattern", pattern));
    }
}
