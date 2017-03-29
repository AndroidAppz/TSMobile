package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CPermIdGetByName extends Command {
    public CPermIdGetByName(String permName) {
        super("permidgetbyname");
        add(new KeyValueParam("permsid", permName));
    }
}
