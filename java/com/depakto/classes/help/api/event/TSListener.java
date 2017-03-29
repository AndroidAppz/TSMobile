package com.depakto.classes.help.api.event;

public interface TSListener {
    void onBanList(BanEvent banEvent);

    void onChannelCreate(ChannelCreatedEvent channelCreatedEvent);

    void onChannelDelete(ChannelDeletedEvent channelDeletedEvent);

    void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent);

    void onChannelEdit(ChannelEditedEvent channelEditedEvent);

    void onChannelMove(ChannelMovedEvent channelMovedEvent);

    void onClientJoin(ClientJoinEvent clientJoinEvent);

    void onClientLeave(ClientLeaveEvent clientLeaveEvent);

    void onClientMoved(ClientMovedEvent clientMovedEvent);

    void onClientNameFromUid(ClientNameFromUidEvent clientNameFromUidEvent);

    void onClientPoke(ClientPokeEvent clientPokeEvent);

    void onClientTalkStatus(ClientTalkStatus clientTalkStatus);

    void onClientUpdate(ClientUpdatedEvent clientUpdatedEvent);

    void onComplainList(ComplainEvent complainEvent);

    void onGetOfflineMessage(OfflineMessageGetEvent offlineMessageGetEvent);

    void onOfflineMessageList(OfflineMessageEvent offlineMessageEvent);

    void onServerChannelGroupList(ServerChannelGroupEvent serverChannelGroupEvent);

    void onServerEdit(ServerEditedEvent serverEditedEvent);

    void onServerGroupChannelPermList(ServerGroupChannelPermEvent serverGroupChannelPermEvent);

    void onServerGroupList(ServerGroupEvent serverGroupEvent);

    void onServerGroupPermList(ServerGroupPermEvent serverGroupPermEvent);

    void onServerPermissionList(ServerPermissionEvent serverPermissionEvent);

    void onTextMessage(TextMessageEvent textMessageEvent);
}
