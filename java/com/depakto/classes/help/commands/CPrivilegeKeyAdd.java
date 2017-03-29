package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CPrivilegeKeyAdd extends Command {
    public CPrivilegeKeyAdd(int type, int groupId, int channelId, String description) {
        super("privilegekeyadd");
        add(new KeyValueParam("tokentype", type + BuildConfig.FLAVOR));
        add(new KeyValueParam("tokenid1", groupId + BuildConfig.FLAVOR));
        add(new KeyValueParam("tokenid2", channelId + BuildConfig.FLAVOR));
        if (description != null) {
            add(new KeyValueParam("tokendescription", description));
        }
    }
}
