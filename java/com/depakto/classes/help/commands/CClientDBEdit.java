package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ClientProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;
import java.util.HashMap;

public class CClientDBEdit extends Command {
    public CClientDBEdit(int clientDBId, HashMap<ClientProperty, String> options) {
        super("clientdbedit");
        add(new KeyValueParam("cldbid", clientDBId + BuildConfig.FLAVOR));
        if (options != null) {
            for (ClientProperty p : options.keySet()) {
                if (p.isChangeable()) {
                    add(new KeyValueParam(p.getName(), (String) options.get(p)));
                }
            }
        }
    }
}
