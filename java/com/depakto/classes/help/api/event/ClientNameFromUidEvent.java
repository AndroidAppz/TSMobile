package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ClientNameFromUidEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ClientNameFromUidEvent() {
        super(null);
    }

    public ClientNameFromUidEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onClientNameFromUid(new ClientNameFromUidEvent(map));
    }
}
