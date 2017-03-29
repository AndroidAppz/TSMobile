package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientDBInfo extends Command {
    public CClientDBInfo(int clientDBId) {
        super("clientdbinfo");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
    }
}
