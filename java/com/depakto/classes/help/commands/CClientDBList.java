package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.OptionParam;
import com.depakto.tsmobile.BuildConfig;

public class CClientDBList extends Command {
    public CClientDBList(int begin, int amount, boolean count) {
        super("clientdblist");
        add(new KeyValueParam("start", begin + BuildConfig.FLAVOR));
        add(new KeyValueParam("duration", amount + BuildConfig.FLAVOR));
        if (count) {
            add(new OptionParam("count"));
        }
    }
}
