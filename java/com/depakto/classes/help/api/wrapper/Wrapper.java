package com.depakto.classes.help.api.wrapper;

import com.depakto.classes.help.StringUtil;
import com.depakto.classes.help.api.Property;
import com.depakto.tsmobile.BuildConfig;
import java.util.HashMap;

public class Wrapper {
    private HashMap<String, String> map;

    public Wrapper(HashMap<String, String> map) {
        this.map = map;
    }

    public HashMap<String, String> getMap() {
        return this.map;
    }

    protected boolean getBoolean(String str) {
        if (str.isEmpty()) {
            return false;
        }
        return StringUtil.getBoolean(get(str));
    }

    protected boolean getBoolean(Property p) {
        return getBoolean(p.getName());
    }

    protected double getDouble(String str) {
        if (str.isEmpty()) {
            return -1.0d;
        }
        return StringUtil.getDouble(get(str));
    }

    protected double getDouble(Property p) {
        return getDouble(p.getName());
    }

    protected long getLong(String str) {
        if (str.isEmpty()) {
            return -1;
        }
        return StringUtil.getLong(get(str));
    }

    protected long getLong(Property p) {
        return getLong(p.getName());
    }

    protected int getInt(String str) {
        if (str.isEmpty()) {
            return -1;
        }
        return StringUtil.getInt(get(str));
    }

    protected int getInt(Property p) {
        return getInt(p.getName());
    }

    public String get(String str) {
        String result = (String) this.map.get(str);
        return result != null ? result : BuildConfig.FLAVOR;
    }

    public String get(Property p) {
        return get(p.getName());
    }

    public String toString() {
        return this.map.toString();
    }
}
