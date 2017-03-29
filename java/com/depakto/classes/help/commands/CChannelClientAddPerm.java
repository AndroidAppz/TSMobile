package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelClientAddPerm extends Command {
    public CChannelClientAddPerm(int channelId, int clientDBId, String permName, int permValue) {
        super("channelclientaddperm");
        add(new KeyValueParam("cid", channelId));
        add(new KeyValueParam("cldbid", clientDBId));
        add(new KeyValueParam("permsid", permName));
        add(new KeyValueParam("permvalue", permValue));
    }
}
