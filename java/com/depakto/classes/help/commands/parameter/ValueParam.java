package com.depakto.classes.help.commands.parameter;

import com.depakto.classes.help.StringUtil;

public class ValueParam extends Parameter {
    private String value;

    public ValueParam(String value) {
        this.value = value;
    }

    public String build() {
        return StringUtil.encode(this.value);
    }
}
