package com.depakto.classes.help.api.wrapper;

import java.util.HashMap;

public class VirtualServerIP extends Wrapper {
    public VirtualServerIP(HashMap<String, String> map) {
        super(map);
    }

    public String getIP() {
        return get("ip");
    }

    public String getPort() {
        return get("port");
    }
}
