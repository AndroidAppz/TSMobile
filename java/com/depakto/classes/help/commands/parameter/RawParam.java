package com.depakto.classes.help.commands.parameter;

public class RawParam extends Parameter {
    private String raw;

    public RawParam(String raw) {
        this.raw = raw;
    }

    public String build() {
        return this.raw;
    }
}
