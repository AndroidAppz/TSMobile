package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ReasonIdentifier;
import com.depakto.classes.help.commands.parameter.ArrayParameter;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientKick extends Command {
    public CClientKick(ReasonIdentifier reason, String reasonMessage, int... clientIds) {
        super("clientkick");
        ArrayParameter p = new ArrayParameter();
        for (int id : clientIds) {
            p.add(new KeyValueParam("clid", id + BuildConfig.FLAVOR));
        }
        add(p);
        add(new KeyValueParam("reasonid", reason.getIndex() + BuildConfig.FLAVOR));
        if (reasonMessage != null) {
            add(new KeyValueParam("reasonmsg", reasonMessage));
        }
    }
}
