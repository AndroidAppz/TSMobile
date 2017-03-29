package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientDelPerm extends Command {
    public CClientDelPerm(int clientDBId, String permName) {
        super("clientdelperm");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
        add(new KeyValueParam("permsid", permName));
    }
}
