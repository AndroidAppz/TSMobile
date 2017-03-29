package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class OfflineMessageGetEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public OfflineMessageGetEvent() {
        super(null);
    }

    public OfflineMessageGetEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onGetOfflineMessage(new OfflineMessageGetEvent(map));
    }
}
