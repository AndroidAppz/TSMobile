package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ServerEditedEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ServerEditedEvent(HashMap<String, String> map) {
        super(map);
    }

    public ServerEditedEvent() {
        super(null);
    }

    public int getReasonId() {
        return getInt("reasonid");
    }

    public int getInvokerId() {
        return getInt("invokerid");
    }

    public String getInvokerName() {
        return get("invokername");
    }

    public String getInvokerUniqueId() {
        return get("invokeruid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onServerEdit(new ServerEditedEvent(map));
    }
}
