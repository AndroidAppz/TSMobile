package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelGroupCopy extends Command {
    public CChannelGroupCopy(int sourceGroupId, int targetGroupId, PermissionGroupDatabaseType t) {
        this(sourceGroupId, targetGroupId, "name", t);
    }

    public CChannelGroupCopy(int sourceGroupId, String groupName, PermissionGroupDatabaseType t) {
        this(sourceGroupId, 0, groupName, t);
    }

    public CChannelGroupCopy(int sourceGroupId, int targetGroupId, String groupName, PermissionGroupDatabaseType t) {
        super("channelgroupcopy");
        add(new KeyValueParam("scgid", sourceGroupId));
        add(new KeyValueParam("tcgid", targetGroupId));
        add(new KeyValueParam("name", groupName));
        add(new KeyValueParam("type", t.getIndex()));
    }
}
