package com.depakto.classes.help.api.wrapper;

import java.util.HashMap;

public class ServerSchandlerID extends Wrapper {
    public ServerSchandlerID(HashMap<String, String> map) {
        super(map);
    }

    public String getID() {
        return get("schandlerid");
    }
}
