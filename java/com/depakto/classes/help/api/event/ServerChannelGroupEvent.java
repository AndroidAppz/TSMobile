package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ServerChannelGroupEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ServerChannelGroupEvent() {
        super(null);
    }

    public ServerChannelGroupEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onServerChannelGroupList(new ServerChannelGroupEvent(map));
    }
}
