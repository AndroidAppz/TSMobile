package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CClientSetServerQueryLogin extends Command {
    public CClientSetServerQueryLogin(String username) {
        super("clientsetserverquerylogin");
        add(new KeyValueParam("client_login_name", username));
    }
}
