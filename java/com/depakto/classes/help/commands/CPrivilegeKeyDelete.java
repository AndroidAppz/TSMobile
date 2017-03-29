package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CPrivilegeKeyDelete extends Command {
    public CPrivilegeKeyDelete(String token) {
        super("privilegekeydelete");
        add(new KeyValueParam("token", token));
    }
}
