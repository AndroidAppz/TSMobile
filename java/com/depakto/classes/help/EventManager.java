package com.depakto.classes.help;

import com.depakto.classes.help.api.event.BanEvent;
import com.depakto.classes.help.api.event.ChannelCreatedEvent;
import com.depakto.classes.help.api.event.ChannelDeletedEvent;
import com.depakto.classes.help.api.event.ChannelDescriptionEditedEvent;
import com.depakto.classes.help.api.event.ChannelEditedEvent;
import com.depakto.classes.help.api.event.ChannelMovedEvent;
import com.depakto.classes.help.api.event.ClientJoinEvent;
import com.depakto.classes.help.api.event.ClientLeaveEvent;
import com.depakto.classes.help.api.event.ClientMovedEvent;
import com.depakto.classes.help.api.event.ClientNameFromUidEvent;
import com.depakto.classes.help.api.event.ClientPokeEvent;
import com.depakto.classes.help.api.event.ClientTalkStatus;
import com.depakto.classes.help.api.event.ClientUpdatedEvent;
import com.depakto.classes.help.api.event.ComplainEvent;
import com.depakto.classes.help.api.event.OfflineMessageEvent;
import com.depakto.classes.help.api.event.OfflineMessageGetEvent;
import com.depakto.classes.help.api.event.ServerChannelGroupEvent;
import com.depakto.classes.help.api.event.ServerEditedEvent;
import com.depakto.classes.help.api.event.ServerGroupChannelPermEvent;
import com.depakto.classes.help.api.event.ServerGroupEvent;
import com.depakto.classes.help.api.event.ServerGroupPermEvent;
import com.depakto.classes.help.api.event.ServerPermissionEvent;
import com.depakto.classes.help.api.event.TSEventEmitter;
import com.depakto.classes.help.api.event.TSListener;
import com.depakto.classes.help.api.event.TextMessageEvent;
import com.depakto.classes.help.commands.response.DefaultArrayResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private List<TSListener> listeners = new ArrayList();
    private Map<String, TSEventEmitter> map = new HashMap();

    public EventManager() {
        this.map.put("notifytextmessage", new TextMessageEvent());
        this.map.put("notifycliententerview", new ClientJoinEvent());
        this.map.put("notifyclientleftview", new ClientLeaveEvent());
        this.map.put("notifyserveredited", new ServerEditedEvent());
        this.map.put("notifychanneledited", new ChannelEditedEvent());
        this.map.put("notifychannelcreated", new ChannelCreatedEvent());
        this.map.put("notifychannelmoved", new ChannelMovedEvent());
        this.map.put("notifychanneldeleted", new ChannelDeletedEvent());
        this.map.put("notifychanneldescriptionchanged", new ChannelDescriptionEditedEvent());
        this.map.put("notifyclientmoved", new ClientMovedEvent());
        this.map.put("notifytalkstatuschange", new ClientTalkStatus());
        this.map.put("notifyclientupdated", new ClientUpdatedEvent());
        this.map.put("notifyclientpoke", new ClientPokeEvent());
        this.map.put("notifyservergrouplist", new ServerGroupEvent());
        this.map.put("notifychannelgrouplist", new ServerChannelGroupEvent());
        this.map.put("notifypermoverview", new ServerPermissionEvent());
        this.map.put("notifyservergrouppermlist", new ServerGroupPermEvent());
        this.map.put("notifychannelgrouppermlist", new ServerGroupChannelPermEvent());
        this.map.put("notifycomplainlist", new ComplainEvent());
        this.map.put("notifybanlist", new BanEvent());
        this.map.put("notifymessagelist", new OfflineMessageEvent());
        this.map.put("notifymessage", new OfflineMessageGetEvent());
        this.map.put("notifyclientnamefromuid", new ClientNameFromUidEvent());
    }

    public void addListeners(TSListener... listeners) {
        for (TSListener l : listeners) {
            this.listeners.add(l);
        }
    }

    public void removeListeners(TSListener... listeners) {
        for (TSListener l : listeners) {
            this.listeners.remove(l);
        }
    }

    public void fireEvent(String notifyName, String notifyBody) {
        TSEventEmitter emitter = (TSEventEmitter) this.map.get(notifyName);
        if (emitter != null) {
            for (TSListener l : this.listeners) {
                HashMap<String, String> myM;
                if (notifyName.contains("notifyservergrouplist")) {
                    myM = new HashMap();
                    myM.put("group", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifychannelgrouplist")) {
                    myM = new HashMap();
                    myM.put("channelgroup", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifypermoverview")) {
                    myM = new HashMap();
                    myM.put("permission", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifyservergrouppermlist")) {
                    myM = new HashMap();
                    myM.put("servergrouppermlist", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifychannelgrouppermlist")) {
                    myM = new HashMap();
                    myM.put("servergroupchannelpermlist", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifycomplainlist")) {
                    myM = new HashMap();
                    myM.put("complainlist", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifybanlist")) {
                    myM = new HashMap();
                    myM.put("banlist", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifymessagelist")) {
                    myM = new HashMap();
                    myM.put("offlinemessagelist", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifymessage")) {
                    myM = new HashMap();
                    myM.put("getofflinemsg", notifyBody);
                    emitter.fire(l, myM);
                } else if (notifyName.contains("notifyclientnamefromuid")) {
                    myM = new HashMap();
                    myM.put("clientnamefromuid", notifyBody);
                    emitter.fire(l, myM);
                } else {
                    emitter.fire(l, (HashMap) new DefaultArrayResponse(notifyBody).getArray().get(0));
                }
            }
        }
    }
}
