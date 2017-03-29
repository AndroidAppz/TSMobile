package com.depakto.classes.help;

import com.depakto.classes.help.api.ChannelProperty;
import com.depakto.classes.help.api.ClientProperty;
import com.depakto.classes.help.api.PermissionGroupDatabaseType;
import com.depakto.classes.help.api.ReasonIdentifier;
import com.depakto.classes.help.api.ServerGroupType;
import com.depakto.classes.help.api.ServerInstanceProperty;
import com.depakto.classes.help.api.Snapshot;
import com.depakto.classes.help.api.TextMessageTargetMode;
import com.depakto.classes.help.api.VirtualServerProperty;
import com.depakto.classes.help.api.event.TSEventType;
import com.depakto.classes.help.api.event.TSListener;
import com.depakto.classes.help.api.wrapper.AdvancedPermission;
import com.depakto.classes.help.api.wrapper.Ban;
import com.depakto.classes.help.api.wrapper.Binding;
import com.depakto.classes.help.api.wrapper.Channel;
import com.depakto.classes.help.api.wrapper.ChannelGroup;
import com.depakto.classes.help.api.wrapper.ChannelGroupClient;
import com.depakto.classes.help.api.wrapper.ChannelInfo;
import com.depakto.classes.help.api.wrapper.Client;
import com.depakto.classes.help.api.wrapper.ClientInfo;
import com.depakto.classes.help.api.wrapper.Complaint;
import com.depakto.classes.help.api.wrapper.ConnectionInfo;
import com.depakto.classes.help.api.wrapper.DatabaseClient;
import com.depakto.classes.help.api.wrapper.DatabaseClientInfo;
import com.depakto.classes.help.api.wrapper.HostInfo;
import com.depakto.classes.help.api.wrapper.InstanceInfo;
import com.depakto.classes.help.api.wrapper.Message;
import com.depakto.classes.help.api.wrapper.Password;
import com.depakto.classes.help.api.wrapper.Permission;
import com.depakto.classes.help.api.wrapper.PermissionInfo;
import com.depakto.classes.help.api.wrapper.PrivilegeKey;
import com.depakto.classes.help.api.wrapper.ServerGroup;
import com.depakto.classes.help.api.wrapper.ServerGroupClient;
import com.depakto.classes.help.api.wrapper.ServerQueryInfo;
import com.depakto.classes.help.api.wrapper.ServerSchandlerID;
import com.depakto.classes.help.api.wrapper.Version;
import com.depakto.classes.help.api.wrapper.VirtualServer;
import com.depakto.classes.help.api.wrapper.VirtualServerInfo;
import com.depakto.classes.help.api.wrapper.Wrapper;
import com.depakto.classes.help.commands.CBanAdd;
import com.depakto.classes.help.commands.CBanClient;
import com.depakto.classes.help.commands.CBanDel;
import com.depakto.classes.help.commands.CBanDelAll;
import com.depakto.classes.help.commands.CBanList;
import com.depakto.classes.help.commands.CBindingList;
import com.depakto.classes.help.commands.CChannelAddPerm;
import com.depakto.classes.help.commands.CChannelClientAddPerm;
import com.depakto.classes.help.commands.CChannelClientDelPerm;
import com.depakto.classes.help.commands.CChannelClientPermList;
import com.depakto.classes.help.commands.CChannelCreate;
import com.depakto.classes.help.commands.CChannelDelPerm;
import com.depakto.classes.help.commands.CChannelDelete;
import com.depakto.classes.help.commands.CChannelEdit;
import com.depakto.classes.help.commands.CChannelFind;
import com.depakto.classes.help.commands.CChannelGroupAdd;
import com.depakto.classes.help.commands.CChannelGroupAddPerm;
import com.depakto.classes.help.commands.CChannelGroupClientList;
import com.depakto.classes.help.commands.CChannelGroupCopy;
import com.depakto.classes.help.commands.CChannelGroupDel;
import com.depakto.classes.help.commands.CChannelGroupDelPerm;
import com.depakto.classes.help.commands.CChannelGroupList;
import com.depakto.classes.help.commands.CChannelGroupPermList;
import com.depakto.classes.help.commands.CChannelGroupRename;
import com.depakto.classes.help.commands.CChannelInfo;
import com.depakto.classes.help.commands.CChannelList;
import com.depakto.classes.help.commands.CChannelMove;
import com.depakto.classes.help.commands.CChannelPermList;
import com.depakto.classes.help.commands.CClientAddPerm;
import com.depakto.classes.help.commands.CClientDBDelelete;
import com.depakto.classes.help.commands.CClientDBEdit;
import com.depakto.classes.help.commands.CClientDBFind;
import com.depakto.classes.help.commands.CClientDBInfo;
import com.depakto.classes.help.commands.CClientDBList;
import com.depakto.classes.help.commands.CClientDelPerm;
import com.depakto.classes.help.commands.CClientEdit;
import com.depakto.classes.help.commands.CClientFind;
import com.depakto.classes.help.commands.CClientGetDBIdFromUId;
import com.depakto.classes.help.commands.CClientGetIds;
import com.depakto.classes.help.commands.CClientInfo;
import com.depakto.classes.help.commands.CClientKick;
import com.depakto.classes.help.commands.CClientList;
import com.depakto.classes.help.commands.CClientMove;
import com.depakto.classes.help.commands.CClientMute;
import com.depakto.classes.help.commands.CClientPermList;
import com.depakto.classes.help.commands.CClientPoke;
import com.depakto.classes.help.commands.CClientSetServerQueryLogin;
import com.depakto.classes.help.commands.CClientUnMute;
import com.depakto.classes.help.commands.CClientUpdate;
import com.depakto.classes.help.commands.CComplainAdd;
import com.depakto.classes.help.commands.CComplainDel;
import com.depakto.classes.help.commands.CComplainDelAll;
import com.depakto.classes.help.commands.CComplainList;
import com.depakto.classes.help.commands.CGM;
import com.depakto.classes.help.commands.CHostInfo;
import com.depakto.classes.help.commands.CInstanceEdit;
import com.depakto.classes.help.commands.CInstanceInfo;
import com.depakto.classes.help.commands.CLogin;
import com.depakto.classes.help.commands.CLogout;
import com.depakto.classes.help.commands.CMessageAdd;
import com.depakto.classes.help.commands.CMessageDel;
import com.depakto.classes.help.commands.CMessageGet;
import com.depakto.classes.help.commands.CMessageList;
import com.depakto.classes.help.commands.CMessageUpdateFlag;
import com.depakto.classes.help.commands.CPermFind;
import com.depakto.classes.help.commands.CPermGet;
import com.depakto.classes.help.commands.CPermIdGetByName;
import com.depakto.classes.help.commands.CPermOverview;
import com.depakto.classes.help.commands.CPermReset;
import com.depakto.classes.help.commands.CPermissionList;
import com.depakto.classes.help.commands.CPrivilegeKeyAdd;
import com.depakto.classes.help.commands.CPrivilegeKeyDelete;
import com.depakto.classes.help.commands.CPrivilegeKeyList;
import com.depakto.classes.help.commands.CPrivilegeKeyUse;
import com.depakto.classes.help.commands.CQuit;
import com.depakto.classes.help.commands.CSendTextMessage;
import com.depakto.classes.help.commands.CServerCreate;
import com.depakto.classes.help.commands.CServerDelete;
import com.depakto.classes.help.commands.CServerEdit;
import com.depakto.classes.help.commands.CServerGroupAdd;
import com.depakto.classes.help.commands.CServerGroupAddClient;
import com.depakto.classes.help.commands.CServerGroupAddPerm;
import com.depakto.classes.help.commands.CServerGroupAutoAddPerm;
import com.depakto.classes.help.commands.CServerGroupAutoDelPerm;
import com.depakto.classes.help.commands.CServerGroupClientList;
import com.depakto.classes.help.commands.CServerGroupCopy;
import com.depakto.classes.help.commands.CServerGroupDel;
import com.depakto.classes.help.commands.CServerGroupDelClient;
import com.depakto.classes.help.commands.CServerGroupDelPerm;
import com.depakto.classes.help.commands.CServerGroupList;
import com.depakto.classes.help.commands.CServerGroupPermList;
import com.depakto.classes.help.commands.CServerGroupRename;
import com.depakto.classes.help.commands.CServerGroupsByClientId;
import com.depakto.classes.help.commands.CServerIdGetByPort;
import com.depakto.classes.help.commands.CServerInfo;
import com.depakto.classes.help.commands.CServerList;
import com.depakto.classes.help.commands.CServerNotifyRegister;
import com.depakto.classes.help.commands.CServerNotifyUnregister;
import com.depakto.classes.help.commands.CServerProcessStop;
import com.depakto.classes.help.commands.CServerRequestConnectionInfo;
import com.depakto.classes.help.commands.CServerSchandlerID;
import com.depakto.classes.help.commands.CServerSnapshotCreate;
import com.depakto.classes.help.commands.CServerSnapshotDeploy;
import com.depakto.classes.help.commands.CServerStart;
import com.depakto.classes.help.commands.CServerStop;
import com.depakto.classes.help.commands.CSetClientChannelGroup;
import com.depakto.classes.help.commands.CUse;
import com.depakto.classes.help.commands.CVersion;
import com.depakto.classes.help.commands.CWhoAmI;
import com.depakto.classes.help.commands.response.CHashPassword;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TSApiGroups {
    private final TSQueryGroups query;

    public TSApiGroups(TSQueryGroups query) {
        this.query = query;
    }

    public int addBan(String ip, String name, String uid, long timeInSeconds, String reason) {
        CBanAdd add = new CBanAdd(ip, name, uid, timeInSeconds, reason);
        if (this.query.doCommand(add)) {
            return StringUtil.getInt(add.getFirstResponse().get("banid"));
        }
        return -1;
    }

    public boolean addChannelClientPermission(int channelId, int clientDBId, String permName, int permValue) {
        CChannelClientAddPerm add = new CChannelClientAddPerm(channelId, clientDBId, permName, permValue);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public int addChannelGroup(String name) {
        return addChannelGroup(name, null);
    }

    public int addChannelGroup(String name, PermissionGroupDatabaseType t) {
        CChannelGroupAdd add = new CChannelGroupAdd(name, t);
        if (this.query.doCommand(add)) {
            return StringUtil.getInt(add.getFirstResponse().get("cgid"));
        }
        return -1;
    }

    public boolean addChannelPermission(int channelId, String permName, int permValue) {
        CChannelAddPerm perm = new CChannelAddPerm(channelId, permName, permValue);
        if (this.query.doCommand(perm)) {
            return perm.getError().isSuccessful();
        }
        return false;
    }

    public boolean addClientPermission(int clientDBId, String permName, int permValue, boolean permSkipped) {
        CClientAddPerm add = new CClientAddPerm(clientDBId, permName, permValue, permSkipped);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public boolean addClientToServerGroup(int groupId, int clientDatabaseId) {
        CServerGroupAddClient add = new CServerGroupAddClient(groupId, clientDatabaseId);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public boolean addComplain(int clientDBId, String text) {
        CComplainAdd add = new CComplainAdd(clientDBId, text);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public boolean addPermissionToAllServerGroups(ServerGroupType t, String permName, int permValue, boolean permNegated, boolean permSkipped) {
        CServerGroupAutoAddPerm add = new CServerGroupAutoAddPerm(t, permName, permValue, permNegated, permSkipped);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public boolean addPermissionToChannelGroup(int groupId, String permName, int permValue) {
        CChannelGroupAddPerm add = new CChannelGroupAddPerm(groupId, permName, permValue);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public String addPrivilegeKey(int type, int groupId, int channelId, String description) {
        CPrivilegeKeyAdd add = new CPrivilegeKeyAdd(type, groupId, channelId, description);
        if (this.query.doCommand(add)) {
            return add.getFirstResponse().get("token");
        }
        return null;
    }

    public String addPrivilegeKeyChannelGroup(int channelGroupId, int channelId, String description) {
        return addPrivilegeKey(1, channelGroupId, channelId, description);
    }

    public String addPrivilegeKeyServerGroup(int serverGroupId, String description) {
        return addPrivilegeKey(0, serverGroupId, 0, description);
    }

    public int addServerGroup(String name) {
        return addServerGroup(name, PermissionGroupDatabaseType.REGULAR);
    }

    public int addServerGroup(String name, PermissionGroupDatabaseType t) {
        CServerGroupAdd add = new CServerGroupAdd(name, t);
        if (this.query.doCommand(add)) {
            return StringUtil.getInt(add.getFirstResponse().get("sgid"));
        }
        return -1;
    }

    public boolean addServerGroupPermission(int groupId, String permName, int value, boolean negated, boolean skipped) {
        CServerGroupAddPerm add = new CServerGroupAddPerm(groupId, permName, value, negated, skipped);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public void addTSListeners(TSListener... l) {
        this.query.getEventManager().addListeners(l);
    }

    public int banClient(int clientId, long timeInSeconds) {
        return banClient(clientId, timeInSeconds, null);
    }

    public int banClient(int clientId, long timeInSeconds, String reason) {
        CBanClient client = new CBanClient(clientId, timeInSeconds, reason);
        if (this.query.doCommand(client)) {
            return StringUtil.getInt(client.getFirstResponse().get("banid"));
        }
        return -1;
    }

    public int banClient(int clientId, String reason) {
        return banClient(clientId, reason);
    }

    public boolean broadcast(String message) {
        CGM broadcast = new CGM(message);
        if (this.query.doCommand(broadcast)) {
            return broadcast.getError().isSuccessful();
        }
        return false;
    }

    public boolean copyChannelGroup(int sourceGroupId, int targetGroupId, PermissionGroupDatabaseType t) {
        CChannelGroupCopy copy = new CChannelGroupCopy(sourceGroupId, targetGroupId, t);
        if (this.query.doCommand(copy)) {
            return copy.getError().isSuccessful();
        }
        return false;
    }

    public int copyChannelGroup(int sourceGroupId, String targetName, PermissionGroupDatabaseType t) {
        CChannelGroupCopy copy = new CChannelGroupCopy(sourceGroupId, targetName, t);
        if (this.query.doCommand(copy)) {
            return StringUtil.getInt(copy.getFirstResponse().get("cgid"));
        }
        return -1;
    }

    public int copyServerGroup(int idSource, int idTarget, PermissionGroupDatabaseType t) {
        return copyServerGroup(idSource, idTarget, "ignored", t);
    }

    private int copyServerGroup(int idSource, int idTarget, String name, PermissionGroupDatabaseType t) {
        CServerGroupCopy copy = new CServerGroupCopy(idSource, idTarget, name, t);
        if (this.query.doCommand(copy)) {
            return StringUtil.getInt(copy.getFirstResponse().get("sgid"));
        }
        return -1;
    }

    public int copyServerGroup(int idSource, String name, PermissionGroupDatabaseType t) {
        return copyServerGroup(idSource, 0, name, t);
    }

    public int createChannel(String name, HashMap<ChannelProperty, String> options) {
        CChannelCreate create = new CChannelCreate(name, options);
        if (this.query.doCommand(create)) {
            return StringUtil.getInt(create.getFirstResponse().get("cid"));
        }
        return -1;
    }

    public boolean createServer(String name, HashMap<VirtualServerProperty, String> map) {
        CServerCreate create = new CServerCreate(name, map);
        if (this.query.doCommand(create)) {
            return create.getError().isSuccessful();
        }
        return false;
    }

    public String createServerQueryLogin(String name) {
        CClientSetServerQueryLogin login = new CClientSetServerQueryLogin(name);
        if (this.query.doCommand(login)) {
            return login.getFirstResponse().get("client_login_password");
        }
        return null;
    }

    public Snapshot createServerSnapshot() {
        CServerSnapshotCreate create = new CServerSnapshotCreate();
        if (this.query.doCommand(create)) {
            return new Snapshot(create.getRaw());
        }
        return null;
    }

    public boolean deleteAllBans() {
        CBanDelAll del = new CBanDelAll();
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteAllComplaints(int clientDBId) {
        CComplainDelAll del = new CComplainDelAll(clientDBId);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteBan(int banId) {
        CBanDel del = new CBanDel(banId);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteChannel(int channelId) {
        CChannelDelete del = new CChannelDelete(channelId, true);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteChannelClientPermission(int channelId, int clientDBId, String permName) {
        CChannelClientDelPerm del = new CChannelClientDelPerm(channelId, clientDBId, permName);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteChannelGroup(int groupId) {
        CChannelGroupDel del = new CChannelGroupDel(groupId, true);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteChannelGroupPermission(int groupId, String permName) {
        CChannelGroupDelPerm del = new CChannelGroupDelPerm(groupId, permName);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteChannelPermission(int channelId, String permName) {
        CChannelDelPerm del = new CChannelDelPerm(channelId, permName);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteClientPermission(int clientDBId, String permName) {
        CClientDelPerm del = new CClientDelPerm(clientDBId, permName);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteComplaint(int targetClientDBId, int fromClientDBId) {
        CComplainDel del = new CComplainDel(targetClientDBId, fromClientDBId);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteDatabaseClientProperties(int clientDBId) {
        CClientDBDelelete del = new CClientDBDelelete(clientDBId);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteOfflineMessage(int messageId) {
        CMessageDel del = new CMessageDel(messageId);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deletePermissionFromAllServerGroups(ServerGroupType t, String permName) {
        CServerGroupAutoDelPerm del = new CServerGroupAutoDelPerm(t, permName);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deletePrivilegeKey(String token) {
        CPrivilegeKeyDelete del = new CPrivilegeKeyDelete(token);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteServer(int id) {
        CServerDelete delete = new CServerDelete(id);
        if (this.query.doCommand(delete)) {
            return delete.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteServerGroup(int id) {
        return deleteServerGroup(id, true);
    }

    public boolean deleteServerGroup(int id, boolean forced) {
        CServerGroupDel del = new CServerGroupDel(id, forced);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deleteServerGroupPermission(int groupId, String permName) {
        CServerGroupDelPerm del = new CServerGroupDelPerm(groupId, permName);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public boolean deployServerSnapshot(Snapshot snapshot) {
        CServerSnapshotDeploy deploy = new CServerSnapshotDeploy(snapshot.get());
        if (this.query.doCommand(deploy)) {
            return deploy.getError().isSuccessful();
        }
        return false;
    }

    public boolean editChannel(int channelId, HashMap<ChannelProperty, String> options) {
        CChannelEdit edit = new CChannelEdit(channelId, options);
        if (this.query.doCommand(edit)) {
            return edit.getError().isSuccessful();
        }
        return false;
    }

    public void editClient(int clientId, HashMap<ClientProperty, String> options) {
        this.query.doCommand(new CClientEdit(clientId, options));
    }

    public boolean editDatabaseClient(int clientDBId, HashMap<ClientProperty, String> options) {
        CClientDBEdit edit = new CClientDBEdit(clientDBId, options);
        if (this.query.doCommand(edit)) {
            return edit.getError().isSuccessful();
        }
        return false;
    }

    public boolean editInstance(ServerInstanceProperty p, String value) {
        if (p.isChangeable()) {
            CInstanceEdit edit = new CInstanceEdit(p, value);
            if (this.query.doCommand(edit)) {
                return edit.getError().isSuccessful();
            }
        }
        return false;
    }

    public boolean editServer(HashMap<VirtualServerProperty, String> map) {
        CServerEdit edit = new CServerEdit(map);
        if (this.query.doCommand(edit)) {
            return edit.getError().isSuccessful();
        }
        return false;
    }

    public List<Ban> getBans() {
        CBanList list = new CBanList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Ban> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Ban(opt));
        }
        return arrayList;
    }

    public List<Binding> getBindings() {
        CBindingList list = new CBindingList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Binding> arrayList = new ArrayList();
        for (HashMap<String, String> map : list.getResponse()) {
            arrayList.add(new Binding(map));
        }
        return arrayList;
    }

    public Channel getChannelByName(String name) {
        CChannelFind find = new CChannelFind(name);
        if (this.query.doCommand(find)) {
            for (Channel c : getChannels()) {
                if (c.getId() == StringUtil.getInt(find.getFirstResponse().get("cid"))) {
                    return c;
                }
            }
        }
        return null;
    }

    public List<Permission> getChannelClientPermissions(int channelId, int clientDBId) {
        CChannelClientPermList list = new CChannelClientPermList(channelId, clientDBId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Permission> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Permission(opt));
        }
        return arrayList;
    }

    public List<ChannelGroupClient> getChannelGroupClients(int channelId, int clientDBId, int groupId) {
        CChannelGroupClientList list = new CChannelGroupClientList(channelId, clientDBId, groupId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<ChannelGroupClient> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new ChannelGroupClient(opt));
        }
        return arrayList;
    }

    public List<ChannelGroupClient> getChannelGroupClientsByChannelGroupId(int groupId) {
        return getChannelGroupClients(-1, -1, groupId);
    }

    public List<ChannelGroupClient> getChannelGroupClientsByChannelId(int channelId) {
        return getChannelGroupClients(channelId, -1, -1);
    }

    public List<ChannelGroupClient> getChannelGroupClientsByClientDBId(int clientDBId) {
        return getChannelGroupClients(-1, clientDBId, -1);
    }

    public List<Permission> getChannelGroupPermissions(int groupId) {
        CChannelGroupPermList list = new CChannelGroupPermList(groupId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Permission> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Permission(opt));
        }
        return arrayList;
    }

    public List<ChannelGroup> getChannelGroups() {
        CChannelGroupList list = new CChannelGroupList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<ChannelGroup> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new ChannelGroup(opt));
        }
        return arrayList;
    }

    public ChannelInfo getChannelInfo(int channelId) {
        CChannelInfo info = new CChannelInfo(channelId);
        if (this.query.doCommand(info)) {
            return new ChannelInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public List<Permission> getChannelPermissions(int channelId) {
        CChannelPermList list = new CChannelPermList(channelId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Permission> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Permission(opt));
        }
        return arrayList;
    }

    public List<Channel> getChannels() {
        CChannelList list = new CChannelList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Channel> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Channel(opt));
        }
        return arrayList;
    }

    public List<Client> getClientByName(String pattern) {
        CClientFind find = new CClientFind(pattern);
        if (!this.query.doCommand(find)) {
            return null;
        }
        List<Client> arrayList = new ArrayList();
        for (Client c : getClients()) {
            for (HashMap<String, String> opt : find.getResponse()) {
                if (c.getId() == StringUtil.getInt(new Wrapper(opt).get("clid"))) {
                    arrayList.add(c);
                }
            }
        }
        return arrayList;
    }

    public ClientInfo getClientByUId(String clientUId) {
        CClientGetIds get = new CClientGetIds(clientUId);
        if (this.query.doCommand(get)) {
            return getClientInfo(StringUtil.getInt((String) get.getFirstResponse().getMap().get("clid")));
        }
        return null;
    }

    public ClientInfo getClientInfo(int clientId) {
        CClientInfo info = new CClientInfo(clientId, true);
        if (this.query.doCommand(info)) {
            return new ClientInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public List<Permission> getClientPermissions(int clientDBId) {
        CClientPermList list = new CClientPermList(clientDBId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Permission> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Permission(opt));
        }
        return arrayList;
    }

    public List<Client> getClients() {
        CClientList list = new CClientList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Client> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Client(opt));
        }
        return arrayList;
    }

    public List<Complaint> getComplaints() {
        return getComplaints(-1);
    }

    public List<Complaint> getComplaints(int clientDBId) {
        CComplainList list = new CComplainList(clientDBId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Complaint> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Complaint(opt));
        }
        return arrayList;
    }

    public ConnectionInfo getConnectionInfo() {
        CServerRequestConnectionInfo info = new CServerRequestConnectionInfo();
        if (this.query.doCommand(info)) {
            return new ConnectionInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public DatabaseClientInfo getDatabaseClientByName(String name) {
        CClientDBFind find = new CClientDBFind(name, false);
        if (this.query.doCommand(find)) {
            return getDatabaseClientInfo(StringUtil.getInt(find.getFirstResponse().get("cldbid")));
        }
        return null;
    }

    public DatabaseClientInfo getDatabaseClientByUId(String clientUId) {
        CClientGetDBIdFromUId get = new CClientGetDBIdFromUId(clientUId);
        if (this.query.doCommand(get)) {
            return getDatabaseClientInfo(StringUtil.getInt(get.getFirstResponse().get("cldbid")));
        }
        return null;
    }

    public DatabaseClientInfo getDatabaseClientInfo(int clientDBId) {
        CClientDBInfo info = new CClientDBInfo(clientDBId);
        if (this.query.doCommand(info)) {
            return new DatabaseClientInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public List<DatabaseClient> getDatabaseClients() {
        CClientDBList countList = new CClientDBList(0, 1, true);
        if (!this.query.doCommand(countList)) {
            return null;
        }
        int count = StringUtil.getInt(countList.getFirstResponse().get("count"));
        List<DatabaseClient> arrayList = new ArrayList();
        for (int i = 0; i < count; i += 200) {
            CClientDBList list = new CClientDBList(i, 200, false);
            if (this.query.doCommand(list)) {
                for (HashMap<String, String> map : list.getResponse()) {
                    arrayList.add(new DatabaseClient(map));
                }
            }
        }
        return arrayList;
    }

    public HostInfo getHostInfo() {
        CHostInfo info = new CHostInfo();
        if (this.query.doCommand(info)) {
            return new HostInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public InstanceInfo getInstanceInfo() {
        CInstanceInfo info = new CInstanceInfo();
        if (this.query.doCommand(info)) {
            return new InstanceInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public String getOfflineMessage(int messageId) {
        CMessageGet get = new CMessageGet(messageId);
        if (this.query.doCommand(get)) {
            return get.getFirstResponse().get("message");
        }
        return null;
    }

    public List<Message> getOfflineMessages() {
        CMessageList list = new CMessageList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Message> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Message(opt));
        }
        return arrayList;
    }

    public void getPermission(String permName) {
        if (!this.query.doCommand(new CPermFind(permName))) {
        }
    }

    public int getPermissionIdByName(String permName) {
        CPermIdGetByName get = new CPermIdGetByName(permName);
        if (this.query.doCommand(get)) {
            return StringUtil.getInt(get.getFirstResponse().get("permid"));
        }
        return -1;
    }

    public List<AdvancedPermission> getPermissionOverview(int channelId, int clientDBId) {
        CPermOverview overview = new CPermOverview(channelId, clientDBId);
        if (!this.query.doCommand(overview)) {
            return null;
        }
        List<AdvancedPermission> arrayList = new ArrayList();
        for (HashMap<String, String> opt : overview.getResponse()) {
            arrayList.add(new AdvancedPermission(opt));
        }
        return arrayList;
    }

    public List<PermissionInfo> getPermissions() {
        CPermissionList list = new CPermissionList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<PermissionInfo> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new PermissionInfo(opt));
        }
        return arrayList;
    }

    public int getPermissionValue(String permName) {
        CPermGet get = new CPermGet(permName);
        if (this.query.doCommand(get)) {
            return StringUtil.getInt(get.getFirstResponse().get("permvalue"));
        }
        return -1;
    }

    public List<PrivilegeKey> getPrivilegeKeys() {
        CPrivilegeKeyList list = new CPrivilegeKeyList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<PrivilegeKey> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new PrivilegeKey(opt));
        }
        return arrayList;
    }

    public List<ServerGroupClient> getServerGroupClients(int groupId) {
        CServerGroupClientList list = new CServerGroupClientList(groupId);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<ServerGroupClient> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new ServerGroupClient(opt));
        }
        return arrayList;
    }

    public List<Permission> getServerGroupPermissions(int id) {
        CServerGroupPermList list = new CServerGroupPermList(id);
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<Permission> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new Permission(opt));
        }
        return arrayList;
    }

    public List<ServerGroup> getServerGroups() {
        CServerGroupList list = new CServerGroupList();
        if (!this.query.doCommand(list)) {
            return null;
        }
        List<ServerGroup> arrayList = new ArrayList();
        for (HashMap<String, String> opt : list.getResponse()) {
            arrayList.add(new ServerGroup(opt));
        }
        return arrayList;
    }

    public List<ServerGroup> getServerGroupsByClientId(int clientDatabaseId) {
        CServerGroupsByClientId client = new CServerGroupsByClientId(clientDatabaseId);
        if (!this.query.doCommand(client)) {
            return null;
        }
        List<ServerGroup> arrayList = new ArrayList();
        List<ServerGroup> allGroups = getServerGroups();
        for (HashMap<String, String> opt : client.getResponse()) {
            for (ServerGroup s : allGroups) {
                if (s.getId() == StringUtil.getInt((String) opt.get("sgid"))) {
                    arrayList.add(s);
                }
            }
        }
        return arrayList;
    }

    public int getServerIdByPort(int port) {
        CServerIdGetByPort s = new CServerIdGetByPort(port);
        if (this.query.doCommand(s)) {
            return StringUtil.getInt(s.getFirstResponse().get("server_id"));
        }
        return -1;
    }

    public VirtualServerInfo getServerInfo() {
        CServerInfo info = new CServerInfo();
        if (this.query.doCommand(info)) {
            return new VirtualServerInfo(info.getFirstResponse().getMap());
        }
        return null;
    }

    public Version getVersion() {
        CVersion version = new CVersion();
        if (this.query.doCommand(version)) {
            return new Version(version.getFirstResponse().getMap());
        }
        return null;
    }

    public List<VirtualServer> getVirtualServers() {
        CServerList serverList = new CServerList();
        if (!this.query.doCommand(serverList)) {
            return null;
        }
        List<VirtualServer> arrayList = new ArrayList();
        for (HashMap<String, String> opt : serverList.getResponse()) {
            arrayList.add(new VirtualServer(opt));
        }
        return arrayList;
    }

    public boolean kickClientFromChannel(int... clientIds) {
        return kickClients(ReasonIdentifier.REASON_KICK_CHANNEL, null, clientIds);
    }

    public boolean kickClientFromChannel(String message, int... clientIds) {
        return kickClients(ReasonIdentifier.REASON_KICK_CHANNEL, message, clientIds);
    }

    public boolean kickClientFromServer(int... clientIds) {
        return kickClients(ReasonIdentifier.REASON_KICK_SERVER, null, clientIds);
    }

    public boolean kickClientFromServer(String message, int... clientIds) {
        return kickClients(ReasonIdentifier.REASON_KICK_SERVER, message, clientIds);
    }

    private boolean kickClients(ReasonIdentifier reason, String message, int... clientIds) {
        CClientKick kick = new CClientKick(reason, message, clientIds);
        if (this.query.doCommand(kick)) {
            return kick.getError().isSuccessful();
        }
        return false;
    }

    public boolean login(String username, String password) {
        CLogin login = new CLogin(username, password);
        if (this.query.doCommand(login)) {
            return login.getError().isSuccessful();
        }
        return false;
    }

    public boolean logout() {
        if (this.query.doCommand(new CLogout())) {
            return true;
        }
        return false;
    }

    public void moveChannel(int channelId, int channelTargetId) {
        moveChannel(channelId, channelTargetId, -1);
    }

    public boolean moveChannel(int channelId, int channelTargetId, int order) {
        CChannelMove move = new CChannelMove(channelId, channelTargetId, order);
        if (this.query.doCommand(move)) {
            return move.getError().isSuccessful();
        }
        return false;
    }

    public boolean moveClient(int clientId, int channelId) {
        return moveClient(clientId, channelId, null);
    }

    public boolean moveClient(int clientId, int channelId, String channelPassword) {
        CClientMove move = new CClientMove(clientId, channelId, channelPassword);
        if (this.query.doCommand(move)) {
            return move.getError().isSuccessful();
        }
        return false;
    }

    public boolean moveClient(int channelId) {
        return moveClient(whoAmI().getId(), channelId);
    }

    public boolean moveClient(int channelId, String channelPassword) {
        return moveClient(whoAmI().getId(), channelId, channelPassword);
    }

    public boolean pokeClient(int clientId, String message) {
        CClientPoke poke = new CClientPoke(clientId, message);
        if (this.query.doCommand(poke)) {
            return poke.getError().isSuccessful();
        }
        return false;
    }

    public List<Password> hashPassword(String password) {
        CHashPassword pass = new CHashPassword(password);
        if (!this.query.doCommand(pass)) {
            return null;
        }
        List<Password> arrayList = new ArrayList();
        for (HashMap<String, String> opt : pass.getResponse()) {
            arrayList.add(new Password(opt));
        }
        return arrayList;
    }

    public boolean quit() {
        if (!this.query.doCommand(new CQuit())) {
            return false;
        }
        System.exit(0);
        return true;
    }

    public ServerSchandlerID getSchandlerID() {
        CServerSchandlerID info = new CServerSchandlerID();
        if (this.query.doCommand(info)) {
            return new ServerSchandlerID(info.getFirstResponse().getMap());
        }
        return null;
    }

    public void registerAllEvents(int schandlerID) {
        registerEvent(TSEventType.CHANNEL, schandlerID);
    }

    public boolean registerEvent(TSEventType t, int schandlerID) {
        return registerEvent(t, -1, schandlerID);
    }

    public boolean registerEvent(TSEventType t, int channelId, int schandlerID) {
        CServerNotifyRegister r = new CServerNotifyRegister(t, channelId, schandlerID);
        if (this.query.doCommand(r)) {
            return r.getError().isSuccessful();
        }
        return false;
    }

    public void registerEvents(TSEventType... t) {
        for (TSEventType type : t) {
            registerEvent(type, -1);
        }
    }

    public boolean removeClientFromServerGroup(int groupId, int clientDatabaseId) {
        CServerGroupDelClient del = new CServerGroupDelClient(groupId, clientDatabaseId);
        if (this.query.doCommand(del)) {
            return del.getError().isSuccessful();
        }
        return false;
    }

    public void removeTSListeners(TSListener... l) {
        this.query.getEventManager().removeListeners(l);
    }

    public boolean renameChannelGroup(int groupId, String name) {
        CChannelGroupRename rename = new CChannelGroupRename(groupId, name);
        if (this.query.doCommand(rename)) {
            return rename.getError().isSuccessful();
        }
        return false;
    }

    public boolean renameServerGroup(int id, String name) {
        CServerGroupRename rename = new CServerGroupRename(id, name);
        if (this.query.doCommand(rename)) {
            return rename.getError().isSuccessful();
        }
        return false;
    }

    public String resetPermissions() {
        CPermReset reset = new CPermReset();
        if (this.query.doCommand(reset)) {
            return reset.getFirstResponse().get("token");
        }
        return null;
    }

    public boolean selectVirtualServerById(int id) {
        CUse use = new CUse(id, -1);
        if (this.query.doCommand(use)) {
            return use.getError().isSuccessful();
        }
        return false;
    }

    public boolean selectVirtualServerByPort(int port) {
        CUse use = new CUse(-1, port);
        if (this.query.doCommand(use)) {
            return use.getError().isSuccessful();
        }
        return false;
    }

    public boolean selectVirtualServer(VirtualServer server) {
        return selectVirtualServerById(server.getId());
    }

    public boolean sendOfflineMessage(String clientUId, String subject, String message) {
        CMessageAdd add = new CMessageAdd(clientUId, subject, message);
        if (this.query.doCommand(add)) {
            return add.getError().isSuccessful();
        }
        return false;
    }

    public boolean sendTextMessage(TextMessageTargetMode targetMode, int targetId, String message) {
        CSendTextMessage msg = new CSendTextMessage(targetMode.getIndex(), targetId, message);
        if (this.query.doCommand(msg)) {
            return msg.getError().isSuccessful();
        }
        return false;
    }

    public boolean sendChannelMessage(int channelId, String message) {
        return sendTextMessage(TextMessageTargetMode.CHANNEL, channelId, message);
    }

    public boolean sendChannelMessage(String message) {
        return sendChannelMessage(whoAmI().getChannelId(), message);
    }

    public boolean sendServerMessage(int serverId, String message) {
        return sendTextMessage(TextMessageTargetMode.SERVER, serverId, message);
    }

    public boolean sendServerMessage(String message) {
        return sendServerMessage(1, message);
    }

    public boolean sendPrivateMessage(int clientId, String message) {
        return sendTextMessage(TextMessageTargetMode.CLIENT, clientId, message);
    }

    public boolean setClientChannelGroup(int groupId, int channelId, int clientDBId) {
        CSetClientChannelGroup group = new CSetClientChannelGroup(groupId, channelId, clientDBId);
        if (this.query.doCommand(group)) {
            return group.getError().isSuccessful();
        }
        return false;
    }

    public boolean setMessageRead(int messageId) {
        return setMessageReadFlag(messageId, true);
    }

    public boolean setMessageReadFlag(int messageId, boolean read) {
        CMessageUpdateFlag flag = new CMessageUpdateFlag(messageId, read);
        if (this.query.doCommand(flag)) {
            return flag.getError().isSuccessful();
        }
        return read;
    }

    public boolean setNickname(String name) {
        HashMap<ClientProperty, String> options = new HashMap();
        options.put(ClientProperty.CLIENT_NICKNAME, name);
        return updateClient(options);
    }

    public boolean setMikro(String name) {
        HashMap<ClientProperty, String> options = new HashMap();
        options.put(ClientProperty.CLIENT_INPUT_MUTED, name);
        return updateClient(options);
    }

    public boolean setSpeaker(String name) {
        HashMap<ClientProperty, String> options = new HashMap();
        options.put(ClientProperty.CLIENT_OUTPUT_MUTED, name);
        return updateClient(options);
    }

    public boolean setAway(String name) {
        HashMap<ClientProperty, String> options = new HashMap();
        options.put(ClientProperty.CLIENT_AWAY, name);
        return updateClient(options);
    }

    public boolean muteClient(int clientId) {
        CClientMute mute = new CClientMute(clientId);
        if (this.query.doCommand(mute)) {
            return mute.getError().isSuccessful();
        }
        return false;
    }

    public boolean unmuteClient(int clientId) {
        CClientUnMute unmute = new CClientUnMute(clientId);
        if (this.query.doCommand(unmute)) {
            return unmute.getError().isSuccessful();
        }
        return false;
    }

    public boolean startServer(int id) {
        CServerStart start = new CServerStart(id);
        if (this.query.doCommand(start)) {
            return start.getError().isSuccessful();
        }
        return false;
    }

    public boolean stopServer(int id) {
        CServerStop start = new CServerStop(id);
        if (this.query.doCommand(start)) {
            return start.getError().isSuccessful();
        }
        return false;
    }

    public boolean stopServerProcess() {
        CServerProcessStop stop = new CServerProcessStop();
        if (this.query.doCommand(stop)) {
            return stop.getError().isSuccessful();
        }
        return false;
    }

    public boolean unregisterAllEvents() {
        CServerNotifyUnregister unr = new CServerNotifyUnregister();
        if (this.query.doCommand(unr)) {
            return unr.getError().isSuccessful();
        }
        return false;
    }

    public boolean updateClient(HashMap<ClientProperty, String> options) {
        CClientUpdate update = new CClientUpdate(options);
        if (this.query.doCommand(update)) {
            return update.getError().isSuccessful();
        }
        return false;
    }

    public boolean usePrivilegeKey(String token) {
        CPrivilegeKeyUse use = new CPrivilegeKeyUse(token);
        if (this.query.doCommand(use)) {
            return use.getError().isSuccessful();
        }
        return false;
    }

    public ServerQueryInfo whoAmI() {
        CWhoAmI whoAmI = new CWhoAmI();
        if (this.query.doCommand(whoAmI)) {
            return new ServerQueryInfo(whoAmI.getFirstResponse().getMap());
        }
        return null;
    }
}
