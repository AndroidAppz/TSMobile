package com.depakto.classes.help.api.wrapper;

import java.util.HashMap;

public class Binding extends Wrapper {
    public Binding(HashMap<String, String> map) {
        super(map);
    }

    public String getIp() {
        return get("ip");
    }
}
