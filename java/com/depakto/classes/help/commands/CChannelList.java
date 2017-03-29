package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.OptionParam;

public class CChannelList extends Command {
    public CChannelList() {
        super("channellist");
        add(new OptionParam("topic"));
        add(new OptionParam("flags"));
        add(new OptionParam("voice"));
        add(new OptionParam("limits"));
        add(new OptionParam("icon"));
    }
}
