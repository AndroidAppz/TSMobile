package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CPermFind extends Command {
    public CPermFind(String permName) {
        super("permfind");
        add(new KeyValueParam("permsid", permName));
    }
}
