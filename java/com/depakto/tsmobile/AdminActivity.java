package com.depakto.tsmobile;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.depakto.classes.adapters.AdminVirtualServersArrayAdapter;
import com.depakto.classes.adapters.ChannelAdminArrayAdapter;
import com.depakto.classes.adapters.ChannelListAdminArrayAdapter;
import com.depakto.classes.adapters.ClientAdminArrayAdapter;
import com.depakto.classes.adapters.ClientLeftListAdminArrayAdapter;
import com.depakto.classes.adapters.ClientServerInfoArrayAdapter;
import com.depakto.classes.construktor.MyChannelClass;
import com.depakto.classes.construktor.OsobyIchannel;
import com.depakto.classes.construktor.ServerInfo;
import com.depakto.classes.construktor.Student;
import com.depakto.classes.help.TSApiAdmin;
import com.depakto.classes.help.TSConfig;
import com.depakto.classes.help.TSQueryAdmin;
import com.depakto.classes.help.api.ChannelProperty;
import com.depakto.classes.help.api.wrapper.Channel;
import com.depakto.classes.help.api.wrapper.Client;
import com.depakto.classes.help.api.wrapper.Password;
import com.depakto.classes.help.api.wrapper.VirtualServer;
import com.depakto.classes.help.api.wrapper.VirtualServerInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class AdminActivity extends AppCompatActivity {
    public HashMap<Integer, Integer> HM1;
    public HashMap<Integer, Channel> Hchannels = new HashMap();
    public HashMap<Integer, Integer> LM1;
    public ChannelAdminArrayAdapter adapter2;
    private ChannelListAdminArrayAdapter adapter2Channels;
    public ArrayAdapter<String> adapterListUsers;
    private ClientLeftListAdminArrayAdapter adapter_main;
    public AdminVirtualServersArrayAdapter adapter_virtuals;
    private ClientServerInfoArrayAdapter adapter_virtuals1;
    public TSApiAdmin api;
    public ArrayList<Channel> arrayList;
    public ArrayList<OsobyIchannel> arrayListMieszankiListy = new ArrayList();
    public List<VirtualServer> arrayList_virtuals;
    private List<Student> bazaListViewUsers = new ArrayList();
    private boolean blokadaWywolan = false;
    public MyChannelClass chann;
    public ListView channelList;
    public TextView channelsText;
    private Button chat_button;
    private List<Client> clients;
    public TSConfig config = new TSConfig();
    private ServerInfo dane1 = new ServerInfo();
    private List<ServerInfo> daneList1 = new ArrayList();
    public OsobyIchannel do_paczki;
    public DrawerLayout drawer_admin;
    public ExecutorService executor;
    public ArrayList items;
    private HashMap<Integer, Student> kopia_osob;
    public String last_error_query = BuildConfig.FLAVOR;
    public ListView leftList;
    public int liczba;
    public List<Channel> listCh;
    private ListView listInfoAdmin;
    public HashMap<Integer, Channel> list_channels = new HashMap();
    private ListView list_nav_main;
    public List<MyChannelClass> lista_channel_array = new ArrayList();
    private String loginPassword;
    private String loginUsername;
    private ActionMode mActionMode;
    private Callback mActionModeCallback = new Callback() {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.list_channel_menu, menu);
            if (AdminActivity.this.selected_channeluser.idClient > -1) {
                Snackbar.make(AdminActivity.this.leftList, "Chosen user: " + AdminActivity.this.selected_channeluser.nameClient, 0).setAction("Action", null).show();
                menu.getItem(0).getSubMenu().removeItem(R.id.action_join_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_del_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_new_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_sub_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_edit_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_kick_client_server);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_ban_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_client_info);
            } else {
                Snackbar.make(AdminActivity.this.leftList, "Chosen channel: " + AdminActivity.this.selected_channeluser.nameChannel, 0).setAction("Action", null).show();
                menu.getItem(0).getSubMenu().removeItem(R.id.action_join_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_sub_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_edit_channel);
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
            mode.finish();
            return false;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_poke_client /*2131493450*/:
                    AdminActivity.this.callPoke(AdminActivity.this.selected_channeluser.idClient);
                    mode.finish();
                    return true;
                case R.id.action_chat_client /*2131493451*/:
                    Builder builder3 = new Builder(AdminActivity.this);
                    builder3.setTitle("Enter private message");
                    final EditText input3 = new EditText(AdminActivity.this);
                    input3.setInputType(1);
                    builder3.setView(input3);
                    builder3.setPositiveButton("OK", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                AdminActivity.this.api.sendPrivateMessage(AdminActivity.this.selected_channeluser.idClient, input3.getText().toString());
                                Toast.makeText(AdminActivity.this, "Private: " + AdminActivity.this.last_error_query, 1).show();
                            } catch (Exception e) {
                            }
                        }
                    });
                    builder3.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder3.show();
                    mode.finish();
                    return true;
                case R.id.action_kick_client /*2131493452*/:
                    AdminActivity.this.callKickCh(AdminActivity.this.selected_channeluser.idClient);
                    if (!AdminActivity.this.last_error_query.contains("ok")) {
                        return true;
                    }
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.leftList.setEnabled(false);
                            AdminActivity.this.executor.execute(AdminActivity.this.getChannels2());
                        }
                    });
                    mode.finish();
                    return true;
                case R.id.action_complain_client /*2131493456*/:
                    Builder builder2 = new Builder(AdminActivity.this);
                    builder2.setTitle("Enter complaint");
                    final EditText input2 = new EditText(AdminActivity.this);
                    input2.setInputType(1);
                    builder2.setView(input2);
                    builder2.setPositiveButton("OK", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                AdminActivity.this.api.addComplain(AdminActivity.this.selected_channeluser.idClientDB, input2.getText().toString());
                                Toast.makeText(AdminActivity.this, "Complaint: " + AdminActivity.this.last_error_query, 1).show();
                            } catch (Exception e) {
                            }
                        }
                    });
                    builder2.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder2.show();
                    mode.finish();
                    return true;
                case R.id.action_join_channel /*2131493458*/:
                    AdminActivity.this.api.moveClient(AdminActivity.this.myIDclient, AdminActivity.this.selected_channeluser.idChannel);
                    if (AdminActivity.this.last_error_query.contains("ok")) {
                        AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                    }
                    Toast.makeText(AdminActivity.this, "Channel change: " + AdminActivity.this.last_error_query, 0).show();
                    if (AdminActivity.this.last_error_query.contains("password")) {
                        Builder builder = new Builder(AdminActivity.this);
                        builder.setTitle("Enter password");
                        final EditText input = new EditText(AdminActivity.this);
                        input.setInputType(1);
                        builder.setView(input);
                        builder.setPositiveButton("OK", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String hashPass = ((Password) AdminActivity.this.api.hashPassword(input.getText().toString()).get(0)).getPassword();
                                if (AdminActivity.this.last_error_query.contains("ok")) {
                                    if (AdminActivity.this.drawer_admin.isDrawerOpen(8388611)) {
                                        AdminActivity.this.drawer_admin.closeDrawer(8388611);
                                    }
                                    Toast.makeText(AdminActivity.this, "Channel change: " + AdminActivity.this.last_error_query, 0).show();
                                    return;
                                }
                                Toast.makeText(AdminActivity.this, "Channel change: " + AdminActivity.this.last_error_query, 0).show();
                            }
                        });
                        builder.setNegativeButton("Cancel", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                    mode.finish();
                    return true;
                case R.id.action_new_channel /*2131493459*/:
                    AdminActivity.this.callNewChannel();
                    mode.finish();
                    return true;
                case R.id.action_del_channel /*2131493463*/:
                    Builder builder4 = new Builder(AdminActivity.this);
                    builder4.setTitle("You will delete it, are you sure?");
                    builder4.setPositiveButton("Yes", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            AdminActivity.this.api.deleteChannel(AdminActivity.this.selected_channeluser.idChannel);
                            Toast.makeText(AdminActivity.this.getApplicationContext(), "Delete channel: " + AdminActivity.this.last_error_query, 1).show();
                            if (AdminActivity.this.last_error_query.contains("ok")) {
                                AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                            }
                        }
                    });
                    builder4.setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder4.show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        public void onDestroyActionMode(ActionMode mode) {
            AdminActivity.this.mActionMode = null;
        }
    };
    public List<Integer> mala_paczka_idkow;
    public List<Integer> mala_paczka_idkow2;
    private Bitmap mapa1;
    private int myIDclient;
    private int myVirtualServerID;
    public TextView noUsersText;
    public TextView operationText;
    private HashMap<Integer, Student> osobyLista = new HashMap();
    public int po;
    public int po2;
    private int portOptional;
    private ProgressBar progressBasic;
    public ProgressBar progress_icon;
    public TSQueryAdmin query;
    private int queryPort;
    private List<MyChannelClass> selected_channels = new ArrayList();
    public OsobyIchannel selected_channeluser;
    private List<Student> selected_clients = new ArrayList();
    public List<Student> selected_clients_temp = new ArrayList();
    private String serverAddress;
    private Button startButton;
    public TextView statusText;
    private Button stopButton;
    public Student stud;
    public ArrayList<OsobyIchannel> temp_arrayListMieszankiListy = new ArrayList();
    private List<Student> temp_clients_all = new ArrayList();
    public TextView text_connection;
    public TextView text_selectVirtual;
    public TextView timeText;
    private Toolbar toolbar_admin;
    private Menu topMenu;
    public ListView userList;
    public TextView usersText;
    public TextView versionText;
    public ListView virtual_list;
    public ImageView welcome_image;
    public ImageView welcome_image2;
    public ImageView welcome_image3;
    public ImageView welcome_image4;
    private HashMap<Integer, List<Integer>> zgrupowane_osoby = new HashMap();
    public int zmChannelID;
    public Channel zmObjectChannel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.toolbar_admin = (Toolbar) findViewById(R.id.toolbar_admin);
        this.toolbar_admin.setTitle(BuildConfig.FLAVOR);
        setSupportActionBar(this.toolbar_admin);
        this.drawer_admin = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        this.leftList = (ListView) findViewById(R.id.left_drawer_admin);
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.nav_header_main_admin, this.leftList, false);
        String[] valuesMain = new String[]{"TS3mobile", "Give a review!", "Join to Us!"};
        ArrayList<String> list_main = new ArrayList();
        for (Object add : valuesMain) {
            list_main.add(add);
        }
        this.adapter_main = new ClientLeftListAdminArrayAdapter(getApplicationContext(), list_main);
        this.leftList.addHeaderView(header, null, false);
        this.list_nav_main = (ListView) findViewById(R.id.listMain);
        this.list_nav_main.setAdapter(this.adapter_main);
        setListViewHeightBasedOnItems(this.list_nav_main);
        this.leftList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AdminActivity.this.selected_channeluser = (OsobyIchannel) AdminActivity.this.arrayListMieszankiListy.get(position - 1);
                AdminActivity.this.mActionMode = AdminActivity.this.startActionMode(AdminActivity.this.mActionModeCallback);
                view.setSelected(true);
            }
        });
        this.list_nav_main.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String itemik = AdminActivity.this.list_nav_main.getItemAtPosition(position).toString();
                boolean z = true;
                switch (itemik.hashCode()) {
                    case -1633790154:
                        if (itemik.equals("TS3mobile")) {
                            z = false;
                            break;
                        }
                        break;
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
                }
                switch (z) {
                    case R.styleable.View_android_theme /*0*/:
                        System.gc();
                        return;
                    case R.styleable.View_android_focusable /*1*/:
                        if (!AdminActivity.this.blokadaWywolan) {
                            AdminActivity.this.blokadaWywolan = true;
                            AdminActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.depakto.tsmobile")));
                            System.gc();
                            AdminActivity.this.blokadaWywolan = false;
                            return;
                        }
                        return;
                    case R.styleable.View_paddingStart /*2*/:
                        if (!AdminActivity.this.blokadaWywolan) {
                            AdminActivity.this.blokadaWywolan = true;
                            AdminActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.facebook.com/TS3MobileApp")));
                            System.gc();
                            AdminActivity.this.blokadaWywolan = false;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        this.mapa1 = decodeSampledBitmapFromResource(getResources(), R.drawable.tlowelcome, displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.welcome_image = (ImageView) findViewById(R.id.welcome_image);
        this.welcome_image.setImageBitmap(this.mapa1);
        this.welcome_image2 = (ImageView) findViewById(R.id.welcome_image2);
        this.welcome_image3 = (ImageView) findViewById(R.id.welcome_image3);
        this.welcome_image4 = (ImageView) findViewById(R.id.welcome_image4);
        this.welcome_image2.setImageBitmap(this.mapa1);
        this.welcome_image3.setImageBitmap(this.mapa1);
        this.welcome_image4.setImageBitmap(this.mapa1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawer_admin, this.toolbar_admin, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                if (AdminActivity.this.leftList.isEnabled()) {
                    Snackbar.make(drawerView, "REFRESHING...", 0).setAction("Action", null).show();
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.leftList.setEnabled(false);
                            if (AdminActivity.this.stopButton.isEnabled()) {
                                AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                                AdminActivity.this.executor.execute(AdminActivity.this.server_info_get());
                                return;
                            }
                            Toast.makeText(AdminActivity.this.getApplicationContext(), "Server offline!", 0).show();
                        }
                    });
                }
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                AdminActivity.this.leftList.clearChoices();
                if (AdminActivity.this.mActionMode != null) {
                    AdminActivity.this.mActionMode.finish();
                }
            }
        };
        this.drawer_admin.setDrawerListener(toggle);
        toggle.syncState();
        this.toolbar_admin.setVisibility(8);
        this.progress_icon = (ProgressBar) findViewById(R.id.progressBar_admin);
        Bundle bBundle = getIntent().getExtras();
        if (bBundle != null) {
            this.serverAddress = (String) bBundle.get("address");
            this.loginUsername = (String) bBundle.get("login");
            this.loginPassword = (String) bBundle.get("password");
            this.queryPort = ((Integer) bBundle.get("queryport")).intValue();
            this.portOptional = ((Integer) bBundle.get("port")).intValue();
        }
        this.config.setHost(this.serverAddress);
        this.config.setDebugToFile(false);
        this.config.setDebugLevel(Level.OFF);
        this.config.setQueryPort(this.queryPort);
        this.config.setLoginCredentials(this.loginUsername, this.loginPassword);
        this.executor = Executors.newFixedThreadPool(100);
        this.executor.execute(serverConnection());
        getWindow().setSoftInputMode(3);
        this.noUsersText = (TextView) findViewById(R.id.usersOnlineText);
        this.userList = (ListView) findViewById(R.id.usersListAdmin);
        ListView listView = this.userList;
        ListView listView2 = this.userList;
        listView.setChoiceMode(3);
        this.userList.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(AdminActivity.this.userList.getCheckedItemCount() + " Selected");
                Student st = (Student) AdminActivity.this.userList.getItemAtPosition(position);
                if (AdminActivity.this.selected_clients.contains(st)) {
                    AdminActivity.this.selected_clients.remove(st);
                } else {
                    AdminActivity.this.selected_clients.add(st);
                }
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.list_channel_menu, menu);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_new_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_sub_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_edit_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_join_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_del_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_kick_client_server);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_ban_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_client_info);
                AdminActivity.this.toolbar_admin.setVisibility(8);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_poke_client /*2131493450*/:
                        List<Integer> lip = new ArrayList();
                        for (Student st : AdminActivity.this.selected_clients) {
                            lip.add(Integer.valueOf(st.id));
                        }
                        AdminActivity.this.callPokeAll(lip);
                        mode.finish();
                        return true;
                    case R.id.action_chat_client /*2131493451*/:
                        List<Integer> lip3 = new ArrayList();
                        for (Student st2 : AdminActivity.this.selected_clients) {
                            lip3.add(Integer.valueOf(st2.id));
                        }
                        AdminActivity.this.addPrivateMess(lip3);
                        mode.finish();
                        return true;
                    case R.id.action_kick_client /*2131493452*/:
                        List<Integer> lip1 = new ArrayList();
                        for (Student st22 : AdminActivity.this.selected_clients) {
                            lip1.add(Integer.valueOf(st22.id));
                        }
                        AdminActivity.this.callKickChAll(lip1);
                        AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                        mode.finish();
                        return true;
                    case R.id.action_complain_client /*2131493456*/:
                        List<Integer> lip2 = new ArrayList();
                        for (Student st222 : AdminActivity.this.selected_clients) {
                            lip2.add(Integer.valueOf(st222.idDB));
                        }
                        AdminActivity.this.addComplainAll(lip2);
                        mode.finish();
                        return true;
                    case R.id.action_join_channel /*2131493458*/:
                        AdminActivity.this.api.moveClient(AdminActivity.this.myIDclient, AdminActivity.this.selected_channeluser.idChannel);
                        if (AdminActivity.this.last_error_query.contains("ok") && AdminActivity.this.drawer_admin.isDrawerOpen(8388611)) {
                            AdminActivity.this.drawer_admin.closeDrawer(8388611);
                        }
                        Toast.makeText(AdminActivity.this, "Channel change: " + AdminActivity.this.last_error_query, 0).show();
                        if (AdminActivity.this.last_error_query.contains("password")) {
                            Builder builder = new Builder(AdminActivity.this);
                            builder.setTitle("Enter password");
                            final EditText input = new EditText(AdminActivity.this);
                            input.setInputType(1);
                            builder.setView(input);
                            builder.setPositiveButton("OK", new OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String hashPass = ((Password) AdminActivity.this.api.hashPassword(input.getText().toString()).get(0)).getPassword();
                                    if (AdminActivity.this.last_error_query.contains("ok")) {
                                        if (AdminActivity.this.drawer_admin.isDrawerOpen(8388611)) {
                                            AdminActivity.this.drawer_admin.closeDrawer(8388611);
                                        }
                                        Toast.makeText(AdminActivity.this, "Channel change: " + AdminActivity.this.last_error_query, 0).show();
                                        return;
                                    }
                                    Toast.makeText(AdminActivity.this, "Channel change: " + AdminActivity.this.last_error_query, 0).show();
                                }
                            });
                            builder.setNegativeButton("Cancel", new OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }
                        mode.finish();
                        return true;
                    case R.id.action_new_channel /*2131493459*/:
                        AdminActivity.this.callNewChannel();
                        mode.finish();
                        return true;
                    case R.id.action_del_channel /*2131493463*/:
                        AdminActivity.this.api.deleteChannel(AdminActivity.this.selected_channeluser.idChannel);
                        Toast.makeText(AdminActivity.this.getApplicationContext(), "Delete channel: " + AdminActivity.this.last_error_query, 1).show();
                        if (AdminActivity.this.last_error_query.contains("ok")) {
                            AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                        }
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            public void onDestroyActionMode(ActionMode mode) {
                AdminActivity.this.selected_clients.clear();
                AdminActivity.this.toolbar_admin.setVisibility(0);
            }
        });
        this.userList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AdminActivity.this.userList.setItemChecked(position, true);
            }
        });
        this.channelList = (ListView) findViewById(R.id.channelsListAdmin);
        listView = this.channelList;
        listView2 = this.channelList;
        listView.setChoiceMode(3);
        this.channelList.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(AdminActivity.this.channelList.getCheckedItemCount() + " Selected");
                MyChannelClass st = (MyChannelClass) AdminActivity.this.channelList.getItemAtPosition(position);
                if (AdminActivity.this.selected_channels.contains(st)) {
                    AdminActivity.this.selected_channels.remove(st);
                } else {
                    AdminActivity.this.selected_channels.add(st);
                }
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.list_channel_menu, menu);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_join_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_sub_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_edit_channel);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_poke_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_chat_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_complain_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_kick_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_kick_client_server);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_ban_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_move_client);
                menu.getItem(0).getSubMenu().removeItem(R.id.action_client_info);
                AdminActivity.this.toolbar_admin.setVisibility(8);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_new_channel /*2131493459*/:
                        AdminActivity.this.callNewChannel();
                        mode.finish();
                        return true;
                    case R.id.action_del_channel /*2131493463*/:
                        List<Integer> lip = new ArrayList();
                        for (MyChannelClass st : AdminActivity.this.selected_channels) {
                            lip.add(Integer.valueOf(st.id));
                        }
                        AdminActivity.this.callDeleteAll(lip);
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            public void onDestroyActionMode(ActionMode mode) {
                AdminActivity.this.selected_channels.clear();
                AdminActivity.this.toolbar_admin.setVisibility(0);
            }
        });
        this.channelList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AdminActivity.this.channelList.setItemChecked(position, true);
            }
        });
        this.startButton = (Button) findViewById(R.id.startButton);
        this.stopButton = (Button) findViewById(R.id.stopButton);
        this.text_connection = (TextView) findViewById(R.id.text_connecting);
        this.text_selectVirtual = (TextView) findViewById(R.id.text_selectVirtual);
        this.virtual_list = (ListView) findViewById(R.id.virtual_list);
        this.virtual_list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AdminActivity.this.myVirtualServerID = ((VirtualServer) AdminActivity.this.arrayList_virtuals.get(position)).getId();
                AdminActivity.this.api.selectVirtualServerById(AdminActivity.this.myVirtualServerID);
                if (AdminActivity.this.last_error_query.contains("not running")) {
                    AdminActivity.this.myIDclient = AdminActivity.this.api.whoAmI().getId();
                    AdminActivity.this.welcome_image.destroyDrawingCache();
                    AdminActivity.this.findViewById(R.id.admin_lay1).setVisibility(8);
                    AdminActivity.this.findViewById(R.id.admin_lay2).setVisibility(0);
                    AdminActivity.this.toolbar_admin.setVisibility(0);
                    AdminActivity.this.startButton.setEnabled(true);
                    AdminActivity.this.startButton.setAlpha(1.0f);
                    AdminActivity.this.stopButton.setEnabled(false);
                    AdminActivity.this.stopButton.setAlpha(0.5f);
                    return;
                }
                AdminActivity.this.myIDclient = AdminActivity.this.api.whoAmI().getId();
                AdminActivity.this.welcome_image.destroyDrawingCache();
                AdminActivity.this.findViewById(R.id.admin_lay1).setVisibility(8);
                AdminActivity.this.findViewById(R.id.admin_lay2).setVisibility(0);
                AdminActivity.this.toolbar_admin.setVisibility(0);
                AdminActivity.this.executor.execute(AdminActivity.this.server_info_get());
                AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                AdminActivity.this.startButton.setEnabled(false);
                AdminActivity.this.startButton.setAlpha(0.5f);
                AdminActivity.this.stopButton.setEnabled(true);
                AdminActivity.this.stopButton.setAlpha(1.0f);
            }
        });
        this.startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (AdminActivity.this.portOptional > 0) {
                    Toast.makeText(AdminActivity.this.getApplicationContext(), "Need privileges to all virtual servers.\n (Admin account)", 1).show();
                    return;
                }
                AdminActivity.this.api.startServer(AdminActivity.this.myVirtualServerID);
                AdminActivity.this.api.selectVirtualServerById(AdminActivity.this.myVirtualServerID);
                Toast.makeText(AdminActivity.this.getApplicationContext(), "Start server: " + AdminActivity.this.last_error_query, 1).show();
                AdminActivity.this.executor.execute(AdminActivity.this.server_info_get());
                AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                AdminActivity.this.startButton.setEnabled(false);
                AdminActivity.this.startButton.setAlpha(0.5f);
                AdminActivity.this.stopButton.setEnabled(true);
                AdminActivity.this.stopButton.setAlpha(1.0f);
            }
        });
        this.stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (AdminActivity.this.portOptional > 0) {
                    Toast.makeText(AdminActivity.this.getApplicationContext(), "Need privileges to all virtual servers.\n (Admin account)", 1).show();
                    return;
                }
                AdminActivity.this.api.stopServer(AdminActivity.this.myVirtualServerID);
                Toast.makeText(AdminActivity.this.getApplicationContext(), "Stop server: " + AdminActivity.this.last_error_query, 1).show();
                AdminActivity.this.startButton.setEnabled(true);
                AdminActivity.this.startButton.setAlpha(1.0f);
                AdminActivity.this.stopButton.setEnabled(false);
                AdminActivity.this.stopButton.setAlpha(0.5f);
                AdminActivity.this.executor.execute(AdminActivity.this.server_info_get());
            }
        });
        this.listInfoAdmin = (ListView) findViewById(R.id.listBasicAdmin);
        this.progressBasic = (ProgressBar) findViewById(R.id.progressBasic);
    }

    private void callDeleteAll(final List<Integer> lip) {
        try {
            Builder builder = new Builder(this);
            builder.setTitle("You will delete it, are you sure?");
            builder.setPositiveButton("OK", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    for (Integer ct : lip) {
                        AdminActivity.this.api.deleteChannel(ct.intValue());
                        if (AdminActivity.this.last_error_query.contains("ok")) {
                            Toast.makeText(AdminActivity.this, "Delete: " + AdminActivity.this.last_error_query, 0).show();
                        } else {
                            Toast.makeText(AdminActivity.this, "Delete: " + AdminActivity.this.last_error_query, 0).show();
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
        } catch (Exception e) {
        }
    }

    void dane1Uzupelnij(String _id, String _details) {
        this.dane1 = new ServerInfo();
        this.dane1.id = _id;
        this.dane1.details = _details;
        this.daneList1.add(this.dane1);
    }

    private void addPrivateMess(final List<Integer> list3) {
        Builder builder3 = new Builder(this);
        builder3.setTitle("Enter private message");
        final EditText input3 = new EditText(this);
        input3.setInputType(1);
        builder3.setView(input3);
        builder3.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input3.getText().toString();
                for (Integer ct : list3) {
                    try {
                        AdminActivity.this.api.sendPrivateMessage(ct.intValue(), m_Text);
                        Toast.makeText(AdminActivity.this, "Private: " + AdminActivity.this.last_error_query, 0).show();
                    } catch (Exception e) {
                    }
                }
            }
        });
        builder3.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder3.show();
    }

    private void addComplainAll(final List<Integer> list2) {
        Builder builder2 = new Builder(this);
        builder2.setTitle("Enter complaint");
        final EditText input2 = new EditText(this);
        input2.setInputType(1);
        builder2.setView(input2);
        builder2.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input2.getText().toString();
                for (Integer ct : list2) {
                    try {
                        AdminActivity.this.api.addComplain(ct.intValue(), m_Text);
                        Toast.makeText(AdminActivity.this, "Complaint: " + AdminActivity.this.last_error_query, 0).show();
                    } catch (Exception e) {
                    }
                }
            }
        });
        builder2.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder2.show();
    }

    private void callKickChAll(List<Integer> list1) {
        try {
            for (Integer ct : list1) {
                this.api.kickClientFromChannel(ct.intValue());
                Toast.makeText(this, "Kick: " + this.last_error_query, 0).show();
            }
        } catch (Exception e) {
        }
    }

    public void petla(HashMap<Integer, Integer> LM, Integer zmChannelID, Integer i) {
        for (Entry<Integer, Integer> entry2 : LM.entrySet()) {
            if (((Integer) entry2.getValue()).equals(zmChannelID)) {
                this.items.add(this.Hchannels.get(entry2.getKey()));
                i = Integer.valueOf(i.intValue() + 1);
                petla(LM, (Integer) entry2.getKey(), i);
            }
        }
    }

    private Runnable getChannels() {
        return new Runnable() {
            public void run() {
                AdminActivity.this.listCh = new ArrayList();
                AdminActivity.this.liczba = 99999;
                AdminActivity.this.HM1 = new HashMap();
                AdminActivity.this.LM1 = new HashMap();
                AdminActivity.this.items = new ArrayList();
                AdminActivity.this.Hchannels = new HashMap();
                try {
                    AdminActivity.this.listCh = AdminActivity.this.api.getChannels();
                    for (Channel c : AdminActivity.this.listCh) {
                        AdminActivity.this.Hchannels.put(Integer.valueOf(c.getId()), c);
                        if (c.getParentChannelId() == 0) {
                            AdminActivity.this.HM1.put(Integer.valueOf(c.getOrder()), Integer.valueOf(c.getId()));
                            if (AdminActivity.this.liczba > c.getOrder()) {
                                AdminActivity.this.liczba = c.getOrder();
                            }
                        } else {
                            AdminActivity.this.LM1.put(Integer.valueOf(c.getId()), Integer.valueOf(c.getParentChannelId()));
                        }
                    }
                    while (AdminActivity.this.HM1.containsKey(Integer.valueOf(AdminActivity.this.liczba))) {
                        AdminActivity.this.zmChannelID = ((Integer) AdminActivity.this.HM1.get(Integer.valueOf(AdminActivity.this.liczba))).intValue();
                        AdminActivity.this.zmObjectChannel = (Channel) AdminActivity.this.Hchannels.get(Integer.valueOf(AdminActivity.this.zmChannelID));
                        AdminActivity.this.items.add(AdminActivity.this.zmObjectChannel);
                        AdminActivity.this.petla(AdminActivity.this.LM1, Integer.valueOf(AdminActivity.this.zmChannelID), Integer.valueOf(0));
                        AdminActivity.this.liczba = AdminActivity.this.zmChannelID;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AdminActivity.this.temp_arrayListMieszankiListy = new ArrayList();
                AdminActivity.this.arrayList = AdminActivity.this.items;
                if (AdminActivity.this.lista_channel_array == null) {
                    AdminActivity.this.lista_channel_array = new ArrayList();
                } else {
                    AdminActivity.this.lista_channel_array.clear();
                }
                AdminActivity.this.po = 0;
                Iterator it = AdminActivity.this.arrayList.iterator();
                while (it.hasNext()) {
                    Channel ch = (Channel) it.next();
                    AdminActivity.this.chann = new MyChannelClass();
                    AdminActivity.this.chann.id = ch.getId();
                    AdminActivity.this.chann.name = ch.getName();
                    AdminActivity.this.chann.hasPassword = ch.hasPassword();
                    AdminActivity.this.chann.maxClients = ch.getMaxClients();
                    AdminActivity.this.chann.totalClients = ch.getTotalClients();
                    AdminActivity.this.chann.parentId = ch.getParentChannelId();
                    AdminActivity.this.lista_channel_array.add(AdminActivity.this.chann);
                    AdminActivity.this.do_paczki = new OsobyIchannel();
                    AdminActivity.this.do_paczki.hasPasswordChannel = ch.hasPassword();
                    AdminActivity.this.do_paczki.idChannel = ch.getId();
                    AdminActivity.this.do_paczki.maxClientsChannel = ch.getMaxClients();
                    AdminActivity.this.do_paczki.nameChannel = ch.getName();
                    AdminActivity.this.do_paczki.parentIdChannel = ch.getParentChannelId();
                    AdminActivity.this.do_paczki.totalClientsChannel = ch.getTotalClients();
                    AdminActivity.this.temp_arrayListMieszankiListy.add(AdminActivity.this.po, AdminActivity.this.do_paczki);
                    AdminActivity adminActivity = AdminActivity.this;
                    adminActivity.po++;
                    if (ch.getTotalClients() > 0 && AdminActivity.this.zgrupowane_osoby.containsKey(Integer.valueOf(ch.getId()))) {
                        AdminActivity.this.mala_paczka_idkow = new ArrayList();
                        AdminActivity.this.mala_paczka_idkow = (List) AdminActivity.this.zgrupowane_osoby.get(Integer.valueOf(ch.getId()));
                        for (Integer ab : AdminActivity.this.mala_paczka_idkow) {
                            AdminActivity.this.do_paczki = new OsobyIchannel();
                            try {
                                AdminActivity.this.do_paczki.awayClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).away;
                                AdminActivity.this.do_paczki.idClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).id;
                                AdminActivity.this.do_paczki.idClientDB = ((Student) AdminActivity.this.kopia_osob.get(ab)).idDB;
                                AdminActivity.this.do_paczki.inputClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).input;
                                AdminActivity.this.do_paczki.mutedByMeClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).mutedByMe;
                                AdminActivity.this.do_paczki.nameClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).name;
                                AdminActivity.this.do_paczki.outputClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).output;
                            } catch (Exception e2) {
                            }
                            AdminActivity.this.temp_arrayListMieszankiListy.add(AdminActivity.this.po, AdminActivity.this.do_paczki);
                            adminActivity = AdminActivity.this;
                            adminActivity.po++;
                        }
                    }
                }
                if (AdminActivity.this.adapter2 == null) {
                    AdminActivity.this.arrayListMieszankiListy.clear();
                    AdminActivity.this.arrayListMieszankiListy.addAll(AdminActivity.this.temp_arrayListMieszankiListy);
                    AdminActivity.this.adapter2 = new ChannelAdminArrayAdapter(AdminActivity.this.getApplicationContext(), AdminActivity.this.arrayListMieszankiListy, AdminActivity.this);
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.leftList.setAdapter(AdminActivity.this.adapter2);
                            AdminActivity.this.leftList.setEnabled(true);
                        }
                    });
                } else {
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.arrayListMieszankiListy.clear();
                            AdminActivity.this.arrayListMieszankiListy.addAll(AdminActivity.this.temp_arrayListMieszankiListy);
                            AdminActivity.this.adapter2.notifyDataSetChanged();
                            AdminActivity.this.leftList.setEnabled(true);
                            Snackbar.make(AdminActivity.this.leftList, "READY!", -1).setAction("Action", null).show();
                        }
                    });
                }
                for (int temp = 0; temp <= AdminActivity.this.arrayList.size() - 1; temp++) {
                    AdminActivity.this.list_channels.put(Integer.valueOf(temp), AdminActivity.this.arrayList.get(temp));
                }
            }
        };
    }

    private Runnable getClients() {
        return new Runnable() {
            public void run() {
                AdminActivity.this.clients = new ArrayList();
                AdminActivity.this.osobyLista = new HashMap();
                AdminActivity.this.kopia_osob = new HashMap();
                AdminActivity.this.bazaListViewUsers = new ArrayList();
                AdminActivity.this.temp_clients_all = new ArrayList();
                AdminActivity.this.zgrupowane_osoby = new HashMap();
                while (true) {
                    AdminActivity.this.clients = AdminActivity.this.api.getClients();
                    if (AdminActivity.this.clients != null && AdminActivity.this.clients.size() > 0) {
                        break;
                    }
                }
                AdminActivity.this.po2 = 0;
                for (Client c : AdminActivity.this.clients) {
                    AdminActivity.this.stud = new Student();
                    AdminActivity.this.stud.id = c.getId();
                    AdminActivity.this.stud.idDB = c.getDatabaseId();
                    AdminActivity.this.stud.name = c.getNickname();
                    AdminActivity.this.stud.country = c.getCountry();
                    AdminActivity.this.stud.channel = c.getChannelId();
                    AdminActivity.this.stud.input = c.isInputMuted();
                    AdminActivity.this.stud.output = c.isOutputMuted();
                    AdminActivity.this.stud.away = c.isAway();
                    AdminActivity.this.stud.mutedByMe = c.isMuted();
                    AdminActivity.this.stud.talking = c.isTalking();
                    AdminActivity.this.osobyLista.put(Integer.valueOf(c.getId()), AdminActivity.this.stud);
                    AdminActivity.this.kopia_osob.put(Integer.valueOf(c.getId()), AdminActivity.this.stud);
                    AdminActivity.this.stud.index = AdminActivity.this.po2;
                    AdminActivity.this.bazaListViewUsers.add(AdminActivity.this.stud);
                    AdminActivity adminActivity = AdminActivity.this;
                    adminActivity.po2++;
                    if (AdminActivity.this.zgrupowane_osoby.containsKey(Integer.valueOf(c.getChannelId()))) {
                        AdminActivity.this.mala_paczka_idkow2 = (List) AdminActivity.this.zgrupowane_osoby.get(Integer.valueOf(c.getChannelId()));
                        AdminActivity.this.mala_paczka_idkow2.add(Integer.valueOf(c.getId()));
                        AdminActivity.this.zgrupowane_osoby.put(Integer.valueOf(c.getChannelId()), AdminActivity.this.mala_paczka_idkow2);
                    } else {
                        AdminActivity.this.mala_paczka_idkow2 = new ArrayList();
                        AdminActivity.this.mala_paczka_idkow2.add(Integer.valueOf(c.getId()));
                        AdminActivity.this.zgrupowane_osoby.put(Integer.valueOf(c.getChannelId()), AdminActivity.this.mala_paczka_idkow2);
                    }
                    AdminActivity.this.temp_clients_all.add(AdminActivity.this.stud);
                }
                AdminActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        List<Channel> chane = AdminActivity.this.api.getChannels();
                        AdminActivity.this.adapterListUsers = new ClientAdminArrayAdapter(AdminActivity.this.getApplicationContext(), AdminActivity.this.bazaListViewUsers, chane, AdminActivity.this);
                        AdminActivity.this.userList.setAdapter(AdminActivity.this.adapterListUsers);
                        if (AdminActivity.this.userList.getCount() <= 0) {
                            AdminActivity.this.noUsersText.setVisibility(0);
                        } else {
                            AdminActivity.this.noUsersText.setVisibility(4);
                        }
                    }
                });
                AdminActivity.this.executor.execute(AdminActivity.this.getChannels());
            }
        };
    }

    private Runnable server_info_get() {
        return new Runnable() {
            public void run() {
                VirtualServerInfo get_info_server = AdminActivity.this.api.getServerInfo();
                AdminActivity.this.daneList1.clear();
                try {
                    AdminActivity.this.dane1Uzupelnij("Users ", get_info_server.getClientsOnline() + "/" + get_info_server.getMaxClients());
                    AdminActivity.this.dane1Uzupelnij("Channels ", get_info_server.getChannelsOnline() + BuildConfig.FLAVOR);
                    AdminActivity.this.dane1Uzupelnij("Status ", get_info_server.getServerStatus().getName());
                    AdminActivity.this.dane1Uzupelnij("upTime ", (((int) get_info_server.getUptime()) / 3600) + "h");
                    AdminActivity.this.dane1Uzupelnij("Operation System: ", get_info_server.getPlatform());
                    AdminActivity.this.dane1Uzupelnij("Version: ", get_info_server.getVersion());
                } catch (Exception e) {
                    Toast.makeText(AdminActivity.this.getApplicationContext(), "Lost connections!", 0).show();
                    AdminActivity.this.finish();
                }
                AdminActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            AdminActivity.this.adapter_virtuals1 = new ClientServerInfoArrayAdapter(AdminActivity.this.getApplicationContext(), AdminActivity.this.daneList1);
                            AdminActivity.this.listInfoAdmin.setAdapter(AdminActivity.this.adapter_virtuals1);
                            AdminActivity.setListViewHeightBasedOnItems(AdminActivity.this.listInfoAdmin);
                            AdminActivity.this.progressBasic.setVisibility(8);
                            AdminActivity.this.myIDclient = AdminActivity.this.api.whoAmI().getId();
                        } catch (Exception e) {
                            AdminActivity.this.finish();
                        }
                    }
                });
            }
        };
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

    protected void onNewIntent(Intent intent) {
    }

    private Runnable serverConnection() {
        return new Runnable() {
            public void run() {
                AdminActivity.this.query = new TSQueryAdmin(AdminActivity.this.config);
                try {
                    AdminActivity.this.query.connect(AdminActivity.this);
                    final String temp_info = AdminActivity.this.last_error_query;
                    if (temp_info.contains("invalid")) {
                        AdminActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                AdminActivity.this.text_connection.setText("invalid login or password");
                                AdminActivity.this.progress_icon.setVisibility(4);
                            }
                        });
                        AdminActivity.this.executor.shutdown();
                        AdminActivity.this.executor.shutdownNow();
                        if (AdminActivity.this.api != null) {
                            AdminActivity.this.api.unregisterAllEvents();
                        }
                        if (AdminActivity.this.query != null) {
                            AdminActivity.this.query.exit();
                        }
                    } else if (temp_info.contains("ok")) {
                        AdminActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                AdminActivity.this.text_connection.setText("Ok!");
                                AdminActivity.this.progress_icon.setVisibility(4);
                                AdminActivity.this.text_connection.setVisibility(8);
                                AdminActivity.this.text_selectVirtual.setVisibility(0);
                                AdminActivity.this.virtual_list.setVisibility(0);
                            }
                        });
                        try {
                            AdminActivity.this.showVirtualServers();
                        } catch (Exception e) {
                        }
                    } else {
                        AdminActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                AdminActivity.this.text_connection.setText(temp_info);
                                AdminActivity.this.progress_icon.setVisibility(4);
                            }
                        });
                        AdminActivity.this.executor.shutdown();
                        AdminActivity.this.executor.shutdownNow();
                        if (AdminActivity.this.api != null) {
                            AdminActivity.this.api.unregisterAllEvents();
                        }
                        if (AdminActivity.this.query != null) {
                            AdminActivity.this.query.exit();
                        }
                    }
                } catch (Exception e2) {
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.text_connection.setText("QueryPort out of range! Fix it.");
                            AdminActivity.this.progress_icon.setVisibility(4);
                        }
                    });
                    AdminActivity.this.executor.shutdown();
                    AdminActivity.this.executor.shutdownNow();
                    if (AdminActivity.this.api != null) {
                        AdminActivity.this.api.unregisterAllEvents();
                    }
                    if (AdminActivity.this.query != null) {
                        AdminActivity.this.query.exit();
                    }
                }
            }
        };
    }

    private void showVirtualServers() {
        this.api = this.query.getApi();
        this.api.whoAmI();
        if (this.portOptional > 0) {
            this.api.selectVirtualServerByPort(this.portOptional);
            if (this.last_error_query.contains("ok")) {
                this.myIDclient = this.api.whoAmI().getId();
                this.welcome_image.destroyDrawingCache();
                runOnUiThread(new Runnable() {
                    public void run() {
                        AdminActivity.this.findViewById(R.id.admin_lay1).setVisibility(8);
                        AdminActivity.this.findViewById(R.id.admin_lay2).setVisibility(0);
                        AdminActivity.this.toolbar_admin.setVisibility(0);
                        AdminActivity.this.startButton.setEnabled(false);
                        AdminActivity.this.startButton.setAlpha(0.5f);
                        AdminActivity.this.stopButton.setEnabled(true);
                        AdminActivity.this.stopButton.setAlpha(1.0f);
                    }
                });
                this.executor.execute(server_info_get());
                this.executor.execute(getClients());
                return;
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    AdminActivity.this.text_connection.setVisibility(0);
                    if (AdminActivity.this.last_error_query.contains("not running")) {
                        AdminActivity.this.text_connection.setText(AdminActivity.this.last_error_query + ", contact with Admin.");
                    } else {
                        AdminActivity.this.text_connection.setText(AdminActivity.this.last_error_query + ", port(optional) is wrong! Fix it.");
                    }
                }
            });
            return;
        }
        this.arrayList_virtuals = this.api.getVirtualServers();
        if (this.last_error_query.contains("permissions")) {
            runOnUiThread(new Runnable() {
                public void run() {
                    AdminActivity.this.text_connection.setVisibility(0);
                    AdminActivity.this.text_connection.setText(AdminActivity.this.last_error_query + ",\n not access to list of all servers, use port(optional)!");
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    AdminActivity.this.adapter_virtuals = new AdminVirtualServersArrayAdapter(AdminActivity.this.getApplicationContext(), AdminActivity.this.arrayList_virtuals, AdminActivity.this);
                    AdminActivity.this.virtual_list.setAdapter(AdminActivity.this.adapter_virtuals);
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_admin, menu);
        this.topMenu = menu;
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clients) {
            if (this.stopButton.isEnabled()) {
                Snackbar.make(this.leftList, "Refreshing...", -1).setAction("Action", null).show();
                this.executor.execute(getClients());
                findViewById(R.id.admin_lay1).setVisibility(8);
                findViewById(R.id.admin_lay2).setVisibility(8);
                findViewById(R.id.admin_lay_channels).setVisibility(8);
                findViewById(R.id.admin_lay_clients).setVisibility(0);
                return true;
            }
            Toast.makeText(getApplicationContext(), "Server offline!", 0).show();
            return true;
        } else if (id == R.id.action_channels) {
            if (this.stopButton.isEnabled()) {
                Snackbar.make(this.leftList, "Refreshing...", -1).setAction("Action", null).show();
                this.executor.execute(getChannels2());
                findViewById(R.id.admin_lay1).setVisibility(8);
                findViewById(R.id.admin_lay2).setVisibility(8);
                findViewById(R.id.admin_lay_channels).setVisibility(0);
                findViewById(R.id.admin_lay_clients).setVisibility(8);
                return true;
            }
            Toast.makeText(getApplicationContext(), "Server offline!", 0).show();
            return true;
        } else if (id != R.id.action_main) {
            return super.onOptionsItemSelected(item);
        } else {
            this.progressBasic.setVisibility(0);
            this.executor.execute(server_info_get());
            findViewById(R.id.admin_lay1).setVisibility(8);
            findViewById(R.id.admin_lay2).setVisibility(0);
            findViewById(R.id.admin_lay_channels).setVisibility(8);
            findViewById(R.id.admin_lay_clients).setVisibility(8);
            return true;
        }
    }

    private Runnable getChannels2() {
        return new Runnable() {
            public void run() {
                AdminActivity.this.clients = new ArrayList();
                AdminActivity.this.osobyLista = new HashMap();
                AdminActivity.this.kopia_osob = new HashMap();
                AdminActivity.this.temp_clients_all = new ArrayList();
                HashMap<Integer, List<Integer>> zgrupowane_osoby2 = new HashMap();
                AdminActivity.this.mala_paczka_idkow = new ArrayList();
                while (true) {
                    AdminActivity.this.clients = AdminActivity.this.api.getClients();
                    if (AdminActivity.this.clients != null && AdminActivity.this.clients.size() > 0) {
                        break;
                    }
                }
                AdminActivity.this.po2 = 0;
                for (Client c : AdminActivity.this.clients) {
                    AdminActivity.this.stud = new Student();
                    AdminActivity.this.stud.id = c.getId();
                    AdminActivity.this.stud.idDB = c.getDatabaseId();
                    AdminActivity.this.stud.name = c.getNickname();
                    AdminActivity.this.stud.country = c.getCountry();
                    AdminActivity.this.stud.channel = c.getChannelId();
                    AdminActivity.this.stud.input = c.isInputMuted();
                    AdminActivity.this.stud.output = c.isOutputMuted();
                    AdminActivity.this.stud.away = c.isAway();
                    AdminActivity.this.stud.mutedByMe = c.isMuted();
                    AdminActivity.this.stud.talking = c.isTalking();
                    AdminActivity.this.osobyLista.put(Integer.valueOf(c.getId()), AdminActivity.this.stud);
                    AdminActivity.this.kopia_osob.put(Integer.valueOf(c.getId()), AdminActivity.this.stud);
                    if (zgrupowane_osoby2.containsKey(Integer.valueOf(c.getChannelId()))) {
                        AdminActivity.this.mala_paczka_idkow2 = (List) zgrupowane_osoby2.get(Integer.valueOf(c.getChannelId()));
                        AdminActivity.this.mala_paczka_idkow2.add(Integer.valueOf(c.getId()));
                        zgrupowane_osoby2.put(Integer.valueOf(c.getChannelId()), AdminActivity.this.mala_paczka_idkow2);
                    } else {
                        AdminActivity.this.mala_paczka_idkow2 = new ArrayList();
                        AdminActivity.this.mala_paczka_idkow2.add(Integer.valueOf(c.getId()));
                        zgrupowane_osoby2.put(Integer.valueOf(c.getChannelId()), AdminActivity.this.mala_paczka_idkow2);
                    }
                    AdminActivity.this.temp_clients_all.add(AdminActivity.this.stud);
                }
                AdminActivity.this.listCh = new ArrayList();
                AdminActivity.this.liczba = 99999;
                AdminActivity.this.HM1 = new HashMap();
                AdminActivity.this.LM1 = new HashMap();
                AdminActivity.this.items = new ArrayList();
                AdminActivity.this.Hchannels = new HashMap();
                try {
                    AdminActivity.this.listCh = AdminActivity.this.api.getChannels();
                    for (Channel c2 : AdminActivity.this.listCh) {
                        AdminActivity.this.Hchannels.put(Integer.valueOf(c2.getId()), c2);
                        if (c2.getParentChannelId() == 0) {
                            AdminActivity.this.HM1.put(Integer.valueOf(c2.getOrder()), Integer.valueOf(c2.getId()));
                            if (AdminActivity.this.liczba > c2.getOrder()) {
                                AdminActivity.this.liczba = c2.getOrder();
                            }
                        } else {
                            AdminActivity.this.LM1.put(Integer.valueOf(c2.getId()), Integer.valueOf(c2.getParentChannelId()));
                        }
                    }
                    while (AdminActivity.this.HM1.containsKey(Integer.valueOf(AdminActivity.this.liczba))) {
                        AdminActivity.this.zmChannelID = ((Integer) AdminActivity.this.HM1.get(Integer.valueOf(AdminActivity.this.liczba))).intValue();
                        AdminActivity.this.zmObjectChannel = (Channel) AdminActivity.this.Hchannels.get(Integer.valueOf(AdminActivity.this.zmChannelID));
                        AdminActivity.this.items.add(AdminActivity.this.zmObjectChannel);
                        AdminActivity.this.petla(AdminActivity.this.LM1, Integer.valueOf(AdminActivity.this.zmChannelID), Integer.valueOf(0));
                        AdminActivity.this.liczba = AdminActivity.this.zmChannelID;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AdminActivity.this.temp_arrayListMieszankiListy = new ArrayList();
                AdminActivity.this.arrayList = AdminActivity.this.items;
                if (AdminActivity.this.lista_channel_array == null) {
                    AdminActivity.this.lista_channel_array = new ArrayList();
                } else {
                    AdminActivity.this.lista_channel_array.clear();
                }
                AdminActivity.this.po = 0;
                Iterator it = AdminActivity.this.arrayList.iterator();
                while (it.hasNext()) {
                    Channel ch = (Channel) it.next();
                    AdminActivity.this.chann = new MyChannelClass();
                    AdminActivity.this.chann.id = ch.getId();
                    AdminActivity.this.chann.name = ch.getName();
                    AdminActivity.this.chann.hasPassword = ch.hasPassword();
                    AdminActivity.this.chann.maxClients = ch.getMaxClients();
                    AdminActivity.this.chann.totalClients = ch.getTotalClients();
                    AdminActivity.this.chann.parentId = ch.getParentChannelId();
                    if (ch.isPermanent()) {
                        AdminActivity.this.chann.type = "Permanent";
                    } else if (ch.isSemiPermanent()) {
                        AdminActivity.this.chann.type = "Semi-Permanent";
                    }
                    if (ch.isDefault()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        MyChannelClass myChannelClass = AdminActivity.this.chann;
                        myChannelClass.type = stringBuilder.append(myChannelClass.type).append(", Default").toString();
                    }
                    AdminActivity.this.lista_channel_array.add(AdminActivity.this.chann);
                    AdminActivity.this.do_paczki = new OsobyIchannel();
                    AdminActivity.this.do_paczki.hasPasswordChannel = ch.hasPassword();
                    AdminActivity.this.do_paczki.idChannel = ch.getId();
                    AdminActivity.this.do_paczki.maxClientsChannel = ch.getMaxClients();
                    AdminActivity.this.do_paczki.nameChannel = ch.getName();
                    AdminActivity.this.do_paczki.parentIdChannel = ch.getParentChannelId();
                    AdminActivity.this.do_paczki.totalClientsChannel = ch.getTotalClients();
                    AdminActivity.this.temp_arrayListMieszankiListy.add(AdminActivity.this.po, AdminActivity.this.do_paczki);
                    AdminActivity adminActivity = AdminActivity.this;
                    adminActivity.po++;
                    if (ch.getTotalClients() > 0 && zgrupowane_osoby2.containsKey(Integer.valueOf(ch.getId()))) {
                        AdminActivity.this.mala_paczka_idkow = new ArrayList();
                        AdminActivity.this.mala_paczka_idkow = (List) zgrupowane_osoby2.get(Integer.valueOf(ch.getId()));
                        for (Integer ab : AdminActivity.this.mala_paczka_idkow) {
                            AdminActivity.this.do_paczki = new OsobyIchannel();
                            AdminActivity.this.do_paczki.awayClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).away;
                            AdminActivity.this.do_paczki.idClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).id;
                            AdminActivity.this.do_paczki.idClientDB = ((Student) AdminActivity.this.kopia_osob.get(ab)).idDB;
                            AdminActivity.this.do_paczki.inputClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).input;
                            AdminActivity.this.do_paczki.mutedByMeClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).mutedByMe;
                            AdminActivity.this.do_paczki.nameClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).name;
                            AdminActivity.this.do_paczki.outputClient = ((Student) AdminActivity.this.kopia_osob.get(ab)).output;
                            AdminActivity.this.temp_arrayListMieszankiListy.add(AdminActivity.this.po, AdminActivity.this.do_paczki);
                            adminActivity = AdminActivity.this;
                            adminActivity.po++;
                        }
                    }
                }
                AdminActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AdminActivity.this.adapter2Channels = new ChannelListAdminArrayAdapter(AdminActivity.this.getApplicationContext(), AdminActivity.this.lista_channel_array, AdminActivity.this);
                        AdminActivity.this.channelList.setAdapter(AdminActivity.this.adapter2Channels);
                    }
                });
                if (AdminActivity.this.adapter2 == null) {
                    AdminActivity.this.arrayListMieszankiListy.clear();
                    AdminActivity.this.arrayListMieszankiListy.addAll(AdminActivity.this.temp_arrayListMieszankiListy);
                    AdminActivity.this.adapter2 = new ChannelAdminArrayAdapter(AdminActivity.this.getApplicationContext(), AdminActivity.this.arrayListMieszankiListy, AdminActivity.this);
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.leftList.setAdapter(AdminActivity.this.adapter2);
                            AdminActivity.this.leftList.setEnabled(true);
                        }
                    });
                } else {
                    AdminActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            AdminActivity.this.arrayListMieszankiListy.clear();
                            AdminActivity.this.arrayListMieszankiListy.addAll(AdminActivity.this.temp_arrayListMieszankiListy);
                            AdminActivity.this.adapter2.notifyDataSetChanged();
                            AdminActivity.this.leftList.setEnabled(true);
                            Snackbar.make(AdminActivity.this.leftList, "READY!", -1).setAction("Action", null).show();
                        }
                    });
                }
                for (int temp = 0; temp <= AdminActivity.this.arrayList.size() - 1; temp++) {
                    AdminActivity.this.list_channels.put(Integer.valueOf(temp), AdminActivity.this.arrayList.get(temp));
                }
            }
        };
    }

    public void callNewChannel() {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        Builder builder = new Builder(this);
        builder.setTitle("Create new channel");
        Context context = getApplicationContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(1);
        int pixels = (int) ((1.0f * scale) + 0.5f);
        View line = new View(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, pixels);
        layoutParams.topMargin = (int) ((10.0f * scale) + 0.5f);
        layoutParams.bottomMargin = (int) ((10.0f * scale) + 0.5f);
        line.setLayoutParams(layoutParams);
        line.setBackgroundColor(-16777216);
        layout.addView(line);
        final EditText titleBox = new EditText(context);
        titleBox.setInputType(1);
        titleBox.setHint("Name");
        titleBox.setHintTextColor(-16777216);
        titleBox.setTextColor(-16777216);
        layout.addView(titleBox);
        final CheckBox boxPass = new CheckBox(context);
        boxPass.setText("Password");
        boxPass.setHintTextColor(-16777216);
        boxPass.setTextColor(-16777216);
        layout.addView(boxPass);
        final EditText passText = new EditText(context);
        passText.setHint("Password");
        passText.setInputType(1);
        passText.setHintTextColor(-16777216);
        passText.setTextColor(-16777216);
        passText.setVisibility(8);
        layout.addView(passText);
        View view = new View(context);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(-16777216);
        layout.addView(view);
        final Spinner channelType = new Spinner(context);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.channel_type, 17367048);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        channelType.setAdapter(adapter);
        layout.addView(channelType);
        final CheckBox defaultBox = new CheckBox(context);
        defaultBox.setText("Default Channel");
        defaultBox.setHintTextColor(-16777216);
        defaultBox.setTextColor(-16777216);
        layout.addView(defaultBox);
        view = new View(context);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(-16777216);
        layout.addView(view);
        view = new TextView(context);
        view.setText("Max Users");
        view.setTextColor(-16777216);
        layout.addView(view);
        final CheckBox UnlimitedBoxUsers = new CheckBox(context);
        UnlimitedBoxUsers.setText("Unlimited");
        UnlimitedBoxUsers.setHintTextColor(-16777216);
        UnlimitedBoxUsers.setTextColor(-16777216);
        UnlimitedBoxUsers.setChecked(true);
        layout.addView(UnlimitedBoxUsers);
        final EditText limitText = new EditText(context);
        limitText.setInputType(2);
        limitText.setText("1");
        limitText.setTextColor(-16777216);
        limitText.setVisibility(8);
        layout.addView(limitText);
        builder.setView(layout);
        boxPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (boxPass.isChecked()) {
                    passText.setVisibility(0);
                    passText.setText(BuildConfig.FLAVOR);
                    return;
                }
                passText.setVisibility(8);
                passText.setText(BuildConfig.FLAVOR);
            }
        });
        defaultBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (defaultBox.isChecked()) {
                    channelType.setVisibility(8);
                } else {
                    channelType.setVisibility(0);
                }
            }
        });
        UnlimitedBoxUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (UnlimitedBoxUsers.isChecked()) {
                    limitText.setVisibility(8);
                    limitText.setText("1");
                    return;
                }
                limitText.setVisibility(0);
                limitText.setText("1");
            }
        });
        builder.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String m_name = titleBox.getText().toString();
                String m_pass = passText.getText().toString();
                HashMap<ChannelProperty, String> options = new HashMap();
                if (boxPass.isChecked() && m_pass.length() > 0) {
                    options.put(ChannelProperty.CHANNEL_PASSWORD, m_pass);
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
                AdminActivity.this.api.createChannel(m_name, options);
                if (AdminActivity.this.last_error_query.contains("ok")) {
                    AdminActivity.this.executor.execute(AdminActivity.this.getClients());
                    Toast.makeText(AdminActivity.this, "Create channel: " + AdminActivity.this.last_error_query, 0).show();
                    return;
                }
                Toast.makeText(AdminActivity.this, "Create channel: " + AdminActivity.this.last_error_query, 0).show();
            }
        });
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void callPoke(final int zmienna) {
        try {
            Builder builder = new Builder(this);
            builder.setTitle("Enter poke message");
            final EditText input = new EditText(this);
            input.setInputType(1);
            builder.setView(input);
            builder.setPositiveButton("OK", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    AdminActivity.this.api.pokeClient(zmienna, input.getText().toString());
                    if (AdminActivity.this.last_error_query.contains("ok")) {
                        Toast.makeText(AdminActivity.this, "Poke: " + AdminActivity.this.last_error_query, 0).show();
                    } else {
                        Toast.makeText(AdminActivity.this, "Poke: " + AdminActivity.this.last_error_query, 0).show();
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

    public void callPokeAll(final List<Integer> list) {
        try {
            Builder builder = new Builder(this);
            builder.setTitle("Enter poke message");
            final EditText input = new EditText(this);
            input.setInputType(1);
            builder.setView(input);
            builder.setPositiveButton("OK", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String m_Text = input.getText().toString();
                    for (Integer ct : list) {
                        AdminActivity.this.api.pokeClient(ct.intValue(), m_Text);
                        if (AdminActivity.this.last_error_query.contains("ok")) {
                            Toast.makeText(AdminActivity.this, "Poke: " + AdminActivity.this.last_error_query, 0).show();
                        } else {
                            Toast.makeText(AdminActivity.this, "Poke: " + AdminActivity.this.last_error_query, 0).show();
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
        } catch (Exception e) {
        }
    }

    public void callKickCh(int zmienna) {
        try {
            this.api.kickClientFromChannel(zmienna);
            if (this.last_error_query != "ok") {
                Toast.makeText(this, "Kick: " + this.last_error_query, 0).show();
            }
        } catch (Exception e) {
        }
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

    protected void onResume() {
        super.onResume();
        System.gc();
    }

    protected void onStop() {
        super.onStop();
        System.gc();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.executor.shutdown();
        if (this.api != null) {
            this.api.unregisterAllEvents();
        }
        if (this.query != null) {
            this.query.exit();
        }
        this.welcome_image2.destroyDrawingCache();
        this.welcome_image3.destroyDrawingCache();
        this.welcome_image4.destroyDrawingCache();
        try {
            this.mapa1.recycle();
        } catch (Exception e) {
        }
        this.executor.shutdownNow();
        System.gc();
        finish();
    }
}
