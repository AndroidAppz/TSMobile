package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CSetClientChannelGroup extends Command {
    public CSetClientChannelGroup(int groupId, int channelId, int clientDBId) {
        super("setclientchannelgroup");
        add(new KeyValueParam("cgid", groupId + BuildConfig.FLAVOR));
        add(new KeyValueParam("cid", channelId + BuildConfig.FLAVOR));
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
    }
}
