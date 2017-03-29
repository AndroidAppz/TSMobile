package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientGetNickname extends Command {
    public CClientGetNickname(String clientUId) {
        super("clientgetnamefromuid");
        add(new KeyValueParam("cluid", clientUId));
    }
}
