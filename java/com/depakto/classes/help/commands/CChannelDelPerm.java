package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelDelPerm extends Command {
    public CChannelDelPerm(int channelId, String permName) {
        super("channeldelperm");
        add(new KeyValueParam("cid", channelId));
        add(new KeyValueParam("permsid", permName));
    }
}
