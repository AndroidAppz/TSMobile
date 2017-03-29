package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ClientPokeEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ClientPokeEvent(HashMap<String, String> map) {
        super(map);
    }

    public ClientPokeEvent() {
        super(null);
    }

    public String getMessage() {
        return get("msg");
    }

    public int getInvokerId() {
        return getInt("invokerid");
    }

    public String getInvokerName() {
        return get("invokername");
    }

    public String getInvokerUserId() {
        return get("invokeruid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onClientPoke(new ClientPokeEvent(map));
    }
}
