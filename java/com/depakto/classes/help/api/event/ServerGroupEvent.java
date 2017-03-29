package com.depakto.classes.help.api.event;

import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import com.depakto.classes.help.api.wrapper.Wrapper;
import java.util.HashMap;

public class ServerGroupEvent extends Wrapper implements TSEvent, TSEventEmitter {
    public ServerGroupEvent() {
        super(null);
    }

    public ServerGroupEvent(HashMap<String, String> map) {
        super(map);
    }

    public int getId() {
        return getInt("sgid");
    }

    public String getName() {
        return get("name");
    }

    public PermissionGroupDatabaseType getType() {
        for (PermissionGroupDatabaseType p : PermissionGroupDatabaseType.values()) {
            if (p.getIndex() == getInt("type")) {
                return p;
            }
        }
        return null;
    }

    public int getIconId() {
        return getInt("iconid");
    }

    public int getSaveDb() {
        return getInt("savedb");
    }

    public int getSortId() {
        return getInt("sortid");
    }

    public int getNameMode() {
        return getInt("namemode");
    }

    public int getModifyPower() {
        return getInt("n_modifyp");
    }

    public int getMemberAddPower() {
        return getInt("n_member_addp");
    }

    public int getMemberRemovePower() {
        return getInt("n_member_removep");
    }

    public void fire(TSListener listener, HashMap<String, String> map) {
        listener.onServerGroupList(new ServerGroupEvent(map));
    }
}
