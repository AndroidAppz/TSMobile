package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CChannelGroupDel extends Command {
    public CChannelGroupDel(int channelGroupId, boolean forced) {
        super("channelgroupdel");
        add(new KeyValueParam("cgid", channelGroupId + BuildConfig.FLAVOR));
        add(new KeyValueParam("force", forced ? "1" : "0"));
    }
}
