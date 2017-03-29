package com.depakto.classes.help.commands;

import com.depakto.classes.help.api.ServerGroupType;
import com.depakto.classes.help.commands.parameter.KeyValueParam;

public class CServerGroupAutoAddPerm extends Command {
    public CServerGroupAutoAddPerm(ServerGroupType t, String permName, int permValue, boolean permNegated, boolean permSkipped) {
        super("servergroupautoaddperm");
        add(new KeyValueParam("sgtype", t.getIndex()));
        add(new KeyValueParam("permsid", permName));
        add(new KeyValueParam("permvalue", permValue));
        add(new KeyValueParam("permnegated", permNegated ? "1" : "0"));
        add(new KeyValueParam("permskip", permSkipped ? "1" : "0"));
    }
}
