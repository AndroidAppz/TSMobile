package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupDelPerm extends Command {
    public CServerGroupDelPerm(int groupId, String permName) {
        super("servergroupdelperm");
        add(new KeyValueParam("sgid", groupId));
        add(new KeyValueParam("permsid", permName));
    }
}
