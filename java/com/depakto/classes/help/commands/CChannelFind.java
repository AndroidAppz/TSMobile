package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CChannelFind extends Command {
    public CChannelFind(String pattern) {
        super("channelfind");
        add(new KeyValueParam("pattern", pattern));
    }
}
