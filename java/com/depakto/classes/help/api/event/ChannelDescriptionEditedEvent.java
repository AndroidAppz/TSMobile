package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ChannelDescriptionEditedEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ChannelDescriptionEditedEvent() {
        super(null);
    }

    public ChannelDescriptionEditedEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getChannelId() {
        return getInt("cid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onChannelDescriptionChanged(new ChannelDescriptionEditedEvent(map));
    }
}
