package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CMessageAdd extends Command {
    public CMessageAdd(String clientUId, String subject, String message) {
        super("messageadd");
        add(new KeyValueParam("cluid", clientUId));
        add(new KeyValueParam("subject", subject));
        add(new KeyValueParam("message", message));
    }
}
