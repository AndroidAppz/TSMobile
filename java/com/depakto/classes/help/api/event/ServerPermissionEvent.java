package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ServerPermissionEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ServerPermissionEvent() {
        super(null);
    }

    public ServerPermissionEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onServerPermissionList(new ServerPermissionEvent(map));
    }
}
