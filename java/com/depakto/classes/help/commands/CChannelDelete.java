package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelDelete extends Command {
    public CChannelDelete(int channelId, boolean forced) {
        super("channeldelete");
        add(new KeyValueParam("cid", channelId));
        add(new KeyValueParam("force", forced ? "1" : "0"));
    }
}
