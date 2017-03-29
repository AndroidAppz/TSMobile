package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CComplainAdd extends Command {
    public CComplainAdd(int clientDBId, String text) {
        super("complainadd");
        add(new KeyValueParam("tcldbid", clientDBId));
        add(new KeyValueParam("message", text));
    }
}
