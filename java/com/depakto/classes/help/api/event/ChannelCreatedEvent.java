package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ChannelCreatedEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ChannelCreatedEvent() {
        super(null);
    }

    public ChannelCreatedEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getChannelId() {
        return getInt("cid");
    }

    public int getReasonId() {
        return getInt("reasonid");
    }

    public int getMaxClients() {
        return getInt("channel_maxclients");
    }

    public boolean hasPassword() {
        return getBoolean("channel_flag_password");
    }

    public int getTotalClients() {
        return getInt("total_clients");
    }

    public int getInvokerId() {
        return getInt("invokerid");
    }

    public int getParentChannelId() {
        return getInt("cpid");
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
        listener.onChannelCreate(new ChannelCreatedEvent(map));
    }
}
