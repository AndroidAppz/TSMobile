package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerIdGetByPort extends Command {
    public CServerIdGetByPort(int port) {
        super("serveridgetbyport");
        add(new KeyValueParam("virtualserver_port", port));
    }
}
