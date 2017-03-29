package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.event.TSEventType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerNotifyRegister extends Command {
    public CServerNotifyRegister(TSEventType t, int schandlerTabID) {
        this(t, -1, schandlerTabID);
    }

    public CServerNotifyRegister(TSEventType t, int channelId, int schandlerTabID) {
        super("clientnotifyregister");
        add(new KeyValueParam("schandlerid", schandlerTabID));
        add(new KeyValueParam("event", t.toString()));
        if (channelId >= 0) {
            add(new KeyValueParam("id", channelId));
        }
    }
}
