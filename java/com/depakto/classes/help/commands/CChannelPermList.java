package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.OptionParam;
import com.depakto.tsmobile.BuildConfig;

public class CChannelPermList extends Command {
    public CChannelPermList(int channelId) {
        super("channelpermlist");
        add(new KeyValueParam("cid", channelId + BuildConfig.FLAVOR));
        add(new OptionParam("permsid"));
    }
}
