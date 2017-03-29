package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelInfo extends Command {
    public CChannelInfo(int channelId) {
        super("channelinfo");
        add(new KeyValueParam("cid", channelId));
    }
}
