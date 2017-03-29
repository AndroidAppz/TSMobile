package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.OptionParam;

public class CClientDBFind extends Command {
    public CClientDBFind(String pattern, boolean uid) {
        super("clientdbfind");
        add(new KeyValueParam("pattern", pattern));
        if (uid) {
            add(new OptionParam("uid"));
        }
    }
}
