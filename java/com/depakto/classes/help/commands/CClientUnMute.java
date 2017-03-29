package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientUnMute extends Command {
    public CClientUnMute(int clientId) {
        super("clientunmute");
        add(new KeyValueParam("clid", clientId));
    }
}
