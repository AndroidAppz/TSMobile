package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.Date;
import java.util.HashMap;

public class ComplainEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ComplainEvent() {
        super(null);
    }

    public ComplainEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getTargetClientDatabaseId() {
        return getInt("tcldbid");
    }

    public String getTargetName() {
        return get("tname");
    }

    public int getSourceClientDatabaseId() {
        return getInt("fcldbid");
    }

    public String getSourceName() {
        return get("fname");
    }

    public String getMessage() {
        return get("message");
    }

    public Date getTimestamp() {
        return new Date(getLong("timestamp") * 1000);
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onComplainList(new ComplainEvent(map));
    }
}
