package com.depakto.tsmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.depakto.classes.adapters.ClientServerInfoArrayAdapter;
import com.depakto.classes.construktor.ServerInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerClientActivity extends AppCompatActivity {
    private ClientServerInfoArrayAdapter adapter_virtuals1;
    private ClientServerInfoArrayAdapter adapter_virtuals2;
    private ClientServerInfoArrayAdapter adapter_virtuals3;
    private ClientServerInfoArrayAdapter adapter_virtuals4;
    private ServerInfo dane1 = new ServerInfo();
    private ServerInfo dane2 = new ServerInfo();
    private ServerInfo dane3 = new ServerInfo();
    private ServerInfo dane4 = new ServerInfo();
    private List<ServerInfo> daneList1 = new ArrayList();
    private List<ServerInfo> daneList2 = new ArrayList();
    private List<ServerInfo> daneList3 = new ArrayList();
    private List<ServerInfo> daneList4 = new ArrayList();
    ListView listInfo1;
    ListView listInfo2;
    ListView listInfo3;
    ListView listInfo4;
    private Toolbar toolbar;
    private HashMap<String, String> wartosciServera = new HashMap();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_client);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.toolbar.setVisibility(8);
        Bundle bBundle = getIntent().getExtras();
        if (bBundle != null) {
            if (bBundle.containsKey("client_is_talker")) {
                this.wartosciServera.put("client_is_talker", bBundle.get("client_is_talker").toString());
            }
            if (bBundle.containsKey("client_away_message")) {
                this.wartosciServera.put("client_away_message", bBundle.get("client_away_message").toString());
            }
            if (bBundle.containsKey("client_channel_group_id")) {
                this.wartosciServera.put("client_channel_group_id", bBundle.get("client_channel_group_id").toString());
            }
            if (bBundle.containsKey("client_country")) {
                this.wartosciServera.put("client_country", bBundle.get("client_country").toString());
            }
            if (bBundle.containsKey("client_database_id")) {
                this.wartosciServera.put("client_database_id", bBundle.get("client_database_id").toString());
            }
            if (bBundle.containsKey("client_nickname")) {
                this.wartosciServera.put("client_nickname", bBundle.get("client_nickname").toString());
            }
            if (bBundle.containsKey("client_talk_power")) {
                this.wartosciServera.put("client_talk_power", bBundle.get("client_talk_power").toString());
            }
            if (bBundle.containsKey("client_away")) {
                this.wartosciServera.put("client_away", bBundle.get("client_away").toString());
            }
            if (bBundle.containsKey("client_is_muted")) {
                this.wartosciServera.put("client_is_muted", bBundle.get("client_is_muted").toString());
            }
            if (bBundle.containsKey("client_is_channel_commander")) {
                this.wartosciServera.put("client_is_channel_commander", bBundle.get("client_is_channel_commander").toString());
            }
            if (bBundle.containsKey("client_input_hardware")) {
                this.wartosciServera.put("client_input_hardware", bBundle.get("client_input_hardware").toString());
            }
            if (bBundle.containsKey("client_input_muted")) {
                this.wartosciServera.put("client_input_muted", bBundle.get("client_input_muted").toString());
            }
            if (bBundle.containsKey("client_output_hardware")) {
                this.wartosciServera.put("client_output_hardware", bBundle.get("client_output_hardware").toString());
            }
            if (bBundle.containsKey("client_output_muted")) {
                this.wartosciServera.put("client_output_muted", bBundle.get("client_output_muted").toString());
            }
            if (bBundle.containsKey("client_is_priority_speaker")) {
                this.wartosciServera.put("client_is_priority_speaker", bBundle.get("client_is_priority_speaker").toString());
            }
            if (bBundle.containsKey("client_is_recording")) {
                this.wartosciServera.put("client_is_recording", bBundle.get("client_is_recording").toString());
            }
            if (bBundle.containsKey("client_unread_messages")) {
                this.wartosciServera.put("client_unread_messages", bBundle.get("client_unread_messages").toString());
            }
            if (bBundle.containsKey("client_servergroups")) {
                this.wartosciServera.put("client_servergroups", bBundle.get("client_servergroups").toString());
            }
            if (bBundle.containsKey("client_talk_request")) {
                this.wartosciServera.put("client_talk_request", bBundle.get("client_talk_request").toString());
            }
            if (bBundle.containsKey("client_talk_request_msg")) {
                this.wartosciServera.put("client_talk_request_msg", bBundle.get("client_talk_request_msg").toString());
            }
            if (bBundle.containsKey("client_description")) {
                this.wartosciServera.put("client_description", bBundle.get("client_description").toString());
            }
            if (bBundle.containsKey("client_volume_modificator")) {
                this.wartosciServera.put("client_volume_modificator", bBundle.get("client_volume_modificator").toString());
            }
            if (bBundle.containsKey("client_channel_name")) {
                this.wartosciServera.put("client_channel_name", bBundle.get("client_channel_name").toString());
            }
            if (bBundle.containsKey("client_unique_identifier")) {
                this.wartosciServera.put("client_unique_identifier", bBundle.get("client_unique_identifier").toString());
            }
            if (bBundle.containsKey("client_type")) {
                this.wartosciServera.put("client_type", bBundle.get("client_type").toString());
            }
            if (bBundle.containsKey("client_nickname_phonetic")) {
                this.wartosciServera.put("client_nickname_phonetic", bBundle.get("client_nickname_phonetic").toString());
            }
            if (bBundle.containsKey("client_meta_data")) {
                this.wartosciServera.put("client_meta_data", bBundle.get("client_meta_data").toString());
            }
            if (bBundle.containsKey("client_outputonly_muted")) {
                this.wartosciServera.put("client_outputonly_muted", bBundle.get("client_outputonly_muted").toString());
            }
            if (bBundle.containsKey("client_icon_id")) {
                this.wartosciServera.put("client_icon_id", bBundle.get("client_icon_id").toString());
            }
            if (bBundle.containsKey("client_needed_serverquery_view_power")) {
                this.wartosciServera.put("client_needed_serverquery_view_power", bBundle.get("client_needed_serverquery_view_power").toString());
            }
            if (bBundle.containsKey("client_channel_group_inherited_channel_id")) {
                this.wartosciServera.put("client_channel_group_inherited_channel_id", bBundle.get("client_channel_group_inherited_channel_id").toString());
            }
        }
        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtChannelCountry = (TextView) findViewById(R.id.txtChanelCountry);
        this.listInfo1 = (ListView) findViewById(R.id.listBasic);
        this.listInfo2 = (ListView) findViewById(R.id.listChannelInfo);
        this.listInfo3 = (ListView) findViewById(R.id.listDetails);
        this.listInfo4 = (ListView) findViewById(R.id.listServer);
        txtName.setText((CharSequence) this.wartosciServera.get("client_nickname"));
        txtChannelCountry.setText("Channel: " + ((String) this.wartosciServera.get("client_channel_name")) + "  Country: " + ((String) this.wartosciServera.get("client_country")));
        dane1Uzupelnij("isInputMuted", "client_input_muted");
        dane1Uzupelnij("isOutputMuted", "client_output_muted");
        dane1Uzupelnij("isAway", "client_away");
        dane1Uzupelnij("AwayMessage ", "client_away_message");
        dane1Uzupelnij("isRecording", "client_is_recording");
        dane1Uzupelnij("Description ", "client_description");
        this.adapter_virtuals1 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList1);
        this.listInfo1.setAdapter(this.adapter_virtuals1);
        setListViewHeightBasedOnItems(this.listInfo1);
        dane2Uzupelnij("TalkPower", "client_talk_power");
        dane2Uzupelnij("isChannelCommander", "client_is_channel_commander");
        dane2Uzupelnij("isPrioritySpeaker", "client_is_priority_speaker");
        dane2Uzupelnij("isRequestingToTalk", "client_talk_request");
        dane2Uzupelnij("TalkRequestMessage", "client_talk_request_msg");
        this.adapter_virtuals2 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList2);
        this.listInfo2.setAdapter(this.adapter_virtuals2);
        setListViewHeightBasedOnItems(this.listInfo2);
        dane3Uzupelnij("isInputHardware", "client_input_hardware");
        dane3Uzupelnij("isOutputHardware", "client_output_hardware");
        dane3Uzupelnij("canTalk", "client_is_talker");
        dane3Uzupelnij("isMuted", "client_is_muted");
        dane3Uzupelnij("OutputOnly Muted", "client_outputonly_muted");
        dane3Uzupelnij("VolumeModificator", "client_volume_modificator");
        dane3Uzupelnij("NicknamePhonetic", "client_nickname_phonetic");
        dane3Uzupelnij("MetaData", "client_meta_data");
        this.adapter_virtuals3 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList3);
        this.listInfo3.setAdapter(this.adapter_virtuals3);
        setListViewHeightBasedOnItems(this.listInfo3);
        dane4Uzupelnij("UniqueId", "client_unique_identifier");
        dane4Uzupelnij("DatabaseId", "client_database_id");
        dane4Uzupelnij("ClientType", "client_type");
        dane4Uzupelnij("IconId", "client_icon_id");
        dane4Uzupelnij("ServerGroups", "client_servergroups");
        dane4Uzupelnij("ChannelGroupId", "client_channel_group_id");
        dane4Uzupelnij("ServerQuery View Power", "client_needed_serverquery_view_power");
        dane4Uzupelnij("ChannelGroup Inherited Id", "client_channel_group_inherited_channel_id");
        dane4Uzupelnij("UnreadMessages", "client_unread_messages");
        this.adapter_virtuals4 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList4);
        this.listInfo4.setAdapter(this.adapter_virtuals4);
        setListViewHeightBasedOnItems(this.listInfo4);
    }

    void dane1Uzupelnij(String _id, String _details) {
        this.dane1 = new ServerInfo();
        this.dane1.id = _id;
        this.dane1.details = (String) this.wartosciServera.get(_details);
        this.daneList1.add(this.dane1);
    }

    void dane2Uzupelnij(String _id, String _details) {
        this.dane2 = new ServerInfo();
        this.dane2.id = _id;
        this.dane2.details = (String) this.wartosciServera.get(_details);
        this.daneList2.add(this.dane2);
    }

    void dane3Uzupelnij(String _id, String _details) {
        this.dane3 = new ServerInfo();
        this.dane3.id = _id;
        this.dane3.details = (String) this.wartosciServera.get(_details);
        this.daneList3.add(this.dane3);
    }

    void dane4Uzupelnij(String _id, String _details) {
        this.dane4 = new ServerInfo();
        this.dane4.id = _id;
        this.dane4.details = (String) this.wartosciServera.get(_details);
        this.daneList4.add(this.dane4);
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return false;
        }
        int numberOfItems = listAdapter.getCount();
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);
        LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
        return true;
    }

    protected void onResume() {
        super.onResume();
        System.gc();
    }

    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
        System.gc();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.listInfo1.setAdapter(null);
        this.listInfo1 = null;
        this.listInfo2.setAdapter(null);
        this.listInfo2 = null;
        this.listInfo3.setAdapter(null);
        this.listInfo3 = null;
        this.listInfo4.setAdapter(null);
        this.listInfo4 = null;
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }
}
