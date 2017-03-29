package com.depakto.classes.help.commands.parameter;

import com.depakto.classes.help.StringUtil;

public class OptionParam extends Parameter {
    private String option;

    public OptionParam(String option) {
        this.option = option;
    }

    public String build() {
        return "-" + StringUtil.encode(this.option);
    }
}
