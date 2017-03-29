package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CServerGroupsByClientId extends Command {
    public CServerGroupsByClientId(int clientDBId) {
        super("servergroupsbyclientid");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
    }
}
