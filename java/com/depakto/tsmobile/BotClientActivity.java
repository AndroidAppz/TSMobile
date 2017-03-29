package com.depakto.tsmobile;

import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.depakto.classes.adapters.ClientBotArrayAdapter;
import com.depakto.classes.construktor.BotJoinServer;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BotClientActivity extends AppCompatActivity {
    private final String ZONE_ID_demo = "vzfe2c7489c0534ec185";
    private final String ZONE_ID_sltos = "vz5680157e32fa4cb9bf";
    public int activeSlots = 0;
    private ClientBotArrayAdapter adapterBot;
    private ArrayAdapter<CharSequence> adapterb6;
    private AlertDialog alert = null;
    private AlertDialog alert3;
    private String appId = "57aaf7f443150f5d099ee55a";
    private String appSignature = "de06056251fce72b41a5ed28618922e72bb2a395";
    private int askingVideo = 0;
    private Builder builder3;
    private CheckBox checkInfoBack;
    public int demoSlots = 0;
    private View dialogLay3;
    private FloatingActionButton fab;
    private ImageView imageAd1;
    private ImageView imageAd1a;
    private ImageView imageAd2;
    private ImageView imageAd2a;
    private ImageView imgAddSlot;
    private ImageView imgGetSlots;
    private LayoutInflater layout3;
    public List<BotJoinServer> listBotJoinServer = new ArrayList();
    private ListView listViewBot;
    public InterstitialAd mInterstitialAdDemo;
    public InterstitialAd mInterstitialAdSlots;
    private Spinner spinnerB1;
    private Spinner spinnerB2;
    private Spinner spinnerB3;
    private Spinner spinnerB4;
    private Spinner spinnerB5;
    private Spinner spinnerB6;
    private Toolbar toolbar;
    public TextView txtActiveSlotsnumber;
    private EditText txtBotDescription;
    private EditText txtBotName;
    private TextView txtCustomStatus;
    public TextView txtInfoChanges;
    private EditText txtMessage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_client);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BotClientActivity.this.callNewChannel();
            }
        });
        this.mInterstitialAdDemo = new InterstitialAd(getApplicationContext());
        this.mInterstitialAdDemo.setAdUnitId("ca-app-pub-7983017215405047/2727202010");
        this.mInterstitialAdDemo.setAdListener(new AdListener() {
            public void onAdClosed() {
                BotClientActivity.this.requestNewInterstitial(0);
            }
        });
        requestNewInterstitial(0);
        this.mInterstitialAdSlots = new InterstitialAd(getApplicationContext());
        this.mInterstitialAdSlots.setAdUnitId("ca-app-pub-7983017215405047/3918060419");
        this.mInterstitialAdSlots.setAdListener(new AdListener() {
            public void onAdClosed() {
                BotClientActivity.this.requestNewInterstitial(1);
            }
        });
        requestNewInterstitial(1);
        this.txtInfoChanges = (TextView) findViewById(R.id.txtInfoChanges);
        this.txtInfoChanges.setVisibility(4);
        this.imgAddSlot = (ImageView) findViewById(R.id.imgAddSlot);
        this.imgAddSlot.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                BotClientActivity.this.callNewChannel();
            }
        });
        this.imgGetSlots = (ImageView) findViewById(R.id.imgGetSlots);
        this.imgGetSlots.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                BotClientActivity.this.askingVideo = 1;
                BotClientActivity.this.showActiveWatchSlot();
            }
        });
        this.listViewBot = (ListView) findViewById(R.id.listViewBot);
        this.listViewBot.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                BotClientActivity.this.callEditDialog((BotJoinServer) BotClientActivity.this.listViewBot.getItemAtPosition(position));
            }
        });
        loadPrefs();
        this.txtActiveSlotsnumber = (TextView) findViewById(R.id.intActiveSlots);
        this.txtActiveSlotsnumber.setText(String.valueOf(this.activeSlots));
        this.txtCustomStatus = (TextView) findViewById(R.id.txtCustomStatus);
        if (this.demoSlots > 0) {
            this.txtCustomStatus.setText("possible to edit");
            this.txtCustomStatus.setTextColor(-16711936);
        }
        takeBotList();
    }

    private void requestNewInterstitial(int wybor) {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("3E3CBF2A499254ADE9049AD28555B32F").addTestDevice("8199BF7189EE38B4B54EC01D9FE8BE97").build();
        if (wybor == 0) {
            this.mInterstitialAdDemo.loadAd(adRequest);
        } else {
            this.mInterstitialAdSlots.loadAd(adRequest);
        }
    }

    public void loadPrefs() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.activeSlots = sp.getInt("freeslots", 0);
        this.demoSlots = sp.getInt("demoslot", 0);
    }

    public void savePrefs(String key, Integer value) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.putInt(key, value.intValue());
        edit.commit();
    }

    private void callEditDialog(BotJoinServer itemAtPosition) {
        final BotJoinServer selectedBot = itemAtPosition;
        this.builder3 = new Builder(this);
        this.builder3.setTitle("Editor for selected BOT");
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_bot, null);
        ArrayAdapter.createFromResource(this, R.array.bot_who, 17367048).setDropDownViewResource(17367049);
        this.builder3.setView(dialogLay);
        this.builder3.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.content.DialogInterface r14, int r15) {
                /*
                r13 = this;
                r11 = 3;
                r10 = 2;
                r8 = -1;
                r9 = 1;
                r7 = 0;
                r6 = r9;
                r6 = r6.BotId;
                if (r6 != r9) goto L_0x0017;
            L_0x000b:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.demoSlots;
                if (r6 != 0) goto L_0x0017;
            L_0x0011:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6.showActiveWatchDemo();
            L_0x0016:
                return;
            L_0x0017:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r12 = r9;
                r1 = r6.indexOf(r12);
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.ActiveStatus = r7;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.spinnerB1;
                r6 = r6.getSelectedItem();
                r6 = r6.toString();
                r12 = r6.hashCode();
                switch(r12) {
                    case 2488: goto L_0x016a;
                    case 1966197018: goto L_0x0175;
                    case 2021122027: goto L_0x015f;
                    default: goto L_0x0042;
                };
            L_0x0042:
                r6 = r8;
            L_0x0043:
                switch(r6) {
                    case 0: goto L_0x0180;
                    case 1: goto L_0x018e;
                    case 2: goto L_0x019c;
                    default: goto L_0x0046;
                };
            L_0x0046:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.spinnerB2;
                r6 = r6.getSelectedItem();
                r6 = r6.toString();
                r12 = r6.hashCode();
                switch(r12) {
                    case 2314570: goto L_0x01ab;
                    case 2364455: goto L_0x01c1;
                    case 2404337: goto L_0x01b6;
                    default: goto L_0x005b;
                };
            L_0x005b:
                r6 = r8;
            L_0x005c:
                switch(r6) {
                    case 0: goto L_0x01cc;
                    case 1: goto L_0x01da;
                    case 2: goto L_0x01e8;
                    default: goto L_0x005f;
                };
            L_0x005f:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.spinnerB3;
                r6 = r6.getSelectedItem();
                r6 = r6.toString();
                r12 = r6.hashCode();
                switch(r12) {
                    case -1891363613: goto L_0x0201;
                    case -1821959325: goto L_0x01f6;
                    default: goto L_0x0074;
                };
            L_0x0074:
                r6 = r8;
            L_0x0075:
                switch(r6) {
                    case 0: goto L_0x020c;
                    case 1: goto L_0x021a;
                    default: goto L_0x0078;
                };
            L_0x0078:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.spinnerB4;
                r6 = r6.getSelectedItem();
                r6 = r6.toString();
                r12 = r6.hashCode();
                switch(r12) {
                    case 2573224: goto L_0x0228;
                    default: goto L_0x008d;
                };
            L_0x008d:
                r6 = r8;
            L_0x008e:
                switch(r6) {
                    case 0: goto L_0x0233;
                    default: goto L_0x0091;
                };
            L_0x0091:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.spinnerB5;
                r6 = r6.getSelectedItem();
                r6 = r6.toString();
                r12 = r6.hashCode();
                switch(r12) {
                    case -1675388953: goto L_0x024c;
                    case 2493369: goto L_0x0241;
                    default: goto L_0x00a6;
                };
            L_0x00a6:
                r6 = r8;
            L_0x00a7:
                switch(r6) {
                    case 0: goto L_0x0257;
                    case 1: goto L_0x0265;
                    default: goto L_0x00aa;
                };
            L_0x00aa:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.spinnerB6;
                r6 = r6.getSelectedItem();
                r6 = r6.toString();
                r12 = r6.hashCode();
                switch(r12) {
                    case -1891363613: goto L_0x0289;
                    case -1821959325: goto L_0x0294;
                    case 2488: goto L_0x0273;
                    case 2021122027: goto L_0x027e;
                    default: goto L_0x00bf;
                };
            L_0x00bf:
                r6 = r8;
            L_0x00c0:
                switch(r6) {
                    case 0: goto L_0x029f;
                    case 1: goto L_0x02ad;
                    case 2: goto L_0x02bb;
                    case 3: goto L_0x02c9;
                    default: goto L_0x00c3;
                };
            L_0x00c3:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r8 = com.depakto.tsmobile.BotClientActivity.this;
                r8 = r8.txtMessage;
                r8 = r8.getText();
                r8 = r8.toString();
                r6.MessageText = r8;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r8 = com.depakto.tsmobile.BotClientActivity.this;
                r8 = r8.txtBotName;
                r8 = r8.getText();
                r8 = r8.toString();
                r6.BotName = r8;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r8 = com.depakto.tsmobile.BotClientActivity.this;
                r8 = r8.txtBotDescription;
                r8 = r8.getText();
                r8 = r8.toString();
                r6.BotDescription = r8;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r8 = com.depakto.tsmobile.BotClientActivity.this;
                r8 = r8.checkInfoBack;
                r8 = r8.isChecked();
                r6.InfoBack = r8;
                r6 = new com.depakto.tsmobile.BotClientActivity$7$1;
                r6.<init>();
                r2 = r6.getType();
                r0 = new com.google.gson.Gson;
                r0.<init>();
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r5 = r0.toJson(r6, r2);
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r8 = "BotList";
                r3 = r6.getSharedPreferences(r8, r7);
                r4 = r3.edit();
                r6 = "JoinBotsList";
                r4.putString(r6, r5);
                r4.commit();
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.txtInfoChanges;
                r6.setVisibility(r7);
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6.takeBotList();
                goto L_0x0016;
            L_0x015f:
                r12 = "Client";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x0042;
            L_0x0167:
                r6 = r7;
                goto L_0x0043;
            L_0x016a:
                r12 = "Me";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x0042;
            L_0x0172:
                r6 = r9;
                goto L_0x0043;
            L_0x0175:
                r12 = "Anyone";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x0042;
            L_0x017d:
                r6 = r10;
                goto L_0x0043;
            L_0x0180:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.ClientId = r7;
                goto L_0x0046;
            L_0x018e:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.ClientId = r8;
                goto L_0x0046;
            L_0x019c:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r12 = -2;
                r6.ClientId = r12;
                goto L_0x0046;
            L_0x01ab:
                r12 = "Join";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x005b;
            L_0x01b3:
                r6 = r7;
                goto L_0x005c;
            L_0x01b6:
                r12 = "Move";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x005b;
            L_0x01be:
                r6 = r9;
                goto L_0x005c;
            L_0x01c1:
                r12 = "Left";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x005b;
            L_0x01c9:
                r6 = r10;
                goto L_0x005c;
            L_0x01cc:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Event = r7;
                goto L_0x005f;
            L_0x01da:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Event = r9;
                goto L_0x005f;
            L_0x01e8:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Event = r10;
                goto L_0x005f;
            L_0x01f6:
                r12 = "Server";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x0074;
            L_0x01fe:
                r6 = r7;
                goto L_0x0075;
            L_0x0201:
                r12 = "Channel";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x0074;
            L_0x0209:
                r6 = r9;
                goto L_0x0075;
            L_0x020c:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Destination = r7;
                goto L_0x0078;
            L_0x021a:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Destination = r9;
                goto L_0x0078;
            L_0x0228:
                r12 = "Send";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x008d;
            L_0x0230:
                r6 = r7;
                goto L_0x008e;
            L_0x0233:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Reaction = r7;
                goto L_0x0091;
            L_0x0241:
                r12 = "Poke";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x00a6;
            L_0x0249:
                r6 = r7;
                goto L_0x00a7;
            L_0x024c:
                r12 = "Message";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x00a6;
            L_0x0254:
                r6 = r9;
                goto L_0x00a7;
            L_0x0257:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.TypeAction = r7;
                goto L_0x00aa;
            L_0x0265:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.TypeAction = r9;
                goto L_0x00aa;
            L_0x0273:
                r12 = "Me";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x00bf;
            L_0x027b:
                r6 = r7;
                goto L_0x00c0;
            L_0x027e:
                r12 = "Client";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x00bf;
            L_0x0286:
                r6 = r9;
                goto L_0x00c0;
            L_0x0289:
                r12 = "Channel";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x00bf;
            L_0x0291:
                r6 = r10;
                goto L_0x00c0;
            L_0x0294:
                r12 = "Server";
                r6 = r6.equals(r12);
                if (r6 == 0) goto L_0x00bf;
            L_0x029c:
                r6 = r11;
                goto L_0x00c0;
            L_0x029f:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Reciever = r7;
                goto L_0x00c3;
            L_0x02ad:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Reciever = r9;
                goto L_0x00c3;
            L_0x02bb:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Reciever = r10;
                goto L_0x00c3;
            L_0x02c9:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.get(r1);
                r6 = (com.depakto.classes.construktor.BotJoinServer) r6;
                r6.Reciever = r11;
                goto L_0x00c3;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.depakto.tsmobile.BotClientActivity.7.onClick(android.content.DialogInterface, int):void");
            }
        });
        this.builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        this.builder3.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (selectedBot.BotId == 1) {
                    Snackbar.make(BotClientActivity.this.getCurrentFocus(), "You cannot remove demo slot!", 0).setAction("Action", null).show();
                    return;
                }
                BotClientActivity.this.listBotJoinServer.remove(selectedBot);
                for (int i = 0; i < BotClientActivity.this.listBotJoinServer.size(); i++) {
                    ((BotJoinServer) BotClientActivity.this.listBotJoinServer.get(i)).BotId = i + 1;
                }
                dialog.cancel();
                BotClientActivity.this.savePrefers();
                BotClientActivity.this.txtInfoChanges.setVisibility(0);
                BotClientActivity.this.takeBotList();
            }
        });
        this.spinnerB1 = (Spinner) dialogLay.findViewById(R.id.spinnerb1);
        ArrayAdapter<CharSequence> adapterb1 = ArrayAdapter.createFromResource(this, R.array.bot_who, 17367048);
        adapterb1.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB1.setAdapter(adapterb1);
        switch (selectedBot.ClientId) {
            case -2:
                this.spinnerB1.setSelection(2);
                break;
            case -1:
                this.spinnerB1.setSelection(1);
                break;
            case R.styleable.View_android_theme /*0*/:
                this.spinnerB1.setSelection(0);
                break;
        }
        this.spinnerB2 = (Spinner) dialogLay.findViewById(R.id.spinnerChannel);
        ArrayAdapter<CharSequence> adapterb2 = ArrayAdapter.createFromResource(this, R.array.bot_action, 17367048);
        adapterb2.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB2.setAdapter(adapterb2);
        this.spinnerB2.setSelection(selectedBot.Event);
        this.spinnerB3 = (Spinner) dialogLay.findViewById(R.id.spinnerb3);
        ArrayAdapter<CharSequence> adapterb3 = ArrayAdapter.createFromResource(this, R.array.bot_place, 17367048);
        adapterb3.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB3.setAdapter(adapterb3);
        this.spinnerB3.setSelection(selectedBot.Destination);
        final TextView txtWherePlace = (TextView) dialogLay.findViewById(R.id.txtWherePlace);
        this.spinnerB2.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 2) {
                    txtWherePlace.setText("FROM THE");
                } else {
                    txtWherePlace.setText("TO THE");
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerB4 = (Spinner) dialogLay.findViewById(R.id.spinnerb4);
        ArrayAdapter<CharSequence> adapterb4 = ArrayAdapter.createFromResource(this, R.array.bot_reaction, 17367048);
        adapterb4.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB4.setAdapter(adapterb4);
        this.spinnerB4.setSelection(selectedBot.Reaction);
        this.spinnerB5 = (Spinner) dialogLay.findViewById(R.id.spinnerb5);
        ArrayAdapter<CharSequence> adapterb5 = ArrayAdapter.createFromResource(this, R.array.bot_send, 17367048);
        adapterb5.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB5.setAdapter(adapterb5);
        this.spinnerB5.setSelection(selectedBot.TypeAction);
        this.spinnerB5.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    BotClientActivity.this.adapterb6 = ArrayAdapter.createFromResource(BotClientActivity.this, R.array.bot_send_to2, 17367048);
                    BotClientActivity.this.adapterb6.setDropDownViewResource(R.layout.custom_spinner_item);
                    BotClientActivity.this.spinnerB6.setAdapter(BotClientActivity.this.adapterb6);
                    if (selectedBot.Reciever > 1) {
                        BotClientActivity.this.spinnerB6.setSelection(0);
                        return;
                    } else {
                        BotClientActivity.this.spinnerB6.setSelection(selectedBot.Reciever);
                        return;
                    }
                }
                BotClientActivity.this.adapterb6 = ArrayAdapter.createFromResource(BotClientActivity.this, R.array.bot_send_to, 17367048);
                BotClientActivity.this.adapterb6.setDropDownViewResource(R.layout.custom_spinner_item);
                BotClientActivity.this.spinnerB6.setAdapter(BotClientActivity.this.adapterb6);
                BotClientActivity.this.spinnerB6.setSelection(selectedBot.Reciever);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerB6 = (Spinner) dialogLay.findViewById(R.id.spinnerb6);
        ArrayAdapter<CharSequence> adapterb6 = ArrayAdapter.createFromResource(this, R.array.bot_send_to, 17367048);
        adapterb6.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB6.setAdapter(adapterb6);
        this.spinnerB6.setSelection(selectedBot.Reciever);
        this.txtMessage = (EditText) dialogLay.findViewById(R.id.editMessage);
        this.txtBotName = (EditText) dialogLay.findViewById(R.id.editName);
        this.txtBotDescription = (EditText) dialogLay.findViewById(R.id.editDescription);
        this.txtMessage.setText(selectedBot.MessageText);
        this.txtBotDescription.setText(selectedBot.BotDescription);
        this.txtBotName.setText(selectedBot.BotName);
        this.txtMessage.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BotClientActivity.this.hideKeyboard(v);
                }
            }
        });
        this.txtBotName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BotClientActivity.this.hideKeyboard(v);
                }
            }
        });
        this.txtBotDescription.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BotClientActivity.this.hideKeyboard(v);
                }
            }
        });
        this.spinnerB1.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB3.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB4.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB5.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB6.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.checkInfoBack = (CheckBox) dialogLay.findViewById(R.id.checkBoxInfoBack);
        this.checkInfoBack.setChecked(selectedBot.InfoBack);
        this.alert = this.builder3.show();
    }

    private void showActiveWatchDemo() {
        MemoryInfo outInfo = new MemoryInfo();
        Builder builder = new Builder(this);
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_customization_bot, null);
        this.askingVideo = 0;
        builder.setView(dialogLay);
        this.imageAd1 = (ImageView) dialogLay.findViewById(R.id.imageAd1);
        this.imageAd2 = (ImageView) dialogLay.findViewById(R.id.imageAd2);
        this.imageAd1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BotClientActivity.this.mInterstitialAdDemo.isLoaded()) {
                    BotClientActivity.this.mInterstitialAdDemo.show();
                    BotClientActivity.this.aktywacjaSlotow(true);
                    BotClientActivity.this.alert.cancel();
                    return;
                }
                Toast.makeText(BotClientActivity.this.getApplicationContext(), "Something wrong, try again!", 0).show();
                BotClientActivity.this.requestNewInterstitial(0);
            }
        });
        this.imageAd2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BotClientActivity.this.mInterstitialAdDemo.isLoaded()) {
                    BotClientActivity.this.mInterstitialAdDemo.show();
                    BotClientActivity.this.aktywacjaSlotow(true);
                    BotClientActivity.this.alert.cancel();
                    return;
                }
                Toast.makeText(BotClientActivity.this.getApplicationContext(), "Something wrong, try again!", 0).show();
                BotClientActivity.this.requestNewInterstitial(0);
            }
        });
        builder.setPositiveButton("Get Access!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BotClientActivity.this.mInterstitialAdDemo.isLoaded()) {
                    BotClientActivity.this.mInterstitialAdDemo.show();
                    BotClientActivity.this.aktywacjaSlotow(true);
                    return;
                }
                Toast.makeText(BotClientActivity.this.getApplicationContext(), "Something wrong, try again!", 0).show();
                BotClientActivity.this.requestNewInterstitial(0);
            }
        });
        this.alert = builder.show();
    }

    private void aktywacjaSlotow(boolean numerek) {
        if (numerek) {
            this.demoSlots = 1;
            this.txtCustomStatus.setText("possible to edit");
            this.txtCustomStatus.setTextColor(-16711936);
            savePrefs("demoslot", Integer.valueOf(this.demoSlots));
            return;
        }
        this.activeSlots++;
        savePrefs("freeslots", Integer.valueOf(this.activeSlots));
        this.txtActiveSlotsnumber.setText(String.valueOf(this.activeSlots));
    }

    private void showActiveWatchSlot() {
        this.builder3 = new Builder(this);
        this.layout3 = getLayoutInflater();
        this.dialogLay3 = this.layout3.inflate(R.layout.client_alertdialog_slot_bot, null);
        this.askingVideo = 1;
        this.builder3.setView(this.dialogLay3);
        this.imageAd1a = (ImageView) this.dialogLay3.findViewById(R.id.imageAd1a);
        this.imageAd2a = (ImageView) this.dialogLay3.findViewById(R.id.imageAd2a);
        this.imageAd1a.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BotClientActivity.this.mInterstitialAdSlots.isLoaded()) {
                    BotClientActivity.this.mInterstitialAdSlots.show();
                    BotClientActivity.this.aktywacjaSlotow(false);
                    BotClientActivity.this.alert3.cancel();
                    return;
                }
                Toast.makeText(BotClientActivity.this.getApplicationContext(), "Something wrong, try again!", 0).show();
                BotClientActivity.this.requestNewInterstitial(1);
            }
        });
        this.imageAd2a.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (BotClientActivity.this.mInterstitialAdSlots.isLoaded()) {
                    BotClientActivity.this.mInterstitialAdSlots.show();
                    BotClientActivity.this.aktywacjaSlotow(false);
                    BotClientActivity.this.alert3.cancel();
                    return;
                }
                Toast.makeText(BotClientActivity.this.getApplicationContext(), "Something wrong, try again!", 0).show();
                BotClientActivity.this.requestNewInterstitial(1);
            }
        });
        this.builder3.setPositiveButton("Activate!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BotClientActivity.this.mInterstitialAdSlots.isLoaded()) {
                    BotClientActivity.this.mInterstitialAdSlots.show();
                    BotClientActivity.this.aktywacjaSlotow(false);
                    return;
                }
                Toast.makeText(BotClientActivity.this.getApplicationContext(), "Something wrong, try again!", 0).show();
                BotClientActivity.this.requestNewInterstitial(1);
            }
        });
        this.alert3 = this.builder3.show();
    }

    public void savePrefers() {
        String strObject = new Gson().toJson(this.listBotJoinServer, new TypeToken<List<BotJoinServer>>() {
        }.getType());
        Editor prefsEditor = getSharedPreferences("BotList", 0).edit();
        prefsEditor.putString("JoinBotsList", strObject);
        prefsEditor.commit();
        this.adapterBot.notifyDataSetChanged();
    }

    private void takeBotList() {
        Type listOfObjects = new TypeToken<List<BotJoinServer>>() {
        }.getType();
        Gson gson = new Gson();
        String json = getSharedPreferences("BotList", 0).getString("JoinBotsList", BuildConfig.FLAVOR);
        if (json.length() > 0) {
            this.listBotJoinServer = (List) gson.fromJson(json, listOfObjects);
            if (this.activeSlots == 0) {
                for (int i = 1; i < this.listBotJoinServer.size(); i++) {
                    ((BotJoinServer) this.listBotJoinServer.get(i)).ActiveStatus = false;
                }
            }
            this.adapterBot = new ClientBotArrayAdapter(getApplicationContext(), this.listBotJoinServer, this);
            this.listViewBot.setAdapter(this.adapterBot);
        }
    }

    public void callNewChannel() {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        this.builder3 = new Builder(this);
        this.builder3.setTitle("Creator for new BOT");
        View dialogLay = getLayoutInflater().inflate(R.layout.client_alertdialog_bot, null);
        ArrayAdapter.createFromResource(this, R.array.bot_who, 17367048).setDropDownViewResource(17367049);
        this.builder3.setView(dialogLay);
        this.builder3.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.content.DialogInterface r14, int r15) {
                /*
                r13 = this;
                r10 = 3;
                r9 = 2;
                r6 = -1;
                r8 = 1;
                r7 = 0;
                r3 = new com.depakto.classes.construktor.BotJoinServer;
                r3.<init>();
                r3.ActiveStatus = r7;
                r11 = com.depakto.tsmobile.BotClientActivity.this;
                r11 = r11.spinnerB1;
                r11 = r11.getSelectedItem();
                r11 = r11.toString();
                r12 = r11.hashCode();
                switch(r12) {
                    case 2488: goto L_0x0132;
                    case 1966197018: goto L_0x013d;
                    case 2021122027: goto L_0x0127;
                    default: goto L_0x0021;
                };
            L_0x0021:
                r11 = r6;
            L_0x0022:
                switch(r11) {
                    case 0: goto L_0x0148;
                    case 1: goto L_0x014c;
                    case 2: goto L_0x0150;
                    default: goto L_0x0025;
                };
            L_0x0025:
                r11 = com.depakto.tsmobile.BotClientActivity.this;
                r11 = r11.spinnerB2;
                r11 = r11.getSelectedItem();
                r11 = r11.toString();
                r12 = r11.hashCode();
                switch(r12) {
                    case 2314570: goto L_0x0155;
                    case 2364455: goto L_0x016b;
                    case 2404337: goto L_0x0160;
                    default: goto L_0x003a;
                };
            L_0x003a:
                r11 = r6;
            L_0x003b:
                switch(r11) {
                    case 0: goto L_0x0176;
                    case 1: goto L_0x017a;
                    case 2: goto L_0x017e;
                    default: goto L_0x003e;
                };
            L_0x003e:
                r11 = com.depakto.tsmobile.BotClientActivity.this;
                r11 = r11.spinnerB3;
                r11 = r11.getSelectedItem();
                r11 = r11.toString();
                r12 = r11.hashCode();
                switch(r12) {
                    case -1891363613: goto L_0x018d;
                    case -1821959325: goto L_0x0182;
                    default: goto L_0x0053;
                };
            L_0x0053:
                r11 = r6;
            L_0x0054:
                switch(r11) {
                    case 0: goto L_0x0198;
                    case 1: goto L_0x019c;
                    default: goto L_0x0057;
                };
            L_0x0057:
                r11 = com.depakto.tsmobile.BotClientActivity.this;
                r11 = r11.spinnerB4;
                r11 = r11.getSelectedItem();
                r11 = r11.toString();
                r12 = r11.hashCode();
                switch(r12) {
                    case 2573224: goto L_0x01a0;
                    default: goto L_0x006c;
                };
            L_0x006c:
                r11 = r6;
            L_0x006d:
                switch(r11) {
                    case 0: goto L_0x01ab;
                    default: goto L_0x0070;
                };
            L_0x0070:
                r11 = com.depakto.tsmobile.BotClientActivity.this;
                r11 = r11.spinnerB5;
                r11 = r11.getSelectedItem();
                r11 = r11.toString();
                r12 = r11.hashCode();
                switch(r12) {
                    case -1675388953: goto L_0x01ba;
                    case 2493369: goto L_0x01af;
                    default: goto L_0x0085;
                };
            L_0x0085:
                r11 = r6;
            L_0x0086:
                switch(r11) {
                    case 0: goto L_0x01c5;
                    case 1: goto L_0x01c9;
                    default: goto L_0x0089;
                };
            L_0x0089:
                r11 = com.depakto.tsmobile.BotClientActivity.this;
                r11 = r11.spinnerB6;
                r11 = r11.getSelectedItem();
                r11 = r11.toString();
                r12 = r11.hashCode();
                switch(r12) {
                    case -1891363613: goto L_0x01e3;
                    case -1821959325: goto L_0x01ee;
                    case 2488: goto L_0x01cd;
                    case 2021122027: goto L_0x01d8;
                    default: goto L_0x009e;
                };
            L_0x009e:
                switch(r6) {
                    case 0: goto L_0x01f9;
                    case 1: goto L_0x01fd;
                    case 2: goto L_0x0201;
                    case 3: goto L_0x0205;
                    default: goto L_0x00a1;
                };
            L_0x00a1:
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6 = r6.size();
                r6 = r6 + 1;
                r3.BotId = r6;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.txtMessage;
                r6 = r6.getText();
                r6 = r6.toString();
                r3.MessageText = r6;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.txtBotName;
                r6 = r6.getText();
                r6 = r6.toString();
                r3.BotName = r6;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.txtBotDescription;
                r6 = r6.getText();
                r6 = r6.toString();
                r3.BotDescription = r6;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.checkInfoBack;
                r6 = r6.isChecked();
                r3.InfoBack = r6;
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r6.add(r3);
                r6 = new com.depakto.tsmobile.BotClientActivity$29$1;
                r6.<init>();
                r1 = r6.getType();
                r0 = new com.google.gson.Gson;
                r0.<init>();
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.listBotJoinServer;
                r5 = r0.toJson(r6, r1);
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r8 = "BotList";
                r2 = r6.getSharedPreferences(r8, r7);
                r4 = r2.edit();
                r6 = "JoinBotsList";
                r4.putString(r6, r5);
                r4.commit();
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6 = r6.txtInfoChanges;
                r6.setVisibility(r7);
                r6 = com.depakto.tsmobile.BotClientActivity.this;
                r6.takeBotList();
                return;
            L_0x0127:
                r12 = "Client";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0021;
            L_0x012f:
                r11 = r7;
                goto L_0x0022;
            L_0x0132:
                r12 = "Me";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0021;
            L_0x013a:
                r11 = r8;
                goto L_0x0022;
            L_0x013d:
                r12 = "Anyone";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0021;
            L_0x0145:
                r11 = r9;
                goto L_0x0022;
            L_0x0148:
                r3.ClientId = r7;
                goto L_0x0025;
            L_0x014c:
                r3.ClientId = r6;
                goto L_0x0025;
            L_0x0150:
                r11 = -2;
                r3.ClientId = r11;
                goto L_0x0025;
            L_0x0155:
                r12 = "Join";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x003a;
            L_0x015d:
                r11 = r7;
                goto L_0x003b;
            L_0x0160:
                r12 = "Move";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x003a;
            L_0x0168:
                r11 = r8;
                goto L_0x003b;
            L_0x016b:
                r12 = "Left";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x003a;
            L_0x0173:
                r11 = r9;
                goto L_0x003b;
            L_0x0176:
                r3.Event = r7;
                goto L_0x003e;
            L_0x017a:
                r3.Event = r8;
                goto L_0x003e;
            L_0x017e:
                r3.Event = r9;
                goto L_0x003e;
            L_0x0182:
                r12 = "Server";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0053;
            L_0x018a:
                r11 = r7;
                goto L_0x0054;
            L_0x018d:
                r12 = "Channel";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0053;
            L_0x0195:
                r11 = r8;
                goto L_0x0054;
            L_0x0198:
                r3.Destination = r7;
                goto L_0x0057;
            L_0x019c:
                r3.Destination = r8;
                goto L_0x0057;
            L_0x01a0:
                r12 = "Send";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x006c;
            L_0x01a8:
                r11 = r7;
                goto L_0x006d;
            L_0x01ab:
                r3.Reaction = r7;
                goto L_0x0070;
            L_0x01af:
                r12 = "Poke";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0085;
            L_0x01b7:
                r11 = r7;
                goto L_0x0086;
            L_0x01ba:
                r12 = "Message";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x0085;
            L_0x01c2:
                r11 = r8;
                goto L_0x0086;
            L_0x01c5:
                r3.TypeAction = r7;
                goto L_0x0089;
            L_0x01c9:
                r3.TypeAction = r8;
                goto L_0x0089;
            L_0x01cd:
                r12 = "Me";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x009e;
            L_0x01d5:
                r6 = r7;
                goto L_0x009e;
            L_0x01d8:
                r12 = "Client";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x009e;
            L_0x01e0:
                r6 = r8;
                goto L_0x009e;
            L_0x01e3:
                r12 = "Channel";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x009e;
            L_0x01eb:
                r6 = r9;
                goto L_0x009e;
            L_0x01ee:
                r12 = "Server";
                r11 = r11.equals(r12);
                if (r11 == 0) goto L_0x009e;
            L_0x01f6:
                r6 = r10;
                goto L_0x009e;
            L_0x01f9:
                r3.Reciever = r7;
                goto L_0x00a1;
            L_0x01fd:
                r3.Reciever = r8;
                goto L_0x00a1;
            L_0x0201:
                r3.Reciever = r9;
                goto L_0x00a1;
            L_0x0205:
                r3.Reciever = r10;
                goto L_0x00a1;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.depakto.tsmobile.BotClientActivity.29.onClick(android.content.DialogInterface, int):void");
            }
        });
        this.builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        this.spinnerB1 = (Spinner) dialogLay.findViewById(R.id.spinnerb1);
        ArrayAdapter<CharSequence> adapterb1 = ArrayAdapter.createFromResource(this, R.array.bot_who, 17367048);
        adapterb1.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB1.setAdapter(adapterb1);
        this.spinnerB2 = (Spinner) dialogLay.findViewById(R.id.spinnerChannel);
        ArrayAdapter<CharSequence> adapterb2 = ArrayAdapter.createFromResource(this, R.array.bot_action, 17367048);
        adapterb2.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB2.setAdapter(adapterb2);
        this.spinnerB3 = (Spinner) dialogLay.findViewById(R.id.spinnerb3);
        ArrayAdapter<CharSequence> adapterb3 = ArrayAdapter.createFromResource(this, R.array.bot_place, 17367048);
        adapterb3.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB3.setAdapter(adapterb3);
        final TextView txtWherePlace = (TextView) dialogLay.findViewById(R.id.txtWherePlace);
        this.spinnerB2.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 2) {
                    txtWherePlace.setText("FROM THE");
                } else {
                    txtWherePlace.setText("TO THE");
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerB4 = (Spinner) dialogLay.findViewById(R.id.spinnerb4);
        ArrayAdapter<CharSequence> adapterb4 = ArrayAdapter.createFromResource(this, R.array.bot_reaction, 17367048);
        adapterb4.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB4.setAdapter(adapterb4);
        this.spinnerB5 = (Spinner) dialogLay.findViewById(R.id.spinnerb5);
        ArrayAdapter<CharSequence> adapterb5 = ArrayAdapter.createFromResource(this, R.array.bot_send, 17367048);
        adapterb5.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB5.setAdapter(adapterb5);
        this.spinnerB5.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    BotClientActivity.this.adapterb6 = ArrayAdapter.createFromResource(BotClientActivity.this, R.array.bot_send_to2, 17367048);
                    BotClientActivity.this.adapterb6.setDropDownViewResource(R.layout.custom_spinner_item);
                    BotClientActivity.this.spinnerB6.setAdapter(BotClientActivity.this.adapterb6);
                    return;
                }
                BotClientActivity.this.adapterb6 = ArrayAdapter.createFromResource(BotClientActivity.this, R.array.bot_send_to, 17367048);
                BotClientActivity.this.adapterb6.setDropDownViewResource(R.layout.custom_spinner_item);
                BotClientActivity.this.spinnerB6.setAdapter(BotClientActivity.this.adapterb6);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerB6 = (Spinner) dialogLay.findViewById(R.id.spinnerb6);
        this.adapterb6 = ArrayAdapter.createFromResource(this, R.array.bot_send_to, 17367048);
        this.adapterb6.setDropDownViewResource(R.layout.custom_spinner_item);
        this.spinnerB6.setAdapter(this.adapterb6);
        this.txtMessage = (EditText) dialogLay.findViewById(R.id.editMessage);
        this.txtBotName = (EditText) dialogLay.findViewById(R.id.editName);
        this.txtBotDescription = (EditText) dialogLay.findViewById(R.id.editDescription);
        this.txtMessage.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BotClientActivity.this.hideKeyboard(v);
                }
            }
        });
        this.txtBotName.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BotClientActivity.this.hideKeyboard(v);
                }
            }
        });
        this.txtBotDescription.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BotClientActivity.this.hideKeyboard(v);
                }
            }
        });
        this.spinnerB1.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB3.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB4.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB5.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.spinnerB6.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                BotClientActivity.this.hideKeyboard(v);
                return false;
            }
        });
        this.checkInfoBack = (CheckBox) dialogLay.findViewById(R.id.checkBoxInfoBack);
        this.builder3.show();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.listViewBot.setAdapter(null);
        this.listViewBot.setOnItemClickListener(null);
        this.listViewBot = null;
        this.adapterBot.clear();
        this.adapterBot = null;
        this.fab.setOnClickListener(null);
        this.fab = null;
        this.imgGetSlots.setOnClickListener(null);
        this.imgGetSlots.destroyDrawingCache();
        this.imgGetSlots = null;
        this.imgAddSlot.setOnClickListener(null);
        this.imgAddSlot.destroyDrawingCache();
        this.imgAddSlot = null;
        if (this.alert != null) {
            this.alert.setOnCancelListener(null);
            this.alert.setOnDismissListener(null);
            this.alert.setOnKeyListener(null);
            this.alert.setOnShowListener(null);
            this.alert = null;
        }
        if (this.alert3 != null) {
            this.alert3.setOnCancelListener(null);
            this.alert3.setOnDismissListener(null);
            this.alert3.setOnKeyListener(null);
            this.alert3.setOnShowListener(null);
            this.alert3 = null;
        }
        if (this.imageAd1 != null) {
            this.imageAd1.setOnClickListener(null);
            this.imageAd1.destroyDrawingCache();
            this.imageAd1 = null;
            this.imageAd2.setOnClickListener(null);
            this.imageAd2.destroyDrawingCache();
            this.imageAd2 = null;
        }
        if (this.imageAd1a != null) {
            this.imageAd1a.setOnClickListener(null);
            this.imageAd1a.destroyDrawingCache();
            this.imageAd1a = null;
            this.imageAd2a.setOnClickListener(null);
            this.imageAd2a.destroyDrawingCache();
            this.imageAd2a = null;
        }
        this.builder3 = null;
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }
}
