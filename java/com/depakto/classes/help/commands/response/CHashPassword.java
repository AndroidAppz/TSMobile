package com.depakto.classes.help.commands.response;

import com.depakto.classes.help.commands.Command;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CHashPassword extends Command {
    public CHashPassword(String password) {
        super("hashpassword");
        add(new KeyValueParam("password", password));
    }
}
