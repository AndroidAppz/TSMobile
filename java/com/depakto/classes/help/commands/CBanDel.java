package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CBanDel extends Command {
    public CBanDel(int banId) {
        super("bandel");
        add(new KeyValueParam("banid", banId));
    }
}
