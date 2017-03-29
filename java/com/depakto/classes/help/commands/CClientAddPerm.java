package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientAddPerm extends Command {
    public CClientAddPerm(int clientDBId, String permName, int permValue, boolean permSkipped) {
        super("clientaddperm");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
        add(new KeyValueParam("permsid", permName));
        add(new KeyValueParam("permvalue", permValue + BuildConfig.FLAVOR));
        add(new KeyValueParam("permskip", permSkipped ? "1" : "0"));
    }
}
