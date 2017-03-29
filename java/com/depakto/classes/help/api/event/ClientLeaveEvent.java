package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ClientLeaveEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ClientLeaveEvent() {
        super(null);
    }

    public ClientLeaveEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getClientFromChannelId() {
        return getInt("cfid");
    }

    public int getClientTargetId() {
        return getInt("ctid");
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

    public String getReasonMessage() {
        return get("reasonmsg");
    }

    public int getClientId() {
        return getInt("clid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onClientLeave(new ClientLeaveEvent(map));
    }
}
