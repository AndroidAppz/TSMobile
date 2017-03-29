package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.RawParam;
import com.depakto.tsmobile.BuildConfig;

public class CChannelDetails extends Command {
    public CChannelDetails(int channelId) {
        super("channelvariable");
        add(new KeyValueParam("cid", channelId + BuildConfig.FLAVOR));
        add(new RawParam("channel_topic"));
        add(new RawParam("channel_description"));
        add(new RawParam("channel_codec"));
        add(new RawParam("channel_codec_quality"));
        add(new RawParam("channel_codec_is_unencrypted"));
        add(new RawParam("channel_name_phonetic"));
        add(new RawParam("channel_needed_talk_power"));
        add(new RawParam("channel_flag_maxfamilyclients_inherited"));
        add(new RawParam("channel_flag_maxfamilyclients_unlimited"));
        add(new RawParam("channel_maxfamilyclients"));
        add(new RawParam("channel_delete_delay"));
    }
}
