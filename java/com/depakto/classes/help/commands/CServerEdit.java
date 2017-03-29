package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.VirtualServerProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import java.util.HashMap;

public class CServerEdit extends Command {
    public CServerEdit(HashMap<VirtualServerProperty, String> map) {
        super("serveredit");
        for (VirtualServerProperty p : map.keySet()) {
            add(new KeyValueParam(p.getName(), (String) map.get(p)));
        }
    }
}
