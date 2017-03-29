package com.depakto.classes.help.api.wrapper;

import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import java.util.HashMap;

public class ChannelGroup extends Wrapper {
    public ChannelGroup(HashMap<String, String> map) {
        super(map);
    }

    public int getGroupId() {
        return getInt("cgid");
    }

    public String getName() {
        return get("name");
    }

    public PermissionGroupDatabaseType getType() {
        for (PermissionGroupDatabaseType t : PermissionGroupDatabaseType.values()) {
            if (t.getIndex() == getInt("type")) {
                return t;
            }
        }
        return null;
    }

    public long getIconId() {
        return getLong("iconid");
    }

    public boolean isSavedInDatabase() {
        return getBoolean("savedb");
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
}
