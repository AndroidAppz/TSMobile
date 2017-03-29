package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ClientUpdatedEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ClientUpdatedEvent() {
        super(null);
    }

    public ClientUpdatedEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getClientTargetId() {
        return getInt("ctid");
    }

    public int getReasonId() {
        return getInt("reasonid");
    }

    public int getClientId() {
        return getInt("clid");
    }

    public int getClientTalkPower() {
        return getInt("client_talk_power");
    }

    public int getClientOutputMuted() {
        return getInt("client_output_muted");
    }

    public int getClientCommander() {
        return getInt("client_is_channel_commander");
    }

    public int getClientInputMuted() {
        return getInt("client_input_muted");
    }

    public String getClientNickname() {
        return get("client_nickname");
    }

    public int getClientAway() {
        return getInt("client_away");
    }

    public String getClientAwayMessage() {
        return get("client_away_message");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onClientUpdate(new ClientUpdatedEvent(map));
    }
}
