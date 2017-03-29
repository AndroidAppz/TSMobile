package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CComplainDelAll extends Command {
    public CComplainDelAll(int clientDBId) {
        super("complaindelall");
        add(new KeyValueParam("tcldbid", clientDBId + BuildConfig.FLAVOR));
    }
}
