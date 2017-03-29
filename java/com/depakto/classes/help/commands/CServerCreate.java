package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.VirtualServerProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import java.util.HashMap;

public class CServerCreate extends Command {
    public CServerCreate(String name, HashMap<VirtualServerProperty, String> map) {
        super("servercreate");
        add(new KeyValueParam(VirtualServerProperty.VIRTUALSERVER_NAME.getName(), name));
        if (map != null) {
            for (VirtualServerProperty p : map.keySet()) {
                add(new KeyValueParam(p.getName(), (String) map.get(p)));
            }
        }
    }
}
