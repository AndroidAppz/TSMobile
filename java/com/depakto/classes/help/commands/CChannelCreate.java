package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ChannelProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import java.util.HashMap;

public class CChannelCreate extends Command {
    public CChannelCreate(String name, HashMap<ChannelProperty, String> options) {
        super("channelcreate");
        add(new KeyValueParam("channel_name", name));
        if (options != null) {
            for (ChannelProperty p : options.keySet()) {
                if (p.isChangeable()) {
                    add(new KeyValueParam(p.getName(), (String) options.get(p)));
                }
            }
        }
    }
}
