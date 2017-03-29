package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientMove extends Command {
    public CClientMove(int clientId, int channelid, String channelPassword) {
        super("clientmove");
        add(new KeyValueParam("clid", clientId));
        add(new KeyValueParam("cid", channelid));
        if (channelPassword != null) {
            add(new KeyValueParam("cpw", channelPassword));
        }
    }
}
