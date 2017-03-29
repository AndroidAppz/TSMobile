package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CComplainList extends Command {
    public CComplainList(int clientDBId) {
        super("complainlist");
        if (clientDBId > 0) {
            add(new KeyValueParam("tcldbid", clientDBId + BuildConfig.FLAVOR));
        }
    }
}
