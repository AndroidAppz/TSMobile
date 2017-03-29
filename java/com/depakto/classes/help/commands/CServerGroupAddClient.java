package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupAddClient extends Command {
    public CServerGroupAddClient(int groupId, int clientDBId) {
        super("servergroupaddclient");
        add(new KeyValueParam("sgid", groupId));
        add(new KeyValueParam("cldbid", clientDBId));
    }
}
