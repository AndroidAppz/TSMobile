package com.depakto.classes.help.commands.parameter;

import com.depakto.classes.help.StringUtil;
import com.depakto.tsmobile.BuildConfig;

public class KeyValueParam extends Parameter {
    private String key;
    private String value;

    public KeyValueParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueParam(String key, int value) {
        this(key, value + BuildConfig.FLAVOR);
    }

    public KeyValueParam(String key, long value) {
        this(key, value + BuildConfig.FLAVOR);
    }

    public String build() {
        return StringUtil.encode(this.key) + "=" + StringUtil.encode(this.value);
    }
}
