package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class BanEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public BanEvent() {
        super(null);
    }

    public BanEvent(HashMap<String, String> map) {
        super(map);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onBanList(new BanEvent(map));
    }
}
