package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.OptionParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientPermList extends Command {
    public CClientPermList(int clientDBId) {
        super("clientpermlist");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
        add(new OptionParam("permsid"));
    }
}
