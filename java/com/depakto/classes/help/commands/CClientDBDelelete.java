package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientDBDelelete extends Command {
    public CClientDBDelelete(int clientDBId) {
        super("clientdbdelete");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
    }
}
