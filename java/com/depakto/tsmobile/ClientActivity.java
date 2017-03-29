package com.depakto.tsmobile;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.depakto.classes.adapters.ChannelArrayAdapter;
import com.depakto.classes.adapters.ChatArrayAdapter;
import com.depakto.classes.adapters.ClientArrayAdapter;
import com.depakto.classes.adapters.ClientBansArrayAdapter;
import com.depakto.classes.adapters.ClientComplainsArrayAdapter;
import com.depakto.classes.adapters.ClientLeftListArrayAdapter;
import com.depakto.classes.adapters.ClientOfflineMessageArrayAdapter;
import com.depakto.classes.construktor.BotJoinServer;
import com.depakto.classes.construktor.ChatMessage;
import com.depakto.classes.construktor.MyChannelClass;
import com.depakto.classes.construktor.OsobyIchannel;
import com.depakto.classes.construktor.Student;
import com.depakto.classes.help.TSApi;
import com.depakto.classes.help.TSConfig;
import com.depakto.classes.help.TSQuery;
import com.depakto.classes.help.api.ChannelProperty;
import com.depakto.classes.help.api.ClientProperty;
import com.depakto.classes.help.api.TextMessageTargetMode;
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
import com.depakto.classes.help.api.event.TSListener;
import com.depakto.classes.help.api.event.TextMessageEvent;
import com.depakto.classes.help.api.wrapper.Ban;
import com.depakto.classes.help.api.wrapper.Channel;
import com.depakto.classes.help.api.wrapper.Client;
import com.depakto.classes.help.api.wrapper.ClientInfo;
import com.depakto.classes.help.api.wrapper.Complaint;
import com.depakto.classes.help.api.wrapper.Message;
import com.depakto.classes.help.api.wrapper.Password;
import com.depakto.classes.help.api.wrapper.VirtualServerIP;
import com.depakto.classes.help.api.wrapper.VirtualServerInfoClient;
import com.depakto.classes.help.commands.response.DefaultArrayResponse;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ClientActivity extends AppCompatActivity {
    private static final String TAG = "TEST";
    private final String APP_ID = "app65949c6a0eb64889be";
    public HashMap<Integer, Integer> HM1;
    public HashMap<Integer, Channel> Hchannels = new HashMap();
    public HashMap<Integer, Integer> LM1;
    private final String ZONE_ID = "vzb139462e4a5d4cd7b3";
    private final String[] ZONE_IDs = new String[]{"vzb139462e4a5d4cd7b3", "vzfe2c7489c0534ec185", "vz5680157e32fa4cb9bf", "vz9e88383635a24535b7"};
    private Bitmap adButtonBitmap;
    private AdRequest adRequest;
    public ChannelArrayAdapter adapter2;
    private ClientBansArrayAdapter adapterBans;
    private ClientComplainsArrayAdapter adapterComplains;
    public ArrayAdapter<String> adapterListUsers;
    private ClientOfflineMessageArrayAdapter adapterOfflineMessages;
    public ArrayAdapter<String> adapter_info;
    public ArrayAdapter<String> adapter_info_bot;
    public ArrayAdapter<String> adapter_info_lists;
    public ArrayAdapter<String> adapter_main;
    public Button againButton;
    public TSApi api;
    private String appId = "57aaf7f443150f5d099ee55a";
    private String appSignature = "de06056251fce72b41a5ed28618922e72bb2a395";
    public ArrayList<Channel> arrayList;
    public ArrayList<OsobyIchannel> arrayListMieszankiListy = new ArrayList();
    private TextView bandwidthText;
    public List<Student> bazaListViewUsers = new ArrayList();
    private boolean blokadaWywolan = false;
    private boolean brakreklam = false;
    private ImageView buttonAd;
    private Button buttonSend3;
    public Client c;
    public MyChannelClass chann;
    private int channelIDtoMove = 0;
    public TextView channel_name_chat;
    private List<ChatMessage> chat = new ArrayList();
    private ChatArrayAdapter chatArrayAdapter3;
    private Bitmap chatImageBitmapOff;
    private Bitmap chatImageBitmapOn;
    private EditText chatText3;
    private boolean chat_bool = false;
    public ImageView chat_image;
    private ImageView chat_image_button;
    public ImageView chat_image_tlo;
    public List<Client> clients;
    public ImageView commanderImage;
    public TSConfig config = new TSConfig();
    private TextView dataOnComplains;
    public OsobyIchannel do_paczki;
    public DrawerLayout drawer;
    private EditText editFrom;
    private Bitmap editNameBitmap;
    private ImageView edit_image_button;
    public TextView empty_text;
    public ExecutorService executor;
    private Channel global_tempChannel;
    public String hostIP = "192.168.0.1";
    private ImageView imageButtonBanAdd;
    private ImageView imageViewComplains;
    public ImageView img1;
    private ImageView imgPodglad;
    public int indexOsobyzMojegoKanalu = -1;
    private TextView infoComplaints;
    Intent intent3;
    private Intent intentG;
    public ArrayList items;
    public ImageView jaAway;
    private Bitmap jaAwayBitmapOff;
    private Bitmap jaAwayBitmapOn;
    private Bitmap jaBitmapOff;
    private Bitmap jaBitmapOn;
    public ImageView jaMikro;
    private Bitmap jaMikroBitmapOff;
    private Bitmap jaMikroBitmapOn;
    public ImageView jaSpeaker;
    private Bitmap jaSpeakerBitmapOff;
    private Bitmap jaSpeakerBitmapOn;
    private boolean kill = false;
    public HashMap<Integer, Student> kopia_osob;
    private TextView labelUser;
    public String last_error_query;
    private ListView leftList;
    public int liczba;
    public int liczbaAktywnychBotow = 0;
    public int liczbaNieodczytanychOfflineMsg = 0;
    public int liczbaNiesprawdzonychLogsow = 0;
    private List<BotJoinServer> listBotJoinServer = new ArrayList();
    private List<String> listBotJoinServerLog = new ArrayList();
    public List<Channel> listCh;
    private ListView listView3;
    private ListView listViewComplains;
    public ListView listViewUsers;
    public HashMap<Integer, Channel> list_channels = new HashMap();
    public ListView list_nav_info;
    public ListView list_nav_info_bot;
    public ListView list_nav_info_lists;
    public ListView list_nav_main;
    private List<Ban> listaBanow;
    private List<Message> listaOffline;
    private List<Complaint> listaSkarg;
    public List<MyChannelClass> lista_channel_array = new ArrayList();
    private TSListener listenerInicjacja;
    private ActionMode mActionMode;
    private Callback mActionModeCallback = new Callback() {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.list_channel_menu, menu);
            if (ClientActivity.this.selected_channeluser.idClient > -1) {
                mode.setTitle(ClientActivity.this.selected_channeluser.nameClient);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_new_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_sub_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_edit_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_join_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_del_channel);
            } else {
                mode.setTitle(ClientActivity.this.selected_channeluser.nameChannel.replaceAll(ClientActivity.this.regex, BuildConfig.FLAVOR));
                menu.getItem(0).getSubMenu().removeItem(R.id.action_poke_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_chat_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_complain_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_kick_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_kick_client_server);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_ban_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_client_info);
            }
            return true;
        }

        public boolean onTouch(ActionMode mode) {
            ClientActivity.this.clearSelectOnLeftList();
            mode.finish();
            return false;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            final ActionMode actionMode;
            switch (item.getItemId()) {
                case R.id.action_poke_client /*2131493450*/:
                    ClientActivity.this.callPoke(ClientActivity.this.selected_channeluser.idClient, 1);
                    return true;
                case R.id.action_chat_client /*2131493451*/:
                    Builder builder3 = new Builder(ClientActivity.this);
                    builder3.setTitle("Enter private message");
                    final EditText input3 = new EditText(ClientActivity.this);
                    input3.setOnFocusChangeListener(new OnFocusChangeListener() {
                        public void onFocusChange(View view, boolean b) {
                            ClientActivity.this.hideKeyboard(view);
                        }
                    });
                    input3.setInputType(524289);
                    builder3.setView(input3);
                    builder3.setPositiveButton("Send", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            int i = 0;
                            String m_Text = input3.getText().toString();
                            String grupowyTekst = BuildConfig.FLAVOR;
                            if (m_Text.length() > 0) {
                                String[] parts = m_Text.split("\\s+");
                                int length = parts.length;
                                while (i < length) {
                                    String item = parts[i];
                                    try {
                                        grupowyTekst = grupowyTekst + "[URL]" + new URL(item) + "[/URL] ";
                                    } catch (MalformedURLException e) {
                                        if (item.contains("www.")) {
                                            grupowyTekst = grupowyTekst + "[URL]" + item + "[/URL] ";
                                        } else {
                                            grupowyTekst = grupowyTekst + item + " ";
                                        }
                                    }
                                    i++;
                                }
                                try {
                                    ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.selected_channeluser.idClient, grupowyTekst);
                                    if (ClientActivity.this.last_error_query.contains("ok")) {
                                        ClientActivity.this.chatArrayAdapter3.add(new ChatMessage(false, " (PRIVATE) to " + ClientActivity.this.selected_channeluser.nameClient + ":   " + m_Text));
                                    } else {
                                        ClientActivity.this.zliczBledyUprawnien();
                                    }
                                    Snackbar.make(ClientActivity.this.leftList, "Private: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                                    return;
                                } catch (Exception e2) {
                                    return;
                                }
                            }
                            Snackbar.make(ClientActivity.this.leftList, "Private: You need write something!", 0).setAction("Action", null).show();
                        }
                    });
                    builder3.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder3.show();
                    return true;
                case R.id.action_kick_client /*2131493452*/:
                    ClientActivity.this.indexOsobyzMojegoKanalu = ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.selected_channeluser.idClient))).index;
                    ClientActivity.this.wyborZakcjiListy = true;
                    ClientActivity.this.callKickCh(ClientActivity.this.selected_channeluser.idClient, 1);
                    if (ClientActivity.this.last_error_query.contains("ok")) {
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.getClients();
                                ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                            }
                        });
                        mode.finish();
                    } else {
                        ClientActivity.this.zliczBledyUprawnien();
                    }
                    return true;
                case R.id.action_move_client /*2131493453*/:
                    ClientActivity.this.callMoveUser(ClientActivity.this.selected_channeluser.idClient);
                    return true;
                case R.id.action_kick_client_server /*2131493454*/:
                    if (ClientActivity.this.selected_channeluser.idClient == ClientActivity.this.myIDclientOnserver) {
                        Snackbar.make(ClientActivity.this.leftList, "You can't kick yourself from server! ", 0).setAction("Action", null).show();
                    } else {
                        ClientActivity.this.indexOsobyzMojegoKanalu = ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.selected_channeluser.idClient))).index;
                        ClientActivity.this.wyborZakcjiListy = true;
                        if (ClientActivity.this.last_error_query.contains("ok")) {
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.getClients();
                                    ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                                }
                            });
                            mode.finish();
                        } else {
                            ClientActivity.this.zliczBledyUprawnien();
                        }
                    }
                    return true;
                case R.id.action_ban_client /*2131493455*/:
                    Builder builderBan = new Builder(ClientActivity.this);
                    View dialogLayA = ClientActivity.this.getLayoutInflater().inflate(R.layout.client_alertdialog_banclientselected, null);
                    builderBan.setView(dialogLayA);
                    TextView editNameClient = (TextView) dialogLayA.findViewById(R.id.editBanClientName);
                    final EditText editReasonBan = (EditText) dialogLayA.findViewById(R.id.editReasonClientBan);
                    final EditText editTime = (EditText) dialogLayA.findViewById(R.id.editTextTimeBanClient);
                    Spinner spinnerBanTime = (Spinner) dialogLayA.findViewById(R.id.spinnerBanClient);
                    ArrayAdapter<CharSequence> adapterTimeBan = ArrayAdapter.createFromResource(ClientActivity.this, R.array.time_ban_client, 17367048);
                    adapterTimeBan.setDropDownViewResource(R.layout.custom_spinner_item);
                    spinnerBanTime.setAdapter(adapterTimeBan);
                    spinnerBanTime.setSelection(3);
                    final Spinner spinner = spinnerBanTime;
                    spinnerBanTime.setOnItemSelectedListener(new OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> adapterView, View view, int possitionTime, long l) {
                            ClientActivity.this.selectedTimeBan = possitionTime;
                            spinner.setSelection(possitionTime);
                            if (ClientActivity.this.selectedTimeBan == 4) {
                                editTime.setEnabled(false);
                            } else {
                                editTime.setEnabled(true);
                            }
                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    editReasonBan.setOnFocusChangeListener(new OnFocusChangeListener() {
                        public void onFocusChange(View view, boolean b) {
                            ClientActivity.this.hideKeyboard(view);
                        }
                    });
                    editTime.setOnFocusChangeListener(new OnFocusChangeListener() {
                        public void onFocusChange(View view, boolean b) {
                            if (editTime.getText().length() <= 0) {
                                editTime.setText("1");
                            }
                            ClientActivity.this.hideKeyboard(view);
                        }
                    });
                    spinner = spinnerBanTime;
                    editTime.addTextChangedListener(new TextWatcher() {
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        public void afterTextChanged(Editable editable) {
                            if (editTime.getText().length() > 0 && editTime.getText().toString().startsWith("0")) {
                                spinner.setSelection(4);
                                editTime.setText("1");
                            }
                        }
                    });
                    editNameClient.setText(ClientActivity.this.selected_channeluser.nameClient);
                    actionMode = mode;
                    builderBan.setPositiveButton("Set Ban!", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            long timeToBan = 0;
                            switch (ClientActivity.this.selectedTimeBan) {
                                case R.styleable.View_android_theme /*0*/:
                                    timeToBan = Long.parseLong(editTime.getText().toString());
                                    break;
                                case R.styleable.View_android_focusable /*1*/:
                                    timeToBan = Long.parseLong(editTime.getText().toString()) * 60;
                                    break;
                                case R.styleable.View_paddingStart /*2*/:
                                    timeToBan = Long.parseLong(editTime.getText().toString()) * 3600;
                                    break;
                                case R.styleable.View_paddingEnd /*3*/:
                                    timeToBan = Long.parseLong(editTime.getText().toString()) * 86400;
                                    break;
                                case R.styleable.View_theme /*4*/:
                                    timeToBan = 0;
                                    break;
                            }
                            if (timeToBan > 0) {
                                ClientActivity.this.api.banClient(ClientActivity.this.selected_channeluser.idClient, timeToBan, editReasonBan.getText().toString());
                            } else {
                                ClientActivity.this.api.banClient(ClientActivity.this.selected_channeluser.idClient, 0, editReasonBan.getText().toString());
                            }
                            if (ClientActivity.this.last_error_query.contains("ok")) {
                                ClientActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        actionMode.finish();
                                        ClientActivity.this.getClients();
                                        ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                                    }
                                });
                            } else {
                                ClientActivity.this.zliczBledyUprawnien();
                            }
                            Snackbar.make(ClientActivity.this.leftList, "Ban add: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                        }
                    });
                    builderBan.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builderBan.show();
                    return true;
                case R.id.action_complain_client /*2131493456*/:
                    Builder builder2 = new Builder(ClientActivity.this);
                    builder2.setTitle("Enter complaint");
                    final EditText input2 = new EditText(ClientActivity.this);
                    input2.setOnFocusChangeListener(new OnFocusChangeListener() {
                        public void onFocusChange(View view, boolean b) {
                            ClientActivity.this.hideKeyboard(view);
                        }
                    });
                    input2.setInputType(524289);
                    builder2.setView(input2);
                    builder2.setPositiveButton("Send", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String m_Text = input2.getText().toString();
                            if (m_Text.length() > 0) {
                                try {
                                    ClientActivity.this.api.addComplain(ClientActivity.this.selected_channeluser.idClientDB, m_Text);
                                    Snackbar.make(ClientActivity.this.leftList, "Complain: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                                    if (!ClientActivity.this.last_error_query.contains("ok")) {
                                        ClientActivity.this.zliczBledyUprawnien();
                                        return;
                                    }
                                    return;
                                } catch (Exception e) {
                                    return;
                                }
                            }
                            Snackbar.make(ClientActivity.this.leftList, "Complain: You need write something!", 0).setAction("Action", null).show();
                        }
                    });
                    builder2.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder2.show();
                    return true;
                case R.id.action_client_info /*2131493457*/:
                    ClientActivity.this.callClientInfo(ClientActivity.this.selected_channeluser.idClient);
                    return true;
                case R.id.action_join_channel /*2131493458*/:
                    ClientActivity.this.api.moveClient(ClientActivity.this.myIDclientOnserver, ClientActivity.this.selected_channeluser.idChannel);
                    Snackbar.make(ClientActivity.this.leftList, "Channel change: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
                    if (!ClientActivity.this.last_error_query.contains("ok")) {
                        ClientActivity.this.zliczBledyUprawnien();
                    }
                    ClientActivity.this.nazwa_kanalu = ClientActivity.this.selected_channeluser.nameChannel;
                    if (ClientActivity.this.last_error_query.contains("password")) {
                        Builder builder = new Builder(ClientActivity.this);
                        builder.setTitle("Enter password");
                        final EditText input = new EditText(ClientActivity.this);
                        input.setInputType(524289);
                        builder.setView(input);
                        builder.setPositiveButton("OK", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ClientActivity.this.api.moveClient(ClientActivity.this.myIDclientOnserver, ClientActivity.this.selected_channeluser.idChannel, ((Password) ClientActivity.this.api.hashPassword(input.getText().toString()).get(0)).getPassword());
                                Snackbar.make(ClientActivity.this.leftList, "Channel change: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                                if (!ClientActivity.this.last_error_query.contains("ok")) {
                                    ClientActivity.this.zliczBledyUprawnien();
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                    ClientActivity.this.clearSelectOnLeftList();
                    mode.finish();
                    return true;
                case R.id.action_new_channel /*2131493459*/:
                    ClientActivity.this.callNewChannel2(0);
                    ClientActivity.this.clearSelectOnLeftList();
                    mode.finish();
                    return true;
                case R.id.action_sub_channel /*2131493460*/:
                    ClientActivity.this.callSubChannel();
                    ClientActivity.this.clearSelectOnLeftList();
                    mode.finish();
                    return true;
                case R.id.action_move_channel /*2131493461*/:
                    ClientActivity.this.callMoveChannel();
                    ClientActivity.this.clearSelectOnLeftList();
                    mode.finish();
                    return true;
                case R.id.action_edit_channel /*2131493462*/:
                    ClientActivity.this.callNewChannel2(1);
                    ClientActivity.this.clearSelectOnLeftList();
                    mode.finish();
                    return true;
                case R.id.action_del_channel /*2131493463*/:
                    Builder builder4 = new Builder(ClientActivity.this);
                    builder4.setTitle("You will delete it, are you sure?");
                    actionMode = mode;
                    builder4.setPositiveButton("Yes", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ClientActivity.this.clearSelectOnLeftList();
                            actionMode.finish();
                            ClientActivity.this.api.deleteChannel(ClientActivity.this.selected_channeluser.idChannel);
                            Snackbar.make(ClientActivity.this.leftList, "Delete channel: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                            if (ClientActivity.this.last_error_query.contains("ok")) {
                                ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                            } else {
                                ClientActivity.this.zliczBledyUprawnien();
                            }
                        }
                    });
                    builder4.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder4.show();
                    return true;
                default:
                    return false;
            }
        }

        public void onDestroyActionMode(ActionMode mode) {
            ClientActivity.this.clearSelectOnLeftList();
            ClientActivity.this.mActionMode = null;
        }
    };
    private AdView mAdView;
    public InterstitialAd mInterstitialAd;
    public List<Integer> mala_paczka_idkow;
    public List<Integer> mala_paczka_idkow2;
    private Bitmap mapa1;
    private Bitmap mapa2;
    public int myIDchannelOnServer;
    public int myIDclientOnserver;
    public int myIDdatabaseOnServer;
    private String name_sender_offline = BuildConfig.FLAVOR;
    private String nazwa_kanalu = "TSClient";
    private DateFormat now;
    private DateFormat nowBot;
    private TextView numerOfComplains;
    private int odliczanieDoReklamy = 0;
    private boolean oknoLists = false;
    public HashMap<Integer, Student> osobyLista = new HashMap();
    public int po;
    public int po2;
    public int portIntent = 10011;
    public ImageView posCallMute;
    public ImageView posCallUnMute;
    public ProgressBar progressAgain;
    private ProgressBar progressOfflineMsg;
    public TSQuery query;
    private String regex = "\\[[^\\]]*\\]";
    private int selectedTimeBan = 3;
    public OsobyIchannel selected_channeluser;
    public int selected_row = -1;
    public String showText = "It looks like You are not connected to any TS Server!\nConnect to one and try again.";
    private boolean side = false;
    private Spinner spinnerMove;
    public Student stud;
    public ConnectivityChangeReceiver tempBrod;
    public IntentFilter tempIntent;
    public ArrayList<OsobyIchannel> temp_arrayListMieszankiListy = new ArrayList();
    public List<Student> temp_clients_all = new ArrayList();
    public TextView textNumberCreateBots;
    public TextView textNumberLogs;
    public TextView textNumberOfflineMsg;
    private TextView textTitleComplains;
    public TextView title_text;
    private ActionBarDrawerToggle toggle;
    private ActionBarDrawerToggle toggleActionWartosc;
    private Toolbar toolbar;
    public TextView txtNaAgain;
    public TextView txtNickname;
    public View vAway;
    public View vMikro;
    public View vSpeaker;
    private View viewId1;
    private View viewId2;
    private View viewId3;
    private View viewId4_complain;
    private View viewTolbar;
    private boolean waitServerGroups = false;
    private View waitServerGroupsView;
    private boolean waitServerPermission = false;
    private View waitServerPermissionView;
    private boolean wait_for_banadd = false;
    private boolean wait_for_banlist = false;
    private boolean wait_for_complainlist = false;
    private boolean wait_for_get_offline = false;
    private boolean wait_for_offlinemessagelist = false;
    private boolean wait_for_uid_nickname = false;
    public ImageView welcome_image;
    private boolean wiadomosci_odczytane = true;
    public boolean wifi = false;
    private boolean wyborZakcjiListy = false;
    public int zaznaczony;
    public HashMap<Integer, List<Integer>> zgrupowane_osoby = new HashMap();
    public int zmChannelID;
    public Channel zmObjectChannel;
    public int zmiennaTalkedId;
    public int zmiennaTalkedStatus;

    private class GrupaUserow {
        List<Integer> paczkaIDkow;

        private GrupaUserow() {
        }
    }

    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog = new ProgressDialog(ClientActivity.this);

        private YourAsyncTask() {
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Loading...");
            this.dialog.show();
            super.onPreExecute();
        }

        protected Void doInBackground(Void... args) {
            return null;
        }

        protected void onPostExecute(Void result) {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        }
    }

    public boolean setListViewHeightBasedOnItems(ListView listView) {
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

    private void showOfflineMsgDetails(final Message msg) {
        final Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_offline_read, null);
        EditText editSubject = (EditText) dialogLay.findViewById(R.id.editMsgSubject);
        this.editFrom = (EditText) dialogLay.findViewById(R.id.editMsgFrom);
        EditText editDate = (EditText) dialogLay.findViewById(R.id.editMsgDate);
        EditText editMessage = (EditText) dialogLay.findViewById(R.id.editMsgMessage);
        editSubject.setText(msg.getSubject());
        this.nowBot = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateBot = this.nowBot.format(Long.valueOf(msg.getReceivedDate().getTime()));
        this.editFrom.setText(msg.getClientUniqueIdentifier() + "\n(");
        editDate.setText(dateBot + BuildConfig.FLAVOR);
        editMessage.setText(msg.getMessage());
        this.wait_for_uid_nickname = true;
        this.api.getNicknameFromUid(msg.getClientUniqueIdentifier());
        builder.setView(dialogLay);
        builder.setPositiveButton("Reply", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ClientActivity.this.showOfflineMsgReply(msg);
            }
        });
        builder.setNegativeButton("Close", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        runOnUiThread(new Runnable() {
            public void run() {
                builder.show();
            }
        });
    }

    private void showOfflineMsgReply(final Message msg) {
        final Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_offline_read, null);
        TextView txtTitle = (TextView) dialogLay.findViewById(R.id.txtOfflineMsg);
        final EditText editSubject = (EditText) dialogLay.findViewById(R.id.editMsgSubject);
        this.editFrom = (EditText) dialogLay.findViewById(R.id.editMsgFrom);
        EditText editDate = (EditText) dialogLay.findViewById(R.id.editMsgDate);
        final EditText editMessage = (EditText) dialogLay.findViewById(R.id.editMsgMessage);
        ((TextView) dialogLay.findViewById(R.id.labelMsgFrom)).setText("To:");
        TextView txtDate = (TextView) dialogLay.findViewById(R.id.textMsgDate);
        editSubject.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editMessage.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        this.editFrom.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        txtDate.setVisibility(8);
        editDate.setVisibility(8);
        txtTitle.setText("Create offline message");
        editSubject.setEnabled(true);
        editSubject.setText("Re: " + msg.getSubject());
        this.editFrom.setText(msg.getClientUniqueIdentifier() + "\n(" + this.name_sender_offline + ")");
        editMessage.setText(this.name_sender_offline + " wrote: \n\"" + msg.getMessage() + "\"\n");
        editMessage.setEnabled(true);
        builder.setView(dialogLay);
        builder.setPositiveButton("Send", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ClientActivity.this.api.sendOfflineMessage(msg.getClientUniqueIdentifier(), editSubject.getText().toString(), editMessage.getText().toString());
                Snackbar.make(ClientActivity.this.listViewComplains, "Send offline message: " + ClientActivity.this.last_error_query, -1).show();
            }
        });
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        runOnUiThread(new Runnable() {
            public void run() {
                builder.show();
            }
        });
    }

    private void showUpDonate() {
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.demoo, null);
        ((ImageView) dialogLay.findViewById(R.id.imageView4)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=3XJ8AX8MULW22")));
            }
        });
        builder.setView(dialogLay);
        builder.setPositiveButton("Donate!", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ClientActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=3XJ8AX8MULW22")));
            }
        });
        builder.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.kill) {
            finish();
            finish();
        }
        setContentView(R.layout.activity_main_client);
        this.tempBrod = new ConnectivityChangeReceiver();
        this.tempIntent = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        this.mapa1 = decodeSampledBitmapFromResource(getResources(), R.drawable.tlowelcome, displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.editNameBitmap = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.c_edit_name1, 1, 1);
        this.chatImageBitmapOn = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.c_chat_on, 1, 1);
        this.chatImageBitmapOff = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.c_chat_off, 1, 1);
        this.adButtonBitmap = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.watchtoads, 1, 1);
        this.jaBitmapOn = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_talk, 1, 1);
        this.jaBitmapOff = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_no_talk, 1, 1);
        this.jaMikroBitmapOn = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_speak, 1, 1);
        this.jaMikroBitmapOff = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_no_mic, 1, 1);
        this.jaSpeakerBitmapOn = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_glosnik, 1, 1);
        this.jaSpeakerBitmapOff = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_no_speak, 1, 1);
        this.jaAwayBitmapOn = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_obecny, 1, 1);
        this.jaAwayBitmapOff = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.me_nieobecny, 1, 1);
        this.welcome_image = (ImageView) findViewById(R.id.welcome_image);
        this.chat_image_tlo = (ImageView) findViewById(R.id.chat_tlo);
        this.welcome_image.setImageBitmap(this.mapa1);
        this.chat_image = (ImageView) findViewById(R.id.chat_image);
        this.empty_text = (TextView) findViewById(R.id.empty_text);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle(BuildConfig.FLAVOR);
        this.toolbar.setVisibility(8);
        setSupportActionBar(this.toolbar);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.leftList = (ListView) findViewById(R.id.left_drawer);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.nav_header_main, this.leftList, false);
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.nav_footer_main, this.leftList, false);
        String[] valuesMain = new String[]{"Give a review!", "Join to Us!", "Unlimited TSmobile"};
        ArrayList<String> list_main = new ArrayList();
        for (Object add : valuesMain) {
            list_main.add(add);
        }
        this.adapter_main = new ClientLeftListArrayAdapter(getApplicationContext(), list_main, this);
        String[] values = new String[]{"Client Connection", "Server Connection", "Groups"};
        ArrayList<String> list_info = new ArrayList();
        for (Object add2 : values) {
            list_info.add(add2);
        }
        this.adapter_info = new ClientLeftListArrayAdapter(getApplicationContext(), list_info, this);
        String[] valuesBot = new String[]{"Create & Run", "Check the Logs"};
        ArrayList<String> list_info_bot = new ArrayList();
        for (Object add22 : valuesBot) {
            list_info_bot.add(add22);
        }
        this.adapter_info_bot = new ClientLeftListArrayAdapter(getApplicationContext(), list_info_bot, this);
        String[] valuesLists = new String[]{"Ban List", "Complaint List", "Offline Message List"};
        ArrayList<String> list_info_lists = new ArrayList();
        for (Object add222 : valuesLists) {
            list_info_lists.add(add222);
        }
        this.adapter_info_lists = new ClientLeftListArrayAdapter(getApplicationContext(), list_info_lists, this);
        this.leftList.addHeaderView(header, null, false);
        this.leftList.addFooterView(footer, null, false);
        this.list_nav_main = (ListView) findViewById(R.id.listMain);
        this.list_nav_main.setAdapter(this.adapter_main);
        setListViewHeightBasedOnItems(this.list_nav_main);
        this.list_nav_info = (ListView) findViewById(R.id.list1);
        this.list_nav_info.setAdapter(this.adapter_info);
        setListViewHeightBasedOnItems(this.list_nav_info);
        this.list_nav_info_bot = (ListView) findViewById(R.id.listBot);
        this.list_nav_info_bot.setAdapter(this.adapter_info_bot);
        setListViewHeightBasedOnItems(this.list_nav_info_bot);
        this.list_nav_info_lists = (ListView) findViewById(R.id.listLists);
        this.list_nav_info_lists.setAdapter(this.adapter_info_lists);
        setListViewHeightBasedOnItems(this.list_nav_info_lists);
        this.list_nav_main.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String itemik = ClientActivity.this.list_nav_main.getItemAtPosition(position).toString();
                boolean z = true;
                switch (itemik.hashCode()) {
                    case -1221650213:
                        if (itemik.equals("Give a review!")) {
                            z = true;
                            break;
                        }
                        break;
                    case -777248364:
                        if (itemik.equals("Join to Us!")) {
                            z = true;
                            break;
                        }
                        break;
                    case 502018144:
                        if (itemik.equals("Unlimited TSmobile")) {
                            z = false;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case R.styleable.View_android_theme /*0*/:
                        ClientActivity.this.showUpDonate();
                        Runtime.getRuntime().gc();
                        System.gc();
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            ClientActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.depakto.tsmobile")));
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                            return;
                        }
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            ClientActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/TS3MobileApp")));
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        this.list_nav_info.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {
                String itemik2 = ClientActivity.this.list_nav_info.getItemAtPosition(position).toString();
                int i = -1;
                switch (itemik2.hashCode()) {
                    case -1316377637:
                        if (itemik2.equals("Server Connection")) {
                            i = 0;
                            break;
                        }
                        break;
                    case 460735059:
                        if (itemik2.equals("Client Connection")) {
                            i = 2;
                            break;
                        }
                        break;
                    case 1475846639:
                        if (itemik2.equals("Permission")) {
                            i = 3;
                            break;
                        }
                        break;
                    case 2141373940:
                        if (itemik2.equals("Groups")) {
                            boolean z = true;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            new Thread(new Runnable() {
                                public void run() {
                                    VirtualServerInfoClient infoServer = ClientActivity.this.api.getServerInfoClient();
                                    Intent intent3 = new Intent(ClientActivity.this, ServerInfoActivity.class);
                                    intent3.putExtra("sName", infoServer.getName());
                                    intent3.putExtra("sHostbannerGfxUrl", infoServer.getHostbannerGfxUrl());
                                    intent3.putExtra("sHostbannerUrl", infoServer.getHostbannerUrl());
                                    intent3.putExtra("sHostbuttonTooltip", infoServer.getHostbuttonTooltip());
                                    intent3.putExtra("sHostbuttonUrl", infoServer.getHostbuttonUrl());
                                    intent3.putExtra("sPlatform", infoServer.getPlatform());
                                    intent3.putExtra("sVersion", infoServer.getVersion());
                                    ClientActivity.this.nowBot = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    intent3.putExtra("sCreatedDate", ClientActivity.this.nowBot.format(Long.valueOf(infoServer.getCreatedDate().getTime())));
                                    intent3.putExtra("sDefaultChannelGroup", infoServer.getDefaultChannelGroup());
                                    intent3.putExtra("sDefaultServerGroup", infoServer.getDefaultServerGroup());
                                    intent3.putExtra("sPrioritySpeakerDimmModificator", infoServer.getPrioritySpeakerDimmModificator());
                                    intent3.putExtra("sServerId", infoServer.getServerId());
                                    int sClientsOnline = ClientActivity.this.api.getClients().size();
                                    int sChannelsOnline = ClientActivity.this.api.getChannels().size();
                                    intent3.putExtra("sClientsOnline", sClientsOnline);
                                    intent3.putExtra("sChannelsOnline", sChannelsOnline);
                                    VirtualServerIP infoIP = ClientActivity.this.api.getServerIP();
                                    intent3.putExtra("sServerIP", infoIP.getIP());
                                    intent3.putExtra("sServerPort", infoIP.getPort());
                                    ClientActivity.this.startActivity(intent3);
                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                                        }
                                    });
                                    ClientActivity.this.blokadaWywolan = false;
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                }
                            }).start();
                            return;
                        }
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            ClientActivity.this.waitServerGroups = true;
                            ClientActivity.this.waitServerGroupsView = view;
                            new Thread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.api.getServerGroups();
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                }
                            }).start();
                            return;
                        }
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            new Thread(new Runnable() {
                                public void run() {
                                    ClientInfo infoClient = ClientActivity.this.api.getClientInfo(ClientActivity.this.myIDclientOnserver, true);
                                    Intent intentC = new Intent(ClientActivity.this, ServerClientActivity.class);
                                    intentC.putExtra("client_is_talker", infoClient.canTalk());
                                    intentC.putExtra("client_away_message", infoClient.getAwayMessage());
                                    intentC.putExtra("client_channel_group_id", infoClient.getChannelGroupId());
                                    intentC.putExtra("client_country", infoClient.getCountry());
                                    intentC.putExtra("client_database_id", infoClient.getDatabaseId());
                                    intentC.putExtra("client_nickname", infoClient.getNickname());
                                    intentC.putExtra("client_talk_power", infoClient.getTalkPower());
                                    intentC.putExtra("client_away", infoClient.isAway());
                                    intentC.putExtra("client_is_muted", infoClient.isMuted());
                                    intentC.putExtra("client_is_channel_commander", infoClient.isChannelCommander());
                                    intentC.putExtra("client_input_hardware", infoClient.isInputHardware());
                                    intentC.putExtra("client_input_muted", infoClient.isInputMuted());
                                    intentC.putExtra("client_output_hardware", infoClient.isOutputHardware());
                                    intentC.putExtra("client_output_muted", infoClient.isOutputMuted());
                                    intentC.putExtra("client_is_priority_speaker", infoClient.isPrioritySpeaker());
                                    intentC.putExtra("client_is_recording", infoClient.isRecording());
                                    intentC.putExtra("client_unread_messages", infoClient.getUnreadMessages());
                                    intentC.putExtra("client_servergroups", infoClient.getServerGroupsStr());
                                    intentC.putExtra("client_talk_request", infoClient.isRequestingToTalk());
                                    intentC.putExtra("client_talk_request_msg", infoClient.getTalkRequestMessage());
                                    intentC.putExtra("client_description", infoClient.getDescription());
                                    intentC.putExtra("client_volume_modificator", infoClient.getVolume());
                                    intentC.putExtra("client_channel_name", ((Channel) ClientActivity.this.Hchannels.get(Integer.valueOf(ClientActivity.this.myIDchannelOnServer))).getName());
                                    intentC.putExtra("client_unique_identifier", infoClient.getUniqueIdentifier());
                                    intentC.putExtra("client_type", infoClient.getType());
                                    intentC.putExtra("client_nickname_phonetic", infoClient.getPhoneticNickname());
                                    intentC.putExtra("client_meta_data", infoClient.getMetaData());
                                    intentC.putExtra("client_outputonly_muted", infoClient.isOutputOnlyMuted());
                                    intentC.putExtra("client_icon_id", infoClient.getIconId());
                                    intentC.putExtra("client_needed_serverquery_view_power", infoClient.getNeededServerQueryViewPower());
                                    intentC.putExtra("client_channel_group_inherited_channel_id", infoClient.getChannelGroupInheritedChannelId());
                                    ClientActivity.this.startActivity(intentC);
                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                                        }
                                    });
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                    ClientActivity.this.blokadaWywolan = false;
                                }
                            }).start();
                            return;
                        }
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            ClientActivity.this.waitServerPermission = true;
                            ClientActivity.this.waitServerPermissionView = view;
                            new Thread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.api.getPermissionOverview(ClientActivity.this.myIDchannelOnServer, ClientActivity.this.myIDdatabaseOnServer);
                                    if (!ClientActivity.this.last_error_query.contains("ok")) {
                                        Snackbar.make(ClientActivity.this.waitServerPermissionView, ClientActivity.this.last_error_query.toString() + BuildConfig.FLAVOR, -1).setAction("Action", null).show();
                                        ClientActivity.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                view.findViewById(R.id.imgRightMenu).setVisibility(8);
                                            }
                                        });
                                    }
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                }
                            }).start();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        this.list_nav_info_bot.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String itemik = ClientActivity.this.list_nav_info_bot.getItemAtPosition(position).toString();
                boolean z = true;
                switch (itemik.hashCode()) {
                    case -2132871082:
                        if (itemik.equals("Check the Logs")) {
                            z = true;
                            break;
                        }
                        break;
                    case 165521261:
                        if (itemik.equals("Create & Run")) {
                            z = false;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case R.styleable.View_android_theme /*0*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            ClientActivity.this.textNumberCreateBots.setVisibility(8);
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            ClientActivity.this.startActivity(new Intent(ClientActivity.this, BotClientActivity.class));
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                            return;
                        }
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            ClientActivity.this.liczbaNiesprawdzonychLogsow = 0;
                            ClientActivity.this.textNumberLogs.setVisibility(8);
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            ClientActivity.this.startActivity(new Intent(ClientActivity.this, BotLogsActivity.class));
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        this.list_nav_info_lists.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String itemik = ClientActivity.this.list_nav_info_lists.getItemAtPosition(position).toString();
                boolean z = true;
                switch (itemik.hashCode()) {
                    case -1909181292:
                        if (itemik.equals("Offline Message List")) {
                            z = true;
                            break;
                        }
                        break;
                    case -1863963057:
                        if (itemik.equals("Ban List")) {
                            z = false;
                            break;
                        }
                        break;
                    case 1301878579:
                        if (itemik.equals("Complaint List")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case R.styleable.View_android_theme /*0*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            ClientActivity.this.listViewComplains.setAdapter(null);
                            if (ClientActivity.this.odliczanieDoReklamy >= 3) {
                                if (ClientActivity.this.mInterstitialAd.isLoaded() && !ClientActivity.this.brakreklam) {
                                    ClientActivity.this.mInterstitialAd.show();
                                }
                                ClientActivity.this.odliczanieDoReklamy = 0;
                            } else {
                                ClientActivity.this.odliczanieDoReklamy = ClientActivity.this.odliczanieDoReklamy + 1;
                            }
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            new Thread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.wait_for_banlist = true;
                                    ClientActivity.this.api.getBans();
                                    final String tempLastquery = ClientActivity.this.last_error_query;
                                    Snackbar.make(ClientActivity.this.listViewComplains, "Ban list: " + tempLastquery, -1).setAction("Action", null).show();
                                    if (!tempLastquery.contains("ok")) {
                                        ClientActivity.this.wait_for_banlist = false;
                                        ClientActivity.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                if (tempLastquery.contains("database")) {
                                                    ClientActivity.this.infoComplaints.setText("Database is empty, there is no bans!");
                                                }
                                                ClientActivity.this.pokazPusteComplains(1);
                                            }
                                        });
                                    }
                                    ClientActivity.this.blokadaWywolan = false;
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                }
                            }).start();
                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                            return;
                        }
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            if (ClientActivity.this.odliczanieDoReklamy >= 3) {
                                if (ClientActivity.this.mInterstitialAd.isLoaded() && !ClientActivity.this.brakreklam) {
                                    ClientActivity.this.mInterstitialAd.show();
                                }
                                ClientActivity.this.odliczanieDoReklamy = 0;
                            } else {
                                ClientActivity.this.odliczanieDoReklamy = ClientActivity.this.odliczanieDoReklamy + 1;
                            }
                            ClientActivity.this.listViewComplains.setAdapter(null);
                            new Thread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.wait_for_complainlist = true;
                                    ClientActivity.this.api.getComplaints();
                                    final String tempLastquery = ClientActivity.this.last_error_query;
                                    Snackbar.make(ClientActivity.this.listViewComplains, "Complaint list: " + tempLastquery, -1).setAction("Action", null).show();
                                    if (!tempLastquery.contains("ok")) {
                                        ClientActivity.this.wait_for_complainlist = false;
                                        ClientActivity.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                if (tempLastquery.contains("database")) {
                                                    ClientActivity.this.infoComplaints.setText("Database is empty, there is no complaints!");
                                                }
                                                ClientActivity.this.pokazPusteComplains(2);
                                            }
                                        });
                                    }
                                    ClientActivity.this.blokadaWywolan = false;
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                }
                            }).start();
                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                            return;
                        }
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        if (!ClientActivity.this.blokadaWywolan) {
                            ClientActivity.this.blokadaWywolan = true;
                            ClientActivity.this.liczbaNieodczytanychOfflineMsg = 0;
                            ClientActivity.this.textNumberOfflineMsg.setVisibility(8);
                            view.findViewById(R.id.imgRightMenu).setVisibility(0);
                            if (ClientActivity.this.odliczanieDoReklamy >= 3) {
                                if (ClientActivity.this.mInterstitialAd.isLoaded() && !ClientActivity.this.brakreklam) {
                                    ClientActivity.this.mInterstitialAd.show();
                                }
                                ClientActivity.this.odliczanieDoReklamy = 0;
                            } else {
                                ClientActivity.this.odliczanieDoReklamy = ClientActivity.this.odliczanieDoReklamy + 1;
                            }
                            ClientActivity.this.listViewComplains.setAdapter(null);
                            new Thread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.wait_for_offlinemessagelist = true;
                                    ClientActivity.this.api.getOfflineMessages();
                                    final String tempLastquery = ClientActivity.this.last_error_query;
                                    Snackbar.make(ClientActivity.this.listViewComplains, "Offline Message list: " + tempLastquery, -1).setAction("Action", null).show();
                                    if (!tempLastquery.contains("ok")) {
                                        ClientActivity.this.wait_for_offlinemessagelist = false;
                                        ClientActivity.this.runOnUiThread(new Runnable() {
                                            public void run() {
                                                if (tempLastquery.contains("database")) {
                                                    ClientActivity.this.infoComplaints.setText("Database is empty, there is no offline messages!");
                                                }
                                                ClientActivity.this.pokazPusteComplains(3);
                                            }
                                        });
                                    }
                                    ClientActivity.this.blokadaWywolan = false;
                                    Runtime.getRuntime().gc();
                                    System.gc();
                                }
                            }).start();
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                            view.findViewById(R.id.imgRightMenu).setVisibility(8);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        this.leftList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.selected_channeluser = (OsobyIchannel) ClientActivity.this.arrayListMieszankiListy.get(position - 1);
                ClientActivity.this.mActionMode = ClientActivity.this.startActionMode(ClientActivity.this.mActionModeCallback);
                view.setSelected(true);
                ClientActivity.this.clearSelectOnLeftList();
                ClientActivity.this.selected_row = position - 1;
                ClientActivity.this.imgPodglad = (ImageView) view.findViewById(R.id.image_podglad);
                ClientActivity.this.imgPodglad.setVisibility(0);
            }
        });
        this.toggleActionWartosc = new ActionBarDrawerToggle(this, this.drawer, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                if (ClientActivity.this.leftList.isEnabled()) {
                    Snackbar.make(drawerView, "REFRESHING...", 0).setAction("Action", null).show();
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.getClients();
                            ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                        }
                    });
                }
                ClientActivity.this.zliczCreatedBotsRunning();
                ClientActivity.this.zliczBledyUprawnien();
                ClientActivity.this.zliczNieodczytaneOfflineMsg();
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ClientActivity.this.wyborZakcjiListy = false;
                ClientActivity.this.leftList.clearChoices();
                if (ClientActivity.this.mActionMode != null) {
                    ClientActivity.this.mActionMode.finish();
                }
            }
        };
        this.toggle = this.toggleActionWartosc;
        this.drawer.setDrawerListener(this.toggle);
        this.toggle.syncState();
        Bundle bBundle = getIntent().getExtras();
        if (bBundle != null) {
            if (bBundle.containsKey("wifi")) {
                this.wifi = ((Boolean) bBundle.get("wifi")).booleanValue();
            }
            if (bBundle.containsKey("port")) {
                this.portIntent = ((Integer) bBundle.get("port")).intValue();
            }
            if (bBundle.containsKey("ipSelected")) {
                this.hostIP = (String) bBundle.get("ipSelected");
            }
        }
        if (this.wifi) {
            this.config.setHost(this.hostIP);
            this.config.setDebugToFile(false);
            this.config.setDebugLevel(Level.OFF);
        } else {
            if (((WifiManager) getSystemService("wifi")).getConnectionInfo().getIpAddress() <= 1) {
                Toast.makeText(this, "Connection by 3G", 1).show();
            } else {
                this.wifi = true;
            }
            this.config.setHost(this.hostIP);
            this.config.setDebugToFile(false);
            this.config.setDebugLevel(Level.OFF);
            this.config.setQueryPort(this.portIntent);
        }
        this.txtNickname = (TextView) findViewById(R.id.txtNickname);
        this.title_text = (TextView) findViewById(R.id.title_channel);
        this.title_text.setText("Channel");
        this.img1 = (ImageView) findViewById(R.id.ja);
        this.jaMikro = (ImageView) findViewById(R.id.jaMikro);
        this.jaSpeaker = (ImageView) findViewById(R.id.jaSpeaker);
        this.jaAway = (ImageView) findViewById(R.id.jaAway);
        this.commanderImage = (ImageView) findViewById(R.id.imageViewCommander);
        this.jaMikro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.vMikro = v;
                new Thread(new Runnable() {
                    public void run() {
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.vMikro.startAnimation(AnimationUtils.loadAnimation(ClientActivity.this, R.anim.image_click));
                            }
                        });
                        if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).input) {
                            ClientActivity.this.api.setMikro("0");
                            try {
                                ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).input = false;
                            } catch (Exception e) {
                            }
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.jaMikro.setImageBitmap(ClientActivity.this.jaMikroBitmapOn);
                                }
                            });
                            return;
                        }
                        ClientActivity.this.api.setMikro("1");
                        try {
                            ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).input = true;
                        } catch (Exception e2) {
                        }
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.jaMikro.setImageBitmap(ClientActivity.this.jaMikroBitmapOff);
                            }
                        });
                    }
                }).start();
            }
        });
        this.jaSpeaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.vSpeaker = v;
                new Thread(new Runnable() {
                    public void run() {
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.vSpeaker.startAnimation(AnimationUtils.loadAnimation(ClientActivity.this, R.anim.image_click));
                            }
                        });
                        if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).output) {
                            ClientActivity.this.api.setSpeaker("0");
                            try {
                                ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).output = false;
                            } catch (Exception e) {
                            }
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.jaSpeaker.setImageBitmap(ClientActivity.this.jaSpeakerBitmapOn);
                                }
                            });
                            return;
                        }
                        ClientActivity.this.api.setSpeaker("1");
                        try {
                            ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).output = true;
                        } catch (Exception e2) {
                        }
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.jaSpeaker.setImageBitmap(ClientActivity.this.jaSpeakerBitmapOff);
                            }
                        });
                    }
                }).start();
            }
        });
        this.jaAway.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.vAway = v;
                new Thread(new Runnable() {
                    public void run() {
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.vAway.startAnimation(AnimationUtils.loadAnimation(ClientActivity.this, R.anim.image_click));
                            }
                        });
                        if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).away) {
                            ClientActivity.this.api.setAway("0");
                            try {
                                ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).away = false;
                            } catch (Exception e) {
                            }
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.jaAway.setImageBitmap(ClientActivity.this.jaAwayBitmapOn);
                                    ClientActivity.this.txtNickname.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).name);
                                }
                            });
                            return;
                        }
                        ClientActivity.this.api.setAway("1");
                        try {
                            ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).away = true;
                        } catch (Exception e2) {
                        }
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.jaAway.setImageBitmap(ClientActivity.this.jaAwayBitmapOff);
                                if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).awayMessage.length() > 0) {
                                    ClientActivity.this.txtNickname.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).name + " [" + ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).awayMessage + "]");
                                }
                            }
                        });
                    }
                }).start();
            }
        });
        this.txtNaAgain = (TextView) findViewById(R.id.txtAgain);
        this.againButton = (Button) findViewById(R.id.againB);
        this.againButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        if (ClientActivity.this.query != null) {
                            ClientActivity.this.query.exit();
                        }
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.progressAgain.setVisibility(0);
                                ClientActivity.this.againButton.setVisibility(4);
                                ClientActivity.this.txtNaAgain.setText("Recieving information from TS...");
                            }
                        });
                        ClientActivity.this.executor.execute(ClientActivity.this.testConnection());
                    }
                }).start();
            }
        });
        this.progressAgain = (ProgressBar) findViewById(R.id.progressBar2);
        this.executor = Executors.newFixedThreadPool(100);
        this.executor.execute(testConnection());
        this.listViewUsers = (ListView) findViewById(R.id.listView);
        this.listViewUsers.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            }
        });
        this.listViewUsers.setChoiceMode(1);
        this.listViewUsers.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.main, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }
        });
        getWindow().setSoftInputMode(3);
        this.buttonSend3 = (Button) findViewById(R.id.buttonSend);
        this.listView3 = (ListView) findViewById(R.id.listView1);
        this.listView3.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                ClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.chatArrayAdapter3 = new ChatArrayAdapter(this, R.layout.listview_client_chat_singlemessage);
        this.listView3.setAdapter(this.chatArrayAdapter3);
        this.chatText3 = (EditText) findViewById(R.id.chatText);
        this.chatText3.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 0 && keyCode == 66) {
                    return ClientActivity.this.sendChatMessage();
                }
                return false;
            }
        });
        this.chatText3.setSelected(false);
        this.buttonSend3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (ClientActivity.this.chatText3.getText().length() > 0) {
                    ClientActivity.this.sendChatMessage();
                }
            }
        });
        this.listView3.setTranscriptMode(2);
        this.listView3.setAdapter(this.chatArrayAdapter3);
        this.chatArrayAdapter3.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                ClientActivity.this.listView3.setSelection(ClientActivity.this.chatArrayAdapter3.getCount() - 1);
            }
        });
        this.channel_name_chat = (TextView) findViewById(R.id.name_channel);
        this.chat_image_button = (ImageView) findViewById(R.id.chatImageButton);
        this.chat_image_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.chatActivity();
                if (!ClientActivity.this.wiadomosci_odczytane) {
                    ClientActivity.this.wiadomosci_odczytane = true;
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.chat_image_button.setImageBitmap(ClientActivity.this.chatImageBitmapOff);
                        }
                    });
                }
            }
        });
        this.edit_image_button = (ImageView) findViewById(R.id.editImageButton);
        this.edit_image_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.edit_image_button.startAnimation(AnimationUtils.loadAnimation(ClientActivity.this, R.anim.image_click));
                Builder builder = new Builder(ClientActivity.this);
                View dialogLay = ClientActivity.this.getLayoutInflater().inflate(R.layout.client_alertdialog_update_you, null);
                builder.setView(dialogLay);
                final EditText upNickname = (EditText) dialogLay.findViewById(R.id.editTextNickname);
                final CheckBox upNicknameBox = (CheckBox) dialogLay.findViewById(R.id.checkBoxNickname);
                final EditText upAwayMessage = (EditText) dialogLay.findViewById(R.id.editTextAway);
                final CheckBox upAwayMessageBox = (CheckBox) dialogLay.findViewById(R.id.checkBoxAway);
                final ToggleButton upCommander = (ToggleButton) dialogLay.findViewById(R.id.toggleButtonCommander);
                final CheckBox upCommanderBox = (CheckBox) dialogLay.findViewById(R.id.checkBoxCommander);
                upNickname.setOnFocusChangeListener(new OnFocusChangeListener() {
                    public void onFocusChange(View view, boolean b) {
                        ClientActivity.this.hideKeyboard(view);
                    }
                });
                upAwayMessage.setOnFocusChangeListener(new OnFocusChangeListener() {
                    public void onFocusChange(View view, boolean b) {
                        ClientActivity.this.hideKeyboard(view);
                    }
                });
                upNicknameBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (upNicknameBox.isChecked()) {
                            upNickname.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).name);
                            upNickname.setVisibility(0);
                            return;
                        }
                        upNickname.setVisibility(8);
                    }
                });
                upAwayMessageBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (upAwayMessageBox.isChecked()) {
                            upAwayMessage.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).awayMessage);
                            upAwayMessage.setVisibility(0);
                            return;
                        }
                        upAwayMessage.setVisibility(8);
                    }
                });
                upCommanderBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (upCommanderBox.isChecked()) {
                            if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).commander) {
                                upCommander.setChecked(true);
                            } else {
                                upCommander.setChecked(false);
                            }
                            upCommander.setVisibility(0);
                            return;
                        }
                        upCommander.setVisibility(8);
                    }
                });
                builder.setPositiveButton("Update", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (upNicknameBox.isChecked()) {
                            try {
                                ClientActivity.this.api.setNickname(upNickname.getText().toString());
                                Snackbar.make(ClientActivity.this.leftList, "Edit nickname: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
                            } catch (Exception ex) {
                                Snackbar.make(ClientActivity.this.leftList, "Edit nickname: " + ex.getMessage(), 0).setAction("Action", null).show();
                            }
                        }
                        if (upAwayMessageBox.isChecked()) {
                            String m_Text2 = upAwayMessage.getText().toString();
                            try {
                                ClientActivity.this.api.setAwayMessage(m_Text2);
                                Snackbar.make(ClientActivity.this.leftList, "Away message: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
                                if (ClientActivity.this.last_error_query.contains("ok")) {
                                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).awayMessage = m_Text2;
                                    if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).away) {
                                        if (m_Text2.length() > 0) {
                                            ClientActivity.this.txtNickname.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).name + " [" + m_Text2 + "]");
                                        } else {
                                            ClientActivity.this.txtNickname.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).name);
                                        }
                                    }
                                }
                            } catch (Exception ex2) {
                                Snackbar.make(ClientActivity.this.leftList, "Away message: " + ex2.getMessage(), 0).setAction("Action", null).show();
                            }
                        }
                        if (upCommanderBox.isChecked()) {
                            Boolean m_Text22 = Boolean.valueOf(upCommander.isChecked());
                            try {
                                HashMap<ClientProperty, String> options = new HashMap();
                                if (m_Text22.booleanValue()) {
                                    options.put(ClientProperty.CLIENT_IS_CHANNEL_COMMANDER, "1");
                                } else {
                                    options.put(ClientProperty.CLIENT_IS_CHANNEL_COMMANDER, "0");
                                }
                                ClientActivity.this.api.updateClient(options);
                                if (ClientActivity.this.last_error_query.contains("ok")) {
                                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.myIDclientOnserver))).commander = m_Text22.booleanValue();
                                    if (m_Text22.booleanValue()) {
                                        ClientActivity.this.commanderImage.setVisibility(0);
                                    } else {
                                        ClientActivity.this.commanderImage.setVisibility(8);
                                    }
                                }
                                Snackbar.make(ClientActivity.this.leftList, "Channel Commander: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
                            } catch (Exception ex22) {
                                Snackbar.make(ClientActivity.this.leftList, "Channel Commander: " + ex22.getMessage(), 0).setAction("Action", null).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        this.viewTolbar = findViewById(R.id.toolbar);
        this.viewId1 = findViewById(R.id.id1);
        this.viewId2 = findViewById(R.id.id2);
        this.viewId3 = findViewById(R.id.id3);
        this.viewId4_complain = findViewById(R.id.id4_complain_list);
        LinearLayout adviewLayout = (LinearLayout) findViewById(R.id.adviewLayout);
        if (this.mAdView == null) {
            this.mAdView = new AdView(AdMobActivity.AdMobMemoryLeakWorkAroundActivity);
            this.mAdView.setAdSize(AdSize.SMART_BANNER);
            this.mAdView.setAdUnitId("ca-app-pub-7983017215405047/2451896819");
            this.mAdView.loadAd(new AdRequest.Builder().addTestDevice("3E3CBF2A499254ADE9049AD28555B32F").addTestDevice("8199BF7189EE38B4B54EC01D9FE8BE97").build());
            adviewLayout.addView(this.mAdView);
        } else {
            ((LinearLayout) this.mAdView.getParent()).removeAllViews();
            adviewLayout.addView(this.mAdView);
            this.mAdView.loadAd(new AdRequest.Builder().build());
        }
        this.mInterstitialAd = new InterstitialAd(getApplicationContext());
        this.mInterstitialAd.setAdUnitId("ca-app-pub-7983017215405047/9579394016");
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                ClientActivity.this.requestNewInterstitial();
            }
        });
        requestNewInterstitial();
        this.buttonAd = (ImageView) findViewById(R.id.buttonAds);
        this.buttonAd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        ClientActivity.this.buttonAd.startAnimation(AnimationUtils.loadAnimation(ClientActivity.this, R.anim.image_click));
                    }
                });
            }
        });
        this.listViewComplains = (ListView) findViewById(R.id.listViewComplains);
        this.numerOfComplains = (TextView) findViewById(R.id.txtComplainNumber);
        this.dataOnComplains = (TextView) findViewById(R.id.textViewData);
        this.infoComplaints = (TextView) findViewById(R.id.txtInfoComplaints);
        this.imageViewComplains = (ImageView) findViewById(R.id.imgComplainLogo);
        this.textTitleComplains = (TextView) findViewById(R.id.textTitleComplains);
        this.imageButtonBanAdd = (ImageView) findViewById(R.id.imageButtonBanAdd);
        this.progressOfflineMsg = (ProgressBar) findViewById(R.id.progressBarOfflineMSG);
        this.listViewComplains.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String nameClass = parent.getAdapter().getClass().getSimpleName();
                if (nameClass.contains("Complains")) {
                    ClientActivity.this.showComplainDialog(position);
                } else if (nameClass.contains("ClientBans")) {
                    ClientActivity.this.showBansDialog(position);
                } else if (nameClass.contains("Offline")) {
                    ClientActivity.this.wait_for_get_offline = true;
                    ClientActivity.this.progressOfflineMsg.setVisibility(0);
                    new Thread(new Runnable() {
                        public void run() {
                            ClientActivity.this.api.getOfflineMessage(((Message) ClientActivity.this.listaOffline.get(position)).getId());
                            if (!ClientActivity.this.last_error_query.contains("ok")) {
                                ClientActivity.this.progressOfflineMsg.setVisibility(8);
                                Snackbar.make(ClientActivity.this.listViewComplains, "Get Offline Message: " + ClientActivity.this.last_error_query, -1).show();
                                ClientActivity.this.wait_for_get_offline = false;
                            }
                        }
                    }).start();
                }
            }
        });
        this.imageButtonBanAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.imageButtonBanAdd.startAnimation(AnimationUtils.loadAnimation(ClientActivity.this, R.anim.image_click));
                ClientActivity.this.showAlertDialogBanAdd();
            }
        });
    }

    private void zliczBledyUprawnien() {
        if (this.liczbaNiesprawdzonychLogsow > 0) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.textNumberLogs.setText(ClientActivity.this.liczbaNiesprawdzonychLogsow + BuildConfig.FLAVOR);
                    ClientActivity.this.textNumberLogs.setVisibility(0);
                }
            });
        }
    }

    private void zliczCreatedBotsRunning() {
        if (this.liczbaAktywnychBotow > 0) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.textNumberCreateBots.setText(ClientActivity.this.liczbaAktywnychBotow + BuildConfig.FLAVOR);
                    ClientActivity.this.textNumberCreateBots.setVisibility(0);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.textNumberCreateBots.setVisibility(8);
                }
            });
        }
    }

    private void zliczNieodczytaneOfflineMsg() {
        new Thread(new Runnable() {
            public void run() {
                ClientActivity.this.myUnreadMsg();
            }
        }).start();
    }

    private long mod(long x, int y) {
        long result = x % ((long) y);
        return result < 0 ? result + ((long) y) : result;
    }

    private void showBansDialog(int position) {
        final Ban _selectedBan = (Ban) this.listaBanow.get(position);
        Builder builderBan = new Builder(this);
        View dialogLayA = getLayoutInflater().inflate(R.layout.client_alertdialog_ban_add, null);
        builderBan.setView(dialogLayA);
        ((TextView) dialogLayA.findViewById(R.id.txtCreateBan)).setText("Edit ban with ID: " + _selectedBan.getId());
        final EditText editBanIp = (EditText) dialogLayA.findViewById(R.id.editBanIp);
        final EditText editBanName = (EditText) dialogLayA.findViewById(R.id.editBanName);
        final EditText editBanUnique = (EditText) dialogLayA.findViewById(R.id.editBanUnique);
        final EditText editBanReason = (EditText) dialogLayA.findViewById(R.id.editTextReason);
        dialogLayA.findViewById(R.id.line1Ban).setVisibility(0);
        TextView textNameCreator = (TextView) dialogLayA.findViewById(R.id.txtViewBanOwner);
        textNameCreator.setVisibility(0);
        this.nowBot = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TextView textView = textNameCreator;
        textView.setText(_selectedBan.getInvokerName() + " on " + this.nowBot.format(Long.valueOf(_selectedBan.getCreatedDate().getTime())));
        if (_selectedBan.getBannedIp().length() > 0) {
            editBanIp.setText(_selectedBan.getBannedIp());
        }
        if (_selectedBan.getBannedName().length() > 0) {
            editBanName.setText(_selectedBan.getBannedName());
        }
        if (_selectedBan.getBannedUId().length() > 0) {
            editBanUnique.setText(_selectedBan.getBannedUId());
        }
        if (_selectedBan.getReason().length() > 0) {
            editBanReason.setText(_selectedBan.getReason());
        }
        final EditText editTime = (EditText) dialogLayA.findViewById(R.id.editTextTimeBanClient);
        Spinner spinnerBanTime = (Spinner) dialogLayA.findViewById(R.id.spinnerBanClient);
        ArrayAdapter<CharSequence> adapterTimeBan = ArrayAdapter.createFromResource(this, R.array.time_ban_client, 17367048);
        adapterTimeBan.setDropDownViewResource(R.layout.custom_spinner_item);
        spinnerBanTime.setAdapter(adapterTimeBan);
        long xTime = _selectedBan.getDuration();
        if (xTime == 0) {
            this.selectedTimeBan = 4;
        } else if (xTime > 0) {
            if (mod(xTime, 86400) == 0) {
                this.selectedTimeBan = 3;
                xTime /= 86400;
            } else if (mod(xTime, 3600) == 0) {
                this.selectedTimeBan = 2;
                xTime /= 3600;
            } else if (mod(xTime, 60) == 0) {
                this.selectedTimeBan = 1;
                xTime /= 60;
            } else {
                this.selectedTimeBan = 0;
            }
        }
        spinnerBanTime.setSelection(this.selectedTimeBan);
        editTime.setText(String.valueOf(xTime));
        final Spinner spinner = spinnerBanTime;
        spinnerBanTime.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int possitionTime, long l) {
                ClientActivity.this.selectedTimeBan = possitionTime;
                spinner.setSelection(possitionTime);
                if (ClientActivity.this.selectedTimeBan == 4) {
                    editTime.setEnabled(false);
                } else {
                    editTime.setEnabled(true);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        editBanIp.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editBanName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editBanUnique.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editBanReason.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editTime.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                if (editTime.getText().length() <= 0) {
                    editTime.setText("1");
                }
                ClientActivity.this.hideKeyboard(view);
            }
        });
        spinner = spinnerBanTime;
        editTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (editTime.getText().length() > 0 && editTime.getText().toString().startsWith("0")) {
                    spinner.setSelection(4);
                    editTime.setText("1");
                }
            }
        });
        builderBan.setPositiveButton("Save", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                long timeToBan = 0;
                switch (ClientActivity.this.selectedTimeBan) {
                    case R.styleable.View_android_theme /*0*/:
                        timeToBan = Long.parseLong(editTime.getText().toString());
                        break;
                    case R.styleable.View_android_focusable /*1*/:
                        timeToBan = Long.parseLong(editTime.getText().toString()) * 60;
                        break;
                    case R.styleable.View_paddingStart /*2*/:
                        timeToBan = Long.parseLong(editTime.getText().toString()) * 3600;
                        break;
                    case R.styleable.View_paddingEnd /*3*/:
                        timeToBan = Long.parseLong(editTime.getText().toString()) * 86400;
                        break;
                    case R.styleable.View_theme /*4*/:
                        timeToBan = 0;
                        break;
                }
                if (timeToBan > 0) {
                    ClientActivity.this.api.addBan(editBanIp.getText().toString(), editBanName.getText().toString(), editBanUnique.getText().toString(), timeToBan, editBanReason.getText().toString());
                } else {
                    ClientActivity.this.api.addBan(editBanIp.getText().toString(), editBanName.getText().toString(), editBanUnique.getText().toString(), 0, editBanReason.getText().toString());
                }
                if (ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                        }
                    });
                    ClientActivity.this.api.deleteBan(_selectedBan.getId());
                    ClientActivity.this.wait_for_banadd = true;
                    ClientActivity.this.api.getBans();
                }
                Snackbar.make(ClientActivity.this.leftList, "Ban edit: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
            }
        });
        builderBan.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderBan.show();
    }

    private void showAlertDialogBanAdd() {
        Builder builderBan = new Builder(this);
        View dialogLayA = getLayoutInflater().inflate(R.layout.client_alertdialog_ban_add, null);
        builderBan.setView(dialogLayA);
        final EditText editBanIp = (EditText) dialogLayA.findViewById(R.id.editBanIp);
        final EditText editBanName = (EditText) dialogLayA.findViewById(R.id.editBanName);
        final EditText editBanUnique = (EditText) dialogLayA.findViewById(R.id.editBanUnique);
        final EditText editBanReason = (EditText) dialogLayA.findViewById(R.id.editTextReason);
        final EditText editTime = (EditText) dialogLayA.findViewById(R.id.editTextTimeBanClient);
        final Spinner spinnerBanTime = (Spinner) dialogLayA.findViewById(R.id.spinnerBanClient);
        ArrayAdapter<CharSequence> adapterTimeBan = ArrayAdapter.createFromResource(this, R.array.time_ban_client, 17367048);
        adapterTimeBan.setDropDownViewResource(R.layout.custom_spinner_item);
        spinnerBanTime.setAdapter(adapterTimeBan);
        spinnerBanTime.setSelection(3);
        spinnerBanTime.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int possitionTime, long l) {
                ClientActivity.this.selectedTimeBan = possitionTime;
                spinnerBanTime.setSelection(possitionTime);
                if (ClientActivity.this.selectedTimeBan == 4) {
                    editTime.setEnabled(false);
                } else {
                    editTime.setEnabled(true);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        editBanIp.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editBanName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editBanUnique.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editBanReason.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editTime.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                if (editTime.getText().length() <= 0) {
                    editTime.setText("1");
                }
                ClientActivity.this.hideKeyboard(view);
            }
        });
        editTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (editTime.getText().length() > 0 && editTime.getText().toString().startsWith("0")) {
                    spinnerBanTime.setSelection(4);
                    editTime.setText("1");
                }
            }
        });
        builderBan.setPositiveButton("Add Ban", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                long timeToBan = 0;
                switch (ClientActivity.this.selectedTimeBan) {
                    case R.styleable.View_android_theme /*0*/:
                        timeToBan = Long.parseLong(editTime.getText().toString());
                        break;
                    case R.styleable.View_android_focusable /*1*/:
                        timeToBan = Long.parseLong(editTime.getText().toString()) * 60;
                        break;
                    case R.styleable.View_paddingStart /*2*/:
                        timeToBan = Long.parseLong(editTime.getText().toString()) * 3600;
                        break;
                    case R.styleable.View_paddingEnd /*3*/:
                        timeToBan = Long.parseLong(editTime.getText().toString()) * 86400;
                        break;
                    case R.styleable.View_theme /*4*/:
                        timeToBan = 0;
                        break;
                }
                if (timeToBan > 0) {
                    ClientActivity.this.api.addBan(editBanIp.getText().toString(), editBanName.getText().toString(), editBanUnique.getText().toString(), timeToBan, editBanReason.getText().toString());
                } else {
                    ClientActivity.this.api.addBan(editBanIp.getText().toString(), editBanName.getText().toString(), editBanUnique.getText().toString(), 0, editBanReason.getText().toString());
                }
                if (ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                        }
                    });
                    ClientActivity.this.wait_for_banadd = true;
                    ClientActivity.this.api.getBans();
                }
                Snackbar.make(ClientActivity.this.leftList, "Ban add: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
            }
        });
        builderBan.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderBan.show();
    }

    private void pokazPusteComplains(int listaNumer) {
        this.oknoLists = true;
        this.nowBot = new SimpleDateFormat("yyyy-MM-dd");
        String dateBot = this.nowBot.format(Calendar.getInstance().getTime());
        this.viewTolbar.setVisibility(8);
        this.viewId1.setVisibility(8);
        if (this.drawer.isDrawerOpen(8388611)) {
            this.drawer.closeDrawer(8388611);
            clearSelectOnLeftList();
        }
        this.imageButtonBanAdd.setVisibility(8);
        this.viewId4_complain.setVisibility(0);
        this.numerOfComplains.setText("empty");
        switch (listaNumer) {
            case R.styleable.View_android_focusable /*1*/:
                this.textTitleComplains.setText("Number of bans:");
                this.imageViewComplains.setImageResource(R.drawable.c_block_white);
                this.imageButtonBanAdd.setVisibility(0);
                break;
            case R.styleable.View_paddingStart /*2*/:
                this.textTitleComplains.setText("Number of complaints:");
                this.imageViewComplains.setImageResource(R.drawable.c_skarga);
                break;
            case R.styleable.View_paddingEnd /*3*/:
                this.textTitleComplains.setText("Number of offline messages:");
                this.imageViewComplains.setImageResource(R.drawable.ic_menu_sort_by_size);
                break;
        }
        this.dataOnComplains.setText(dateBot);
        this.infoComplaints.setVisibility(0);
        if (this.last_error_query.contains("permission")) {
            this.infoComplaints.setText("You don't have permission to check this list!");
        }
    }

    private void clearSelectOnLeftList() {
        if (this.selected_row != -1) {
            this.imgPodglad.setVisibility(8);
            this.adapter2.notifyDataSetChanged();
            this.selected_row = -1;
        }
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("3E3CBF2A499254ADE9049AD28555B32F").addTestDevice("8199BF7189EE38B4B54EC01D9FE8BE97").build());
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    private void callClientInfo(final int idClienta) {
        if (!this.blokadaWywolan) {
            this.blokadaWywolan = true;
            new Thread(new Runnable() {
                public void run() {
                    ClientInfo infoClient = ClientActivity.this.api.getClientInfo(idClienta, true);
                    Intent intentC = new Intent(ClientActivity.this, ServerClientActivity.class);
                    intentC.putExtra("client_is_talker", infoClient.canTalk());
                    intentC.putExtra("client_away_message", infoClient.getAwayMessage());
                    intentC.putExtra("client_channel_group_id", infoClient.getChannelGroupId());
                    intentC.putExtra("client_country", infoClient.getCountry());
                    intentC.putExtra("client_database_id", infoClient.getDatabaseId());
                    intentC.putExtra("client_nickname", infoClient.getNickname());
                    intentC.putExtra("client_talk_power", infoClient.getTalkPower());
                    intentC.putExtra("client_away", infoClient.isAway());
                    intentC.putExtra("client_is_muted", infoClient.isMuted());
                    intentC.putExtra("client_is_channel_commander", infoClient.isChannelCommander());
                    intentC.putExtra("client_input_hardware", infoClient.isInputHardware());
                    intentC.putExtra("client_input_muted", infoClient.isInputMuted());
                    intentC.putExtra("client_output_hardware", infoClient.isOutputHardware());
                    intentC.putExtra("client_output_muted", infoClient.isOutputMuted());
                    intentC.putExtra("client_is_priority_speaker", infoClient.isPrioritySpeaker());
                    intentC.putExtra("client_is_recording", infoClient.isRecording());
                    intentC.putExtra("client_unread_messages", infoClient.getUnreadMessages());
                    intentC.putExtra("client_servergroups", infoClient.getServerGroupsStr());
                    intentC.putExtra("client_talk_request", infoClient.isRequestingToTalk());
                    intentC.putExtra("client_talk_request_msg", infoClient.getTalkRequestMessage());
                    intentC.putExtra("client_description", infoClient.getDescription());
                    intentC.putExtra("client_volume_modificator", infoClient.getVolume());
                    try {
                        intentC.putExtra("client_channel_name", ((Channel) ClientActivity.this.Hchannels.get(Integer.valueOf(ClientActivity.this.selected_channeluser.idChannelInfoClient))).getName());
                    } catch (Exception e) {
                        intentC.putExtra("client_channel_name", "name");
                    }
                    intentC.putExtra("client_unique_identifier", infoClient.getUniqueIdentifier());
                    intentC.putExtra("client_type", infoClient.getType());
                    intentC.putExtra("client_nickname_phonetic", infoClient.getPhoneticNickname());
                    intentC.putExtra("client_meta_data", infoClient.getMetaData());
                    intentC.putExtra("client_outputonly_muted", infoClient.isOutputOnlyMuted());
                    intentC.putExtra("client_icon_id", infoClient.getIconId());
                    intentC.putExtra("client_needed_serverquery_view_power", infoClient.getNeededServerQueryViewPower());
                    intentC.putExtra("client_channel_group_inherited_channel_id", infoClient.getChannelGroupInheritedChannelId());
                    ClientActivity.this.startActivity(intentC);
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                        }
                    });
                    Runtime.getRuntime().gc();
                    System.gc();
                    ClientActivity.this.blokadaWywolan = false;
                }
            }).start();
        }
    }

    private void showComplainDialog(int position) {
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_complaint, null);
        TextView targetDesc = (TextView) dialogLay.findViewById(R.id.textViewComplainDesc);
        TextView ownerComplain = (TextView) dialogLay.findViewById(R.id.txtViewComplaintOwner);
        ((TextView) dialogLay.findViewById(R.id.textViewTarget)).setText(((Complaint) this.listaSkarg.get(position)).getTargetName());
        targetDesc.setText(((Complaint) this.listaSkarg.get(position)).getMessage());
        this.now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ownerComplain.setText(((Complaint) this.listaSkarg.get(position)).getSourceName() + " on " + this.now.format(Long.valueOf(((Complaint) this.listaSkarg.get(position)).getTimestamp().getTime())));
        builder.setView(dialogLay);
        builder.setPositiveButton("Close", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void callMoveChannel() {
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_move_channel, null);
        builder.setView(dialogLay);
        final CheckBox parentBox = (CheckBox) dialogLay.findViewById(R.id.checkBoxParent);
        parentBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (parentBox.isChecked()) {
                    ClientActivity.this.spinnerMove.setVisibility(8);
                } else {
                    ClientActivity.this.spinnerMove.setVisibility(0);
                }
            }
        });
        this.spinnerMove = (Spinner) dialogLay.findViewById(R.id.spinnerChannel2);
        this.labelUser = (TextView) dialogLay.findViewById(R.id.labelChannel);
        this.labelUser.setText(((Channel) this.Hchannels.get(Integer.valueOf(this.selected_channeluser.idChannel))).getName());
        List<String> categories = new ArrayList();
        for (MyChannelClass a : this.lista_channel_array) {
            categories.add(a.name);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, 17367048, categories);
        dataAdapter.setDropDownViewResource(17367049);
        this.spinnerMove.setAdapter(dataAdapter);
        this.spinnerMove.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.channelIDtoMove = ((MyChannelClass) ClientActivity.this.lista_channel_array.get(ClientActivity.this.spinnerMove.getSelectedItemPosition())).id;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        builder.setPositiveButton("Move into", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (parentBox.isChecked()) {
                    ClientActivity.this.callMoveChannel(((Channel) ClientActivity.this.Hchannels.get(Integer.valueOf(ClientActivity.this.selected_channeluser.idChannel))).getId(), 0);
                } else {
                    ClientActivity.this.callMoveChannel(((Channel) ClientActivity.this.Hchannels.get(Integer.valueOf(ClientActivity.this.selected_channeluser.idChannel))).getId(), ClientActivity.this.channelIDtoMove);
                }
                if (ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.getClients();
                            ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                        }
                    });
                } else {
                    ClientActivity.this.zliczBledyUprawnien();
                }
            }
        });
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void callMoveUser(int idClient) {
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_move_client, null);
        builder.setView(dialogLay);
        this.spinnerMove = (Spinner) dialogLay.findViewById(R.id.spinnerChannel);
        this.labelUser = (TextView) dialogLay.findViewById(R.id.labelUser);
        this.labelUser.setText(((Student) this.osobyLista.get(Integer.valueOf(this.selected_channeluser.idClient))).name);
        List<String> categories = new ArrayList();
        for (MyChannelClass a : this.lista_channel_array) {
            categories.add(a.name);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, 17367048, categories);
        dataAdapter.setDropDownViewResource(17367049);
        this.spinnerMove.setAdapter(dataAdapter);
        this.spinnerMove.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.channelIDtoMove = ((MyChannelClass) ClientActivity.this.lista_channel_array.get(ClientActivity.this.spinnerMove.getSelectedItemPosition())).id;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        builder.setPositiveButton("Move into", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ClientActivity.this.callMoveClient(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(ClientActivity.this.selected_channeluser.idClient))).id, ClientActivity.this.channelIDtoMove);
                if (ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.getClients();
                            ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                        }
                    });
                } else {
                    ClientActivity.this.zliczBledyUprawnien();
                }
            }
        });
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void petla(HashMap<Integer, Integer> LM, Integer zmChannelID, Integer i) {
        List<String> zbior1 = new ArrayList();
        HashMap<Integer, Integer> zbiorPetli = new HashMap();
        for (Entry<Integer, Integer> entry2 : LM.entrySet()) {
            if (((Integer) entry2.getValue()).equals(zmChannelID)) {
                zbiorPetli.put(Integer.valueOf(((Channel) this.Hchannels.get(entry2.getKey())).getOrder()), entry2.getKey());
            }
        }
        if (zbiorPetli.size() > 0) {
            List sortedKeys = new ArrayList(zbiorPetli.keySet());
            Collections.sort(sortedKeys);
            for (Object iixx : sortedKeys) {
                this.items.add(this.Hchannels.get(zbiorPetli.get(iixx)));
                i = Integer.valueOf(i.intValue() + 1);
                petla(LM, (Integer) zbiorPetli.get(iixx), i);
            }
        }
    }

    private Runnable getChannels() {
        return new Runnable() {
            public void run() {
                ClientActivity.this.listCh = new ArrayList();
                ClientActivity.this.liczba = 99999;
                ClientActivity.this.HM1 = new HashMap();
                ClientActivity.this.LM1 = new HashMap();
                ClientActivity.this.items = new ArrayList();
                ClientActivity.this.Hchannels = new HashMap();
                try {
                    ClientActivity.this.listCh = ClientActivity.this.api.getChannels();
                    for (Channel c : ClientActivity.this.listCh) {
                        ClientActivity.this.Hchannels.put(Integer.valueOf(c.getId()), c);
                        if (c.getParentChannelId() == 0) {
                            ClientActivity.this.HM1.put(Integer.valueOf(c.getOrder()), Integer.valueOf(c.getId()));
                            if (ClientActivity.this.liczba > c.getOrder()) {
                                ClientActivity.this.liczba = c.getOrder();
                            }
                        } else {
                            ClientActivity.this.LM1.put(Integer.valueOf(c.getId()), Integer.valueOf(c.getParentChannelId()));
                        }
                    }
                    ClientActivity.this.nazwa_kanalu = ((Channel) ClientActivity.this.Hchannels.get(Integer.valueOf(ClientActivity.this.myIDchannelOnServer))).getName().replaceAll(ClientActivity.this.regex, BuildConfig.FLAVOR);
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.title_text.setText(ClientActivity.this.nazwa_kanalu);
                            ClientActivity.this.channel_name_chat.setText(ClientActivity.this.nazwa_kanalu);
                        }
                    });
                    while (ClientActivity.this.HM1.containsKey(Integer.valueOf(ClientActivity.this.liczba))) {
                        ClientActivity.this.zmChannelID = ((Integer) ClientActivity.this.HM1.get(Integer.valueOf(ClientActivity.this.liczba))).intValue();
                        ClientActivity.this.zmObjectChannel = (Channel) ClientActivity.this.Hchannels.get(Integer.valueOf(ClientActivity.this.zmChannelID));
                        ClientActivity.this.items.add(ClientActivity.this.zmObjectChannel);
                        ClientActivity.this.petla(ClientActivity.this.LM1, Integer.valueOf(ClientActivity.this.zmChannelID), Integer.valueOf(0));
                        ClientActivity.this.liczba = ClientActivity.this.zmChannelID;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ClientActivity.this.temp_arrayListMieszankiListy = new ArrayList();
                ClientActivity.this.arrayList = ClientActivity.this.items;
                if (ClientActivity.this.lista_channel_array == null) {
                    ClientActivity.this.lista_channel_array = new ArrayList();
                } else {
                    ClientActivity.this.lista_channel_array.clear();
                }
                ClientActivity.this.po = 0;
                Iterator it = ClientActivity.this.arrayList.iterator();
                while (it.hasNext()) {
                    Channel ch = (Channel) it.next();
                    ClientActivity.this.chann = new MyChannelClass();
                    ClientActivity.this.chann.id = ch.getId();
                    ClientActivity.this.chann.name = ch.getName();
                    ClientActivity.this.chann.hasPassword = ch.hasPassword();
                    ClientActivity.this.chann.maxClients = ch.getMaxClients();
                    ClientActivity.this.chann.totalClients = ch.getTotalClients();
                    ClientActivity.this.chann.parentId = ch.getParentChannelId();
                    ClientActivity.this.lista_channel_array.add(ClientActivity.this.chann);
                    ClientActivity.this.do_paczki = new OsobyIchannel();
                    ClientActivity.this.do_paczki.hasPasswordChannel = ch.hasPassword();
                    ClientActivity.this.do_paczki.idChannel = ch.getId();
                    ClientActivity.this.do_paczki.maxClientsChannel = ch.getMaxClients();
                    ClientActivity.this.do_paczki.nameChannel = ch.getName();
                    ClientActivity.this.do_paczki.parentIdChannel = ch.getParentChannelId();
                    ClientActivity.this.do_paczki.totalClientsChannel = ch.getTotalClients();
                    ClientActivity.this.do_paczki.isPermanent = ch.isPermanent();
                    ClientActivity.this.do_paczki.isSemiPermanent = ch.isSemiPermanent();
                    ClientActivity.this.do_paczki.isDefault = ch.isDefault();
                    ClientActivity.this.temp_arrayListMieszankiListy.add(ClientActivity.this.po, ClientActivity.this.do_paczki);
                    ClientActivity clientActivity = ClientActivity.this;
                    clientActivity.po++;
                    if (ch.getTotalClients() > 0 && ClientActivity.this.zgrupowane_osoby.containsKey(Integer.valueOf(ch.getId()))) {
                        ClientActivity.this.mala_paczka_idkow = new ArrayList();
                        ClientActivity.this.mala_paczka_idkow = (List) ClientActivity.this.zgrupowane_osoby.get(Integer.valueOf(ch.getId()));
                        for (Integer ab : ClientActivity.this.mala_paczka_idkow) {
                            ClientActivity.this.do_paczki = new OsobyIchannel();
                            ClientActivity.this.do_paczki.awayClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).away;
                            ClientActivity.this.do_paczki.idClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).id;
                            ClientActivity.this.do_paczki.idClientDB = ((Student) ClientActivity.this.kopia_osob.get(ab)).idDB;
                            ClientActivity.this.do_paczki.inputClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).input;
                            ClientActivity.this.do_paczki.mutedByMeClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).mutedByMe;
                            ClientActivity.this.do_paczki.nameClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).name;
                            ClientActivity.this.do_paczki.outputClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).output;
                            ClientActivity.this.do_paczki.idChannelInfoClient = ((Student) ClientActivity.this.kopia_osob.get(ab)).channel;
                            ClientActivity.this.temp_arrayListMieszankiListy.add(ClientActivity.this.po, ClientActivity.this.do_paczki);
                            clientActivity = ClientActivity.this;
                            clientActivity.po++;
                        }
                    }
                }
                try {
                    if (ClientActivity.this.adapter2 == null) {
                        ClientActivity.this.arrayListMieszankiListy.clear();
                        ClientActivity.this.arrayListMieszankiListy.addAll(ClientActivity.this.temp_arrayListMieszankiListy);
                        ClientActivity.this.adapter2 = new ChannelArrayAdapter(ClientActivity.this.getApplicationContext(), ClientActivity.this.arrayListMieszankiListy, ClientActivity.this);
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.leftList.setAdapter(ClientActivity.this.adapter2);
                            }
                        });
                    } else {
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ClientActivity.this.arrayListMieszankiListy.clear();
                                ClientActivity.this.arrayListMieszankiListy.addAll(ClientActivity.this.temp_arrayListMieszankiListy);
                                ClientActivity.this.adapter2.notifyDataSetChanged();
                                ClientActivity.this.clearSelectOnLeftList();
                                Snackbar.make(ClientActivity.this.leftList, "READY!", -1).setAction("Action", null).show();
                            }
                        });
                    }
                    for (int temp = 0; temp <= ClientActivity.this.arrayList.size() - 1; temp++) {
                        ClientActivity.this.list_channels.put(Integer.valueOf(temp), ClientActivity.this.arrayList.get(temp));
                    }
                } catch (Exception es) {
                    es.printStackTrace();
                }
            }
        };
    }

    public String encodeTSString(String str) {
        return str.replace("\\", "\\\\").replace(" ", "\\s").replace("/", "\\/").replace("|", "\\p").replace("\b", "\\b").replace("\f", "\\f").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t").replace(new Character('\u0007').toString(), "\\a").replace(new Character('\u000b').toString(), "\\v");
    }

    private Runnable testConnection() {
        return new Runnable() {
            public void run() {
                ClientActivity.this.query = new TSQuery(ClientActivity.this.config);
                try {
                    ClientActivity.this.query.connect(ClientActivity.this);
                } catch (Exception ed) {
                    ed.printStackTrace();
                    ClientActivity.this.showText = "Can not connect to TS!\nCheck if is it still running? Try restart your Apps";
                }
                try {
                    ClientActivity.this.api = ClientActivity.this.query.getApi();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                int i = -1;
                try {
                    ClientActivity.this.api.whoAmI();
                    i = ClientActivity.this.api.whoAmI().getCLID();
                    ClientActivity.this.showText = "It looks like You are not connected to any TS Server!\nConnect to one and try again.";
                } catch (Exception e) {
                    ClientActivity.this.showText = "Can not connect to TS!\nCheck if is it still running?";
                }
                if (i > -1 || ClientActivity.this.last_error_query == "ok") {
                    ClientActivity.this.startTS();
                } else {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (ClientActivity.this.progressAgain != null) {
                                ClientActivity.this.progressAgain.setVisibility(4);
                            }
                            if (ClientActivity.this.againButton != null) {
                                ClientActivity.this.againButton.setVisibility(0);
                            }
                            if (ClientActivity.this.txtNaAgain != null) {
                                ClientActivity.this.txtNaAgain.setText(ClientActivity.this.showText);
                            }
                        }
                    });
                }
            }
        };
    }

    private void updateViewMowi(int osoba, int stan) {
        View v = this.listViewUsers.getChildAt(((Student) this.osobyLista.get(Integer.valueOf(osoba))).index - this.listViewUsers.getFirstVisiblePosition());
        if (v != null) {
            ImageView talking = (ImageView) v.findViewById(R.id.icon);
            if (stan == 0) {
                ((Student) this.osobyLista.get(Integer.valueOf(osoba))).talking = false;
                talking.setImageResource(R.drawable.c_quiet);
                return;
            }
            ((Student) this.osobyLista.get(Integer.valueOf(osoba))).talking = true;
            talking.setImageResource(R.drawable.c_talk);
        }
    }

    private void callBandwidth(int codecPozycja, int progressbarQuality) {
        int i = progressbarQuality;
        switch (codecPozycja) {
            case R.styleable.View_android_theme /*0*/:
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.49 KiB/s");
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.69 KiB/s");
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.93 KiB/s");
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.17 KiB/s");
                        return;
                    case R.styleable.View_theme /*4*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.17 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.56 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.56 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.05 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.05 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.44 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.22 KiB/s");
                        return;
                    default:
                        return;
                }
            case R.styleable.View_android_focusable /*1*/:
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.69 KiB/s");
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.93 KiB/s");
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.17 KiB/s");
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.42 KiB/s");
                        return;
                    case R.styleable.View_theme /*4*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.76 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.25 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.74 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.13 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.62 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.40 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.37 KiB/s");
                        return;
                    default:
                        return;
                }
            case R.styleable.View_paddingStart /*2*/:
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.73 KiB/s");
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.13 KiB/s");
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.37 KiB/s");
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.61 KiB/s");
                        return;
                    case R.styleable.View_theme /*4*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.00 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.49 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.93 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.32 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.81 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.59 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.57 KiB/s");
                        return;
                    default:
                        return;
                }
            case R.styleable.View_paddingEnd /*3*/:
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.10 KiB/s");
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.10 KiB/s");
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.08 KiB/s");
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.08 KiB/s");
                        return;
                    case R.styleable.View_theme /*4*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.08 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        this.bandwidthText.setText("Bandwidth usage: 8.06 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        this.bandwidthText.setText("Bandwidth usage: 8.06 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        this.bandwidthText.setText("Bandwidth usage: 8.06 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        this.bandwidthText.setText("Bandwidth usage: 8.06 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                        this.bandwidthText.setText("Bandwidth usage: 10.01 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                        this.bandwidthText.setText("Bandwidth usage: 13.92 KiB/s");
                        return;
                    default:
                        return;
                }
            case R.styleable.View_theme /*4*/:
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        this.bandwidthText.setText("Bandwidth usage: 2.73 KiB/s");
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.22 KiB/s");
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.71 KiB/s");
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.20 KiB/s");
                        return;
                    case R.styleable.View_theme /*4*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.74 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.22 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.71 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.20 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.74 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.23 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.71 KiB/s");
                        return;
                    default:
                        return;
                }
            case R.styleable.Toolbar_contentInsetStart /*5*/:
                switch (i) {
                    case R.styleable.View_android_theme /*0*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.08 KiB/s");
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        this.bandwidthText.setText("Bandwidth usage: 3.96 KiB/s");
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        this.bandwidthText.setText("Bandwidth usage: 4.83 KiB/s");
                        return;
                    case R.styleable.View_paddingEnd /*3*/:
                        this.bandwidthText.setText("Bandwidth usage: 5.71 KiB/s");
                        return;
                    case R.styleable.View_theme /*4*/:
                        this.bandwidthText.setText("Bandwidth usage: 6.59 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStart /*5*/:
                        this.bandwidthText.setText("Bandwidth usage: 7.47 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEnd /*6*/:
                        this.bandwidthText.setText("Bandwidth usage: 8.35 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        this.bandwidthText.setText("Bandwidth usage: 9.23 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetRight /*8*/:
                        this.bandwidthText.setText("Bandwidth usage: 10.11 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
                        this.bandwidthText.setText("Bandwidth usage: 10.99 KiB/s");
                        return;
                    case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
                        this.bandwidthText.setText("Bandwidth usage: 11.87 KiB/s");
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void callNewChannel2(int tryb) {
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_new_channel, null);
        builder.setView(dialogLay);
        TextView title = (TextView) dialogLay.findViewById(R.id.txtMove1);
        EditText titleBox = (EditText) dialogLay.findViewById(R.id.editTextNewChannel);
        EditText txtTopic = (EditText) dialogLay.findViewById(R.id.editTextTopic);
        EditText txtDescription = (EditText) dialogLay.findViewById(R.id.editTextDescription);
        TextView txtGroup1 = (TextView) dialogLay.findViewById(R.id.textGroup1);
        TextView txtGroup2 = (TextView) dialogLay.findViewById(R.id.textGroup2);
        TextView txtGroup3 = (TextView) dialogLay.findViewById(R.id.textGroup3);
        final CheckBox boxPass = (CheckBox) dialogLay.findViewById(R.id.checkBoxPassword);
        EditText passText = (EditText) dialogLay.findViewById(R.id.editTextPassword);
        final Spinner channelType = (Spinner) dialogLay.findViewById(R.id.spinnerTypeC);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.channel_type, R.layout.custom_spinner_item_create_channel);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        channelType.setAdapter(adapter);
        channelType.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ClientActivity.this.hideKeyboard(view);
                return false;
            }
        });
        final CheckBox defaultBox = (CheckBox) dialogLay.findViewById(R.id.checkBoxDefaultC);
        final CheckBox UnlimitedBoxUsers = (CheckBox) dialogLay.findViewById(R.id.checkBoxMaxUser);
        final EditText limitText = (EditText) dialogLay.findViewById(R.id.editTextMaxUser);
        Spinner channelSort = (Spinner) dialogLay.findViewById(R.id.spinnerSort);
        final CheckBox SetItAsFirst = (CheckBox) dialogLay.findViewById(R.id.checkBoxSetFirst);
        final CheckBox DontSortIt = (CheckBox) dialogLay.findViewById(R.id.checkBoxSortIt);
        List<Integer> idWybranegoKanaluRodzica = new ArrayList();
        List<String> categories = new ArrayList();
        LinearLayout layGroup1 = (LinearLayout) dialogLay.findViewById(R.id.layStandard);
        LinearLayout layGroup2 = (LinearLayout) dialogLay.findViewById(R.id.layAudio);
        LinearLayout layGroup3 = (LinearLayout) dialogLay.findViewById(R.id.layAdvance);
        final Spinner audioCodec = (Spinner) dialogLay.findViewById(R.id.spinnerCodec);
        final CheckBox voice1 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice1);
        final CheckBox voice2 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice2);
        final CheckBox voice3 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice3);
        final CheckBox voice4 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice4);
        final TextView codecNumber = (TextView) dialogLay.findViewById(R.id.textViewCodecNumber);
        final SeekBar seekBarCodeNumber = (SeekBar) dialogLay.findViewById(R.id.seekBarCodeNumber);
        this.bandwidthText = (TextView) dialogLay.findViewById(R.id.textViewBandwidth);
        final EditText needTalkPower = (EditText) dialogLay.findViewById(R.id.editTextTalkPower);
        EditText phoneticName = (EditText) dialogLay.findViewById(R.id.editTextPhoneticName);
        final EditText deleteDelay = (EditText) dialogLay.findViewById(R.id.editTextDeleteDelay);
        final CheckBox voiceDataEncrypt = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoiceData);
        final CheckBox familyUserInherited = (CheckBox) dialogLay.findViewById(R.id.checkBoxFMaxInherited);
        CheckBox familyUserUnlimited = (CheckBox) dialogLay.findViewById(R.id.checkBoxFMaxUnlimited);
        final CheckBox familyUserLimited = (CheckBox) dialogLay.findViewById(R.id.checkBoxFMaxLimited);
        final EditText familyUserLimitedNumber = (EditText) dialogLay.findViewById(R.id.editTextFamilyUsersLimited);
        txtDescription.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        passText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        titleBox.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        limitText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        txtTopic.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        needTalkPower.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        phoneticName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        deleteDelay.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        voiceDataEncrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        familyUserLimitedNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
            }
        });
        seekBarCodeNumber.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                codecNumber.setText(i + BuildConfig.FLAVOR);
                ClientActivity.this.callBandwidth(audioCodec.getSelectedItemPosition(), i);
                if (audioCodec.getSelectedItemPosition() == 4 && i == 4) {
                    voice1.setChecked(true);
                    voice2.setChecked(false);
                    voice3.setChecked(false);
                    voice4.setChecked(false);
                } else if (audioCodec.getSelectedItemPosition() == 4 && i == 6) {
                    voice1.setChecked(false);
                    voice2.setChecked(true);
                    voice3.setChecked(false);
                    voice4.setChecked(false);
                } else if (audioCodec.getSelectedItemPosition() == 5 && i == 6) {
                    voice1.setChecked(false);
                    voice2.setChecked(false);
                    voice3.setChecked(true);
                    voice4.setChecked(false);
                } else {
                    voice1.setChecked(false);
                    voice2.setChecked(false);
                    voice3.setChecked(false);
                    voice4.setChecked(true);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        ArrayAdapter<CharSequence> dataAdapterCodec = ArrayAdapter.createFromResource(this, R.array.create_channel_audio, R.layout.custom_spinner_item_create_channel);
        dataAdapterCodec.setDropDownViewResource(R.layout.custom_spinner_item);
        audioCodec.setAdapter(dataAdapterCodec);
        audioCodec.setSelection(4);
        final CheckBox checkBox = voice1;
        final CheckBox checkBox2 = voice2;
        final CheckBox checkBox3 = voice3;
        final CheckBox checkBox4 = voice4;
        audioCodec.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.callBandwidth(position, seekBarCodeNumber.getProgress());
                if (position == 4 && seekBarCodeNumber.getProgress() == 4) {
                    checkBox.setChecked(true);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                } else if (position == 4 && seekBarCodeNumber.getProgress() == 6) {
                    checkBox.setChecked(false);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                } else if (position == 5 && seekBarCodeNumber.getProgress() == 6) {
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(false);
                } else {
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(true);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        checkBox3 = voice1;
        checkBox4 = voice2;
        final CheckBox checkBox5 = voice3;
        final CheckBox checkBox6 = voice4;
        final Spinner spinner = audioCodec;
        final SeekBar seekBar = seekBarCodeNumber;
        voice1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    spinner.setSelection(4);
                    ClientActivity.this.bandwidthText.setText("Bandwidth usage: 4.74 KiB/s");
                    seekBar.setProgress(4);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = voice2;
        checkBox4 = voice1;
        checkBox5 = voice3;
        checkBox6 = voice4;
        spinner = audioCodec;
        seekBar = seekBarCodeNumber;
        voice2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    spinner.setSelection(4);
                    ClientActivity.this.bandwidthText.setText("Bandwidth usage: 5.71 KiB/s");
                    seekBar.setProgress(6);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = voice3;
        checkBox4 = voice1;
        checkBox5 = voice2;
        checkBox6 = voice4;
        spinner = audioCodec;
        seekBar = seekBarCodeNumber;
        voice3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    spinner.setSelection(5);
                    ClientActivity.this.bandwidthText.setText("Bandwidth usage: 8.35 KiB/s");
                    seekBar.setProgress(6);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = voice4;
        checkBox4 = voice1;
        checkBox5 = voice2;
        checkBox6 = voice3;
        voice4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        final TextView textView = txtGroup1;
        final TextView textView2 = txtGroup2;
        final TextView textView3 = txtGroup3;
        final LinearLayout linearLayout = layGroup3;
        final LinearLayout linearLayout2 = layGroup2;
        final LinearLayout linearLayout3 = layGroup1;
        txtGroup1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                textView.setTypeface(null, 1);
                textView2.setTypeface(null, 0);
                textView3.setTypeface(null, 0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                linearLayout3.setVisibility(0);
            }
        });
        textView = txtGroup2;
        textView2 = txtGroup1;
        textView3 = txtGroup3;
        linearLayout = layGroup3;
        linearLayout2 = layGroup1;
        linearLayout3 = layGroup2;
        txtGroup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                textView.setTypeface(null, 1);
                textView2.setTypeface(null, 0);
                textView3.setTypeface(null, 0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                linearLayout3.setVisibility(0);
            }
        });
        textView = txtGroup3;
        textView2 = txtGroup1;
        textView3 = txtGroup2;
        linearLayout = layGroup1;
        linearLayout2 = layGroup2;
        linearLayout3 = layGroup3;
        txtGroup3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                textView.setTypeface(null, 1);
                textView2.setTypeface(null, 0);
                textView3.setTypeface(null, 0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                linearLayout3.setVisibility(0);
            }
        });
        checkBox3 = familyUserInherited;
        checkBox4 = familyUserUnlimited;
        checkBox5 = familyUserLimited;
        final EditText editText = familyUserLimitedNumber;
        familyUserInherited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    editText.setEnabled(false);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = familyUserUnlimited;
        checkBox4 = familyUserInherited;
        checkBox5 = familyUserLimited;
        editText = familyUserLimitedNumber;
        familyUserUnlimited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    editText.setEnabled(false);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = familyUserLimited;
        checkBox4 = familyUserInherited;
        checkBox5 = familyUserUnlimited;
        editText = familyUserLimitedNumber;
        familyUserLimited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    editText.setEnabled(true);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        if (tryb == 0) {
            for (MyChannelClass a : this.lista_channel_array) {
                if (a.parentId == 0) {
                    idWybranegoKanaluRodzica.add(Integer.valueOf(a.id));
                    categories.add(a.name);
                }
            }
        } else {
            for (MyChannelClass a2 : this.lista_channel_array) {
                if (a2.parentId == this.selected_channeluser.parentIdChannel) {
                    idWybranegoKanaluRodzica.add(Integer.valueOf(a2.id));
                    categories.add(a2.name);
                }
            }
        }
        String btnName = "Save";
        if (tryb == 1) {
            btnName = "Update";
            title.setText("Edit selected Channel:");
            titleBox.setText(this.selected_channeluser.nameChannel);
            if (this.selected_channeluser.hasPasswordChannel) {
                boxPass.setText("Password (has it, need change?)");
                passText.setHint("leave empty to erase");
            }
            try {
                Channel _tempChannel = this.api.getChannelDetails(this.selected_channeluser.idChannel);
                this.global_tempChannel = _tempChannel;
                if (_tempChannel.getTopic().length() > 0) {
                    txtTopic.setText(_tempChannel.getTopic());
                }
                if (_tempChannel.getDescription().length() > 0) {
                    txtDescription.setText(_tempChannel.getDescription());
                }
                if (_tempChannel.getNeededTalkPower() > 0) {
                    needTalkPower.setText(_tempChannel.getNeededTalkPower() + BuildConfig.FLAVOR);
                }
                if (_tempChannel.getNamePhonetic().length() > 0) {
                    phoneticName.setText(_tempChannel.getNamePhonetic() + BuildConfig.FLAVOR);
                }
                if (_tempChannel.getDeleteDelay() > 0) {
                    deleteDelay.setText(_tempChannel.getDeleteDelay() + BuildConfig.FLAVOR);
                }
                if (_tempChannel.getCodecIsUnencrypted() == 0) {
                    voiceDataEncrypt.setChecked(true);
                }
                if (_tempChannel.getFamilyUnlimited() != 0) {
                    familyUserInherited.setChecked(false);
                    familyUserLimited.setChecked(false);
                    familyUserUnlimited.setChecked(true);
                    familyUserLimitedNumber.setEnabled(false);
                } else if (_tempChannel.getFamilyInherited() == 1) {
                    familyUserInherited.setChecked(true);
                } else {
                    familyUserInherited.setChecked(false);
                    familyUserLimited.setChecked(true);
                    familyUserLimitedNumber.setEnabled(true);
                    familyUserLimitedNumber.setText(_tempChannel.getMaxFamilyClients() + BuildConfig.FLAVOR);
                }
                audioCodec.setSelection(_tempChannel.getCodec());
                seekBarCodeNumber.setProgress(_tempChannel.getCodecQuality());
            } catch (Exception es) {
                es.printStackTrace();
            }
            if (this.selected_channeluser.isPermanent) {
                channelType.setSelection(2);
            } else if (this.selected_channeluser.isSemiPermanent) {
                channelType.setSelection(1);
            } else {
                channelType.setSelection(0);
            }
            if (this.selected_channeluser.isDefault) {
                defaultBox.setChecked(true);
                channelType.setVisibility(8);
            }
            if (this.selected_channeluser.maxClientsChannel > -1) {
                UnlimitedBoxUsers.setChecked(false);
                limitText.setVisibility(0);
                limitText.setText(BuildConfig.FLAVOR + this.selected_channeluser.maxClientsChannel);
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.custom_spinner_item_create_channel, categories);
        arrayAdapter.setDropDownViewResource(17367049);
        channelSort.setAdapter(arrayAdapter);
        final List<Integer> list = idWybranegoKanaluRodzica;
        final Spinner spinner2 = channelSort;
        channelSort.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.channelIDtoMove = ((Integer) list.get(spinner2.getSelectedItemPosition())).intValue();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        final CheckBox checkBox7 = boxPass;
        final EditText editText2 = passText;
        boxPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    editText2.setVisibility(0);
                    editText2.setText(BuildConfig.FLAVOR);
                    return;
                }
                editText2.setVisibility(8);
                editText2.setText(BuildConfig.FLAVOR);
            }
        });
        checkBox7 = defaultBox;
        spinner2 = channelType;
        defaultBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    spinner2.setVisibility(8);
                } else {
                    spinner2.setVisibility(0);
                }
            }
        });
        checkBox7 = UnlimitedBoxUsers;
        editText2 = limitText;
        UnlimitedBoxUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    editText2.setVisibility(8);
                    editText2.setText("1");
                    return;
                }
                editText2.setVisibility(0);
                editText2.setText("1");
            }
        });
        checkBox7 = SetItAsFirst;
        spinner2 = channelSort;
        SetItAsFirst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    spinner2.setVisibility(8);
                } else {
                    spinner2.setVisibility(0);
                }
            }
        });
        checkBox7 = DontSortIt;
        spinner2 = channelSort;
        final CheckBox checkBox8 = SetItAsFirst;
        DontSortIt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    spinner2.setVisibility(8);
                    checkBox8.setVisibility(8);
                    return;
                }
                checkBox8.setVisibility(0);
                if (!checkBox8.isChecked()) {
                    spinner2.setVisibility(0);
                }
            }
        });
        final EditText editText3 = titleBox;
        final EditText editText4 = passText;
        final EditText editText5 = txtTopic;
        editText = txtDescription;
        final EditText editText6 = phoneticName;
        final int i = tryb;
        final Spinner spinner3 = audioCodec;
        final TextView textView4 = codecNumber;
        builder.setPositiveButton(btnName, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText3.getText().toString();
                String m_pass = editText4.getText().toString();
                String m_topic = editText5.getText().toString();
                String m_description = editText.getText().toString();
                String m_phoneticname = editText6.getText().toString();
                HashMap<ChannelProperty, String> options = new HashMap();
                if (i == 1) {
                    if (!m_name.equals(ClientActivity.this.selected_channeluser.nameChannel)) {
                        options.put(ChannelProperty.CHANNEL_NAME, m_name);
                    }
                    if (ClientActivity.this.global_tempChannel != null) {
                        int zm_voiceData;
                        if (!m_topic.equals(ClientActivity.this.global_tempChannel.getTopic())) {
                            options.put(ChannelProperty.CHANNEL_TOPIC, m_topic);
                        }
                        if (!m_description.equals(ClientActivity.this.global_tempChannel.getDescription())) {
                            options.put(ChannelProperty.CHANNEL_DESCRIPTION, m_description);
                        }
                        if (spinner3.getSelectedItemPosition() != ClientActivity.this.global_tempChannel.getCodec()) {
                            options.put(ChannelProperty.CHANNEL_CODEC, spinner3.getSelectedItemPosition() + BuildConfig.FLAVOR);
                        }
                        if (textView4.getText().length() > 0 && Integer.parseInt(textView4.getText().toString()) != ClientActivity.this.global_tempChannel.getCodecQuality()) {
                            options.put(ChannelProperty.CHANNEL_CODEC_QUALITY, textView4.getText().toString());
                        }
                        if (needTalkPower.getText().length() > 0 && Integer.parseInt(needTalkPower.getText().toString()) != ClientActivity.this.global_tempChannel.getNeededTalkPower()) {
                            options.put(ChannelProperty.CHANNEL_NEEDED_TALK_POWER, needTalkPower.getText().toString());
                        }
                        if (!m_phoneticname.equals(ClientActivity.this.global_tempChannel.getNamePhonetic())) {
                            options.put(ChannelProperty.CHANNEL_NAME_PHONETIC, m_phoneticname);
                        }
                        if (deleteDelay.getText().length() > 0 && Integer.parseInt(deleteDelay.getText().toString()) != ClientActivity.this.global_tempChannel.getDeleteDelay()) {
                            options.put(ChannelProperty.CHANNEL_DELETE_DELAY, deleteDelay.getText().toString());
                        }
                        if (voiceDataEncrypt.isChecked()) {
                            zm_voiceData = 0;
                        } else {
                            zm_voiceData = 1;
                        }
                        if (zm_voiceData != ClientActivity.this.global_tempChannel.getCodecIsUnencrypted()) {
                            options.put(ChannelProperty.CHANNEL_CODEC_IS_UNENCRYPTED, zm_voiceData + BuildConfig.FLAVOR);
                        }
                        if (familyUserInherited.isChecked()) {
                            options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "0");
                            options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_INHERITED, "1");
                        } else if (familyUserLimited.isChecked()) {
                            options.put(ChannelProperty.CHANNEL_MAXFAMILYCLIENTS, familyUserLimitedNumber.getText().toString());
                            options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_INHERITED, "0");
                            options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "0");
                        } else {
                            options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "1");
                            options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_INHERITED, "0");
                        }
                    }
                }
                if (boxPass.isChecked()) {
                    if (m_pass.length() > 0) {
                        options.put(ChannelProperty.CHANNEL_FLAG_PASSWORD, "1");
                        options.put(ChannelProperty.CHANNEL_PASSWORD, ((Password) ClientActivity.this.api.hashPassword(m_pass).get(0)).getPassword());
                    } else {
                        options.put(ChannelProperty.CHANNEL_FLAG_PASSWORD, "0");
                    }
                }
                if (!defaultBox.isChecked()) {
                    switch (channelType.getSelectedItemPosition()) {
                        case R.styleable.View_android_theme /*0*/:
                            options.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "0");
                            options.put(ChannelProperty.CHANNEL_FLAG_SEMI_PERMANENT, "0");
                            options.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "1");
                            break;
                        case R.styleable.View_android_focusable /*1*/:
                            options.put(ChannelProperty.CHANNEL_FLAG_SEMI_PERMANENT, "1");
                            options.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "0");
                            options.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "0");
                            break;
                        case R.styleable.View_paddingStart /*2*/:
                            options.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                            options.put(ChannelProperty.CHANNEL_FLAG_SEMI_PERMANENT, "0");
                            options.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "0");
                            break;
                        default:
                            break;
                    }
                }
                options.put(ChannelProperty.CHANNEL_FLAG_DEFAULT, "1");
                options.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                if (UnlimitedBoxUsers.isChecked()) {
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "1");
                    options.put(ChannelProperty.CHANNEL_MAXCLIENTS, "-1");
                } else {
                    options.put(ChannelProperty.CHANNEL_MAXCLIENTS, limitText.getText().toString());
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "0");
                }
                if (!DontSortIt.isChecked()) {
                    if (SetItAsFirst.isChecked()) {
                        options.put(ChannelProperty.CHANNEL_ORDER, "0");
                    } else {
                        options.put(ChannelProperty.CHANNEL_ORDER, String.valueOf(ClientActivity.this.channelIDtoMove));
                    }
                }
                if (i == 0) {
                    if (m_topic.length() > 0) {
                        options.put(ChannelProperty.CHANNEL_TOPIC, m_topic);
                    }
                    if (m_description.length() > 0) {
                        options.put(ChannelProperty.CHANNEL_DESCRIPTION, m_description);
                    }
                    options.put(ChannelProperty.CHANNEL_CODEC_QUALITY, textView4.getText().toString());
                    if (spinner3.getSelectedItemPosition() != 4) {
                        options.put(ChannelProperty.CHANNEL_CODEC, spinner3.getSelectedItemPosition() + BuildConfig.FLAVOR);
                    }
                    if (needTalkPower.getText().length() > 0) {
                        int _talkpower = Integer.parseInt(needTalkPower.getText().toString());
                        if (_talkpower > 0) {
                            options.put(ChannelProperty.CHANNEL_NEEDED_TALK_POWER, _talkpower + BuildConfig.FLAVOR);
                        }
                    }
                    if (m_phoneticname.length() > 0) {
                        options.put(ChannelProperty.CHANNEL_NAME_PHONETIC, m_phoneticname);
                    }
                    if (voiceDataEncrypt.isChecked()) {
                        options.put(ChannelProperty.CHANNEL_CODEC_IS_UNENCRYPTED, "0");
                    }
                    if (deleteDelay.getText().length() > 0) {
                        int _deletedelay = Integer.parseInt(deleteDelay.getText().toString());
                        if (_deletedelay > 0) {
                            options.put(ChannelProperty.CHANNEL_DELETE_DELAY, _deletedelay + BuildConfig.FLAVOR);
                        }
                    }
                    if (familyUserInherited.isChecked()) {
                        options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "0");
                        options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_INHERITED, "1");
                    } else if (familyUserLimited.isChecked()) {
                        options.put(ChannelProperty.CHANNEL_MAXFAMILYCLIENTS, familyUserLimitedNumber.getText().toString());
                        options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "0");
                    }
                }
                if (i == 0) {
                    ClientActivity.this.api.createChannel(m_name, options);
                } else {
                    ClientActivity.this.api.editChannel(ClientActivity.this.selected_channeluser.idChannel, options);
                }
                if (ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                        }
                    });
                } else {
                    ClientActivity.this.zliczBledyUprawnien();
                }
                Snackbar.make(ClientActivity.this.leftList, "Create channel: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
            }
        });
        builder = builder;
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void callSubChannel() {
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_new_channel, null);
        builder.setView(dialogLay);
        ((TextView) dialogLay.findViewById(R.id.txtMove1)).setText("Create new Sub-Channel for " + this.selected_channeluser.nameChannel);
        EditText titleBox = (EditText) dialogLay.findViewById(R.id.editTextNewChannel);
        EditText txtTopic = (EditText) dialogLay.findViewById(R.id.editTextTopic);
        EditText txtDescription = (EditText) dialogLay.findViewById(R.id.editTextDescription);
        TextView txtGroup1 = (TextView) dialogLay.findViewById(R.id.textGroup1);
        TextView txtGroup2 = (TextView) dialogLay.findViewById(R.id.textGroup2);
        TextView txtGroup3 = (TextView) dialogLay.findViewById(R.id.textGroup3);
        CheckBox boxPass = (CheckBox) dialogLay.findViewById(R.id.checkBoxPassword);
        EditText passText = (EditText) dialogLay.findViewById(R.id.editTextPassword);
        final Spinner channelType = (Spinner) dialogLay.findViewById(R.id.spinnerTypeC);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.channel_type, R.layout.custom_spinner_item_create_channel);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        channelType.setAdapter(adapter);
        channelType.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ClientActivity.this.hideKeyboard(view);
                return false;
            }
        });
        final CheckBox defaultBox = (CheckBox) dialogLay.findViewById(R.id.checkBoxDefaultC);
        final CheckBox UnlimitedBoxUsers = (CheckBox) dialogLay.findViewById(R.id.checkBoxMaxUser);
        final EditText limitText = (EditText) dialogLay.findViewById(R.id.editTextMaxUser);
        Spinner channelSort = (Spinner) dialogLay.findViewById(R.id.spinnerSort);
        final CheckBox SetItAsFirst = (CheckBox) dialogLay.findViewById(R.id.checkBoxSetFirst);
        final CheckBox DontSortIt = (CheckBox) dialogLay.findViewById(R.id.checkBoxSortIt);
        LinearLayout layGroup1 = (LinearLayout) dialogLay.findViewById(R.id.layStandard);
        LinearLayout layGroup2 = (LinearLayout) dialogLay.findViewById(R.id.layAudio);
        LinearLayout layGroup3 = (LinearLayout) dialogLay.findViewById(R.id.layAdvance);
        final Spinner audioCodec = (Spinner) dialogLay.findViewById(R.id.spinnerCodec);
        final CheckBox voice1 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice1);
        final CheckBox voice2 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice2);
        final CheckBox voice3 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice3);
        final CheckBox voice4 = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoice4);
        final TextView codecNumber = (TextView) dialogLay.findViewById(R.id.textViewCodecNumber);
        final SeekBar seekBarCodeNumber = (SeekBar) dialogLay.findViewById(R.id.seekBarCodeNumber);
        this.bandwidthText = (TextView) dialogLay.findViewById(R.id.textViewBandwidth);
        final EditText needTalkPower = (EditText) dialogLay.findViewById(R.id.editTextTalkPower);
        EditText phoneticName = (EditText) dialogLay.findViewById(R.id.editTextPhoneticName);
        final EditText deleteDelay = (EditText) dialogLay.findViewById(R.id.editTextDeleteDelay);
        final CheckBox voiceDataEncrypt = (CheckBox) dialogLay.findViewById(R.id.checkBoxVoiceData);
        final CheckBox familyUserInherited = (CheckBox) dialogLay.findViewById(R.id.checkBoxFMaxInherited);
        CheckBox familyUserUnlimited = (CheckBox) dialogLay.findViewById(R.id.checkBoxFMaxUnlimited);
        final CheckBox familyUserLimited = (CheckBox) dialogLay.findViewById(R.id.checkBoxFMaxLimited);
        final EditText familyUserLimitedNumber = (EditText) dialogLay.findViewById(R.id.editTextFamilyUsersLimited);
        titleBox.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        passText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        limitText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        txtTopic.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        txtDescription.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        needTalkPower.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        phoneticName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        deleteDelay.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        voiceDataEncrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
            }
        });
        familyUserLimitedNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
            }
        });
        seekBarCodeNumber.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                codecNumber.setText(i + BuildConfig.FLAVOR);
                ClientActivity.this.callBandwidth(audioCodec.getSelectedItemPosition(), i);
                if (audioCodec.getSelectedItemPosition() == 4 && i == 4) {
                    voice1.setChecked(true);
                    voice2.setChecked(false);
                    voice3.setChecked(false);
                    voice4.setChecked(false);
                } else if (audioCodec.getSelectedItemPosition() == 4 && i == 6) {
                    voice1.setChecked(false);
                    voice2.setChecked(true);
                    voice3.setChecked(false);
                    voice4.setChecked(false);
                } else if (audioCodec.getSelectedItemPosition() == 5 && i == 6) {
                    voice1.setChecked(false);
                    voice2.setChecked(false);
                    voice3.setChecked(true);
                    voice4.setChecked(false);
                } else {
                    voice1.setChecked(false);
                    voice2.setChecked(false);
                    voice3.setChecked(false);
                    voice4.setChecked(true);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        List<Integer> idWybranegoKanaluRodzica = new ArrayList();
        List<String> categories = new ArrayList();
        for (MyChannelClass a : this.lista_channel_array) {
            if (a.parentId == this.selected_channeluser.idChannel) {
                idWybranegoKanaluRodzica.add(Integer.valueOf(a.id));
                categories.add(a.name);
            }
        }
        ArrayAdapter<CharSequence> dataAdapterCodec = ArrayAdapter.createFromResource(this, R.array.create_channel_audio, R.layout.custom_spinner_item_create_channel);
        dataAdapterCodec.setDropDownViewResource(R.layout.custom_spinner_item);
        audioCodec.setAdapter(dataAdapterCodec);
        audioCodec.setSelection(4);
        final CheckBox checkBox = voice1;
        final CheckBox checkBox2 = voice2;
        final CheckBox checkBox3 = voice3;
        final CheckBox checkBox4 = voice4;
        audioCodec.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.callBandwidth(position, seekBarCodeNumber.getProgress());
                if (position == 4 && seekBarCodeNumber.getProgress() == 4) {
                    checkBox.setChecked(true);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                } else if (position == 4 && seekBarCodeNumber.getProgress() == 6) {
                    checkBox.setChecked(false);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                } else if (position == 5 && seekBarCodeNumber.getProgress() == 6) {
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(false);
                } else {
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(true);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        checkBox3 = voice1;
        checkBox4 = voice2;
        final CheckBox checkBox5 = voice3;
        final CheckBox checkBox6 = voice4;
        final Spinner spinner = audioCodec;
        final SeekBar seekBar = seekBarCodeNumber;
        voice1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    spinner.setSelection(4);
                    ClientActivity.this.bandwidthText.setText("Bandwidth usage: 4.74 KiB/s");
                    seekBar.setProgress(4);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = voice2;
        checkBox4 = voice1;
        checkBox5 = voice3;
        checkBox6 = voice4;
        spinner = audioCodec;
        seekBar = seekBarCodeNumber;
        voice2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    spinner.setSelection(4);
                    ClientActivity.this.bandwidthText.setText("Bandwidth usage: 5.71 KiB/s");
                    seekBar.setProgress(6);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = voice3;
        checkBox4 = voice1;
        checkBox5 = voice2;
        checkBox6 = voice4;
        spinner = audioCodec;
        seekBar = seekBarCodeNumber;
        voice3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    spinner.setSelection(5);
                    ClientActivity.this.bandwidthText.setText("Bandwidth usage: 8.35 KiB/s");
                    seekBar.setProgress(6);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = voice4;
        checkBox4 = voice1;
        checkBox5 = voice2;
        checkBox6 = voice3;
        voice4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox6.setChecked(false);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        final TextView textView = txtGroup1;
        final TextView textView2 = txtGroup2;
        final TextView textView3 = txtGroup3;
        final LinearLayout linearLayout = layGroup3;
        final LinearLayout linearLayout2 = layGroup2;
        final LinearLayout linearLayout3 = layGroup1;
        txtGroup1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                textView.setTypeface(null, 1);
                textView2.setTypeface(null, 0);
                textView3.setTypeface(null, 0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                linearLayout3.setVisibility(0);
            }
        });
        textView = txtGroup2;
        textView2 = txtGroup1;
        textView3 = txtGroup3;
        linearLayout = layGroup3;
        linearLayout2 = layGroup1;
        linearLayout3 = layGroup2;
        txtGroup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                textView.setTypeface(null, 1);
                textView2.setTypeface(null, 0);
                textView3.setTypeface(null, 0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                linearLayout3.setVisibility(0);
            }
        });
        textView = txtGroup3;
        textView2 = txtGroup1;
        textView3 = txtGroup2;
        linearLayout = layGroup1;
        linearLayout2 = layGroup2;
        linearLayout3 = layGroup3;
        txtGroup3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                textView.setTypeface(null, 1);
                textView2.setTypeface(null, 0);
                textView3.setTypeface(null, 0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                linearLayout3.setVisibility(0);
            }
        });
        checkBox3 = familyUserInherited;
        checkBox4 = familyUserUnlimited;
        checkBox5 = familyUserLimited;
        final EditText editText = familyUserLimitedNumber;
        familyUserInherited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    editText.setEnabled(false);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = familyUserUnlimited;
        checkBox4 = familyUserInherited;
        checkBox5 = familyUserLimited;
        editText = familyUserLimitedNumber;
        familyUserUnlimited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    editText.setEnabled(false);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        checkBox3 = familyUserLimited;
        checkBox4 = familyUserInherited;
        checkBox5 = familyUserUnlimited;
        editText = familyUserLimitedNumber;
        familyUserLimited.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClientActivity.this.hideKeyboard(view);
                if (checkBox3.isChecked()) {
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    editText.setEnabled(true);
                    return;
                }
                checkBox3.setChecked(true);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.custom_spinner_item_create_channel, categories);
        arrayAdapter.setDropDownViewResource(17367049);
        channelSort.setAdapter(arrayAdapter);
        final List<Integer> list = idWybranegoKanaluRodzica;
        final Spinner spinner2 = channelSort;
        channelSort.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ClientActivity.this.channelIDtoMove = ((Integer) list.get(spinner2.getSelectedItemPosition())).intValue();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        final CheckBox checkBox7 = boxPass;
        final EditText editText2 = passText;
        boxPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    editText2.setVisibility(0);
                    editText2.setText(BuildConfig.FLAVOR);
                    return;
                }
                editText2.setVisibility(8);
                editText2.setText(BuildConfig.FLAVOR);
            }
        });
        checkBox7 = defaultBox;
        spinner2 = channelType;
        defaultBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    spinner2.setVisibility(8);
                } else {
                    spinner2.setVisibility(0);
                }
            }
        });
        checkBox7 = UnlimitedBoxUsers;
        editText2 = limitText;
        UnlimitedBoxUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    editText2.setVisibility(8);
                    editText2.setText("1");
                    return;
                }
                editText2.setVisibility(0);
                editText2.setText("1");
            }
        });
        checkBox7 = SetItAsFirst;
        spinner2 = channelSort;
        SetItAsFirst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBox7.isChecked()) {
                    spinner2.setVisibility(8);
                } else {
                    spinner2.setVisibility(0);
                }
            }
        });
        checkBox7 = DontSortIt;
        spinner2 = channelSort;
        final CheckBox checkBox8 = SetItAsFirst;
        DontSortIt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClientActivity.this.hideKeyboard(v);
                if (checkBox7.isChecked()) {
                    spinner2.setVisibility(8);
                    checkBox8.setVisibility(8);
                    return;
                }
                checkBox8.setVisibility(0);
                if (!checkBox8.isChecked()) {
                    spinner2.setVisibility(0);
                }
            }
        });
        final EditText editText3 = titleBox;
        final EditText editText4 = passText;
        final EditText editText5 = txtTopic;
        editText = txtDescription;
        final EditText editText6 = phoneticName;
        final CheckBox checkBox9 = boxPass;
        final TextView textView4 = codecNumber;
        final Spinner spinner3 = audioCodec;
        builder.setPositiveButton("Save", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText3.getText().toString();
                String m_pass = editText4.getText().toString();
                String m_topic = editText5.getText().toString();
                String m_description = editText.getText().toString();
                String m_phoneticname = editText6.getText().toString();
                HashMap<ChannelProperty, String> options = new HashMap();
                if (checkBox9.isChecked() && m_pass.length() > 0) {
                    options.put(ChannelProperty.CHANNEL_PASSWORD, ((Password) ClientActivity.this.api.hashPassword(m_pass).get(0)).getPassword());
                    options.put(ChannelProperty.CHANNEL_FLAG_PASSWORD, "1");
                }
                if (!defaultBox.isChecked()) {
                    switch (channelType.getSelectedItemPosition()) {
                        case R.styleable.View_android_theme /*0*/:
                            options.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "1");
                            break;
                        case R.styleable.View_android_focusable /*1*/:
                            options.put(ChannelProperty.CHANNEL_FLAG_SEMI_PERMANENT, "1");
                            break;
                        case R.styleable.View_paddingStart /*2*/:
                            options.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                            break;
                        default:
                            break;
                    }
                }
                options.put(ChannelProperty.CHANNEL_FLAG_DEFAULT, "1");
                options.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                if (UnlimitedBoxUsers.isChecked()) {
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "1");
                } else {
                    options.put(ChannelProperty.CHANNEL_MAXCLIENTS, limitText.getText().toString());
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "0");
                }
                if (!DontSortIt.isChecked()) {
                    if (SetItAsFirst.isChecked()) {
                        options.put(ChannelProperty.CHANNEL_ORDER, "0");
                    } else {
                        options.put(ChannelProperty.CHANNEL_ORDER, String.valueOf(ClientActivity.this.channelIDtoMove));
                    }
                }
                if (m_topic.length() > 0) {
                    options.put(ChannelProperty.CHANNEL_TOPIC, m_topic);
                }
                if (m_description.length() > 0) {
                    options.put(ChannelProperty.CHANNEL_DESCRIPTION, m_description);
                }
                options.put(ChannelProperty.CHANNEL_CODEC_QUALITY, textView4.getText().toString());
                if (spinner3.getSelectedItemPosition() != 4) {
                    options.put(ChannelProperty.CHANNEL_CODEC, spinner3.getSelectedItemPosition() + BuildConfig.FLAVOR);
                }
                if (needTalkPower.getText().length() > 0) {
                    int _talkpower = Integer.parseInt(needTalkPower.getText().toString());
                    if (_talkpower > 0) {
                        options.put(ChannelProperty.CHANNEL_NEEDED_TALK_POWER, _talkpower + BuildConfig.FLAVOR);
                    }
                }
                if (m_phoneticname.length() > 0) {
                    options.put(ChannelProperty.CHANNEL_NAME_PHONETIC, m_phoneticname);
                }
                if (voiceDataEncrypt.isChecked()) {
                    options.put(ChannelProperty.CHANNEL_CODEC_IS_UNENCRYPTED, "0");
                }
                if (deleteDelay.getText().length() > 0) {
                    int _deletedelay = Integer.parseInt(deleteDelay.getText().toString());
                    if (_deletedelay > 0) {
                        options.put(ChannelProperty.CHANNEL_DELETE_DELAY, _deletedelay + BuildConfig.FLAVOR);
                    }
                }
                if (familyUserInherited.isChecked()) {
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "0");
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_INHERITED, "1");
                } else if (familyUserLimited.isChecked()) {
                    options.put(ChannelProperty.CHANNEL_MAXFAMILYCLIENTS, familyUserLimitedNumber.getText().toString());
                    options.put(ChannelProperty.CHANNEL_FLAG_MAXFAMILYCLIENTS_UNLIMITED, "0");
                }
                options.put(ChannelProperty.CPID, BuildConfig.FLAVOR + ClientActivity.this.selected_channeluser.idChannel);
                ClientActivity.this.api.createChannel(m_name, options);
                if (ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                        }
                    });
                } else {
                    ClientActivity.this.zliczBledyUprawnien();
                }
                Snackbar.make(ClientActivity.this.leftList, "Create channel: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
            }
        });
        builder = builder;
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void callPoke(final int zmienna, final int widok) {
        try {
            Builder builder = new Builder(this);
            builder.setTitle("Enter poke message");
            final EditText input = new EditText(this);
            input.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    ClientActivity.this.hideKeyboard(view);
                }
            });
            input.setInputType(524289);
            builder.setView(input);
            builder.setPositiveButton("Send", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String m_Text = input.getText().toString();
                    String hashPass = ((Password) ClientActivity.this.api.hashPassword(m_Text).get(0)).getPassword();
                    if (m_Text.length() > 0) {
                        ClientActivity.this.api.pokeClient(zmienna, m_Text);
                        if (widok == 1) {
                            Snackbar.make(ClientActivity.this.leftList, "Poke: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                            if (!ClientActivity.this.last_error_query.contains("ok")) {
                                ClientActivity.this.zliczBledyUprawnien();
                                return;
                            }
                            return;
                        }
                        Snackbar.make(ClientActivity.this.listViewUsers, "Poke: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
                    } else if (widok == 1) {
                        Snackbar.make(ClientActivity.this.leftList, "Poke: You need write something!", 0).setAction("Action", null).show();
                    } else {
                        Snackbar.make(ClientActivity.this.listViewUsers, "Poke: You need write something!", 0).setAction("Action", null).show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        } catch (Exception e) {
        }
    }

    public void callMute(int zmienna, ImageView position) {
        this.posCallMute = position;
        try {
            this.api.muteClient(zmienna);
            if (this.last_error_query.contains("ok")) {
                ((Student) this.osobyLista.get(Integer.valueOf(zmienna))).mutedByMe = true;
                runOnUiThread(new Runnable() {
                    public void run() {
                        ClientActivity.this.posCallMute.setImageResource(R.drawable.c_be_quiet);
                    }
                });
                Snackbar.make(this.listViewUsers, "Mute: " + this.last_error_query, 0).setAction("Action", null).show();
                return;
            }
            Snackbar.make(this.listViewUsers, "Mute: " + this.last_error_query, 0).setAction("Action", null).show();
            ((Student) this.osobyLista.get(Integer.valueOf(zmienna))).mutedByMe = false;
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.posCallMute.setImageResource(R.drawable.c_quiet);
                }
            });
        } catch (Exception e) {
        }
    }

    public void callUnMute(int zmienna, ImageView position) {
        this.posCallUnMute = position;
        try {
            this.api.unmuteClient(zmienna);
            if (this.last_error_query.contains("ok")) {
                ((Student) this.osobyLista.get(Integer.valueOf(zmienna))).mutedByMe = false;
                runOnUiThread(new Runnable() {
                    public void run() {
                        ClientActivity.this.posCallUnMute.setImageResource(R.drawable.c_quiet);
                    }
                });
                Snackbar.make(this.listViewUsers, "Unmute: " + this.last_error_query, 0).setAction("Action", null).show();
                return;
            }
            Snackbar.make(this.listViewUsers, "Unmute: " + this.last_error_query, 0).setAction("Action", null).show();
            ((Student) this.osobyLista.get(Integer.valueOf(zmienna))).mutedByMe = true;
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.posCallUnMute.setImageResource(R.drawable.c_be_quiet);
                }
            });
        } catch (Exception e) {
        }
    }

    public void callMute(int zmienna) {
        try {
            this.api.muteClient(zmienna);
            if (this.last_error_query != "ok") {
                ((Student) this.osobyLista.get(Integer.valueOf(zmienna))).mutedByMe = true;
                Toast.makeText(this, "Mute: " + this.last_error_query, 0).show();
            }
        } catch (Exception e) {
        }
    }

    public void callUnMute(int zmienna) {
        try {
            this.api.unmuteClient(zmienna);
            if (this.last_error_query != "ok") {
                ((Student) this.osobyLista.get(Integer.valueOf(zmienna))).mutedByMe = false;
                Toast.makeText(this, "Unmute: " + this.last_error_query, 0).show();
            }
        } catch (Exception e) {
        }
    }

    public void callMoveClient(int zmiennaIDclient, int zmiennaIDchannel) {
        try {
            this.api.moveClient(zmiennaIDclient, zmiennaIDchannel);
            this.wyborZakcjiListy = false;
            Snackbar.make(this.leftList, "Move: " + this.last_error_query, 0).setAction("Action", null).show();
        } catch (Exception e) {
        }
    }

    public void callMoveChannel(int zmiennaIDchannel, int zmiennaIDchannelRodzic) {
        try {
            this.api.moveChannel(zmiennaIDchannel, zmiennaIDchannelRodzic);
            this.wyborZakcjiListy = false;
            Snackbar.make(this.leftList, "Move channel: " + this.last_error_query, 0).setAction("Action", null).show();
        } catch (Exception e) {
        }
    }

    public void callKickCh(int zmienna, int widok) {
        try {
            this.api.kickClientFromChannel(zmienna);
            if (!this.last_error_query.contains("ok")) {
                this.wyborZakcjiListy = false;
                if (widok == 1) {
                    Snackbar.make(this.leftList, "Kick: " + this.last_error_query, 0).setAction("Action", null).show();
                } else {
                    Snackbar.make(this.listViewUsers, "Kick: " + this.last_error_query, 0).setAction("Action", null).show();
                }
            }
        } catch (Exception e) {
        }
    }

    public void callKickServ(int zmienna, int widok) {
        try {
            this.api.kickClientFromServer(zmienna);
            if (!this.last_error_query.contains("ok")) {
                if (widok == 1) {
                    Snackbar.make(this.leftList, "Kick Server: " + this.last_error_query, 0).setAction("Action", null).show();
                } else {
                    Snackbar.make(this.listViewUsers, "Kick Server: " + this.last_error_query, 0).setAction("Action", null).show();
                }
            }
        } catch (Exception e) {
        }
    }

    public void getClients() {
        this.clients = new ArrayList();
        this.osobyLista = new HashMap();
        this.kopia_osob = new HashMap();
        this.bazaListViewUsers = new ArrayList();
        this.temp_clients_all = new ArrayList();
        this.zgrupowane_osoby = new HashMap();
        while (true) {
            this.clients = this.api.getClients();
            if (this.clients != null && this.clients.size() > 0) {
                break;
            }
        }
        this.po2 = 0;
        for (Client c : this.clients) {
            this.stud = new Student();
            this.stud.id = c.getId();
            this.stud.idDB = c.getDatabaseId();
            this.stud.name = c.getNickname();
            this.stud.country = c.getCountry();
            this.stud.channel = c.getChannelId();
            this.stud.input = c.isInputMuted();
            this.stud.output = c.isOutputMuted();
            this.stud.away = c.isAway();
            this.stud.mutedByMe = c.isMuted();
            this.stud.talking = c.isTalking();
            this.stud.awayMessage = c.getAwayMessage();
            this.stud.commander = c.isChannelCommander();
            this.stud.uID = c.getUniqueIdentifier();
            this.osobyLista.put(Integer.valueOf(c.getId()), this.stud);
            this.kopia_osob.put(Integer.valueOf(c.getId()), this.stud);
            if (this.stud.channel == this.myIDchannelOnServer && this.stud.id != this.myIDclientOnserver) {
                this.stud.index = this.po2;
                this.bazaListViewUsers.add(this.stud);
                this.po2++;
            }
            if (this.zgrupowane_osoby.containsKey(Integer.valueOf(c.getChannelId()))) {
                this.mala_paczka_idkow2 = (List) this.zgrupowane_osoby.get(Integer.valueOf(c.getChannelId()));
                this.mala_paczka_idkow2.add(Integer.valueOf(c.getId()));
                this.zgrupowane_osoby.put(Integer.valueOf(c.getChannelId()), this.mala_paczka_idkow2);
            } else {
                this.mala_paczka_idkow2 = new ArrayList();
                this.mala_paczka_idkow2.add(Integer.valueOf(c.getId()));
                this.zgrupowane_osoby.put(Integer.valueOf(c.getChannelId()), this.mala_paczka_idkow2);
            }
            this.temp_clients_all.add(this.stud);
        }
        this.adapterListUsers = new ClientArrayAdapter(this, this.bazaListViewUsers, this);
        this.listViewUsers.setAdapter(this.adapterListUsers);
        if (this.listViewUsers.getCount() <= 0) {
            this.empty_text.setVisibility(0);
        } else {
            this.empty_text.setVisibility(4);
        }
    }

    private void mowieJaNie(final int osoba, final int stan) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (osoba != ClientActivity.this.myIDclientOnserver) {
                    try {
                        ClientActivity.this.updateViewMowi(osoba, stan);
                    } catch (Exception e) {
                    }
                } else if (stan == 0) {
                    ClientActivity.this.img1.setImageBitmap(ClientActivity.this.jaBitmapOff);
                } else if (stan == 2) {
                    ClientActivity.this.img1.setImageBitmap(ClientActivity.this.jaBitmapOff);
                } else {
                    ClientActivity.this.img1.setImageBitmap(ClientActivity.this.jaBitmapOn);
                }
            }
        });
    }

    private void startTS() {
        new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        ClientActivity.this.myIDclientOnserver = ClientActivity.this.api.whoAmI().getCLID();
                        ClientActivity.this.myIDchannelOnServer = ClientActivity.this.api.whoAmI().getCID();
                    } catch (Exception e) {
                    }
                } while (ClientActivity.this.myIDclientOnserver < 0);
                ClientActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            ClientActivity.this.getClients();
                            ClientActivity.this.myOptions();
                        } catch (Exception es) {
                            ClientActivity.this.finish();
                            es.printStackTrace();
                        }
                        try {
                            if (ClientActivity.this.leftList.isEnabled()) {
                                ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                            }
                            ClientActivity.this.mapa2 = ClientActivity.decodeSampledBitmapFromResource(ClientActivity.this.getResources(), R.drawable.tloc, ClientActivity.this.listViewUsers.getWidth(), ClientActivity.this.listViewUsers.getHeight());
                            ClientActivity.this.chat_image.setImageBitmap(ClientActivity.this.mapa2);
                            ClientActivity.this.viewTolbar.setVisibility(0);
                            ClientActivity.this.viewId1.setVisibility(0);
                            ClientActivity.this.viewId2.setVisibility(8);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                ClientActivity.this.listenerInicjacja = new TSListener() {
                    public void onTextMessage(TextMessageEvent e) {
                        final String name = e.getInvokerName();
                        String messageOrg = e.getMessage();
                        if (messageOrg.contains("[URL]")) {
                            messageOrg = messageOrg.replace("[URL]", BuildConfig.FLAVOR).replace("[/URL]", BuildConfig.FLAVOR);
                        }
                        final String mess = messageOrg;
                        final TextMessageTargetMode whisper = e.getTargetMode();
                        ClientActivity.this.now = new SimpleDateFormat("HH:mm");
                        final String date = ClientActivity.this.now.format(Calendar.getInstance().getTime());
                        final TextMessageEvent textMessageEvent = e;
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (textMessageEvent.getInvokerId() != ClientActivity.this.myIDclientOnserver) {
                                    if (whisper == TextMessageTargetMode.CLIENT) {
                                        ClientActivity.this.chatArrayAdapter3.add(new ChatMessage(true, date + " (PRIVATE) " + name + ":   " + mess.replace("[URL]", BuildConfig.FLAVOR)));
                                    } else {
                                        ClientActivity.this.chatArrayAdapter3.add(new ChatMessage(true, date + " " + name + ":   " + mess));
                                    }
                                    if (!ClientActivity.this.chat_bool && ClientActivity.this.wiadomosci_odczytane) {
                                        ClientActivity.this.wiadomosci_odczytane = false;
                                        ClientActivity.this.chat_image_button.setImageBitmap(ClientActivity.this.chatImageBitmapOn);
                                    }
                                }
                            }
                        });
                    }

                    public void onServerEdit(ServerEditedEvent e) {
                    }

                    public void onClientMoved(ClientMovedEvent e) {
                        if (ClientActivity.this.listBotJoinServer.size() > 0) {
                            for (BotJoinServer st : ClientActivity.this.listBotJoinServer) {
                                if (st.ActiveStatus && ((st.Event == 0 || st.Event == 1) && st.Destination == 1)) {
                                    final int id;
                                    if (st.ClientId == -2) {
                                        id = st.BotId;
                                        if (st.TypeAction == 0) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        } else if (st.TypeAction == 1) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingStart /*2*/:
                                                    ClientActivity.this.api.sendChannelMessage(e.getChannelTargetId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingEnd /*3*/:
                                                    ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        }
                                    } else if (st.ClientId == -1) {
                                        if (e.getClientId() == ClientActivity.this.myIDclientOnserver) {
                                            id = st.BotId;
                                            if (st.TypeAction == 0) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            } else if (st.TypeAction == 1) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingStart /*2*/:
                                                        ClientActivity.this.api.sendChannelMessage(e.getChannelTargetId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingEnd /*3*/:
                                                        ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    } else if (e.getClientId() != ClientActivity.this.myIDclientOnserver) {
                                        id = st.BotId;
                                        if (st.TypeAction == 0) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        } else if (st.TypeAction == 1) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingStart /*2*/:
                                                    ClientActivity.this.api.sendChannelMessage(e.getChannelTargetId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingEnd /*3*/:
                                                    ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        int clientMovedId = e.getClientId();
                        int channelTargetId = e.getChannelTargetId();
                        if (clientMovedId == ClientActivity.this.myIDclientOnserver) {
                            ClientActivity.this.zmianaChannela(channelTargetId);
                            ClientActivity.this.myIDchannelOnServer = channelTargetId;
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    try {
                                        ClientActivity.this.adapter2.notifyDataSetChanged();
                                    } catch (Exception e) {
                                        ClientActivity.this.title_text.setText(BuildConfig.FLAVOR);
                                        e.printStackTrace();
                                    }
                                }
                            });
                            return;
                        }
                        try {
                            if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(clientMovedId))).channel == ClientActivity.this.myIDchannelOnServer) {
                                deleteZtempView(clientMovedId);
                            }
                            if (channelTargetId == ClientActivity.this.myIDchannelOnServer) {
                                addClientToView(clientMovedId);
                            }
                            int j = 0;
                            for (MyChannelClass ch : ClientActivity.this.lista_channel_array) {
                                MyChannelClass myChannelClass;
                                if (ch.id == ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(clientMovedId))).channel) {
                                    myChannelClass = (MyChannelClass) ClientActivity.this.lista_channel_array.get(j);
                                    myChannelClass.totalClients--;
                                }
                                if (ch.id == channelTargetId) {
                                    myChannelClass = (MyChannelClass) ClientActivity.this.lista_channel_array.get(j);
                                    myChannelClass.totalClients++;
                                }
                                j++;
                            }
                            ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(clientMovedId))).channel = channelTargetId;
                            int i = 0;
                            for (Student st2 : ClientActivity.this.temp_clients_all) {
                                if (st2.id == clientMovedId) {
                                    ((Student) ClientActivity.this.temp_clients_all.get(i)).channel = channelTargetId;
                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            ClientActivity.this.adapter2.notifyDataSetChanged();
                                        }
                                    });
                                }
                                i++;
                            }
                            ClientActivity.this.runOnUiThread(/* anonymous class already generated */);
                        } catch (Exception e2) {
                        }
                    }

                    private void deleteZtempView(int clientMovedId) {
                        final int temp_clid = clientMovedId;
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                try {
                                    if (ClientActivity.this.bazaListViewUsers.contains(ClientActivity.this.osobyLista.get(Integer.valueOf(temp_clid)))) {
                                        ClientActivity.this.bazaListViewUsers.remove(ClientActivity.this.osobyLista.get(Integer.valueOf(temp_clid)));
                                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(temp_clid))).index = -1;
                                        int i = 0;
                                        for (Student t1 : ClientActivity.this.bazaListViewUsers) {
                                            ((Student) ClientActivity.this.bazaListViewUsers.get(i)).index = i;
                                            i++;
                                        }
                                        ClientActivity.this.getClients();
                                        if (ClientActivity.this.wyborZakcjiListy && ClientActivity.this.drawer.isDrawerOpen(8388611)) {
                                            ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                                            ClientActivity.this.wyborZakcjiListy = false;
                                        }
                                        ClientActivity.this.adapterListUsers.notifyDataSetChanged();
                                        if (ClientActivity.this.listViewUsers.getCount() <= 0) {
                                            ClientActivity.this.empty_text.setVisibility(0);
                                            return;
                                        } else {
                                            ClientActivity.this.empty_text.setVisibility(4);
                                            return;
                                        }
                                    }
                                    ClientActivity.this.adapter2.notifyDataSetChanged();
                                } catch (Exception e) {
                                    if (ClientActivity.this.wyborZakcjiListy) {
                                        ClientActivity.this.wyborZakcjiListy = false;
                                        ClientActivity.this.adapter2.notifyDataSetChanged();
                                    } else {
                                        ClientActivity.this.getClients();
                                    }
                                }
                            }
                        });
                    }

                    private void addClientToView(int clientMovedId) {
                        final int temp_client = clientMovedId;
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (!ClientActivity.this.bazaListViewUsers.contains(ClientActivity.this.osobyLista.get(Integer.valueOf(temp_client)))) {
                                    try {
                                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(temp_client))).index = ClientActivity.this.bazaListViewUsers.size();
                                        ClientActivity.this.bazaListViewUsers.add(ClientActivity.this.osobyLista.get(Integer.valueOf(temp_client)));
                                        if (ClientActivity.this.bazaListViewUsers.size() <= 0) {
                                            ClientActivity.this.empty_text.setVisibility(0);
                                        } else {
                                            ClientActivity.this.empty_text.setVisibility(4);
                                        }
                                        ClientActivity.this.adapterListUsers.notifyDataSetChanged();
                                    } catch (Exception e) {
                                        ClientActivity.this.getClients();
                                        ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
                                    }
                                }
                            }
                        });
                    }

                    public void onClientUpdate(ClientUpdatedEvent e) {
                        int osobaUpdate = e.getClientId();
                        int away = e.getClientAway();
                        String awayMSG = e.getClientAwayMessage();
                        int input = e.getClientInputMuted();
                        int output = e.getClientOutputMuted();
                        int commander = e.getClientCommander();
                        String newNick = e.getClientNickname();
                        try {
                            if (osobaUpdate != ClientActivity.this.myIDclientOnserver && ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaUpdate))).away && awayMSG.compareTo(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaUpdate))).awayMessage) != 0 && input == -1 && output == -1 && commander == -1) {
                                ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaUpdate))).awayMessage = awayMSG;
                                if (away == -1) {
                                    ClientActivity.this.clientAway(osobaUpdate, 1);
                                }
                            }
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                        if (away != -1) {
                            if (!(osobaUpdate == ClientActivity.this.myIDclientOnserver || awayMSG.compareTo(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaUpdate))).awayMessage) == 0)) {
                                ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaUpdate))).awayMessage = awayMSG;
                            }
                            ClientActivity.this.clientAway(osobaUpdate, away);
                        }
                        if (input != -1) {
                            ClientActivity.this.clientInput(osobaUpdate, input);
                        }
                        if (output != -1) {
                            ClientActivity.this.clientOutput(osobaUpdate, output);
                        }
                        if (commander != -1) {
                            ClientActivity.this.clientCommander(osobaUpdate, commander);
                        }
                        if (newNick.length() >= 2) {
                            ClientActivity.this.clientNick(osobaUpdate, newNick);
                        }
                    }

                    public void onClientTalkStatus(ClientTalkStatus e) {
                        ClientActivity.this.zmiennaTalkedId = e.getClientId();
                        ClientActivity.this.zmiennaTalkedStatus = e.getClientStatus();
                        try {
                            if (!((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(e.getClientId()))).mutedByMe) {
                                ClientActivity.this.mowieJaNie(ClientActivity.this.zmiennaTalkedId, ClientActivity.this.zmiennaTalkedStatus);
                            }
                        } catch (Exception e2) {
                        }
                    }

                    public void onClientPoke(ClientPokeEvent e) {
                        final String name = e.getInvokerName();
                        final String mess = e.getMessage();
                        ClientActivity.this.now = new SimpleDateFormat("HH:mm");
                        final String date = ClientActivity.this.now.format(Calendar.getInstance().getTime());
                        final ClientPokeEvent clientPokeEvent = e;
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (clientPokeEvent.getInvokerId() != ClientActivity.this.myIDclientOnserver) {
                                    ClientActivity.this.chatArrayAdapter3.add(new ChatMessage(true, date + " (Pokes you) " + name + ":   " + mess));
                                    if (!ClientActivity.this.chat_bool && ClientActivity.this.wiadomosci_odczytane) {
                                        ClientActivity.this.wiadomosci_odczytane = false;
                                        ClientActivity.this.chat_image_button.setImageBitmap(ClientActivity.this.chatImageBitmapOn);
                                    }
                                }
                            }
                        });
                    }

                    public void onServerGroupList(ServerGroupEvent e) {
                        if (ClientActivity.this.waitServerGroups) {
                            ClientActivity.this.intentG = new Intent(ClientActivity.this, ServerGroupsActivity.class);
                            ClientActivity.this.intentG.putExtra("sGroups", e.get("group"));
                            ClientActivity.this.intentG.putExtra("ipSelected", ClientActivity.this.hostIP);
                            ClientActivity.this.api.getChannelGroups();
                        }
                    }

                    public void onServerChannelGroupList(ServerChannelGroupEvent e) {
                        if (ClientActivity.this.waitServerGroups) {
                            ClientActivity.this.waitServerGroups = false;
                            ClientActivity.this.intentG.putExtra("sChannelGroups", e.get("channelgroup"));
                            ClientActivity.this.startActivity(ClientActivity.this.intentG);
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.waitServerGroupsView.findViewById(R.id.imgRightMenu).setVisibility(8);
                                }
                            });
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                        }
                    }

                    public void onServerPermissionList(ServerPermissionEvent e) {
                        if (ClientActivity.this.waitServerPermission) {
                            ClientActivity.this.waitServerPermission = false;
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.waitServerPermissionView.findViewById(R.id.imgRightMenu).setVisibility(8);
                                }
                            });
                            Runtime.getRuntime().gc();
                            System.gc();
                            ClientActivity.this.blokadaWywolan = false;
                        }
                    }

                    public void onServerGroupPermList(ServerGroupPermEvent e) {
                    }

                    public void onServerGroupChannelPermList(ServerGroupChannelPermEvent e) {
                    }

                    public void onComplainList(ComplainEvent e) {
                        if (ClientActivity.this.wait_for_complainlist) {
                            ClientActivity.this.listaSkarg = new ArrayList();
                            for (HashMap<String, String> m : new DefaultArrayResponse(e.get("complainlist")).getArray()) {
                                ClientActivity.this.listaSkarg.add(new Complaint(m));
                            }
                            ClientActivity.this.wait_for_complainlist = false;
                            ClientActivity.this.oknoLists = true;
                            ClientActivity.this.nowBot = new SimpleDateFormat("yyyy-MM-dd");
                            final String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.viewTolbar.setVisibility(8);
                                    ClientActivity.this.viewId1.setVisibility(8);
                                    if (ClientActivity.this.drawer.isDrawerOpen(8388611)) {
                                        ClientActivity.this.drawer.closeDrawer(8388611);
                                        ClientActivity.this.clearSelectOnLeftList();
                                    }
                                    ClientActivity.this.imageButtonBanAdd.setVisibility(8);
                                    ClientActivity.this.viewId4_complain.setVisibility(0);
                                    if (ClientActivity.this.listaSkarg.size() > 0) {
                                        ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaSkarg.size() + BuildConfig.FLAVOR);
                                        ClientActivity.this.textTitleComplains.setText("Number of complains:");
                                        ClientActivity.this.imageViewComplains.setImageResource(R.drawable.c_skarga);
                                        ClientActivity.this.dataOnComplains.setText(dateBot);
                                        ClientActivity.this.infoComplaints.setVisibility(8);
                                        ClientActivity.this.adapterComplains = new ClientComplainsArrayAdapter(ClientActivity.this.getApplicationContext(), ClientActivity.this.listaSkarg, ClientActivity.this);
                                        ClientActivity.this.listViewComplains.setAdapter(ClientActivity.this.adapterComplains);
                                    }
                                }
                            });
                        }
                    }

                    public void onBanList(BanEvent e) {
                        if (ClientActivity.this.wait_for_banlist || ClientActivity.this.wait_for_banadd) {
                            ClientActivity.this.listaBanow = new ArrayList();
                            for (HashMap<String, String> m : new DefaultArrayResponse(e.get("banlist")).getArray()) {
                                ClientActivity.this.listaBanow.add(new Ban(m));
                            }
                        }
                        if (ClientActivity.this.wait_for_banlist) {
                            ClientActivity.this.wait_for_banlist = false;
                            ClientActivity.this.oknoLists = true;
                            ClientActivity.this.nowBot = new SimpleDateFormat("yyyy-MM-dd");
                            final String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.viewTolbar.setVisibility(8);
                                    ClientActivity.this.viewId1.setVisibility(8);
                                    if (ClientActivity.this.drawer.isDrawerOpen(8388611)) {
                                        ClientActivity.this.drawer.closeDrawer(8388611);
                                        ClientActivity.this.clearSelectOnLeftList();
                                    }
                                    ClientActivity.this.viewId4_complain.setVisibility(0);
                                    ClientActivity.this.imageButtonBanAdd.setVisibility(0);
                                    if (ClientActivity.this.listaBanow.size() > 0) {
                                        ClientActivity.this.textTitleComplains.setText("Number of bans:");
                                        ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaBanow.size() + BuildConfig.FLAVOR);
                                        ClientActivity.this.imageViewComplains.setImageResource(R.drawable.c_block_white);
                                        ClientActivity.this.dataOnComplains.setText(dateBot);
                                        ClientActivity.this.infoComplaints.setVisibility(8);
                                        ClientActivity.this.adapterBans = new ClientBansArrayAdapter(ClientActivity.this.getApplicationContext(), ClientActivity.this.listaBanow, ClientActivity.this);
                                        ClientActivity.this.listViewComplains.setAdapter(ClientActivity.this.adapterBans);
                                    }
                                }
                            });
                        } else if (ClientActivity.this.wait_for_banadd) {
                            ClientActivity.this.wait_for_banadd = false;
                            if (ClientActivity.this.listaBanow.size() > 0) {
                                ClientActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        ClientActivity.this.infoComplaints.setVisibility(8);
                                        ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaBanow.size() + BuildConfig.FLAVOR);
                                        ClientActivity.this.adapterBans = new ClientBansArrayAdapter(ClientActivity.this.getApplicationContext(), ClientActivity.this.listaBanow, ClientActivity.this);
                                        ClientActivity.this.listViewComplains.setAdapter(ClientActivity.this.adapterBans);
                                    }
                                });
                            }
                        }
                    }

                    public void onOfflineMessageList(OfflineMessageEvent e) {
                        if (ClientActivity.this.wait_for_offlinemessagelist) {
                            ClientActivity.this.listaOffline = new ArrayList();
                            for (HashMap<String, String> m : new DefaultArrayResponse(e.get("offlinemessagelist")).getArray()) {
                                ClientActivity.this.listaOffline.add(new Message(m));
                            }
                            ClientActivity.this.wait_for_offlinemessagelist = false;
                            ClientActivity.this.oknoLists = true;
                            ClientActivity.this.nowBot = new SimpleDateFormat("yyyy-MM-dd");
                            final String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.viewTolbar.setVisibility(8);
                                    ClientActivity.this.viewId1.setVisibility(8);
                                    if (ClientActivity.this.drawer.isDrawerOpen(8388611)) {
                                        ClientActivity.this.drawer.closeDrawer(8388611);
                                        ClientActivity.this.clearSelectOnLeftList();
                                    }
                                    ClientActivity.this.imageButtonBanAdd.setVisibility(8);
                                    ClientActivity.this.viewId4_complain.setVisibility(0);
                                    if (ClientActivity.this.listaOffline.size() > 0) {
                                        ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaOffline.size() + BuildConfig.FLAVOR);
                                        ClientActivity.this.textTitleComplains.setText("Number of offline messages:");
                                        ClientActivity.this.imageViewComplains.setImageResource(R.drawable.ic_menu_sort_by_size);
                                        ClientActivity.this.dataOnComplains.setText(dateBot);
                                        ClientActivity.this.infoComplaints.setVisibility(8);
                                        ClientActivity.this.adapterOfflineMessages = new ClientOfflineMessageArrayAdapter(ClientActivity.this.getApplicationContext(), ClientActivity.this.listaOffline, ClientActivity.this);
                                        ClientActivity.this.listViewComplains.setAdapter(ClientActivity.this.adapterOfflineMessages);
                                    }
                                }
                            });
                        }
                    }

                    public void onGetOfflineMessage(OfflineMessageGetEvent e) {
                        if (ClientActivity.this.wait_for_get_offline) {
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ClientActivity.this.progressOfflineMsg.setVisibility(8);
                                }
                            });
                            ClientActivity.this.wait_for_get_offline = false;
                            List<Message> tempMSG = new ArrayList();
                            for (HashMap<String, String> m : new DefaultArrayResponse(e.get("getofflinemsg")).getArray()) {
                                tempMSG.add(new Message(m));
                            }
                            if (tempMSG.size() > 0) {
                                ClientActivity.this.showOfflineMsgDetails((Message) tempMSG.get(0));
                            }
                        }
                    }

                    public void onClientNameFromUid(ClientNameFromUidEvent e) {
                        if (ClientActivity.this.wait_for_uid_nickname) {
                            ClientActivity.this.wait_for_uid_nickname = false;
                            String temp_name = BuildConfig.FLAVOR;
                            List<HashMap<String, String>> arrayNameUid = new DefaultArrayResponse(e.get("clientnamefromuid")).getArray();
                            ClientActivity.this.name_sender_offline = BuildConfig.FLAVOR;
                            ClientActivity.this.name_sender_offline = (String) ((HashMap) arrayNameUid.get(0)).get("name");
                            ClientActivity.this.editFrom.setText(ClientActivity.this.editFrom.getText() + ClientActivity.this.name_sender_offline + ")");
                        }
                    }

                    public void onClientLeave(ClientLeaveEvent e) {
                        int clientLeftId = e.getClientId();
                        if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(clientLeftId))).id == ClientActivity.this.myIDclientOnserver) {
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(ClientActivity.this, "Lost connection to TS or Server!", 1).show();
                                }
                            });
                            ClientActivity.this.finish();
                            return;
                        }
                        if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(clientLeftId))).channel == ClientActivity.this.myIDchannelOnServer) {
                            deleteZtempView(clientLeftId);
                        } else {
                            ClientActivity.this.osobyLista.remove(Integer.valueOf(clientLeftId));
                        }
                        if (ClientActivity.this.listBotJoinServer.size() > 0) {
                            for (BotJoinServer st : ClientActivity.this.listBotJoinServer) {
                                if (st.ActiveStatus && st.Event == 2) {
                                    if (st.Destination == 1 || st.Destination == 0) {
                                        final int id;
                                        if (st.ClientId == -2) {
                                            id = st.BotId;
                                            if (st.TypeAction == 0) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            } else if (st.TypeAction == 1) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingStart /*2*/:
                                                        ClientActivity.this.api.sendChannelMessage(e.getClientFromChannelId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingEnd /*3*/:
                                                        ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            }
                                        } else if (st.ClientId == -1) {
                                            if (e.getClientId() == ClientActivity.this.myIDclientOnserver) {
                                                id = st.BotId;
                                                if (st.TypeAction == 0) {
                                                    switch (st.Reciever) {
                                                        case R.styleable.View_android_theme /*0*/:
                                                            ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                            break;
                                                        case R.styleable.View_android_focusable /*1*/:
                                                            ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                            break;
                                                    }
                                                    if (st.InfoBack) {
                                                        ClientActivity.this.runOnUiThread(new Runnable() {
                                                            public void run() {
                                                                ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                                String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                                Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                                ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                            }
                                                        });
                                                    }
                                                } else if (st.TypeAction == 1) {
                                                    switch (st.Reciever) {
                                                        case R.styleable.View_android_theme /*0*/:
                                                            ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                            break;
                                                        case R.styleable.View_android_focusable /*1*/:
                                                            ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                            break;
                                                        case R.styleable.View_paddingStart /*2*/:
                                                            ClientActivity.this.api.sendChannelMessage(e.getClientFromChannelId(), st.MessageText);
                                                            break;
                                                        case R.styleable.View_paddingEnd /*3*/:
                                                            ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                            break;
                                                    }
                                                    if (st.InfoBack) {
                                                        ClientActivity.this.runOnUiThread(new Runnable() {
                                                            public void run() {
                                                                ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                                String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                                Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                                ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                        } else if (e.getClientId() != ClientActivity.this.myIDclientOnserver) {
                                            id = st.BotId;
                                            if (st.TypeAction == 0) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            } else if (st.TypeAction == 1) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingStart /*2*/:
                                                        ClientActivity.this.api.sendChannelMessage(e.getClientFromChannelId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingEnd /*3*/:
                                                        ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    public void onClientJoin(ClientJoinEvent e) {
                        final Student stud = new Student();
                        stud.id = e.getClientId();
                        stud.name = e.getClientNickname();
                        stud.country = e.getClientCountry();
                        stud.channel = e.getClientTargetId();
                        stud.input = e.isClientInputMuted();
                        stud.output = e.isClientOutputMuted();
                        stud.away = e.isClientAway();
                        stud.awayMessage = e.getClientAwayMessage();
                        stud.commander = e.isClientChannelCommander();
                        ClientActivity.this.osobyLista.put(Integer.valueOf(e.getClientId()), stud);
                        ClientActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                try {
                                    ClientActivity.this.temp_clients_all.add(stud);
                                    ClientActivity.this.adapter2.notifyDataSetChanged();
                                } catch (Exception en) {
                                    en.printStackTrace();
                                }
                            }
                        });
                        if (e.getClientTargetId() == ClientActivity.this.myIDchannelOnServer) {
                            addClientToView(e.getClientId());
                        }
                        if (ClientActivity.this.listBotJoinServer != null && ClientActivity.this.listBotJoinServer.size() > 0) {
                            for (BotJoinServer st : ClientActivity.this.listBotJoinServer) {
                                if (st.ActiveStatus && ((st.Event == 0 || st.Event == 1) && st.Destination == 0)) {
                                    final int id;
                                    if (st.ClientId == -2) {
                                        id = st.BotId;
                                        if (st.TypeAction == 0) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        } else if (st.TypeAction == 1) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingStart /*2*/:
                                                    ClientActivity.this.api.sendChannelMessage(e.getClientTargetId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingEnd /*3*/:
                                                    ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        }
                                    } else if (st.ClientId == -1) {
                                        if (e.getClientId() == ClientActivity.this.myIDclientOnserver) {
                                            id = st.BotId;
                                            if (st.TypeAction == 0) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            } else if (st.TypeAction == 1) {
                                                switch (st.Reciever) {
                                                    case R.styleable.View_android_theme /*0*/:
                                                        ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                        break;
                                                    case R.styleable.View_android_focusable /*1*/:
                                                        ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingStart /*2*/:
                                                        ClientActivity.this.api.sendChannelMessage(e.getClientTargetId(), st.MessageText);
                                                        break;
                                                    case R.styleable.View_paddingEnd /*3*/:
                                                        ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                        break;
                                                }
                                                if (st.InfoBack) {
                                                    ClientActivity.this.runOnUiThread(new Runnable() {
                                                        public void run() {
                                                            ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                            String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                            Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                            ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    } else if (e.getClientId() != ClientActivity.this.myIDclientOnserver) {
                                        id = st.BotId;
                                        if (st.TypeAction == 0) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.pokeClient(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.pokeClient(e.getClientId(), st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Poke status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        } else if (st.TypeAction == 1) {
                                            switch (st.Reciever) {
                                                case R.styleable.View_android_theme /*0*/:
                                                    ClientActivity.this.api.sendPrivateMessage(ClientActivity.this.myIDclientOnserver, st.MessageText);
                                                    break;
                                                case R.styleable.View_android_focusable /*1*/:
                                                    ClientActivity.this.api.sendPrivateMessage(e.getClientId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingStart /*2*/:
                                                    ClientActivity.this.api.sendChannelMessage(e.getClientTargetId(), st.MessageText);
                                                    break;
                                                case R.styleable.View_paddingEnd /*3*/:
                                                    ClientActivity.this.api.sendServerMessage(st.MessageText);
                                                    break;
                                            }
                                            if (st.InfoBack) {
                                                ClientActivity.this.runOnUiThread(new Runnable() {
                                                    public void run() {
                                                        ClientActivity.this.nowBot = new SimpleDateFormat("HH:mm:ss");
                                                        String dateBot = ClientActivity.this.nowBot.format(Calendar.getInstance().getTime());
                                                        Toast.makeText(ClientActivity.this.getApplicationContext(), "BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query, 0).show();
                                                        ClientActivity.this.saveLogs("<" + dateBot + "> BotId = [" + id + "], Msg. status -> " + ClientActivity.this.last_error_query);
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    public void onChannelEdit(ChannelEditedEvent e) {
                        final String name_ch = e.getName().replaceAll(ClientActivity.this.regex, BuildConfig.FLAVOR);
                        final int id_ch = e.getChannelId();
                        if (e.getMap().containsKey("channel_name")) {
                            int j = 0;
                            for (MyChannelClass ch : ClientActivity.this.lista_channel_array) {
                                if (ch.id == e.getChannelId()) {
                                    ((MyChannelClass) ClientActivity.this.lista_channel_array.get(j)).name = name_ch;
                                }
                                j++;
                            }
                            ClientActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (id_ch == ClientActivity.this.myIDchannelOnServer) {
                                        ClientActivity.this.channel_name_chat.setText(name_ch);
                                        ClientActivity.this.title_text.setText(name_ch);
                                    }
                                    ClientActivity.this.adapter2.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    public void onChannelDelete(ChannelDeletedEvent e) {
                    }

                    public void onChannelCreate(ChannelCreatedEvent e) {
                    }

                    public void onChannelMove(ChannelMovedEvent e) {
                    }

                    public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent e) {
                    }
                };
                try {
                    if (ClientActivity.this.api != null) {
                        ClientActivity.this.api.addTSListeners(ClientActivity.this.listenerInicjacja);
                        ClientActivity.this.api.registerAllEvents(Integer.valueOf(ClientActivity.this.api.getSchandlerID().getID()).intValue());
                    }
                } catch (Exception e2) {
                    if (ClientActivity.this.api != null) {
                        ClientActivity.this.api.addTSListeners(ClientActivity.this.listenerInicjacja);
                        try {
                            ClientActivity.this.api.registerAllEvents(Integer.valueOf(ClientActivity.this.api.getSchandlerID().getID()).intValue());
                        } catch (Exception e3) {
                            ClientActivity.this.finish();
                        }
                    }
                }
            }
        }).start();
    }

    private void myUnreadMsg() {
        this.liczbaNieodczytanychOfflineMsg = this.api.getClientInfo(this.myIDclientOnserver, false).getUnreadMessages();
        if (this.liczbaNieodczytanychOfflineMsg > 0) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.textNumberOfflineMsg.setText(ClientActivity.this.liczbaNieodczytanychOfflineMsg + BuildConfig.FLAVOR);
                    ClientActivity.this.textNumberOfflineMsg.setVisibility(0);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    ClientActivity.this.textNumberOfflineMsg.setVisibility(8);
                }
            });
        }
    }

    public void saveLogs(String mess) {
        Type listOfObjectsLog = new TypeToken<List<String>>() {
        }.getType();
        Gson gsonLog = new Gson();
        SharedPreferences myPrefsLog = getSharedPreferences("BotListLog", 0);
        String jsonLog = myPrefsLog.getString("JoinBotsListLog", BuildConfig.FLAVOR);
        if (jsonLog.length() > 0) {
            this.listBotJoinServerLog = (List) gsonLog.fromJson(jsonLog, listOfObjectsLog);
            this.listBotJoinServerLog.add(mess);
        }
        String strObjectLog = gsonLog.toJson(this.listBotJoinServerLog, listOfObjectsLog);
        Editor prefsEditorLog = myPrefsLog.edit();
        prefsEditorLog.putString("JoinBotsListLog", strObjectLog);
        prefsEditorLog.commit();
        this.liczbaNiesprawdzonychLogsow++;
        zliczBledyUprawnien();
    }

    private void zmianaChannela(int channelTargetId) {
        runOnUiThread(new Runnable() {
            public void run() {
                ClientActivity.this.getClients();
                ClientActivity.this.executor.execute(ClientActivity.this.getChannels());
            }
        });
    }

    private void clientNickAway(final int osobaNick, final String newAway) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (osobaNick == ClientActivity.this.myIDclientOnserver) {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).awayMessage = newAway;
                        if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).away) {
                            ClientActivity.this.txtNickname.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).name + " [" + ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).awayMessage + "]");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void updateViewNick(int osobaNick, String newNick) {
                View v = ClientActivity.this.listViewUsers.getChildAt(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).index - ClientActivity.this.listViewUsers.getFirstVisiblePosition());
                if (v != null) {
                    TextView nickOsoby = (TextView) v.findViewById(R.id.label);
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).name = newNick;
                    nickOsoby.setText(newNick);
                }
            }
        });
    }

    private void clientNick(final int osobaNick, final String newNick) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (osobaNick == ClientActivity.this.myIDclientOnserver) {
                        ClientActivity.this.txtNickname.setText(newNick);
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).name = newNick;
                    } else if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).channel == ClientActivity.this.myIDchannelOnServer) {
                        updateViewNick(osobaNick, newNick);
                    } else {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).name = newNick;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void updateViewNick(int osobaNick, String newNick) {
                View v = ClientActivity.this.listViewUsers.getChildAt(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).index - ClientActivity.this.listViewUsers.getFirstVisiblePosition());
                if (v != null) {
                    TextView nickOsoby = (TextView) v.findViewById(R.id.label);
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaNick))).name = newNick;
                    nickOsoby.setText(newNick);
                }
            }
        });
    }

    private void clientOutput(final int osobaOutput, final int output) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (osobaOutput == ClientActivity.this.myIDclientOnserver) {
                        if (output == 0) {
                            ClientActivity.this.jaSpeaker.setImageBitmap(ClientActivity.this.jaSpeakerBitmapOn);
                            ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).output = false;
                            return;
                        }
                        ClientActivity.this.jaSpeaker.setImageBitmap(ClientActivity.this.jaSpeakerBitmapOff);
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).output = true;
                    } else if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).channel == ClientActivity.this.myIDchannelOnServer) {
                        updateViewOutput(osobaOutput, output);
                    } else if (output == 0) {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).output = false;
                    } else {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).output = true;
                    }
                } catch (Exception e) {
                }
            }

            private void updateViewOutput(int osobaOutput, int output) {
                View v = ClientActivity.this.listViewUsers.getChildAt(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).index - ClientActivity.this.listViewUsers.getFirstVisiblePosition());
                if (v != null) {
                    ImageView outputImg = (ImageView) v.findViewById(R.id.outputIcon);
                    if (output == 0) {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).output = false;
                        outputImg.setVisibility(8);
                        return;
                    }
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).output = true;
                    outputImg.setVisibility(0);
                }
            }
        });
    }

    private void clientCommander(final int osobaOutput, final int commander) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (osobaOutput != ClientActivity.this.myIDclientOnserver) {
                        return;
                    }
                    if (commander == 0) {
                        ClientActivity.this.commanderImage.setVisibility(8);
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).commander = false;
                        return;
                    }
                    ClientActivity.this.commanderImage.setVisibility(0);
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaOutput))).commander = true;
                } catch (Exception e) {
                }
            }
        });
    }

    private void clientInput(final int osobaInput, final int input) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (osobaInput == ClientActivity.this.myIDclientOnserver) {
                        if (input == 0) {
                            ClientActivity.this.jaMikro.setImageBitmap(ClientActivity.this.jaMikroBitmapOn);
                            ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).input = false;
                            return;
                        }
                        ClientActivity.this.jaMikro.setImageBitmap(ClientActivity.this.jaMikroBitmapOff);
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).input = true;
                    } else if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).channel == ClientActivity.this.myIDchannelOnServer) {
                        updateViewInput(osobaInput, input);
                    } else if (input == 0) {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).input = false;
                    } else {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).input = true;
                    }
                } catch (Exception e) {
                }
            }

            private void updateViewInput(int osobaInput, int input) {
                View v = ClientActivity.this.listViewUsers.getChildAt(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).index - ClientActivity.this.listViewUsers.getFirstVisiblePosition());
                if (v != null) {
                    ImageView inputImg = (ImageView) v.findViewById(R.id.inputIcon);
                    if (input == 0) {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).input = false;
                        inputImg.setVisibility(8);
                        return;
                    }
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaInput))).input = true;
                    inputImg.setVisibility(0);
                }
            }
        });
    }

    private void clientAway(final int osobaAway, final int away) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (osobaAway == ClientActivity.this.myIDclientOnserver) {
                    if (away == 0) {
                        ClientActivity.this.jaAway.setImageBitmap(ClientActivity.this.jaAwayBitmapOn);
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).away = false;
                        return;
                    }
                    ClientActivity.this.jaAway.setImageBitmap(ClientActivity.this.jaAwayBitmapOff);
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).away = true;
                } else if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).channel == ClientActivity.this.myIDchannelOnServer) {
                    updateViewAway(osobaAway, away);
                } else if (away == 0) {
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).away = false;
                } else {
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).away = true;
                }
            }

            private void updateViewAway(int osobaAway, int away) {
                View v = ClientActivity.this.listViewUsers.getChildAt(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).index - ClientActivity.this.listViewUsers.getFirstVisiblePosition());
                if (v != null) {
                    ImageView awayImg = (ImageView) v.findViewById(R.id.awayIcon);
                    TextView nameText = (TextView) v.findViewById(R.id.label);
                    if (away == 0) {
                        ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).away = false;
                        awayImg.setVisibility(8);
                        nameText.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).name);
                        return;
                    }
                    ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).away = true;
                    awayImg.setVisibility(0);
                    if (((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).awayMessage.length() > 0) {
                        nameText.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).name + " (" + ((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).awayMessage + ")");
                    } else {
                        nameText.setText(((Student) ClientActivity.this.osobyLista.get(Integer.valueOf(osobaAway))).name);
                    }
                }
            }
        });
    }

    private void myOptions() {
        if (((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).input) {
            this.jaMikro.setImageBitmap(this.jaMikroBitmapOff);
        } else {
            this.jaMikro.setImageBitmap(this.jaMikroBitmapOn);
            if (((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).commander) {
                this.commanderImage.setVisibility(0);
            }
        }
        if (((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).away) {
            this.jaAway.setImageBitmap(this.jaAwayBitmapOff);
            if (((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).awayMessage.length() > 0) {
                this.txtNickname.setText(((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).name + " [" + ((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).awayMessage + "]");
            } else {
                this.txtNickname.setText(((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).name);
            }
        } else {
            this.jaAway.setImageBitmap(this.jaAwayBitmapOn);
            this.txtNickname.setText(((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).name);
        }
        if (((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).output) {
            this.jaSpeaker.setImageBitmap(this.jaSpeakerBitmapOff);
        } else {
            this.jaSpeaker.setImageBitmap(this.jaSpeakerBitmapOn);
        }
        if (((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).talking) {
            this.img1.setImageBitmap(this.jaBitmapOn);
        } else {
            this.img1.setImageBitmap(this.jaBitmapOff);
        }
        this.chat_image_button.setImageBitmap(this.chatImageBitmapOff);
        this.edit_image_button.setImageBitmap(this.editNameBitmap);
        this.buttonAd.setImageBitmap(this.adButtonBitmap);
        this.myIDdatabaseOnServer = ((Student) this.osobyLista.get(Integer.valueOf(this.myIDclientOnserver))).idDB;
    }

    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(8388611)) {
            this.drawer.closeDrawer(8388611);
            clearSelectOnLeftList();
            Runtime.getRuntime().gc();
            System.gc();
        } else if (this.oknoLists) {
            this.oknoLists = false;
            this.viewId4_complain.setVisibility(8);
            this.viewTolbar.setVisibility(0);
            this.viewId1.setVisibility(0);
            if (this.odliczanieDoReklamy >= 4) {
                if (this.mInterstitialAd.isLoaded() && !this.brakreklam) {
                    this.mInterstitialAd.show();
                }
                this.odliczanieDoReklamy = 0;
            }
        } else if (!this.chat_bool) {
            super.onBackPressed();
            this.viewTolbar.setVisibility(8);
            this.viewId1.setVisibility(8);
            this.txtNaAgain.setText("Closing activity...");
            this.viewId2.setVisibility(0);
        } else if (this.chat_bool) {
            this.chat_bool = false;
            this.chat_image_tlo.destroyDrawingCache();
            this.viewTolbar.setVisibility(0);
            this.viewId1.setVisibility(0);
            this.viewId3.setVisibility(8);
            if (this.odliczanieDoReklamy >= 3) {
                if (this.mInterstitialAd.isLoaded() && !this.brakreklam) {
                    this.mInterstitialAd.show();
                }
                this.odliczanieDoReklamy = 0;
                return;
            }
            this.odliczanieDoReklamy++;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_facebook) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/TS3MobileApp")));
            return true;
        } else if (id == R.id.action_donation) {
            showUpDonate();
            return true;
        } else if (id != R.id.action_googleplay) {
            return super.onOptionsItemSelected(item);
        } else {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.depakto.tsmobile")));
            return true;
        }
    }

    protected void onDestroy() {
        this.mAdView.destroy();
        super.onDestroy();
        this.kill = true;
        if (this.tempBrod != null) {
            try {
                this.tempBrod.abortBroadcast();
            } catch (Exception e) {
            }
            this.tempBrod.clearAbortBroadcast();
            this.tempBrod = null;
        }
        this.executor.shutdown();
        new Thread(new Runnable() {
            public void run() {
                if (ClientActivity.this.api != null) {
                    ClientActivity.this.api.unregisterAllEvents();
                    ClientActivity.this.api.removeTSListeners(ClientActivity.this.listenerInicjacja);
                    ClientActivity.this.api = null;
                    ClientActivity.this.listenerInicjacja = null;
                }
                if (ClientActivity.this.query != null) {
                    ClientActivity.this.query.exit();
                    ClientActivity.this.query = null;
                    ClientActivity.this.config.setFloodRate(null);
                    ClientActivity.this.config.setDebugLevel(null);
                    ClientActivity.this.config.setHost(null);
                    ClientActivity.this.config = null;
                }
            }
        }).start();
        if (this.adapter2 != null) {
            this.adapter2.clear();
            this.adapter2 = null;
        }
        if (this.adapterListUsers != null) {
            this.adapterListUsers.clear();
            this.adapterListUsers = null;
        }
        if (this.adapter_info != null) {
            this.adapter_info.clear();
            this.adapter_info = null;
        }
        if (this.adapter_info_bot != null) {
            this.adapter_info_bot.clear();
            this.adapter_info_bot = null;
        }
        if (this.adapter_info_lists != null) {
            this.adapter_info_lists.clear();
            this.adapter_info_lists = null;
        }
        if (this.adapter_main != null) {
            this.adapter_main.clear();
            this.adapter_main = null;
        }
        this.againButton.setOnClickListener(null);
        this.againButton = null;
        this.jaMikro.setOnClickListener(null);
        this.jaMikro = null;
        this.jaSpeaker.setOnClickListener(null);
        this.jaSpeaker = null;
        this.jaAway.setOnClickListener(null);
        this.jaAway = null;
        this.listViewUsers.setAdapter(null);
        this.listViewUsers.setOnItemClickListener(null);
        this.listViewUsers.setMultiChoiceModeListener(null);
        this.listViewUsers = null;
        this.chatText3.setOnKeyListener(null);
        this.chatText3 = null;
        this.buttonSend3.setOnClickListener(null);
        this.buttonSend3 = null;
        this.chatArrayAdapter3.clear();
        this.chatArrayAdapter3 = null;
        this.chat_image_button.setOnClickListener(null);
        this.chat_image_button = null;
        this.edit_image_button.setOnClickListener(null);
        this.edit_image_button = null;
        this.imageButtonBanAdd.setOnClickListener(null);
        this.imageButtonBanAdd = null;
        this.list_nav_main.setAdapter(null);
        this.list_nav_main.setOnItemClickListener(null);
        this.list_nav_main = null;
        this.listView3.setOnTouchListener(null);
        this.listView3.setAdapter(null);
        this.listView3 = null;
        this.buttonAd.setOnClickListener(null);
        this.buttonAd = null;
        this.list_nav_info.setAdapter(null);
        this.list_nav_info.setOnItemClickListener(null);
        this.list_nav_info = null;
        this.list_nav_info_bot.setAdapter(null);
        this.list_nav_info_bot.setOnItemClickListener(null);
        this.list_nav_info_bot = null;
        this.list_nav_info_lists.setAdapter(null);
        this.list_nav_info_lists.setOnItemClickListener(null);
        this.list_nav_info_lists = null;
        this.listViewComplains.setAdapter(null);
        this.listViewComplains.setOnItemClickListener(null);
        this.listViewComplains = null;
        this.leftList.setAdapter(null);
        this.leftList.setOnItemClickListener(null);
        this.leftList = null;
        this.listBotJoinServer.clear();
        this.listBotJoinServer = null;
        this.lista_channel_array.clear();
        this.lista_channel_array = null;
        this.chann = null;
        if (this.kopia_osob != null) {
            this.kopia_osob.clear();
            this.kopia_osob = null;
        }
        if (this.osobyLista != null) {
            this.osobyLista.clear();
            this.osobyLista = null;
        }
        this.stud = null;
        this.temp_clients_all.clear();
        this.temp_clients_all = null;
        this.do_paczki = null;
        this.temp_arrayListMieszankiListy.clear();
        this.temp_arrayListMieszankiListy = null;
        this.list_channels.clear();
        this.list_channels = null;
        this.zmObjectChannel = null;
        this.Hchannels.clear();
        this.Hchannels = null;
        if (this.clients != null) {
            this.clients.clear();
            this.clients = null;
        }
        this.mAdView.setAdListener(null);
        this.mAdView.destroy();
        this.mAdView = null;
        this.mInterstitialAd.setAdListener(null);
        this.mInterstitialAd = null;
        this.mActionModeCallback = null;
        this.mActionMode = null;
        this.toggleActionWartosc.setToolbarNavigationClickListener(null);
        this.toggleActionWartosc = null;
        this.toggle.setToolbarNavigationClickListener(null);
        this.toolbar.setNavigationOnClickListener(null);
        this.toolbar.setOnMenuItemClickListener(null);
        this.toolbar.setOnClickListener(null);
        this.toolbar.setTouchDelegate(null);
        this.toolbar = null;
        this.toggle = null;
        this.drawer.setDrawerListener(null);
        this.drawer.setOnClickListener(null);
        this.drawer.removeAllViews();
        this.drawer.removeAllViewsInLayout();
        this.drawer = null;
        this.progressAgain = null;
        this.tempIntent = null;
        this.title_text.addTextChangedListener(null);
        this.title_text = null;
        this.txtNaAgain.addTextChangedListener(null);
        this.txtNaAgain = null;
        this.txtNickname.addTextChangedListener(null);
        this.txtNickname = null;
        this.zgrupowane_osoby.clear();
        this.zgrupowane_osoby = null;
        this.img1.destroyDrawingCache();
        this.img1 = null;
        this.empty_text.clearComposingText();
        this.empty_text.destroyDrawingCache();
        this.empty_text.addTextChangedListener(null);
        this.empty_text = null;
        this.channel_name_chat.clearComposingText();
        this.channel_name_chat.destroyDrawingCache();
        this.channel_name_chat.addTextChangedListener(null);
        this.channel_name_chat = null;
        if (this.HM1 != null) {
            this.HM1.clear();
            this.HM1 = null;
        }
        if (this.LM1 != null) {
            this.LM1.clear();
            this.LM1 = null;
        }
        if (this.arrayListMieszankiListy != null) {
            this.arrayListMieszankiListy.clear();
            this.arrayListMieszankiListy = null;
        }
        this.viewTolbar.destroyDrawingCache();
        this.viewTolbar = null;
        this.viewId1.destroyDrawingCache();
        this.viewId1 = null;
        this.viewId2.destroyDrawingCache();
        this.viewId2 = null;
        this.viewId3.destroyDrawingCache();
        this.viewId3 = null;
        this.welcome_image.destroyDrawingCache();
        this.chat_image.destroyDrawingCache();
        this.welcome_image = null;
        this.mapa1.recycle();
        this.mapa1 = null;
        this.chat_image = null;
        if (this.mapa2 != null) {
            this.mapa2.recycle();
            this.mapa2 = null;
        }
        this.chat_image_tlo.destroyDrawingCache();
        this.chat_image_tlo = null;
        this.jaMikroBitmapOn.recycle();
        this.jaMikroBitmapOn = null;
        this.jaMikroBitmapOff.recycle();
        this.jaMikroBitmapOff = null;
        this.jaSpeakerBitmapOn.recycle();
        this.jaSpeakerBitmapOn = null;
        this.jaSpeakerBitmapOff.recycle();
        this.jaSpeakerBitmapOff = null;
        this.jaAwayBitmapOn.recycle();
        this.jaAwayBitmapOn = null;
        this.jaAwayBitmapOff.recycle();
        this.jaAwayBitmapOff = null;
        this.jaBitmapOn.recycle();
        this.jaBitmapOn = null;
        this.jaBitmapOff.recycle();
        this.jaBitmapOff = null;
        this.editNameBitmap.recycle();
        this.editNameBitmap = null;
        this.chatImageBitmapOn.recycle();
        this.chatImageBitmapOn = null;
        this.chatImageBitmapOff.recycle();
        this.chatImageBitmapOff = null;
        this.adButtonBitmap.recycle();
        this.adButtonBitmap = null;
        this.executor.shutdownNow();
        this.executor = null;
        try {
            unregisterReceiver(this.tempBrod);
        } catch (Exception e2) {
        }
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }

    protected void onNewIntent(Intent intent) {
        this.kill = true;
        String memberFieldString = intent.getStringExtra("KEY");
        try {
            unregisterReceiver(this.tempBrod);
        } catch (Exception e) {
        }
        finish();
        super.finish();
    }

    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
        System.gc();
    }

    public void chatActivity() {
        this.chat_image_button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (this.drawer.isDrawerOpen(8388611)) {
            this.drawer.closeDrawer(8388611);
        }
        this.chat_bool = true;
        this.chat_image_tlo.setImageBitmap(this.mapa1);
        this.viewTolbar.setVisibility(8);
        this.viewId1.setVisibility(8);
        this.viewId2.setVisibility(8);
        this.viewId3.setVisibility(0);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inPreferredConfig = Config.RGB_565;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromResourceOrg(Resources res, int resId, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, options.outWidth, options.outHeight);
        options.inPreferredConfig = Config.RGB_565;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public void callOfflineMessageRead(int idMSG, boolean stan, final TextView name, final TextView time) {
        try {
            this.api.setMessageReadFlag(idMSG, stan);
        } catch (Exception e) {
        }
        if (!this.last_error_query.contains("ok")) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(ClientActivity.this, "Mark message: " + ClientActivity.this.last_error_query, 0).show();
                }
            });
        } else if (stan) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(ClientActivity.this, "Read mark: " + ClientActivity.this.last_error_query, 0).show();
                    name.setTypeface(null, 0);
                    time.setTypeface(null, 2);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(ClientActivity.this, "Unread mark: " + ClientActivity.this.last_error_query, 0).show();
                    name.setTypeface(null, 1);
                    time.setTypeface(null, 3);
                }
            });
        }
    }

    public void callOfflineMessageDel(final int idMessage) {
        Builder builder4 = new Builder(this);
        builder4.setTitle("You will delete it, are you sure?");
        builder4.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ClientActivity.this.api.deleteOfflineMessage(idMessage);
                    if (ClientActivity.this.last_error_query.contains("ok")) {
                        for (Message _listaMessage : ClientActivity.this.listaOffline) {
                            if (_listaMessage.getId() == idMessage) {
                                ClientActivity.this.listaOffline.remove(_listaMessage);
                                ClientActivity.this.adapterOfflineMessages.notifyDataSetChanged();
                                ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaOffline.size() + BuildConfig.FLAVOR);
                                break;
                            }
                        }
                        if (ClientActivity.this.listaOffline.size() <= 0) {
                            ClientActivity.this.infoComplaints.setVisibility(0);
                            ClientActivity.this.infoComplaints.setText("Database is empty, there is no offline messages!");
                        }
                    }
                } catch (Exception es) {
                    es.printStackTrace();
                }
                Snackbar.make(ClientActivity.this.listViewComplains, "Delete offline message: " + ClientActivity.this.last_error_query, -1).setAction("Action", null).show();
            }
        });
        builder4.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder4.show();
    }

    public void callBanDel(final int idBan) {
        Builder builder4 = new Builder(this);
        builder4.setTitle("You will delete it, are you sure?");
        builder4.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ClientActivity.this.api.deleteBan(idBan);
                    if (ClientActivity.this.last_error_query.contains("ok")) {
                        for (Ban _listaBan : ClientActivity.this.listaBanow) {
                            if (_listaBan.getId() == idBan) {
                                ClientActivity.this.listaBanow.remove(_listaBan);
                                ClientActivity.this.adapterBans.notifyDataSetChanged();
                                ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaBanow.size() + BuildConfig.FLAVOR);
                                break;
                            }
                        }
                        if (ClientActivity.this.listaBanow.size() <= 0) {
                            ClientActivity.this.infoComplaints.setVisibility(0);
                        }
                    }
                } catch (Exception es) {
                    es.printStackTrace();
                }
                Snackbar.make(ClientActivity.this.listViewComplains, "Delete ban: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
            }
        });
        builder4.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder4.show();
    }

    public void callComplainDialogDell(final int targetClientDBID, final int fromClientDBID) {
        Builder builder4 = new Builder(this);
        builder4.setTitle("You will delete it, are you sure?");
        builder4.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ClientActivity.this.api.deleteComplaint(targetClientDBID, fromClientDBID);
                    if (ClientActivity.this.last_error_query.contains("ok")) {
                        for (Complaint _listaSkarg : ClientActivity.this.listaSkarg) {
                            if (_listaSkarg.getTargetClientDatabaseId() == targetClientDBID && _listaSkarg.getSourceClientDatabaseId() == fromClientDBID) {
                                ClientActivity.this.listaSkarg.remove(_listaSkarg);
                                ClientActivity.this.adapterComplains.notifyDataSetChanged();
                                ClientActivity.this.numerOfComplains.setText(ClientActivity.this.listaSkarg.size() + BuildConfig.FLAVOR);
                                break;
                            }
                        }
                        if (ClientActivity.this.listaSkarg.size() <= 0) {
                            ClientActivity.this.infoComplaints.setVisibility(0);
                        }
                    }
                } catch (Exception es) {
                    es.printStackTrace();
                }
                Snackbar.make(ClientActivity.this.listViewComplains, "Delete complaint: " + ClientActivity.this.last_error_query, 0).setAction("Action", null).show();
            }
        });
        builder4.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder4.show();
    }

    private boolean sendChatMessage() {
        this.now = new SimpleDateFormat("HH:mm");
        String date = this.now.format(Calendar.getInstance().getTime());
        String grupowyTekst = BuildConfig.FLAVOR;
        String editMessage = this.chatText3.getText().toString();
        for (String item : editMessage.split("\\s+")) {
            try {
                grupowyTekst = grupowyTekst + "[URL]" + new URL(item) + "[/URL] ";
            } catch (MalformedURLException e) {
                if (item.contains("www.")) {
                    grupowyTekst = grupowyTekst + "[URL]" + item + "[/URL] ";
                } else {
                    grupowyTekst = grupowyTekst + item + " ";
                }
            }
        }
        final String message = grupowyTekst;
        this.chatArrayAdapter3.add(new ChatMessage(false, date + " :" + "   " + editMessage));
        new Thread(new Runnable() {
            public void run() {
                ClientActivity.this.api.sendChannelMessage(message);
                if (!ClientActivity.this.last_error_query.contains("ok")) {
                    ClientActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(ClientActivity.this, ClientActivity.this.last_error_query, 0).show();
                        }
                    });
                }
            }
        }).start();
        this.chatText3.setText(BuildConfig.FLAVOR);
        return true;
    }

    protected void onResume() {
        super.onResume();
        if (this.wifi) {
            registerReceiver(this.tempBrod, this.tempIntent);
        }
        Runtime.getRuntime().gc();
        System.gc();
        Type listOfObjects = new TypeToken<List<BotJoinServer>>() {
        }.getType();
        Gson gson = new Gson();
        String json = getSharedPreferences("BotList", 0).getString("JoinBotsList", BuildConfig.FLAVOR);
        if (json.length() > 0) {
            this.listBotJoinServer = (List) gson.fromJson(json, listOfObjects);
        }
        this.liczbaAktywnychBotow = 0;
        if (this.listBotJoinServer.size() > 0) {
            for (BotJoinServer _tempBot : this.listBotJoinServer) {
                if (_tempBot.ActiveStatus) {
                    this.liczbaAktywnychBotow++;
                }
            }
        }
        zliczCreatedBotsRunning();
        if (this.odliczanieDoReklamy >= 3) {
            if (this.mInterstitialAd.isLoaded() && !this.brakreklam) {
                this.mInterstitialAd.show();
            }
            this.odliczanieDoReklamy = 0;
            return;
        }
        this.odliczanieDoReklamy++;
    }

    public void onPause() {
        if (this.wifi) {
            unregisterReceiver(this.tempBrod);
        }
        super.onPause();
    }
}
