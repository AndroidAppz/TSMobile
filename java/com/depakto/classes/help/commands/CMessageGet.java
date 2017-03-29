package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.tsmobile.BuildConfig;

public class CMessageGet extends Command {
    public CMessageGet(int messageId) {
        super("messageget");
        add(new KeyValueParam("msgid", messageId + BuildConfig.FLAVOR));
    }
}
