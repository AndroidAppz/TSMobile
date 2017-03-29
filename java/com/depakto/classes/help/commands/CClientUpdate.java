package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ClientProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import java.util.HashMap;

public class CClientUpdate extends Command {
    public CClientUpdate(HashMap<ClientProperty, String> options) {
        super("clientupdate");
        if (options != null) {
            for (ClientProperty p : options.keySet()) {
                if (p.isChangeable()) {
                    add(new KeyValueParam(p.getName(), (String) options.get(p)));
                }
            }
        }
    }
}
