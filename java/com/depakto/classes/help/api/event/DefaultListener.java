package com.depakto.classes.help.api.event;

public class DefaultListener implements TSListener {
    public void onTextMessage(TextMessageEvent e) {
        System.out.println("[Text Message] " + e.getInvokerName() + ": " + e.getMessage());
    }

    public void onClientJoin(ClientJoinEvent e) {
        System.out.println("[Player Joined] " + e.getClientNickname() + " from:" + e.getClientCountry() + " id:" + e.getClientId() + " type:" + e.getClientType());
    }

    public void onClientLeave(ClientLeaveEvent e) {
        System.out.println("[Player Left] id: " + e.getClientId() + "from:" + e.getClientFromChannelId() + " to:" + e.getClientTargetId() + " name:" + e.getInvokerName());
    }

    public void onServerEdit(ServerEditedEvent e) {
        System.out.println("[Server Edited] by " + e.getInvokerName());
    }

    public void onChannelEdit(ChannelEditedEvent e) {
        System.out.println("[Channel Edited] by " + e.getInvokerName());
    }

    public void onChannelDelete(ChannelDeletedEvent e) {
        System.out.println("[Channel Deleted] by " + e.getInvokerName());
    }

    public void onChannelCreate(ChannelCreatedEvent e) {
        System.out.println("[Channel Created] by " + e.getInvokerName());
    }

    public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent e) {
        System.out.println("[Ch. Dscr. Changed] id: " + e.getChannelId());
    }

    public void onClientMoved(ClientMovedEvent e) {
        System.out.println("[Client Moved] id: " + e.getClientId() + "target-id:" + e.getChannelTargetId());
    }

    public void onChannelMove(ChannelMovedEvent e) {
        System.out.println("[Channel Moved] id: " + e.getChannelId());
    }

    public void onClientUpdate(ClientUpdatedEvent e) {
    }

    public void onClientTalkStatus(ClientTalkStatus e) {
        System.out.println("[Client Talk] id: " + e.getClientId() + "status:" + e.getClientStatus());
    }

    public void onClientPoke(ClientPokeEvent e) {
        System.out.println("[Poke Message] " + e.getInvokerName() + ": " + e.getMessage());
    }

    public void onServerGroupList(ServerGroupEvent e) {
        System.out.println("[Group List Message] " + e.getMap());
    }

    public void onServerChannelGroupList(ServerChannelGroupEvent e) {
        System.out.println("[Channel Group List Message] " + e.getMap());
    }

    public void onServerPermissionList(ServerPermissionEvent e) {
        System.out.println("[Permission List Message] " + e.getMap());
    }

    public void onServerGroupPermList(ServerGroupPermEvent e) {
        System.out.println("[Server Group Perm List Message] " + e.getMap());
    }

    public void onServerGroupChannelPermList(ServerGroupChannelPermEvent e) {
        System.out.println("[Channel Group Perm List Message] " + e.getMap());
    }

    public void onComplainList(ComplainEvent e) {
        System.out.println("[Complain List Message] " + e.getMap());
    }

    public void onBanList(BanEvent e) {
        System.out.println("[Ban List Message] " + e.getMap());
    }

    public void onOfflineMessageList(OfflineMessageEvent e) {
        System.out.println("[Offline Message List Message] " + e.getMap());
    }

    public void onGetOfflineMessage(OfflineMessageGetEvent e) {
        System.out.println("[Offline Message Get] " + e.getMap());
    }

    public void onClientNameFromUid(ClientNameFromUidEvent e) {
        System.out.println("[Client name from UID] " + e.getMap());
    }
}
