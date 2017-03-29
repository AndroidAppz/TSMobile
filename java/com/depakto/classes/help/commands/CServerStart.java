package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CServerStart extends Command {
    public CServerStart(int id) {
        super("serverstart");
        add(new KeyValueParam("sid", id + BuildConfig.FLAVOR));
    }
}
