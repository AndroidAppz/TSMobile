package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupAdd extends Command {
    public CServerGroupAdd(String name) {
        this(name, PermissionGroupDatabaseType.REGULAR);
    }

    public CServerGroupAdd(String name, PermissionGroupDatabaseType t) {
        super("servergroupadd");
        add(new KeyValueParam("name", name));
        add(new KeyValueParam("type", t.getIndex()));
    }
}
