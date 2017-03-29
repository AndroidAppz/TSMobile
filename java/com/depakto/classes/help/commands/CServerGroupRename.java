package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupRename extends Command {
    public CServerGroupRename(int id, String name) {
        super("servergrouprename");
        add(new KeyValueParam("sgid", id));
        add(new KeyValueParam("name", name));
    }
}
