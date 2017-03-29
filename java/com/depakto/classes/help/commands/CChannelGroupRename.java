package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelGroupRename extends Command {
    public CChannelGroupRename(int groupId, String name) {
        super("channelgrouprename");
        add(new KeyValueParam("cgid", groupId));
        add(new KeyValueParam("name", name));
    }
}
