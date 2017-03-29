package com.depakto.classes.help.commands;

import com.depakto.classes.help.commands.parameter.KeyValueParam;
import com.depakto.classes.help.commands.parameter.RawParam;

public class CClientInfo extends Command {
    public CClientInfo(int clientId, boolean trybWywolania) {
        super("clientvariable");
        add(new KeyValueParam("clid", clientId));
        if (trybWywolania) {
            add(new RawParam("client_is_talker"));
            add(new RawParam("client_away_message"));
            add(new RawParam("client_channel_group_id"));
            add(new RawParam("client_country"));
            add(new RawParam("client_database_id"));
            add(new RawParam("client_nickname"));
            add(new RawParam("client_talk_power"));
            add(new RawParam("client_away"));
            add(new RawParam("client_is_muted"));
            add(new RawParam("client_is_channel_commander"));
            add(new RawParam("client_input_hardware"));
            add(new RawParam("client_input_muted"));
            add(new RawParam("client_output_hardware"));
            add(new RawParam("client_output_muted"));
            add(new RawParam("client_is_priority_speaker"));
            add(new RawParam("client_is_recording"));
            add(new RawParam("client_unread_messages"));
            add(new RawParam("client_servergroups"));
            add(new RawParam("client_talk_request"));
            add(new RawParam("client_talk_request_msg"));
            add(new RawParam("client_description"));
            add(new RawParam("client_volume_modificator"));
            add(new RawParam("client_unique_identifier"));
            add(new RawParam("client_type"));
            add(new RawParam("client_nickname_phonetic"));
            add(new RawParam("client_meta_data"));
            add(new RawParam("client_outputonly_muted"));
            add(new RawParam("client_icon_id"));
            add(new RawParam("client_needed_serverquery_view_power"));
            add(new RawParam("client_channel_group_inherited_channel_id"));
            return;
        }
        add(new RawParam("client_unread_messages"));
    }
}
