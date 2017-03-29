package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupPermList extends Command {
    public CServerGroupPermList(int id) {
        super("servergrouppermlist");
        add(new KeyValueParam("sgid", id));
    }
}
