package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CPrivilegeKeyUse extends Command {
    public CPrivilegeKeyUse(String token) {
        super("privilegekeyuse");
        add(new KeyValueParam("token", token));
    }
}
