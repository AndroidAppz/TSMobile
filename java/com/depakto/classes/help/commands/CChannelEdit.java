package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ChannelProperty;
import com.depakto.classes.help.commands.parameter.KeyValueParam;
import java.util.HashMap;

public class CChannelEdit extends Command {
    public CChannelEdit(int channelId, HashMap<ChannelProperty, String> options) {
        super("channeledit");
        add(new KeyValueParam("cid", channelId));
        if (options != null) {
            for (ChannelProperty p : options.keySet()) {
                if (p.isChangeable()) {
                    add(new KeyValueParam(p.getName(), (String) options.get(p)));
                }
            }
        }
    }
}
