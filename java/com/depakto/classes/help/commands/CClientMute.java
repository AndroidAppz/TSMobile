package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientMute extends Command {
    public CClientMute(int clientId) {
        super("clientmute");
        add(new KeyValueParam("clid", clientId));
    }
}
