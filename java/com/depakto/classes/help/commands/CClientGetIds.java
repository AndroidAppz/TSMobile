package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientGetIds extends Command {
    public CClientGetIds(String clientUId) {
        super("clientgetids");
        add(new KeyValueParam("cluid", clientUId));
    }
}
