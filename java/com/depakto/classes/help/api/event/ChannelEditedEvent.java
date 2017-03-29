package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ChannelEditedEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ChannelEditedEvent() {
        super(null);
    }

    public ChannelEditedEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getChannelId() {
        return getInt("cid");
    }

    public int getReasonId() {
        return getInt("reasonid");
    }

    public int getInvokerId() {
        return getInt("invokerid");
    }

    public String getInvokerName() {
        return get("invokername");
    }

    public String getName() {
        return get("channel_name");
    }

    public String getInvokerUniqueId() {
        return get("invokeruid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onChannelEdit(new ChannelEditedEvent(map));
    }
}
