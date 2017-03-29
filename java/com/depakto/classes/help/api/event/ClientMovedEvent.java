package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ClientMovedEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ClientMovedEvent() {
        super(null);
    }

    public ClientMovedEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getChannelTargetId() {
        return getInt("ctid");
    }

    public int getReasonId() {
        return getInt("reasonid");
    }

    public int getClientId() {
        return getInt("clid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onClientMoved(new ClientMovedEvent(map));
    }
}
