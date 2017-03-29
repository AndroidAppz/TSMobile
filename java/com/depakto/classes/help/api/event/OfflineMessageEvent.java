package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class OfflineMessageEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public OfflineMessageEvent() {
        super(null);
    }

    public OfflineMessageEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onOfflineMessageList(new OfflineMessageEvent(map));
    }
}
