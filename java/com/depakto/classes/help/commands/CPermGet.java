package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CPermGet extends Command {
    public CPermGet(String permName) {
        super("permget");
        add(new KeyValueParam("permsid", permName));
    }
}
