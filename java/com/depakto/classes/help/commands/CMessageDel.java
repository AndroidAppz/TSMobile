package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CMessageDel extends Command {
    public CMessageDel(int messageId) {
        super("messagedel");
        add(new KeyValueParam("msgid", messageId + BuildConfig.FLAVOR));
    }
}
