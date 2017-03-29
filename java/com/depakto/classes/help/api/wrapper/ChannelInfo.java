package com.depakto.classes.help.api.wrapper;

import com.depakto.classes.help.api.ChannelProperty;
import java.util.HashMap;

public class ChannelInfo extends Wrapper {
    public ChannelInfo(HashMap<String, String> map) {
        super(map);
    }

    public int getParentChannelId() {
        return getInt(ChannelProperty.PID);
    }

    public String getName() {
        return get(ChannelProperty.CHANNEL_NAME);
    }

    public String getTopic() {
        return get(ChannelProperty.CHANNEL_TOPIC);
    }

    public String getDescription() {
        return get(ChannelProperty.CHANNEL_DESCRIPTION);
    }

    public String getPassword() {
        return get(ChannelProperty.CHANNEL_PASSWORD);
    }

    public int getCodec() {
        return getInt(ChannelProperty.CHANNEL_CODEC);
    }

    public int getCodecQuality() {
        return getInt(ChannelProperty.CHANNEL_CODEC_QUALITY);
    }

    public int getMaxClients() {
        return getInt(ChannelProperty.CHANNEL_MAXCLIENTS);
    }

    public int getMaxFamilyClients() {
        return getInt(ChannelProperty.CHANNEL_MAXFAMILYCLIENTS);
    }

    public int getOrder() {
        return getInt(ChannelProperty.CHANNEL_ORDER);
    }

    public boolean isPermanent() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_PERMANENT);
    }

    public boolean isSemiPermanent() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_SEMI_PERMANENT);
    }

    public boolean isDefault() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_DEFAULT);
    }

    public boolean hasPassword() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_PASSWORD);
    }

    public int getCodecLatencyFactor() {
        return getInt(ChannelProperty.CHANNEL_CODEC_LATENCY_FACTOR);
    }

    public boolean isEncrypted() {
        return !getBoolean(ChannelProperty.CHANNEL_CODEC_IS_UNENCRYPTED);
    }

    public boolean hasUnlimitedClients() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED);
    }

    public boolean hasUnlimitedFamilyClients() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED);
    }

    public boolean hasInheritedMaxFamilyClients() {
        return getBoolean(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_INHERITED);
    }

    public String getFilePath() {
        return get(ChannelProperty.PATH);
    }

    public int getNeededTalkPower() {
        return getInt(ChannelProperty.CHANNEL_NEEDED_TALK_POWER);
    }

    public boolean isForcedSilence() {
        return getBoolean(ChannelProperty.CHANNEL_FORCED_SILENCE);
    }

    public String getPhoneticName() {
        return get(ChannelProperty.CHANNEL_NAME_PHONETIC);
    }

    public long getIconId() {
        return getLong(ChannelProperty.CHANNEL_ICON_ID);
    }
}
