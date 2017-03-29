package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupCopy extends Command {
    public CServerGroupCopy(int sourceId, int targetId, String targetName, PermissionGroupDatabaseType t) {
        super("servergroupcopy");
        add(new KeyValueParam("ssgid", sourceId));
        add(new KeyValueParam("tsgid", targetId));
        add(new KeyValueParam("name", targetName));
        add(new KeyValueParam("type", t.getIndex()));
    }
}
