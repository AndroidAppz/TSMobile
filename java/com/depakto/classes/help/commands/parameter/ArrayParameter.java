package com.depakto.classes.help.commands.parameter;

import com.depakto.tsmobile.BuildConfig;
import java.util.ArrayList;
import java.util.List;

public class ArrayParameter extends Parameter {
    private List<Parameter> parameters = new ArrayList();

    public ArrayParameter(Parameter... parameters) {
        for (Parameter p : parameters) {
            this.parameters.add(p);
        }
    }

    public ArrayParameter add(Parameter p) {
        this.parameters.add(p);
        return this;
    }

    public String build() {
        String str = BuildConfig.FLAVOR;
        for (int i = 0; i < this.parameters.size(); i++) {
            str = str + ((Parameter) this.parameters.get(i)).build();
            if (i < this.parameters.size() - 1) {
                str = str + "|";
            }
        }
        return str;
    }
}
