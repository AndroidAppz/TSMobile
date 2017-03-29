package com.depakto.classes.help.api.wrapper;

import com.depakto.classes.help.api.VirtualServerProperty;
import java.util.Date;
import java.util.HashMap;

public class VirtualServerInfoClient extends Wrapper {
    public VirtualServerInfoClient(HashMap<String, String> map) {
        super(map);
    }

    public String getName() {
        return get(VirtualServerProperty.VIRTUALSERVER_NAME);
    }

    public String getVersion() {
        return get(VirtualServerProperty.VIRTUALSERVER_VERSION);
    }

    public String getPlatform() {
        return get(VirtualServerProperty.VIRTUALSERVER_PLATFORM);
    }

    public Date getCreatedDate() {
        return new Date(getLong(VirtualServerProperty.VIRTUALSERVER_CREATED) * 1000);
    }

    public int getDefaultServerGroup() {
        return getInt(VirtualServerProperty.VIRTUALSERVER_DEFAULT_SERVER_GROUP);
    }

    public int getDefaultChannelGroup() {
        return getInt(VirtualServerProperty.VIRTUALSERVER_DEFAULT_CHANNEL_GROUP);
    }

    public String getHostbannerUrl() {
        return get(VirtualServerProperty.VIRTUALSERVER_HOSTBANNER_URL);
    }

    public String getHostbannerGfxUrl() {
        return get(VirtualServerProperty.VIRTUALSERVER_HOSTBANNER_GFX_URL);
    }

    public double getPrioritySpeakerDimmModificator() {
        return getDouble(VirtualServerProperty.VIRTUALSERVER_PRIORITY_SPEAKER_DIMM_MODIFICATOR);
    }

    public int getServerId() {
        return getInt(VirtualServerProperty.VIRTUALSERVER_ID);
    }

    public String getHostbuttonTooltip() {
        return get(VirtualServerProperty.VIRTUALSERVER_HOSTBUTTON_TOOLTIP);
    }

    public String getHostbuttonUrl() {
        return get(VirtualServerProperty.VIRTUALSERVER_HOSTBUTTON_URL);
    }
}
