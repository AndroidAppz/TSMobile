package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.OptionParam;
import com.depakto.tsmobile.BuildConfig;

public class CServerGroupClientList extends Command {
    public CServerGroupClientList(int groupId) {
        super("servergroupclientlist");
        add(new KeyValueParam("sgid", groupId + BuildConfig.FLAVOR));
        add(new OptionParam("names"));
    }
}
