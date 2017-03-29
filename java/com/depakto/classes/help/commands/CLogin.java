package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.ValueParam;

public class CLogin extends Command {
    public CLogin(String username, String password) {
        super("login");
        add(new ValueParam(username));
        add(new ValueParam(password));
    }
}
