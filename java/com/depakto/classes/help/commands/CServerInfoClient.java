package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.RawParam;

public class CServerInfoClient extends Command {
    public CServerInfoClient() {
        super("servervariable");
        add(new RawParam("virtualserver_name"));
        add(new RawParam("virtualserver_version"));
        add(new RawParam("virtualserver_platform"));
        add(new RawParam("virtualserver_created"));
        add(new RawParam("virtualserver_default_server_group"));
        add(new RawParam("virtualserver_default_channel_group"));
        add(new RawParam("virtualserver_hostbanner_url"));
        add(new RawParam("virtualserver_hostbanner_gfx_url"));
        add(new RawParam("virtualserver_priority_speaker_dimm_modificator"));
        add(new RawParam("virtualserver_id"));
        add(new RawParam("virtualserver_hostbutton_tooltip"));
        add(new RawParam("virtualserver_hostbutton_url"));
    }
}
