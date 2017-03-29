package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ServerGroupType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupAutoDelPerm extends Command {
    public CServerGroupAutoDelPerm(ServerGroupType t, String permName) {
        super("servergroupautodelperm");
        add(new KeyValueParam("sgtype", t.getIndex()));
        add(new KeyValueParam("permsid", permName));
    }
}
