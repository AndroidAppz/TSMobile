package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerDelete extends Command {
    public CServerDelete(int id) {
        super("serverdelete");
        add(new KeyValueParam("sid", id));
    }
}
