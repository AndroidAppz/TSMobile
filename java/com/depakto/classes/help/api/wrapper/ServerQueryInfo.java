package com.depakto.classes.help.api.wrapper;

import com.depakto.classes.help.api.VirtualServerStatus;
import java.util.HashMap;

public class ServerQueryInfo extends Wrapper {
    public ServerQueryInfo(HashMap<String, String> map) {
        super(map);
    }

    public int getChannelId() {
        return getInt("client_channel_id");
    }

    public int getDatabaseId() {
        return getInt("client_database_id");
    }

    public int getId() {
        return getInt("client_id");
    }

    public String getMsg() {
        return get("msg");
    }

    public int getCLID() {
        return getInt("clid");
    }

    public int getCID() {
        return getInt("cid");
    }

    public String getLoginName() {
        return get("client_login_name");
    }

    public String getNickname() {
        return get("client_nickname");
    }

    public int getOriginServerId() {
        return getInt("client_origin_server_id");
    }

    public String getUniqueIdentifier() {
        return get("client_unique_identifier");
    }

    public int getVirtualServerId() {
        return getInt("virtualserver_id");
    }

    public int getVirtualServerPort() {
        return getInt("virtualserver_port");
    }

    public VirtualServerStatus getVirtualServerStatus() {
        for (VirtualServerStatus s : VirtualServerStatus.values()) {
            if (s.getName().equals(get("virtualserver_status"))) {
                return s;
            }
        }
        return VirtualServerStatus.UNKNOWN;
    }

    public String getVirtualServerUniqueIdentifier() {
        return get("virtualserver_unique_identifier");
    }
}
