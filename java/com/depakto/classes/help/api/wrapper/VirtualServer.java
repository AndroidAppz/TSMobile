package com.depakto.classes.help.api.wrapper;

import com.depakto.classes.help.api.VirtualServerStatus;
import java.util.HashMap;

public class VirtualServer extends Wrapper {
    public VirtualServer(HashMap<String, String> map) {
        super(map);
    }

    public int getId() {
        return getInt("virtualserver_id");
    }

    public int getPort() {
        return getInt("virtualserver_port");
    }

    public VirtualServerStatus getStatus() {
        for (VirtualServerStatus s : VirtualServerStatus.values()) {
            if (s.getName().equals(get("virtualserver_status"))) {
                return s;
            }
        }
        return VirtualServerStatus.UNKNOWN;
    }

    public int getClientsOnline() {
        return getInt("virtualserver_clientsonline");
    }

    public int getQueryClientsOnline() {
        return getInt("virtualserver_queryclientsonline");
    }

    public int getMaxClients() {
        return getInt("virtualserver_maxclients");
    }

    public int getUptime() {
        return getInt("virtualserver_uptime");
    }

    public String getName() {
        return get("virtualserver_name");
    }

    public boolean isAutoStart() {
        return getBoolean("virtualserver_autostart");
    }
}
