package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelMove extends Command {
    public CChannelMove(int channelId, int channelParentId, int order) {
        super("channelmove");
        add(new KeyValueParam("cid", channelId));
        add(new KeyValueParam("cpid", channelParentId));
        if (order < 0) {
            order = 0;
        }
        add(new KeyValueParam("order", order));
    }
}
