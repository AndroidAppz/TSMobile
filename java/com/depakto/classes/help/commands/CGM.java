package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CGM extends Command {
    public CGM(String message) {
        super("gm");
        add(new KeyValueParam("msg", message));
    }
}
