package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.OptionParam;

public class CChannelClientPermList extends Command {
    public CChannelClientPermList(int channelId, int clientDBId) {
        super("channelclientpermlist");
        add(new KeyValueParam("cid", channelId));
        add(new KeyValueParam("cldbid", clientDBId));
        add(new OptionParam("permsid"));
    }
}
