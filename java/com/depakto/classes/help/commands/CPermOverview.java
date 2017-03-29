package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CPermOverview extends Command {
    public CPermOverview(int channelId, int clientDBId) {
        super("permoverview");
        add(new KeyValueParam("cid", channelId + BuildConfig.FLAVOR));
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
        add(new KeyValueParam("permid", "0"));
    }
}
