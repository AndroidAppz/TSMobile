package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientGetDBIdFromUId extends Command {
    public CClientGetDBIdFromUId(String clientUId) {
        super("clientgetdbidfromuid");
        add(new KeyValueParam("cluid", clientUId));
    }
}
