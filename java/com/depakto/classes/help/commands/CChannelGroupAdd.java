package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelGroupAdd extends Command {
    public CChannelGroupAdd(String name) {
        this(name, null);
    }

    public CChannelGroupAdd(String name, PermissionGroupDatabaseType t) {
        super("channelgroupadd");
        add(new KeyValueParam("name", name));
        if (t != null) {
            add(new KeyValueParam("type", t.getIndex()));
        }
    }
}
