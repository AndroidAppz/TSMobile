package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ClientTalkStatus extends Wrapper implements TSEvent, TSEventEmitter {
    public ClientTalkStatus() {
        super(null);
    }

    public ClientTalkStatus(HashMap<String, String> map) {
        super(map);
    }

    public int getClientId() {
        return getInt("clid");
    }

    public int getClientStatus() {
        return getInt("status");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onClientTalkStatus(new ClientTalkStatus(map));
    }
}
