package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.TextMessageTargetMode;
import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class TextMessageEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public TextMessageEvent(HashMap<String, String> map) {
        super(map);
    }

    public TextMessageEvent() {
        super(null);
    }

    public TextMessageTargetMode getTargetMode() {
        for (TextMessageTargetMode m : TextMessageTargetMode.values()) {
            if (m.getIndex() == getInt("targetmode")) {
                return m;
            }
        }
        return null;
    }

    public String getMessage() {
        return get("msg");
    }

    public int getInvokerId() {
        return getInt("invokerid");
    }

    public String getInvokerName() {
        return get("invokername");
    }

    public String getInvokerUserId() {
        return get("invokeruid");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onTextMessage(new TextMessageEvent(map));
    }
}
