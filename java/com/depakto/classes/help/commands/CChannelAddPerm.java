package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelAddPerm extends Command {
    public CChannelAddPerm(int channelid, String permName, int permValue) {
        super("channeladdperm");
        add(new KeyValueParam("cid", channelid));
        add(new KeyValueParam("permsid", permName));
        add(new KeyValueParam("permvalue", permValue));
    }
}
