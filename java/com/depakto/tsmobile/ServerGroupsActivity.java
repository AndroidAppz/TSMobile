package com.depakto.tsmobile;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.depakto.classes.adapters.ClientGroupsChannelListArrayAdapter;
import com.depakto.classes.adapters.ClientGroupsListArrayAdapter;
import com.depakto.classes.help.TSApiGroups;
import com.depakto.classes.help.TSConfig;
import com.depakto.classes.help.TSQuery.FloodRate;
import com.depakto.classes.help.TSQueryGroups;
import com.depakto.classes.help.api.PermissionGroupDatabaseType;
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
import com.depakto.classes.help.api.event.TSEventType;
import com.depakto.classes.help.api.event.TSListener;
import com.depakto.classes.help.api.event.TextMessageEvent;
import com.depakto.classes.help.api.wrapper.ChannelGroup;
import com.depakto.classes.help.api.wrapper.Permission;
import com.depakto.classes.help.api.wrapper.ServerGroup;
import com.depakto.classes.help.commands.response.DefaultArrayResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ServerGroupsActivity extends AppCompatActivity {
    public ArrayAdapter<String> adapterListServer;
    public ArrayAdapter<String> adapterListServerChannel;
    public TSApiGroups api2;
    private List<HashMap<String, String>> arrayGroups;
    private List<HashMap<String, String>> arrayGroupsChannel;
    private List<String> arrayNameServerPerm = new ArrayList();
    private List<HashMap<String, String>> arrayServerGroupPerm;
    private CheckBox checkBox_2;
    private CheckBox checkBox_2channel;
    private CheckBox checkBox_3;
    private CheckBox checkBox_3a;
    private CheckBox checkBox_3b;
    private CheckBox checkBox_3c;
    private CheckBox checkBox_3d;
    private CheckBox checkBox_4;
    private CheckBox checkBox_4a;
    private CheckBox checkBox_4b;
    private CheckBox checkBox_4c;
    private CheckBox checkBox_5;
    private CheckBox checkBox_5a;
    private CheckBox checkBox_5achannel;
    private CheckBox checkBox_5channel;
    private CheckBox checkBox_6;
    private CheckBox checkBox_6channel;
    private CheckBox checkBox_6e;
    private CheckBox checkBox_6echannel;
    private CheckBox checkBox_6f;
    private CheckBox checkBox_6fchannel;
    private CheckBox checkBox_7;
    private CheckBox checkBox_7c;
    private CheckBox checkBox_7cchannel;
    private CheckBox checkBox_7channel;
    private CheckBox checkBox_8;
    private CheckBox checkBox_8b;
    private CheckBox checkBox_8bchannel;
    private CheckBox checkBox_8c;
    private CheckBox checkBox_8cchannel;
    private CheckBox checkBox_8channel;
    private CheckBox checkBox_8d;
    private CheckBox checkBox_8dchannel;
    private CheckBox checkBox_8e;
    private CheckBox checkBox_8echannel;
    private CheckBox checkBox_9;
    private CheckBox checkBox_9a;
    private CheckBox checkBox_9achannel;
    private CheckBox checkBox_9b;
    private CheckBox checkBox_9bchannel;
    private CheckBox checkBox_9c;
    private CheckBox checkBox_9cchannel;
    private CheckBox checkBox_9channel;
    public TSConfig config2 = new TSConfig();
    private boolean connectedAPI = false;
    public ExecutorService executor2;
    public String hostIP = "192.168.0.1";
    public String last_error_query;
    private RelativeLayout lay_channelGroups;
    private RelativeLayout lay_serverGroups;
    private ArrayList<ChannelGroup> listChannel = new ArrayList();
    private ArrayList<ServerGroup> listServer = new ArrayList();
    public ListView listViewServer;
    public ListView listViewServerChannel;
    public TSQueryGroups query2;
    private Spinner spinner_2a;
    private Spinner spinner_2achannel;
    private Spinner spinner_5a;
    private Spinner spinner_5achannel;
    private Spinner spinner_6a;
    private Spinner spinner_6achannel;
    private Spinner spinner_6b;
    private Spinner spinner_6bchannel;
    private Spinner spinner_6c;
    private Spinner spinner_6cchannel;
    private Spinner spinner_6d;
    private Spinner spinner_6dchannel;
    private Spinner spinner_7a;
    private Spinner spinner_7achannel;
    private Spinner spinner_7b;
    private Spinner spinner_7bchannel;
    private Spinner spinner_8a;
    private Spinner spinner_8achannel;
    private Toolbar toolbar;
    private TextView txtTitleChannel;
    private TextView txtTitleServer;
    private TextView txt_info_groups;
    private boolean wait_for_servergroupperm = false;
    private int zmSpinner2a;
    private int zmSpinner5a;
    private int zmSpinner6a;
    private int zmSpinner6b;
    private int zmSpinner6c;
    private int zmSpinner6d;
    private int zmSpinner7a;
    private int zmSpinner7b;
    private int zmSpinner8a;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_groups);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.toolbar.setVisibility(8);
        Bundle bBundle = getIntent().getExtras();
        if (bBundle != null) {
            if (bBundle.containsKey("ipSelected")) {
                this.hostIP = (String) bBundle.get("ipSelected");
            }
            if (bBundle.containsKey("sGroups")) {
                this.arrayGroups = new DefaultArrayResponse((String) bBundle.get("sGroups")).getArray();
                for (HashMap<String, String> m : this.arrayGroups) {
                    ServerGroup zm = new ServerGroup(m);
                    if (zm.getType() == PermissionGroupDatabaseType.REGULAR) {
                        this.listServer.add(zm);
                    }
                }
            }
            if (bBundle.containsKey("sChannelGroups")) {
                this.arrayGroupsChannel = new DefaultArrayResponse((String) bBundle.get("sChannelGroups")).getArray();
                for (HashMap<String, String> m2 : this.arrayGroupsChannel) {
                    ChannelGroup zmC = new ChannelGroup(m2);
                    if (zmC.getType() == PermissionGroupDatabaseType.REGULAR) {
                        this.listChannel.add(zmC);
                    }
                }
            }
        }
        this.listViewServer = (ListView) findViewById(R.id.listViewServer);
        this.adapterListServer = new ClientGroupsListArrayAdapter(this, this.listServer);
        this.listViewServer.setAdapter(this.adapterListServer);
        setListViewHeightBasedOnItems(this.listViewServer);
        this.listViewServerChannel = (ListView) findViewById(R.id.listViewChannel);
        this.adapterListServerChannel = new ClientGroupsChannelListArrayAdapter(this, this.listChannel);
        this.listViewServerChannel.setAdapter(this.adapterListServerChannel);
        setListViewHeightBasedOnItems(this.listViewServerChannel);
        this.txt_info_groups = (TextView) findViewById(R.id.txt_info_groups);
        this.lay_serverGroups = (RelativeLayout) findViewById(R.id.lay_serverGroups);
        this.lay_channelGroups = (RelativeLayout) findViewById(R.id.lay_channelGroups);
        this.config2.setHost(this.hostIP);
        this.config2.setFloodRate(FloodRate.DEFAULT);
        this.config2.setDebugToFile(false);
        this.config2.setDebugLevel(Level.OFF);
        this.listViewServer.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (ServerGroupsActivity.this.connectedAPI) {
                    try {
                        ServerGroupsActivity.this.txt_info_groups.setVisibility(8);
                        ServerGroupsActivity.this.lay_channelGroups.setVisibility(8);
                        ServerGroupsActivity.this.lay_serverGroups.setVisibility(0);
                        ServerGroupsActivity.this.txtTitleServer.setText("Server Group options for: " + ((ServerGroup) ServerGroupsActivity.this.listServer.get(position)).getName());
                        ServerGroupsActivity.this.wait_for_servergroupperm = true;
                        ServerGroupsActivity.this.api2.getServerGroupPermissions(((ServerGroup) ServerGroupsActivity.this.listServer.get(position)).getId());
                        if (!ServerGroupsActivity.this.last_error_query.contains("ok")) {
                            Snackbar.make(view, BuildConfig.FLAVOR + ServerGroupsActivity.this.last_error_query.toString(), -1).setAction("Action", null).show();
                            ServerGroupsActivity.this.wait_for_servergroupperm = false;
                            return;
                        }
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                Snackbar.make(view, "Wait few seconds to get all information and try again", -1).setAction("Action", null).show();
            }
        });
        this.listViewServerChannel.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (ServerGroupsActivity.this.connectedAPI) {
                    try {
                        ServerGroupsActivity.this.txt_info_groups.setVisibility(8);
                        ServerGroupsActivity.this.lay_serverGroups.setVisibility(8);
                        ServerGroupsActivity.this.lay_channelGroups.setVisibility(0);
                        ServerGroupsActivity.this.txtTitleChannel.setText("Channel Group options for: " + ((ChannelGroup) ServerGroupsActivity.this.listChannel.get(position)).getName());
                        ServerGroupsActivity.this.wait_for_servergroupperm = true;
                        ServerGroupsActivity.this.api2.getChannelGroupPermissions(((ChannelGroup) ServerGroupsActivity.this.listChannel.get(position)).getGroupId());
                        if (!ServerGroupsActivity.this.last_error_query.contains("ok")) {
                            Snackbar.make(view, BuildConfig.FLAVOR + ServerGroupsActivity.this.last_error_query.toString(), -1).setAction("Action", null).show();
                            ServerGroupsActivity.this.wait_for_servergroupperm = false;
                            return;
                        }
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                Snackbar.make(view, "Wait few seconds to get all information and try again", -1).setAction("Action", null).show();
            }
        });
        this.txtTitleServer = (TextView) findViewById(R.id.textView23);
        this.txtTitleChannel = (TextView) findViewById(R.id.textView23channel);
        this.checkBox_2 = (CheckBox) findViewById(R.id.checkBox_2);
        this.checkBox_3 = (CheckBox) findViewById(R.id.checkBox_3);
        this.checkBox_3a = (CheckBox) findViewById(R.id.checkBox_3a);
        this.checkBox_3b = (CheckBox) findViewById(R.id.checkBox_3b);
        this.checkBox_3c = (CheckBox) findViewById(R.id.checkBox_3c);
        this.checkBox_3d = (CheckBox) findViewById(R.id.checkBox_3d);
        this.checkBox_4 = (CheckBox) findViewById(R.id.checkBox_4);
        this.checkBox_4a = (CheckBox) findViewById(R.id.checkBox_4a);
        this.checkBox_4b = (CheckBox) findViewById(R.id.checkBox_4b);
        this.checkBox_4c = (CheckBox) findViewById(R.id.checkBox_4c);
        this.checkBox_5 = (CheckBox) findViewById(R.id.checkBox_5);
        this.checkBox_5a = (CheckBox) findViewById(R.id.checkBox_5a);
        this.checkBox_6 = (CheckBox) findViewById(R.id.checkBox_6);
        this.checkBox_6e = (CheckBox) findViewById(R.id.checkBox_6e);
        this.checkBox_6f = (CheckBox) findViewById(R.id.checkBox_6f);
        this.checkBox_7 = (CheckBox) findViewById(R.id.checkBox_7);
        this.checkBox_7c = (CheckBox) findViewById(R.id.checkBox_7c);
        this.checkBox_8 = (CheckBox) findViewById(R.id.checkBox_8);
        this.checkBox_8b = (CheckBox) findViewById(R.id.checkBox_8b);
        this.checkBox_8c = (CheckBox) findViewById(R.id.checkBox_8c);
        this.checkBox_8d = (CheckBox) findViewById(R.id.checkBox_8d);
        this.checkBox_8e = (CheckBox) findViewById(R.id.checkBox_8e);
        this.checkBox_9 = (CheckBox) findViewById(R.id.checkBox_9);
        this.checkBox_9a = (CheckBox) findViewById(R.id.checkBox_9a);
        this.checkBox_9b = (CheckBox) findViewById(R.id.checkBox_9b);
        this.checkBox_9c = (CheckBox) findViewById(R.id.checkBox_9c);
        this.spinner_2a = (Spinner) findViewById(R.id.spinner_2a);
        this.spinner_2achannel = (Spinner) findViewById(R.id.spinner_2achannel);
        this.spinner_5achannel = (Spinner) findViewById(R.id.spinner_5achannel);
        this.spinner_6achannel = (Spinner) findViewById(R.id.spinner_6achannel);
        this.spinner_6bchannel = (Spinner) findViewById(R.id.spinner_6bchannel);
        this.spinner_6cchannel = (Spinner) findViewById(R.id.spinner_6cchannel);
        this.spinner_6dchannel = (Spinner) findViewById(R.id.spinner_6dchannel);
        this.spinner_7achannel = (Spinner) findViewById(R.id.spinner_7achannel);
        this.spinner_7bchannel = (Spinner) findViewById(R.id.spinner_7bchannel);
        this.spinner_8achannel = (Spinner) findViewById(R.id.spinner_8achannel);
        ArrayAdapter<CharSequence> adapterSpinner2a = ArrayAdapter.createFromResource(this, R.array.server_show_name, 17367048);
        adapterSpinner2a.setDropDownViewResource(17367049);
        this.spinner_2a.setAdapter(adapterSpinner2a);
        this.spinner_2a.setSelection(0);
        this.spinner_2a.setEnabled(false);
        this.spinner_2achannel.setAdapter(adapterSpinner2a);
        this.spinner_2achannel.setSelection(0);
        this.spinner_2achannel.setEnabled(false);
        this.spinner_5a = (Spinner) findViewById(R.id.spinner_5a);
        ArrayAdapter<CharSequence> adapterSpinner5a = ArrayAdapter.createFromResource(this, R.array.server_add_user, 17367048);
        adapterSpinner5a.setDropDownViewResource(17367049);
        this.spinner_5a.setAdapter(adapterSpinner5a);
        this.spinner_5a.setSelection(0);
        this.spinner_5a.setEnabled(false);
        ArrayAdapter<CharSequence> adapterSpinner5achannel = ArrayAdapter.createFromResource(this, R.array.channel_add_user, 17367048);
        adapterSpinner5achannel.setDropDownViewResource(17367049);
        this.spinner_5achannel.setAdapter(adapterSpinner5achannel);
        this.spinner_5achannel.setSelection(0);
        this.spinner_5achannel.setEnabled(false);
        this.spinner_6a = (Spinner) findViewById(R.id.spinner_6a);
        this.spinner_6b = (Spinner) findViewById(R.id.spinner_6b);
        this.spinner_6c = (Spinner) findViewById(R.id.spinner_6c);
        this.spinner_6d = (Spinner) findViewById(R.id.spinner_6d);
        ArrayAdapter<CharSequence> adapterSpinner6 = ArrayAdapter.createFromResource(this, R.array.server_administrate_clients, 17367048);
        adapterSpinner6.setDropDownViewResource(17367049);
        this.spinner_6a.setAdapter(adapterSpinner6);
        this.spinner_6b.setAdapter(adapterSpinner6);
        this.spinner_6c.setAdapter(adapterSpinner6);
        this.spinner_6d.setAdapter(adapterSpinner6);
        this.spinner_6achannel.setAdapter(adapterSpinner6);
        this.spinner_6bchannel.setAdapter(adapterSpinner6);
        this.spinner_6cchannel.setAdapter(adapterSpinner6);
        this.spinner_6dchannel.setAdapter(adapterSpinner6);
        this.spinner_6a.setSelection(0);
        this.spinner_6b.setSelection(0);
        this.spinner_6c.setSelection(0);
        this.spinner_6d.setSelection(0);
        this.spinner_6achannel.setSelection(0);
        this.spinner_6bchannel.setSelection(0);
        this.spinner_6cchannel.setSelection(0);
        this.spinner_6dchannel.setSelection(0);
        this.spinner_6a.setEnabled(false);
        this.spinner_6b.setEnabled(false);
        this.spinner_6c.setEnabled(false);
        this.spinner_6d.setEnabled(false);
        this.spinner_6achannel.setEnabled(false);
        this.spinner_6bchannel.setEnabled(false);
        this.spinner_6cchannel.setEnabled(false);
        this.spinner_6dchannel.setEnabled(false);
        this.spinner_7a = (Spinner) findViewById(R.id.spinner_7a);
        this.spinner_7b = (Spinner) findViewById(R.id.spinner_7b);
        ArrayAdapter<CharSequence> adapterSpinner7 = ArrayAdapter.createFromResource(this, R.array.server_manage_server, 17367048);
        adapterSpinner7.setDropDownViewResource(17367049);
        this.spinner_7a.setAdapter(adapterSpinner7);
        this.spinner_7b.setAdapter(adapterSpinner7);
        this.spinner_7achannel.setAdapter(adapterSpinner7);
        this.spinner_7bchannel.setAdapter(adapterSpinner7);
        this.spinner_7a.setSelection(3);
        this.spinner_7b.setSelection(3);
        this.spinner_7a.setEnabled(false);
        this.spinner_7b.setEnabled(false);
        this.spinner_7achannel.setSelection(3);
        this.spinner_7bchannel.setSelection(3);
        this.spinner_7achannel.setEnabled(false);
        this.spinner_7bchannel.setEnabled(false);
        this.spinner_8a = (Spinner) findViewById(R.id.spinner_8a);
        ArrayAdapter<CharSequence> adapterSpinner8 = ArrayAdapter.createFromResource(this, R.array.server_basic, 17367048);
        adapterSpinner8.setDropDownViewResource(17367049);
        this.spinner_8a.setAdapter(adapterSpinner8);
        this.spinner_8a.setSelection(2);
        this.spinner_8a.setEnabled(false);
        this.spinner_8achannel.setAdapter(adapterSpinner8);
        this.spinner_8achannel.setSelection(2);
        this.spinner_8achannel.setEnabled(false);
        this.checkBox_2channel = (CheckBox) findViewById(R.id.checkBox_2channel);
        this.checkBox_5channel = (CheckBox) findViewById(R.id.checkBox_5channel);
        this.checkBox_5achannel = (CheckBox) findViewById(R.id.checkBox_5achannel);
        this.checkBox_6channel = (CheckBox) findViewById(R.id.checkBox_6channel);
        this.checkBox_6echannel = (CheckBox) findViewById(R.id.checkBox_6echannel);
        this.checkBox_6fchannel = (CheckBox) findViewById(R.id.checkBox_6fchannel);
        this.checkBox_7channel = (CheckBox) findViewById(R.id.checkBox_7channel);
        this.checkBox_7cchannel = (CheckBox) findViewById(R.id.checkBox_7cchannel);
        this.checkBox_8channel = (CheckBox) findViewById(R.id.checkBox_8channel);
        this.checkBox_8bchannel = (CheckBox) findViewById(R.id.checkBox_8bchannel);
        this.checkBox_8cchannel = (CheckBox) findViewById(R.id.checkBox_8cchannel);
        this.checkBox_8dchannel = (CheckBox) findViewById(R.id.checkBox_8dchannel);
        this.checkBox_8echannel = (CheckBox) findViewById(R.id.checkBox_8echannel);
        this.checkBox_9channel = (CheckBox) findViewById(R.id.checkBox_9channel);
        this.checkBox_9achannel = (CheckBox) findViewById(R.id.checkBox_9achannel);
        this.checkBox_9bchannel = (CheckBox) findViewById(R.id.checkBox_9bchannel);
        this.checkBox_9cchannel = (CheckBox) findViewById(R.id.checkBox_9cchannel);
        this.executor2 = Executors.newFixedThreadPool(100);
        this.executor2.execute(testConnection());
    }

    private Runnable testConnection() {
        return new Runnable() {
            public void run() {
                ServerGroupsActivity.this.query2 = new TSQueryGroups(ServerGroupsActivity.this.config2);
                try {
                    ServerGroupsActivity.this.query2.connect(ServerGroupsActivity.this);
                    ServerGroupsActivity.this.api2 = ServerGroupsActivity.this.query2.getApi();
                } catch (Exception es) {
                    es.printStackTrace();
                }
                int zm = -1;
                try {
                    ServerGroupsActivity.this.api2.whoAmI();
                    zm = ServerGroupsActivity.this.api2.whoAmI().getCLID();
                } catch (Exception e) {
                }
                if (zm > -1 || ServerGroupsActivity.this.last_error_query == "ok") {
                    ServerGroupsActivity.this.api2.addTSListeners(new TSListener() {
                        public void onTextMessage(TextMessageEvent e) {
                        }

                        public void onClientJoin(ClientJoinEvent e) {
                        }

                        public void onClientLeave(ClientLeaveEvent e) {
                        }

                        public void onServerEdit(ServerEditedEvent e) {
                        }

                        public void onChannelEdit(ChannelEditedEvent e) {
                        }

                        public void onChannelDelete(ChannelDeletedEvent e) {
                        }

                        public void onChannelCreate(ChannelCreatedEvent e) {
                        }

                        public void onChannelMove(ChannelMovedEvent e) {
                        }

                        public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent e) {
                        }

                        public void onClientMoved(ClientMovedEvent e) {
                        }

                        public void onClientUpdate(ClientUpdatedEvent e) {
                        }

                        public void onClientTalkStatus(ClientTalkStatus e) {
                        }

                        public void onClientPoke(ClientPokeEvent e) {
                        }

                        public void onServerGroupList(ServerGroupEvent e) {
                        }

                        public void onServerChannelGroupList(ServerChannelGroupEvent e) {
                        }

                        public void onServerPermissionList(ServerPermissionEvent e) {
                        }

                        public void onServerGroupPermList(ServerGroupPermEvent e) {
                            if (ServerGroupsActivity.this.wait_for_servergroupperm) {
                                ServerGroupsActivity.this.cleanAllcheckBoxes();
                                ServerGroupsActivity.this.wait_for_servergroupperm = false;
                                ServerGroupsActivity.this.arrayNameServerPerm.clear();
                                ServerGroupsActivity.this.zmSpinner2a = 0;
                                ServerGroupsActivity.this.zmSpinner5a = 0;
                                ServerGroupsActivity.this.zmSpinner6a = 0;
                                ServerGroupsActivity.this.zmSpinner6b = 0;
                                ServerGroupsActivity.this.zmSpinner6c = 0;
                                ServerGroupsActivity.this.zmSpinner6d = 0;
                                ServerGroupsActivity.this.zmSpinner7a = -1;
                                ServerGroupsActivity.this.zmSpinner7b = -1;
                                ServerGroupsActivity.this.zmSpinner8a = -1;
                                ServerGroupsActivity.this.arrayServerGroupPerm = new DefaultArrayResponse(e.get("servergrouppermlist")).getArray();
                                for (HashMap<String, String> m : ServerGroupsActivity.this.arrayServerGroupPerm) {
                                    Permission zmPermServ = new Permission(m);
                                    String idServPerm = zmPermServ.getName();
                                    if (idServPerm.contains("178") || idServPerm.contains("42") || idServPerm.contains("50") || idServPerm.contains("183") || idServPerm.contains("29") || idServPerm.contains("159") || idServPerm.contains("143") || idServPerm.contains("151") || idServPerm.contains("36") || idServPerm.contains("202") || idServPerm.contains("198") || idServPerm.contains("196") || idServPerm.contains("200") || idServPerm.contains("226") || idServPerm.contains("223") || idServPerm.contains("111") || idServPerm.contains("175") || idServPerm.contains("176") || idServPerm.contains("184") || idServPerm.contains("32945") || idServPerm.contains("243") || idServPerm.contains("246") || idServPerm.contains("245") || idServPerm.contains("163") || idServPerm.contains("202") || idServPerm.contains("200") || idServPerm.contains("198") || idServPerm.contains("196") || idServPerm.contains("100") || idServPerm.contains("102") || idServPerm.contains("84") || idServPerm.contains("83") || idServPerm.contains("127") || idServPerm.contains("126") || idServPerm.contains("125") || idServPerm.contains("216") || idServPerm.contains("217")) {
                                        ServerGroupsActivity.this.arrayNameServerPerm.add(idServPerm);
                                    }
                                    if (zmPermServ.getName().contains("149")) {
                                        ServerGroupsActivity.this.zmSpinner2a = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("163")) {
                                        ServerGroupsActivity.this.zmSpinner5a = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("202")) {
                                        ServerGroupsActivity.this.zmSpinner6a = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("198")) {
                                        ServerGroupsActivity.this.zmSpinner6b = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("196")) {
                                        ServerGroupsActivity.this.zmSpinner6c = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("200")) {
                                        ServerGroupsActivity.this.zmSpinner6d = zmPermServ.getValue();
                                    }
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("100") || ServerGroupsActivity.this.arrayNameServerPerm.contains("102")) {
                                    ServerGroupsActivity.this.zmSpinner7a = 2;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("84")) {
                                    ServerGroupsActivity.this.zmSpinner7a = 1;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("83")) {
                                    ServerGroupsActivity.this.zmSpinner7a = 0;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("127")) {
                                    ServerGroupsActivity.this.zmSpinner7b = 2;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("126")) {
                                    ServerGroupsActivity.this.zmSpinner7b = 1;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("125")) {
                                    ServerGroupsActivity.this.zmSpinner7b = 0;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("217")) {
                                    ServerGroupsActivity.this.zmSpinner8a = 1;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("216")) {
                                    ServerGroupsActivity.this.zmSpinner8a = 0;
                                }
                            }
                            ServerGroupsActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    for (String s : ServerGroupsActivity.this.arrayNameServerPerm) {
                                        boolean z = true;
                                        switch (s.hashCode()) {
                                            case 1607:
                                                if (s.equals("29")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 1635:
                                                if (s.equals("36")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 1662:
                                                if (s.equals("42")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 1691:
                                                if (s.equals("50")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48657:
                                                if (s.equals("111")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48752:
                                                if (s.equals("143")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48781:
                                                if (s.equals("151")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48789:
                                                if (s.equals("159")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48814:
                                                if (s.equals("163")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48847:
                                                if (s.equals("175")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48848:
                                                if (s.equals("176")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48850:
                                                if (s.equals("178")) {
                                                    z = false;
                                                    break;
                                                }
                                                break;
                                            case 48876:
                                                if (s.equals("183")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48877:
                                                if (s.equals("184")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48910:
                                                if (s.equals("196")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48912:
                                                if (s.equals("198")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49586:
                                                if (s.equals("200")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49588:
                                                if (s.equals("202")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49651:
                                                if (s.equals("223")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49654:
                                                if (s.equals("226")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49713:
                                                if (s.equals("243")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49715:
                                                if (s.equals("245")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49716:
                                                if (s.equals("246")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48645563:
                                                if (s.equals("32945")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                        }
                                        switch (z) {
                                            case R.styleable.View_android_theme /*0*/:
                                                ServerGroupsActivity.this.checkBox_2.setChecked(true);
                                                break;
                                            case R.styleable.View_android_focusable /*1*/:
                                                ServerGroupsActivity.this.checkBox_3a.setChecked(true);
                                                break;
                                            case R.styleable.View_paddingStart /*2*/:
                                                ServerGroupsActivity.this.checkBox_3b.setChecked(true);
                                                break;
                                            case R.styleable.View_paddingEnd /*3*/:
                                                ServerGroupsActivity.this.checkBox_3c.setChecked(true);
                                                break;
                                            case R.styleable.View_theme /*4*/:
                                                ServerGroupsActivity.this.checkBox_3d.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetStart /*5*/:
                                                ServerGroupsActivity.this.checkBox_4a.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetEnd /*6*/:
                                                ServerGroupsActivity.this.checkBox_4b.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                                                ServerGroupsActivity.this.checkBox_4c.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetRight /*8*/:
                                                ServerGroupsActivity.this.checkBox_5a.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                                                ServerGroupsActivity.this.checkBox_6e.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                                                ServerGroupsActivity.this.checkBox_6f.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_popupTheme /*11*/:
                                                ServerGroupsActivity.this.checkBox_7c.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleTextAppearance /*12*/:
                                                ServerGroupsActivity.this.checkBox_8b.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                                                ServerGroupsActivity.this.checkBox_8c.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMargin /*14*/:
                                                ServerGroupsActivity.this.checkBox_8d.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMarginStart /*15*/:
                                                ServerGroupsActivity.this.checkBox_8e.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMarginEnd /*16*/:
                                                ServerGroupsActivity.this.checkBox_9a.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMarginTop /*17*/:
                                                ServerGroupsActivity.this.checkBox_9b.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMarginBottom /*18*/:
                                                ServerGroupsActivity.this.checkBox_9c.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMargins /*19*/:
                                                ServerGroupsActivity.this.checkBox_5.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_maxButtonHeight /*20*/:
                                                ServerGroupsActivity.this.checkBox_6.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_buttonGravity /*21*/:
                                                ServerGroupsActivity.this.checkBox_6.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_collapseIcon /*22*/:
                                                ServerGroupsActivity.this.checkBox_6.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_collapseContentDescription /*23*/:
                                                ServerGroupsActivity.this.checkBox_6.setChecked(true);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.checkBox_3a.isChecked() || ServerGroupsActivity.this.checkBox_3b.isChecked() || ServerGroupsActivity.this.checkBox_3c.isChecked() || ServerGroupsActivity.this.checkBox_3d.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_3.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_4a.isChecked() || ServerGroupsActivity.this.checkBox_4b.isChecked() || ServerGroupsActivity.this.checkBox_4c.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_4.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_5a.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_5.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_6e.isChecked() || ServerGroupsActivity.this.checkBox_6f.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_6.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_7c.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_7.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_8b.isChecked() || ServerGroupsActivity.this.checkBox_8c.isChecked() || ServerGroupsActivity.this.checkBox_8d.isChecked() || ServerGroupsActivity.this.checkBox_8e.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_8.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_9a.isChecked() || ServerGroupsActivity.this.checkBox_9b.isChecked() || ServerGroupsActivity.this.checkBox_9c.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_9.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner2a > 0) {
                                        ServerGroupsActivity.this.spinner_2a.setSelection(ServerGroupsActivity.this.zmSpinner2a);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner5a > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner5a) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_5a.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_actionModeSplitBackground /*30*/:
                                                ServerGroupsActivity.this.spinner_5a.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_actionButtonStyle /*50*/:
                                                ServerGroupsActivity.this.spinner_5a.setSelection(3);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_5a.setSelection(4);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_5a.setSelection(5);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6a > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6a) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6a.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6a.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6a.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6b > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6b) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6b.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6b.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6b.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6c > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6c) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6c.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6c.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6c.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6d > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6d) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6d.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6d.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6d.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner7a >= 0) {
                                        ServerGroupsActivity.this.checkBox_7.setChecked(true);
                                        ServerGroupsActivity.this.spinner_7a.setSelection(ServerGroupsActivity.this.zmSpinner7a);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner7b >= 0) {
                                        ServerGroupsActivity.this.checkBox_7.setChecked(true);
                                        ServerGroupsActivity.this.spinner_7b.setSelection(ServerGroupsActivity.this.zmSpinner7b);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner8a >= 0) {
                                        ServerGroupsActivity.this.checkBox_8.setChecked(true);
                                        ServerGroupsActivity.this.spinner_8a.setSelection(ServerGroupsActivity.this.zmSpinner8a);
                                    }
                                }
                            });
                        }

                        public void onServerGroupChannelPermList(ServerGroupChannelPermEvent e) {
                            if (ServerGroupsActivity.this.wait_for_servergroupperm) {
                                ServerGroupsActivity.this.cleanAllcheckBoxesChannel();
                                ServerGroupsActivity.this.wait_for_servergroupperm = false;
                                ServerGroupsActivity.this.arrayNameServerPerm.clear();
                                ServerGroupsActivity.this.zmSpinner2a = 0;
                                ServerGroupsActivity.this.zmSpinner5a = 0;
                                ServerGroupsActivity.this.zmSpinner6a = 0;
                                ServerGroupsActivity.this.zmSpinner6b = 0;
                                ServerGroupsActivity.this.zmSpinner6c = 0;
                                ServerGroupsActivity.this.zmSpinner6d = 0;
                                ServerGroupsActivity.this.zmSpinner7a = -1;
                                ServerGroupsActivity.this.zmSpinner7b = -1;
                                ServerGroupsActivity.this.zmSpinner8a = -1;
                                ServerGroupsActivity.this.arrayServerGroupPerm = new DefaultArrayResponse(e.get("servergroupchannelpermlist")).getArray();
                                for (HashMap<String, String> m : ServerGroupsActivity.this.arrayServerGroupPerm) {
                                    Permission zmPermServ = new Permission(m);
                                    String idServPerm = zmPermServ.getName();
                                    if (idServPerm.contains("178") || idServPerm.contains("42") || idServPerm.contains("50") || idServPerm.contains("183") || idServPerm.contains("29") || idServPerm.contains("159") || idServPerm.contains("143") || idServPerm.contains("151") || idServPerm.contains("36") || idServPerm.contains("202") || idServPerm.contains("198") || idServPerm.contains("196") || idServPerm.contains("200") || idServPerm.contains("226") || idServPerm.contains("223") || idServPerm.contains("111") || idServPerm.contains("175") || idServPerm.contains("176") || idServPerm.contains("184") || idServPerm.contains("32945") || idServPerm.contains("243") || idServPerm.contains("246") || idServPerm.contains("245") || idServPerm.contains("163") || idServPerm.contains("202") || idServPerm.contains("200") || idServPerm.contains("198") || idServPerm.contains("196") || idServPerm.contains("100") || idServPerm.contains("102") || idServPerm.contains("84") || idServPerm.contains("83") || idServPerm.contains("127") || idServPerm.contains("126") || idServPerm.contains("125") || idServPerm.contains("216") || idServPerm.contains("217")) {
                                        ServerGroupsActivity.this.arrayNameServerPerm.add(idServPerm);
                                    }
                                    if (zmPermServ.getName().contains("149")) {
                                        ServerGroupsActivity.this.zmSpinner2a = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("163")) {
                                        ServerGroupsActivity.this.zmSpinner5a = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("202")) {
                                        ServerGroupsActivity.this.zmSpinner6a = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("198")) {
                                        ServerGroupsActivity.this.zmSpinner6b = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("196")) {
                                        ServerGroupsActivity.this.zmSpinner6c = zmPermServ.getValue();
                                    }
                                    if (zmPermServ.getName().contains("200")) {
                                        ServerGroupsActivity.this.zmSpinner6d = zmPermServ.getValue();
                                    }
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("100") || ServerGroupsActivity.this.arrayNameServerPerm.contains("102")) {
                                    ServerGroupsActivity.this.zmSpinner7a = 2;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("84")) {
                                    ServerGroupsActivity.this.zmSpinner7a = 1;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("83")) {
                                    ServerGroupsActivity.this.zmSpinner7a = 0;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("127")) {
                                    ServerGroupsActivity.this.zmSpinner7b = 2;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("126")) {
                                    ServerGroupsActivity.this.zmSpinner7b = 1;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("125")) {
                                    ServerGroupsActivity.this.zmSpinner7b = 0;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("217")) {
                                    ServerGroupsActivity.this.zmSpinner8a = 1;
                                }
                                if (ServerGroupsActivity.this.arrayNameServerPerm.contains("216")) {
                                    ServerGroupsActivity.this.zmSpinner8a = 0;
                                }
                            }
                            ServerGroupsActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    for (String s : ServerGroupsActivity.this.arrayNameServerPerm) {
                                        boolean z = true;
                                        switch (s.hashCode()) {
                                            case 1635:
                                                if (s.equals("36")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48657:
                                                if (s.equals("111")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48814:
                                                if (s.equals("163")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48847:
                                                if (s.equals("175")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48848:
                                                if (s.equals("176")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48850:
                                                if (s.equals("178")) {
                                                    z = false;
                                                    break;
                                                }
                                                break;
                                            case 48877:
                                                if (s.equals("184")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48910:
                                                if (s.equals("196")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48912:
                                                if (s.equals("198")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49586:
                                                if (s.equals("200")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49588:
                                                if (s.equals("202")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49651:
                                                if (s.equals("223")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49654:
                                                if (s.equals("226")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49713:
                                                if (s.equals("243")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49715:
                                                if (s.equals("245")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 49716:
                                                if (s.equals("246")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                            case 48645563:
                                                if (s.equals("32945")) {
                                                    z = true;
                                                    break;
                                                }
                                                break;
                                        }
                                        switch (z) {
                                            case R.styleable.View_android_theme /*0*/:
                                                ServerGroupsActivity.this.checkBox_2channel.setChecked(true);
                                                break;
                                            case R.styleable.View_android_focusable /*1*/:
                                                ServerGroupsActivity.this.checkBox_5achannel.setChecked(true);
                                                break;
                                            case R.styleable.View_paddingStart /*2*/:
                                                ServerGroupsActivity.this.checkBox_6echannel.setChecked(true);
                                                break;
                                            case R.styleable.View_paddingEnd /*3*/:
                                                ServerGroupsActivity.this.checkBox_6fchannel.setChecked(true);
                                                break;
                                            case R.styleable.View_theme /*4*/:
                                                ServerGroupsActivity.this.checkBox_7cchannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetStart /*5*/:
                                                ServerGroupsActivity.this.checkBox_8bchannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetEnd /*6*/:
                                                ServerGroupsActivity.this.checkBox_8cchannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                                                ServerGroupsActivity.this.checkBox_8dchannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetRight /*8*/:
                                                ServerGroupsActivity.this.checkBox_8echannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                                                ServerGroupsActivity.this.checkBox_9achannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                                                ServerGroupsActivity.this.checkBox_9bchannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_popupTheme /*11*/:
                                                ServerGroupsActivity.this.checkBox_9cchannel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleTextAppearance /*12*/:
                                                ServerGroupsActivity.this.checkBox_5channel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                                                ServerGroupsActivity.this.checkBox_6channel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMargin /*14*/:
                                                ServerGroupsActivity.this.checkBox_6channel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMarginStart /*15*/:
                                                ServerGroupsActivity.this.checkBox_6channel.setChecked(true);
                                                break;
                                            case R.styleable.Toolbar_titleMarginEnd /*16*/:
                                                ServerGroupsActivity.this.checkBox_6channel.setChecked(true);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.checkBox_5achannel.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_5channel.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_6echannel.isChecked() || ServerGroupsActivity.this.checkBox_6fchannel.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_6channel.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_7cchannel.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_7channel.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_8bchannel.isChecked() || ServerGroupsActivity.this.checkBox_8cchannel.isChecked() || ServerGroupsActivity.this.checkBox_8dchannel.isChecked() || ServerGroupsActivity.this.checkBox_8echannel.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_8channel.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.checkBox_9achannel.isChecked() || ServerGroupsActivity.this.checkBox_9bchannel.isChecked() || ServerGroupsActivity.this.checkBox_9cchannel.isChecked()) {
                                        ServerGroupsActivity.this.checkBox_9channel.setChecked(true);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner2a > 0) {
                                        ServerGroupsActivity.this.spinner_2achannel.setSelection(ServerGroupsActivity.this.zmSpinner2a);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner5a > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner5a) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_5achannel.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_actionModeSplitBackground /*30*/:
                                                ServerGroupsActivity.this.spinner_5achannel.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_actionButtonStyle /*50*/:
                                                ServerGroupsActivity.this.spinner_5achannel.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6a > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6a) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6achannel.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6achannel.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6achannel.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6b > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6b) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6bchannel.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6bchannel.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6bchannel.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6c > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6c) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6cchannel.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6cchannel.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6cchannel.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner6d > 0) {
                                        switch (ServerGroupsActivity.this.zmSpinner6d) {
                                            case R.styleable.Toolbar_navigationContentDescription /*25*/:
                                                ServerGroupsActivity.this.spinner_6dchannel.setSelection(1);
                                                break;
                                            case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle /*60*/:
                                                ServerGroupsActivity.this.spinner_6dchannel.setSelection(2);
                                                break;
                                            case R.styleable.AppCompatTheme_dropDownListViewStyle /*75*/:
                                                ServerGroupsActivity.this.spinner_6dchannel.setSelection(3);
                                                break;
                                        }
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner7a >= 0) {
                                        ServerGroupsActivity.this.checkBox_7channel.setChecked(true);
                                        ServerGroupsActivity.this.spinner_7achannel.setSelection(ServerGroupsActivity.this.zmSpinner7a);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner7b >= 0) {
                                        ServerGroupsActivity.this.checkBox_7channel.setChecked(true);
                                        ServerGroupsActivity.this.spinner_7bchannel.setSelection(ServerGroupsActivity.this.zmSpinner7b);
                                    }
                                    if (ServerGroupsActivity.this.zmSpinner8a >= 0) {
                                        ServerGroupsActivity.this.checkBox_8channel.setChecked(true);
                                        ServerGroupsActivity.this.spinner_8achannel.setSelection(ServerGroupsActivity.this.zmSpinner8a);
                                    }
                                }
                            });
                        }

                        public void onComplainList(ComplainEvent e) {
                        }

                        public void onBanList(BanEvent e) {
                        }

                        public void onOfflineMessageList(OfflineMessageEvent e) {
                        }

                        public void onGetOfflineMessage(OfflineMessageGetEvent e) {
                        }

                        public void onClientNameFromUid(ClientNameFromUidEvent e) {
                        }
                    });
                    try {
                        String schandlerIDokna = ServerGroupsActivity.this.api2.getSchandlerID().getID();
                        ServerGroupsActivity.this.api2.registerEvent(TSEventType.SERVER_GROUP, Integer.valueOf(schandlerIDokna).intValue());
                        ServerGroupsActivity.this.api2.registerEvent(TSEventType.CHANNEL_GROUP, Integer.valueOf(schandlerIDokna).intValue());
                        ServerGroupsActivity.this.connectedAPI = true;
                        return;
                    } catch (Exception e2) {
                        return;
                    }
                }
                ServerGroupsActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                    }
                });
            }
        };
    }

    private void cleanAllcheckBoxes() {
        runOnUiThread(new Runnable() {
            public void run() {
                ServerGroupsActivity.this.checkBox_2.setChecked(false);
                ServerGroupsActivity.this.checkBox_3.setChecked(false);
                ServerGroupsActivity.this.checkBox_3a.setChecked(false);
                ServerGroupsActivity.this.checkBox_3b.setChecked(false);
                ServerGroupsActivity.this.checkBox_3c.setChecked(false);
                ServerGroupsActivity.this.checkBox_3d.setChecked(false);
                ServerGroupsActivity.this.checkBox_4.setChecked(false);
                ServerGroupsActivity.this.checkBox_4a.setChecked(false);
                ServerGroupsActivity.this.checkBox_4b.setChecked(false);
                ServerGroupsActivity.this.checkBox_4c.setChecked(false);
                ServerGroupsActivity.this.checkBox_5.setChecked(false);
                ServerGroupsActivity.this.checkBox_5a.setChecked(false);
                ServerGroupsActivity.this.checkBox_6.setChecked(false);
                ServerGroupsActivity.this.checkBox_6e.setChecked(false);
                ServerGroupsActivity.this.checkBox_6f.setChecked(false);
                ServerGroupsActivity.this.checkBox_7.setChecked(false);
                ServerGroupsActivity.this.checkBox_7c.setChecked(false);
                ServerGroupsActivity.this.checkBox_8.setChecked(false);
                ServerGroupsActivity.this.checkBox_8b.setChecked(false);
                ServerGroupsActivity.this.checkBox_8c.setChecked(false);
                ServerGroupsActivity.this.checkBox_8d.setChecked(false);
                ServerGroupsActivity.this.checkBox_8e.setChecked(false);
                ServerGroupsActivity.this.checkBox_9.setChecked(false);
                ServerGroupsActivity.this.checkBox_9a.setChecked(false);
                ServerGroupsActivity.this.checkBox_9b.setChecked(false);
                ServerGroupsActivity.this.checkBox_9c.setChecked(false);
                ServerGroupsActivity.this.spinner_2a.setSelection(0);
                ServerGroupsActivity.this.spinner_5a.setSelection(0);
                ServerGroupsActivity.this.spinner_6a.setSelection(0);
                ServerGroupsActivity.this.spinner_6b.setSelection(0);
                ServerGroupsActivity.this.spinner_6c.setSelection(0);
                ServerGroupsActivity.this.spinner_6d.setSelection(0);
                ServerGroupsActivity.this.spinner_7a.setSelection(3);
                ServerGroupsActivity.this.spinner_7b.setSelection(3);
                ServerGroupsActivity.this.spinner_8a.setSelection(2);
            }
        });
    }

    private void cleanAllcheckBoxesChannel() {
        runOnUiThread(new Runnable() {
            public void run() {
                ServerGroupsActivity.this.checkBox_2channel.setChecked(false);
                ServerGroupsActivity.this.checkBox_5channel.setChecked(false);
                ServerGroupsActivity.this.checkBox_5achannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_6channel.setChecked(false);
                ServerGroupsActivity.this.checkBox_6echannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_6fchannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_7channel.setChecked(false);
                ServerGroupsActivity.this.checkBox_7cchannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_8channel.setChecked(false);
                ServerGroupsActivity.this.checkBox_8bchannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_8cchannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_8dchannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_8echannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_9channel.setChecked(false);
                ServerGroupsActivity.this.checkBox_9achannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_9bchannel.setChecked(false);
                ServerGroupsActivity.this.checkBox_9cchannel.setChecked(false);
                ServerGroupsActivity.this.spinner_2achannel.setSelection(0);
                ServerGroupsActivity.this.spinner_5achannel.setSelection(0);
                ServerGroupsActivity.this.spinner_6achannel.setSelection(0);
                ServerGroupsActivity.this.spinner_6bchannel.setSelection(0);
                ServerGroupsActivity.this.spinner_6cchannel.setSelection(0);
                ServerGroupsActivity.this.spinner_6dchannel.setSelection(0);
                ServerGroupsActivity.this.spinner_7achannel.setSelection(3);
                ServerGroupsActivity.this.spinner_7bchannel.setSelection(3);
                ServerGroupsActivity.this.spinner_8achannel.setSelection(2);
            }
        });
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
        Runtime.getRuntime().gc();
        System.gc();
    }

    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
        System.gc();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.listViewServer.setOnItemClickListener(null);
        this.listViewServer.setAdapter(null);
        this.listViewServer = null;
        this.listViewServerChannel.setOnItemClickListener(null);
        this.listViewServerChannel.setAdapter(null);
        this.listViewServerChannel = null;
        this.executor2.shutdown();
        new Thread(new Runnable() {
            public void run() {
                if (ServerGroupsActivity.this.api2 != null) {
                    ServerGroupsActivity.this.api2.unregisterAllEvents();
                    ServerGroupsActivity.this.api2 = null;
                }
                if (ServerGroupsActivity.this.query2 != null) {
                    ServerGroupsActivity.this.config2.floodRate = null;
                    ServerGroupsActivity.this.config2.setFloodRate(null);
                    ServerGroupsActivity.this.query2.exit();
                    ServerGroupsActivity.this.query2 = null;
                }
            }
        }).start();
        this.executor2.shutdownNow();
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }
}
