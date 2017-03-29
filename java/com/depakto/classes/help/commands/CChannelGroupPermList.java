package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CChannelGroupPermList extends Command {
    public CChannelGroupPermList(int groupId) {
        super("channelgrouppermlist");
        add(new KeyValueParam("cgid", groupId + BuildConfig.FLAVOR));
    }
}
