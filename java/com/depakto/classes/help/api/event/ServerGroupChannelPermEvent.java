package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ServerGroupChannelPermEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ServerGroupChannelPermEvent() {
        super(null);
    }

    public ServerGroupChannelPermEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onServerGroupChannelPermList(new ServerGroupChannelPermEvent(map));
    }
}
