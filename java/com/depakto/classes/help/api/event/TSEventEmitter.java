package com.depakto.classes.help.api.event;

import java.util.HashMap;

public interface TSEventEmitter {
    void fire(TSListener tSListener, HashMap<String, String> hashMap);
}
