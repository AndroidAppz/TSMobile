package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientPoke extends Command {
    public CClientPoke(int clientId, String message) {
        super("clientpoke");
        add(new KeyValueParam("clid", clientId));
        add(new KeyValueParam("msg", message));
    }
}
