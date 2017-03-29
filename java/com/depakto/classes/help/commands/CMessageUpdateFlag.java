package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CMessageUpdateFlag extends Command {
    public CMessageUpdateFlag(int messageId, boolean read) {
        super("messageupdateflag");
        add(new KeyValueParam("msgid", messageId));
        add(new KeyValueParam("flag", read ? "1" : "0"));
    }
}
