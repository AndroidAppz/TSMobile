package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.RawParam;

public class CServerSnapshotDeploy extends Command {
    public CServerSnapshotDeploy(String snapshot) {
        super("serversnapshotdeploy");
        add(new RawParam(snapshot));
    }
}
