package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ServerInstanceProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CInstanceEdit extends Command {
    public CInstanceEdit(ServerInstanceProperty p, String value) {
        super("instanceedit");
        add(new KeyValueParam(p.getName(), value));
    }
}
