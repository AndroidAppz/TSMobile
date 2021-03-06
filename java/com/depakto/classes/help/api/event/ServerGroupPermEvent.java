package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ServerGroupPermEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ServerGroupPermEvent() {
        super(null);
    }

    public ServerGroupPermEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onServerGroupPermList(new ServerGroupPermEvent(map));
    }
}
