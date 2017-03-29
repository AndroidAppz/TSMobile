package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ClientProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;
import java.util.HashMap;

public class CClientEdit extends Command {
    public CClientEdit(int clientId, HashMap<ClientProperty, String> options) {
        super("clientedit");
        add(new KeyValueParam("clid", clientId + BuildConfig.FLAVOR));
        if (options != null) {
            for (ClientProperty p : options.keySet()) {
                if (p.isChangeable()) {
                    add(new KeyValueParam(p.getName(), (String) options.get(p)));
                }
            }
        }
    }
}
