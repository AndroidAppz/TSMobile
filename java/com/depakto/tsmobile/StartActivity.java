package com.depakto.tsmobile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.depakto.classes.adapters.StartClientIPArrayAdapter;
import com.depakto.classes.construktor.BotJoinServer;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StartActivity extends AppCompatActivity {
    private static final int NB_THREADS = 20;
    private NetworkInfo activeNetInfo;
    private StartClientIPArrayAdapter adapter;
    public Button adminB;
    private EditText adminIP;
    private EditText adminPort;
    public Button admin_connect_button;
    public boolean admin_widok = false;
    private String appId = "57aaf7f443150f5d099ee55a";
    private String appSignature = "de06056251fce72b41a5ed28618922e72bb2a395";
    private ImageView background_image;
    private ImageView background_image2;
    private ImageView background_image3;
    public boolean buttonEnabled = false;
    public Button clientB;
    public boolean client_widok = false;
    private boolean connectionType = true;
    private ConnectivityManager connectivityManager;
    private DisplayMetrics displayMetrics;
    private EditText edit_address;
    private EditText edit_password;
    private EditText edit_port;
    private EditText edit_queryport;
    private EditText edit_username;
    private ExecutorService executor;
    private ImageView fab;
    public Button findB;
    private boolean firstTime = true;
    private int height;
    private ImageView imgButtonAddLocalIp;
    private int in = -1;
    private int ipAddresss;
    private LinearLayout layout_admin_client;
    public List<BotJoinServer> listBotJoinServer = new ArrayList();
    private List<String> listBotJoinServerLog = new ArrayList();
    public ListView listStart;
    public Bitmap mapa = null;
    public List<String> pcs = new ArrayList();
    private int progressStatus = 0;
    public boolean progressVisible = false;
    private int screen = 0;
    public String textButton;
    public TextView textProgress;
    public boolean textProgressVisible = false;
    public TextView txt1;
    public TextView txt2;
    private TextView txtAddLocal;
    public Bitmap use3g;
    public Bitmap usewifi;
    private int width;

    protected void onNewIntent(Intent intent) {
        String memberFieldString = intent.getStringExtra("KEY");
        finish();
    }

    private void clearFocusAdmin() {
        this.edit_address.clearFocus();
        this.edit_queryport.clearFocus();
        this.edit_port.clearFocus();
        this.edit_username.clearFocus();
        this.edit_password.clearFocus();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setSoftInputMode(3);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7983017215405047~7021697213");
        if (AdMobActivity.AdMobMemoryLeakWorkAroundActivity == null) {
            AdMobActivity.startAdMobActivity(this);
        }
        loadPrefsFirstTime();
        this.textButton = getResources().getString(R.string.start_but_find);
        this.fab = (ImageView) findViewById(R.id.fab2);
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!StartActivity.this.findB.isEnabled()) {
                    Snackbar.make(view, "Wait for end of the search...", 0).setAction("Action", null).show();
                } else if (StartActivity.this.connectionType) {
                    StartActivity.this.connectionType = false;
                    StartActivity.this.fab.setImageBitmap(StartActivity.this.usewifi);
                    StartActivity.this.findB.setText("Add your IP");
                    Snackbar.make(view, "I will use 3G to connect with your router!", 0).setAction("Action", null).show();
                    StartActivity.this.imgButtonAddLocalIp.setVisibility(8);
                    StartActivity.this.txtAddLocal.setVisibility(8);
                    if (StartActivity.this.findB.isEnabled()) {
                        StartActivity.this.adapter = new StartClientIPArrayAdapter(StartActivity.this.getApplicationContext(), StartActivity.this.pcs, StartActivity.this);
                        StartActivity.this.adapter.clear();
                        StartActivity.this.adapter.add("Developer Server (app test)");
                        StartActivity.this.listStart.setAdapter(StartActivity.this.adapter);
                    }
                } else {
                    StartActivity.this.connectionType = true;
                    StartActivity.this.fab.setImageBitmap(StartActivity.this.use3g);
                    StartActivity.this.findB.setText(StartActivity.this.textButton);
                    Snackbar.make(view, "I will use WiFi to search local PC!", 0).setAction("Action", null).show();
                    StartActivity.this.imgButtonAddLocalIp.setVisibility(0);
                    StartActivity.this.txtAddLocal.setVisibility(0);
                }
            }
        });
        this.clientB = (Button) findViewById(R.id.client_button);
        this.adminB = (Button) findViewById(R.id.admin_button);
        this.clientB.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                StartActivity.this.background_image.setImageBitmap(null);
                StartActivity.this.background_image.destroyDrawingCache();
                StartActivity.this.background_image2.setImageBitmap(StartActivity.this.mapa);
                StartActivity.this.client_widok = true;
                if (StartActivity.this.connectionType) {
                    StartActivity.this.fab.setImageBitmap(StartActivity.this.use3g);
                } else {
                    StartActivity.this.fab.setImageBitmap(StartActivity.this.usewifi);
                }
                StartActivity.this.findViewById(R.id.start_welcome).setVisibility(8);
                StartActivity.this.findViewById(R.id.start_client).setVisibility(0);
                StartActivity.this.findViewById(R.id.start_admin).setVisibility(8);
                if (StartActivity.this.firstTime) {
                    StartActivity.this.showFirstTimeDialog();
                }
            }
        });
        this.adminB.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                StartActivity.this.background_image.setImageBitmap(null);
                StartActivity.this.background_image.destroyDrawingCache();
                StartActivity.this.background_image3.setImageBitmap(StartActivity.this.mapa);
                StartActivity.this.admin_widok = true;
                StartActivity.this.loadPrefs();
                StartActivity.this.findViewById(R.id.start_welcome).setVisibility(8);
                StartActivity.this.findViewById(R.id.start_client).setVisibility(8);
                StartActivity.this.findViewById(R.id.start_admin).setVisibility(0);
            }
        });
        this.layout_admin_client = (LinearLayout) findViewById(R.id.srodek_admin);
        this.layout_admin_client.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StartActivity.this.hideKeyboard(view);
                StartActivity.this.clearFocusAdmin();
            }
        });
        this.edit_address = (EditText) findViewById(R.id.edit_address);
        this.edit_address.setInputType(524288);
        this.edit_queryport = (EditText) findViewById(R.id.edit_queryport);
        this.edit_port = (EditText) findViewById(R.id.edit_port);
        this.edit_username = (EditText) findViewById(R.id.edit_username);
        this.edit_username.setInputType(524288);
        this.edit_password = (EditText) findViewById(R.id.edit_password);
        this.edit_password.setInputType(524288);
        this.textProgress = (TextView) findViewById(R.id.text_progress);
        this.textProgress.setVisibility(this.textProgressVisible ? 0 : 4);
        this.findB = (Button) findViewById(R.id.button);
        this.background_image = (ImageView) findViewById(R.id.background_welcome);
        this.background_image2 = (ImageView) findViewById(R.id.background_welcome2);
        this.background_image3 = (ImageView) findViewById(R.id.background_welcome3);
        this.txtAddLocal = (TextView) findViewById(R.id.textViewAddLocal);
        this.imgButtonAddLocalIp = (ImageView) findViewById(R.id.imgButtonAddLocalIp);
        this.imgButtonAddLocalIp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (StartActivity.this.findB.isEnabled()) {
                    if (StartActivity.this.adapter == null) {
                        StartActivity.this.adapter = new StartClientIPArrayAdapter(StartActivity.this.getApplicationContext(), StartActivity.this.pcs, StartActivity.this);
                        StartActivity.this.adapter.clear();
                        StartActivity.this.listStart.setAdapter(StartActivity.this.adapter);
                    }
                    StartActivity.this.addDialogLocal();
                    return;
                }
                Snackbar.make(view, "Wait for end of the search...", 0).setAction("Action", null).show();
            }
        });
        this.findB.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                StartActivity.this.ipAddresss = ((WifiManager) StartActivity.this.getSystemService("wifi")).getConnectionInfo().getIpAddress();
                if (StartActivity.this.ipAddresss <= 1 && StartActivity.this.connectionType) {
                    Toast.makeText(StartActivity.this, StartActivity.this.getResources().getString(R.string.start_toast_wifi), 0).show();
                } else if (StartActivity.this.connectionType) {
                    StartActivity.this.adapter = new StartClientIPArrayAdapter(StartActivity.this.getApplicationContext(), StartActivity.this.pcs, StartActivity.this);
                    StartActivity.this.adapter.clear();
                    StartActivity.this.adapter.add("Developer Server (app test)");
                    StartActivity.this.listStart.setAdapter(StartActivity.this.adapter);
                    StartActivity.this.progressStatus = 0;
                    StartActivity.this.progressVisible = true;
                    StartActivity.this.textProgressVisible = true;
                    StartActivity.this.textProgress.setVisibility(StartActivity.this.textProgressVisible ? 0 : 4);
                    StartActivity.this.buttonEnabled = false;
                    StartActivity.this.findB.setEnabled(StartActivity.this.buttonEnabled);
                    StartActivity.this.textButton = StartActivity.this.getResources().getString(R.string.start_but_search);
                    StartActivity.this.findB.setText(StartActivity.this.textButton);
                    Runtime.getRuntime().gc();
                    System.gc();
                    StartActivity.this.find();
                } else {
                    StartActivity.this.addDialog();
                }
            }
        });
        this.listStart = (ListView) findViewById(R.id.listViewStart);
        this.listStart.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                StartActivity.this.ipAddresss = ((WifiManager) StartActivity.this.getSystemService("wifi")).getConnectionInfo().getIpAddress();
                if (StartActivity.this.ipAddresss > 1 || !StartActivity.this.connectionType) {
                    if (StartActivity.this.executor != null) {
                        StartActivity.this.executor.shutdownNow();
                    }
                    StartActivity.this.savePrefs("freeslots", Integer.valueOf(0));
                    StartActivity.this.savePrefs("demoslot", Integer.valueOf(0));
                    Type listOfObjects = new TypeToken<List<BotJoinServer>>() {
                    }.getType();
                    Gson gson = new Gson();
                    SharedPreferences myPrefs = StartActivity.this.getSharedPreferences("BotList", 0);
                    String json = myPrefs.getString("JoinBotsList", BuildConfig.FLAVOR);
                    if (json.length() > 0) {
                        StartActivity.this.listBotJoinServer = (List) gson.fromJson(json, listOfObjects);
                        for (int i = 0; i < StartActivity.this.listBotJoinServer.size(); i++) {
                            if (((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).BotId == 1) {
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).ClientId = 0;
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).Event = 0;
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).Destination = 0;
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).Reaction = 0;
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).TypeAction = 1;
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).Reciever = 0;
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).MessageText = "New user came to your server";
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).BotName = "(Demo) Private msg to Me";
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).BotDescription = "It sends to you private message every time when new client join to this server";
                                ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).InfoBack = true;
                            }
                            ((BotJoinServer) StartActivity.this.listBotJoinServer.get(i)).ActiveStatus = false;
                        }
                    } else {
                        BotJoinServer newBotJoin = new BotJoinServer();
                        newBotJoin.ActiveStatus = false;
                        newBotJoin.ClientId = 0;
                        newBotJoin.Event = 0;
                        newBotJoin.Destination = 0;
                        newBotJoin.Reaction = 0;
                        newBotJoin.TypeAction = 1;
                        newBotJoin.Reciever = 0;
                        newBotJoin.BotId = StartActivity.this.listBotJoinServer.size() + 1;
                        newBotJoin.MessageText = "New user came to your server";
                        newBotJoin.BotName = "(Demo) Private msg to Me";
                        newBotJoin.BotDescription = "It sends to you private message every time when new client join to this server";
                        newBotJoin.InfoBack = true;
                        StartActivity.this.listBotJoinServer.add(newBotJoin);
                    }
                    String strObject = gson.toJson(StartActivity.this.listBotJoinServer, listOfObjects);
                    Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putString("JoinBotsList", strObject);
                    prefsEditor.commit();
                    Type listOfObjectsLog = new TypeToken<List<String>>() {
                    }.getType();
                    Gson gsonLog = new Gson();
                    SharedPreferences myPrefsLog = StartActivity.this.getSharedPreferences("BotListLog", 0);
                    if (StartActivity.this.listBotJoinServerLog.size() > 0) {
                        StartActivity.this.listBotJoinServerLog.clear();
                    }
                    String strObjectLog = gsonLog.toJson(StartActivity.this.listBotJoinServerLog, listOfObjectsLog);
                    Editor prefsEditorLog = myPrefsLog.edit();
                    prefsEditorLog.putString("JoinBotsListLog", strObjectLog);
                    prefsEditorLog.commit();
                    Intent intent = new Intent(StartActivity.this, ClientActivity.class);
                    intent.putExtra("wifi", StartActivity.this.connectionType);
                    intent.putExtra("port", 25639);
                    if (StartActivity.this.listStart.getItemAtPosition(position).toString().contains("Developer")) {
                        intent.putExtra("ipSelected", "94.75.74.33");
                        StartActivity.this.showTest(intent);
                    } else {
                        intent.putExtra("ipSelected", StartActivity.this.listStart.getItemAtPosition(position).toString());
                        StartActivity.this.startActivity(intent);
                        StartActivity.this.clear();
                    }
                    StartActivity.this.findB.setEnabled(true);
                    if (StartActivity.this.connectionType) {
                        StartActivity.this.textButton = StartActivity.this.getResources().getString(R.string.start_but_f_again);
                        StartActivity.this.findB.setText(StartActivity.this.textButton);
                    }
                    StartActivity.this.textProgress.setVisibility(4);
                    return;
                }
                Toast.makeText(StartActivity.this, StartActivity.this.getResources().getString(R.string.start_toast_wifi), 0).show();
            }
        });
        this.admin_connect_button = (Button) findViewById(R.id.admin_connect_button);
        this.admin_connect_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (StartActivity.this.executor != null) {
                    StartActivity.this.executor.shutdownNow();
                    StartActivity.this.findB.setEnabled(true);
                    if (StartActivity.this.connectionType) {
                        StartActivity.this.textButton = StartActivity.this.getResources().getString(R.string.start_but_f_again);
                        StartActivity.this.findB.setText(StartActivity.this.textButton);
                    }
                    StartActivity.this.textProgress.setVisibility(4);
                }
                StartActivity.this.clearFocusAdmin();
                Intent intent = new Intent(StartActivity.this, AdminActivity.class);
                intent.putExtra("address", StartActivity.this.edit_address.getText().toString());
                intent.putExtra("login", StartActivity.this.edit_username.getText().toString());
                intent.putExtra("password", StartActivity.this.edit_password.getText().toString());
                if (StartActivity.this.edit_queryport.getText().toString() == BuildConfig.FLAVOR || StartActivity.this.edit_queryport.getText().toString() == null || StartActivity.this.edit_queryport.getText().length() <= 0) {
                    intent.putExtra("queryport", 0);
                    StartActivity.this.edit_queryport.setText("0");
                } else {
                    intent.putExtra("queryport", Integer.parseInt(StartActivity.this.edit_queryport.getText().toString()));
                }
                if (StartActivity.this.edit_port.getText().toString() == BuildConfig.FLAVOR || StartActivity.this.edit_port.getText().toString() == null || StartActivity.this.edit_port.getText().length() <= 0) {
                    intent.putExtra("port", 0);
                    StartActivity.this.edit_port.setText("0");
                } else {
                    intent.putExtra("port", Integer.parseInt(StartActivity.this.edit_port.getText().toString()));
                }
                StartActivity.this.savePrefs("address", StartActivity.this.edit_address.getText().toString());
                StartActivity.this.savePrefs("login", StartActivity.this.edit_username.getText().toString());
                StartActivity.this.savePrefs("password", StartActivity.this.edit_password.getText().toString());
                StartActivity.this.savePrefs("queryport", StartActivity.this.edit_queryport.getText().toString());
                StartActivity.this.savePrefs("port", StartActivity.this.edit_port.getText().toString());
                StartActivity.this.startActivity(intent);
                StartActivity.this.clear();
            }
        });
        checkApiGoogle();
    }

    private void showFirstTimeDialog() {
        Builder builder = new Builder(this);
        builder.setTitle("First Time Here?");
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        LayoutParams params = new LayoutParams(-1, (int) ((1.0f * scale) + 0.5f));
        params.topMargin = (int) ((50.0f * scale) + 0.5f);
        params.bottomMargin = (int) ((50.0f * scale) + 0.5f);
        params.leftMargin = (int) ((50.0f * scale) + 0.5f);
        params.rightMargin = (int) ((50.0f * scale) + 0.5f);
        TextView inputT = new TextView(this);
        int zmP = (int) ((30.0f * scale) + 0.5f);
        inputT.setText("Watch Step-by-step video guide with a comment how to configure TeamSpeak3 and how to properly use TSmobile app with WiFi or LTE connection.\n");
        inputT.setPadding(zmP, zmP, zmP, zmP);
        inputT.setGravity(17);
        builder.setView(inputT);
        builder.setPositiveButton("Watch", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                StartActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.youtube.com/watch?v=Roe-El6nhtU")));
            }
        });
        builder.setNeutralButton("Never again!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                StartActivity.this.firstTime = false;
                StartActivity.this.savePrefs("firsttime", Boolean.valueOf(false));
            }
        });
        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void checkApiGoogle() {
        Integer resultCode = Integer.valueOf(GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()));
        if (resultCode.intValue() != 0) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode.intValue(), this, 0);
            if (dialog != null) {
                dialog.show();
            }
        }
    }

    private void addDialogLocal() {
        Builder builder = new Builder(this);
        builder.setTitle("Enter your local IP");
        final EditText input2 = new EditText(this);
        input2.setInputType(524289);
        builder.setView(input2);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                StartActivity.this.adapter.add(input2.getText().toString());
                StartActivity.this.adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void addDialog() {
        Builder builder = new Builder(this);
        builder.setTitle("Enter your public IP");
        final EditText input2 = new EditText(this);
        input2.setInputType(1);
        builder.setView(input2);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                StartActivity.this.adapter.add(input2.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void showTest(final Intent intent2) {
        Builder builderT = new Builder(this);
        builderT.setTitle("Warning!");
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        LayoutParams params = new LayoutParams(-1, (int) ((1.0f * scale) + 0.5f));
        params.topMargin = (int) ((50.0f * scale) + 0.5f);
        params.bottomMargin = (int) ((50.0f * scale) + 0.5f);
        params.leftMargin = (int) ((50.0f * scale) + 0.5f);
        params.rightMargin = (int) ((50.0f * scale) + 0.5f);
        TextView input2 = new TextView(this);
        int zmP = (int) ((30.0f * scale) + 0.5f);
        input2.setText("You are going to connect with test server where TeamSpeak3 client is running.\nThis is not your PC!\nRead instruction how to configure your TeamSpeak program and how to connect to it.\nThis is only a demonstration of the app. Have fun!");
        input2.setPadding(zmP, zmP, zmP, zmP);
        input2.setGravity(17);
        builderT.setView(input2);
        builderT.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                StartActivity.this.startActivity(intent2);
                StartActivity.this.clear();
            }
        });
        builderT.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderT.show();
    }

    private void loadPrefs() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.edit_address.setText(sp.getString("address", BuildConfig.FLAVOR));
        this.edit_queryport.setText(sp.getString("queryport", "10011"));
        this.edit_port.setText(sp.getString("port", "0"));
        this.edit_username.setText(sp.getString("login", BuildConfig.FLAVOR));
        this.edit_password.setText(sp.getString("password", BuildConfig.FLAVOR));
    }

    private void loadPrefsFirstTime() {
        this.firstTime = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("firsttime", true);
    }

    private void savePrefs(String key, String value) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putString(key, value);
        edit.commit();
    }

    private void savePrefs(String key, Integer value) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putInt(key, value.intValue());
        edit.commit();
    }

    private void savePrefs(String key, Boolean value) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putBoolean(key, value.booleanValue());
        edit.commit();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void helpActivity(View v) {
        startActivity(new Intent(this, HelpActivity.class));
    }

    public void helpAdminActivity(View v) {
        startActivity(new Intent(this, HelpAdminActivity.class));
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void find() {
        doScan();
    }

    private void doScan() {
        String ipDeviceCaly = String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(this.ipAddresss & 255), Integer.valueOf((this.ipAddresss >> 8) & 255), Integer.valueOf((this.ipAddresss >> 16) & 255), Integer.valueOf((this.ipAddresss >> 24) & 255)});
        final String ipDeviceShort = String.format("%d.%d.%d.", new Object[]{Integer.valueOf(this.ipAddresss & 255), Integer.valueOf((this.ipAddresss >> 8) & 255), Integer.valueOf((this.ipAddresss >> 16) & 255)});
        new Thread(new Runnable() {
            public void run() {
                StartActivity.this.executor = Executors.newFixedThreadPool(StartActivity.NB_THREADS);
                for (int dest = 0; dest < 255; dest++) {
                    StartActivity.this.executor.execute(StartActivity.this.pingRunnable2(ipDeviceShort + dest));
                }
                StartActivity.this.executor.shutdown();
                try {
                    StartActivity.this.executor.awaitTermination(80000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                }
                Runtime.getRuntime().gc();
                System.gc();
            }
        }).start();
    }

    protected void onStop() {
        super.onStop();
        clear();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void clear() {
        this.background_image.setImageBitmap(null);
        this.background_image2.setImageBitmap(null);
        this.background_image3.setImageBitmap(null);
        this.background_image.destroyDrawingCache();
        this.background_image2.destroyDrawingCache();
        this.background_image3.destroyDrawingCache();
        this.fab.setImageBitmap(null);
        this.fab.destroyDrawingCache();
        if (this.mapa != null) {
            this.mapa.recycle();
            this.mapa = null;
        }
        if (this.use3g != null) {
            this.use3g.recycle();
            this.use3g = null;
        }
        if (this.usewifi != null) {
            this.usewifi.recycle();
            this.usewifi = null;
        }
        Runtime.getRuntime().gc();
        System.gc();
    }

    private Runnable pingRunnable2(final String host) {
        return new Runnable() {
            public void run() {
                StartActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        int i = 0;
                        StartActivity.this.progressStatus = StartActivity.this.progressStatus + 1;
                        StartActivity.this.update(Math.round(((double) StartActivity.this.progressStatus) / 2.55d) + "%");
                        if (StartActivity.this.progressStatus >= 255) {
                            StartActivity.this.textButton = StartActivity.this.getResources().getString(R.string.start_but_again);
                            StartActivity.this.buttonEnabled = true;
                            StartActivity.this.findB.setEnabled(StartActivity.this.buttonEnabled);
                            if (StartActivity.this.connectionType) {
                                StartActivity.this.findB.setText(StartActivity.this.textButton);
                            }
                            StartActivity.this.progressVisible = false;
                            StartActivity.this.textProgressVisible = false;
                            TextView textView = StartActivity.this.textProgress;
                            if (!StartActivity.this.textProgressVisible) {
                                i = 4;
                            }
                            textView.setVisibility(i);
                            if (StartActivity.this.pcs.size() < 1 && StartActivity.this.ipAddresss > 1) {
                                Toast.makeText(StartActivity.this, StartActivity.this.getResources().getString(R.string.start_toast_no_result), 1).show();
                            }
                        }
                    }
                });
                try {
                    SocketAddress sockadd1 = new InetSocketAddress(host, 25639);
                    Socket sock2 = new Socket();
                    sock2.connect(sockadd1, 1000);
                    sock2.close();
                    if (true) {
                        StartActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                StartActivity.this.pcs.add(host.toString());
                                StartActivity.this.listStart.setAdapter(new StartClientIPArrayAdapter(StartActivity.this.getApplicationContext(), StartActivity.this.pcs, StartActivity.this));
                            }
                        });
                    }
                } catch (UnknownHostException e) {
                } catch (IOException e2) {
                }
            }
        };
    }

    private void update(String s) {
        this.textProgress.setText(s.toString());
    }

    protected void onResume() {
        super.onResume();
        Runtime.getRuntime().gc();
        System.gc();
        this.displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        this.width = this.displayMetrics.widthPixels;
        this.height = this.displayMetrics.heightPixels;
        if (this.mapa != null) {
            this.mapa.recycle();
            this.mapa = null;
        }
        this.mapa = decodeSampledBitmapFromResource(getResources(), R.drawable.tlowelcome, this.width, this.height);
        if (this.use3g != null) {
            this.use3g.recycle();
            this.use3g = null;
        }
        this.use3g = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.use3g, 1, 1);
        if (this.usewifi != null) {
            this.usewifi.recycle();
            this.usewifi = null;
        }
        this.usewifi = decodeSampledBitmapFromResourceOrg(getResources(), R.drawable.usewifi, 1, 1);
        if (!this.client_widok && !this.admin_widok) {
            this.background_image.setImageBitmap(this.mapa);
        } else if (this.client_widok) {
            if (this.connectionType) {
                this.fab.setImageBitmap(this.use3g);
            } else {
                this.fab.setImageBitmap(this.usewifi);
                this.findB.setText("Add your IP");
            }
            this.background_image2.setImageBitmap(this.mapa);
        } else {
            this.background_image3.setImageBitmap(this.mapa);
        }
        Runtime.getRuntime().gc();
        System.gc();
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

    public void onBackPressed() {
        if (!this.client_widok && !this.admin_widok) {
            super.onBackPressed();
        } else if (this.client_widok || this.admin_widok) {
            Runtime.getRuntime().gc();
            System.gc();
            this.client_widok = false;
            this.admin_widok = false;
            this.background_image2.setImageBitmap(null);
            this.background_image2.destroyDrawingCache();
            this.background_image3.setImageBitmap(null);
            this.background_image2.destroyDrawingCache();
            this.background_image.setImageBitmap(this.mapa);
            findViewById(R.id.start_welcome).setVisibility(0);
            findViewById(R.id.start_client).setVisibility(8);
            findViewById(R.id.start_admin).setVisibility(8);
            Runtime.getRuntime().gc();
            System.gc();
        }
    }
}
