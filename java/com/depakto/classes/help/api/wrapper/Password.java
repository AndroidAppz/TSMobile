package com.depakto.classes.help.api.wrapper;

import java.util.HashMap;

public class Password extends Wrapper {
    public Password(HashMap<String, String> map) {
        super(map);
    }

    public String getPassword() {
        return get("passwordhash");
    }
}
