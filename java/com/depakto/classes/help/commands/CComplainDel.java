package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CComplainDel extends Command {
    public CComplainDel(int targetClientDBId, int fromClientDBId) {
        super("complaindel");
        add(new KeyValueParam("tcldbid", targetClientDBId + BuildConfig.FLAVOR));
        add(new KeyValueParam("fcldbid", fromClientDBId + BuildConfig.FLAVOR));
    }
}
