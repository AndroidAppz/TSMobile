package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupDelClient extends Command {
    public CServerGroupDelClient(int groupId, int clientDBId) {
        super("servergroupdelclient");
        add(new KeyValueParam("sgid", groupId));
        add(new KeyValueParam("cldbid", clientDBId));
    }
}
