package com.depakto.classes.help.commands.parameter;

public abstract class Parameter {
    public abstract String build();

    public String toString() {
        return build();
    }
}
