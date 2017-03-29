package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CChannelGroupDelPerm extends Command {
    public CChannelGroupDelPerm(int groupId, String permName) {
        super("channelgroupdelperm");
        add(new KeyValueParam("cgid", groupId + BuildConfig.FLAVOR));
        add(new KeyValueParam("permsid", permName));
    }
}
