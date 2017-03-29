package com.depakto.tsmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.depakto.classes.adapters.ClientServerInfoArrayAdapter;
import com.depakto.classes.construktor.ServerInfo;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerInfoActivity extends AppCompatActivity {
    private ClientServerInfoArrayAdapter adapter_virtuals1;
    private ClientServerInfoArrayAdapter adapter_virtuals2;
    private ClientServerInfoArrayAdapter adapter_virtuals3;
    private ServerInfo dane1 = new ServerInfo();
    private ServerInfo dane2 = new ServerInfo();
    private ServerInfo dane3 = new ServerInfo();
    private List<ServerInfo> daneList1 = new ArrayList();
    private List<ServerInfo> daneList2 = new ArrayList();
    private List<ServerInfo> daneList3 = new ArrayList();
    private ListView listHost;
    private ListView listInfo1;
    private ListView listInfo2;
    private Toolbar toolbar;
    private HashMap<String, String> wartosciServera = new HashMap();

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap mIcon11 = null;
            try {
                mIcon11 = BitmapFactory.decodeStream(new URL(urls[0]).openStream());
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            this.bmImage.setImageBitmap(result);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_info);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        Bundle bBundle = getIntent().getExtras();
        if (bBundle != null) {
            if (bBundle.containsKey("sName")) {
                this.wartosciServera.put("sName", (String) bBundle.get("sName"));
            }
            if (bBundle.containsKey("sHostbannerGfxUrl")) {
                this.wartosciServera.put("sHostbannerGfxUrl", (String) bBundle.get("sHostbannerGfxUrl"));
            }
            if (bBundle.containsKey("sHostbannerUrl")) {
                this.wartosciServera.put("sHostbannerUrl", (String) bBundle.get("sHostbannerUrl"));
            }
            if (bBundle.containsKey("sHostbuttonTooltip")) {
                this.wartosciServera.put("sHostbuttonTooltip", (String) bBundle.get("sHostbuttonTooltip"));
            }
            if (bBundle.containsKey("sHostbuttonUrl")) {
                this.wartosciServera.put("sHostbuttonUrl", (String) bBundle.get("sHostbuttonUrl"));
            }
            if (bBundle.containsKey("sPlatform")) {
                this.wartosciServera.put("sPlatform", (String) bBundle.get("sPlatform"));
            }
            if (bBundle.containsKey("sVersion")) {
                this.wartosciServera.put("sVersion", (String) bBundle.get("sVersion"));
            }
            if (bBundle.containsKey("sCreatedDate")) {
                this.wartosciServera.put("sCreatedDate", bBundle.get("sCreatedDate").toString());
            }
            if (bBundle.containsKey("sDefaultChannelGroup")) {
                this.wartosciServera.put("sDefaultChannelGroup", bBundle.get("sDefaultChannelGroup").toString());
            }
            if (bBundle.containsKey("sDefaultServerGroup")) {
                this.wartosciServera.put("sDefaultServerGroup", bBundle.get("sDefaultServerGroup").toString());
            }
            if (bBundle.containsKey("sPrioritySpeakerDimmModificator")) {
                this.wartosciServera.put("sPrioritySpeakerDimmModificator", bBundle.get("sPrioritySpeakerDimmModificator").toString());
            }
            if (bBundle.containsKey("sServerId")) {
                this.wartosciServera.put("sServerId", bBundle.get("sServerId").toString());
            }
            if (bBundle.containsKey("sClientsOnline")) {
                this.wartosciServera.put("sClientsOnline", bBundle.get("sClientsOnline").toString());
            }
            if (bBundle.containsKey("sChannelsOnline")) {
                this.wartosciServera.put("sChannelsOnline", bBundle.get("sChannelsOnline").toString());
            }
            if (bBundle.containsKey("sServerIP")) {
                this.wartosciServera.put("sServerIP", bBundle.get("sServerIP").toString());
            }
            if (bBundle.containsKey("sServerPort")) {
                this.wartosciServera.put("sServerPort", bBundle.get("sServerPort").toString());
            }
        }
        ImageView imgGFX = (ImageView) findViewById(R.id.imgServerGFX);
        TextView txtName = (TextView) findViewById(R.id.txtName);
        TextView txtIPiPort = (TextView) findViewById(R.id.txtIPiPort);
        this.listInfo1 = (ListView) findViewById(R.id.listInfo1);
        this.listInfo2 = (ListView) findViewById(R.id.listInfo2);
        this.listHost = (ListView) findViewById(R.id.listHost);
        txtName.setText((CharSequence) this.wartosciServera.get("sName"));
        txtIPiPort.setText("IP: " + ((String) this.wartosciServera.get("sServerIP")) + " Port: " + ((String) this.wartosciServera.get("sServerPort")));
        dane1Uzupelnij("Version", "sVersion");
        dane1Uzupelnij("Platform", "sPlatform");
        dane1Uzupelnij("Created", "sCreatedDate");
        this.adapter_virtuals1 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList1);
        this.listInfo1.setAdapter(this.adapter_virtuals1);
        setListViewHeightBasedOnItems(this.listInfo1);
        dane2Uzupelnij("Server ID", "sServerId");
        dane2Uzupelnij("Channels Online", "sChannelsOnline");
        dane2Uzupelnij("Clients Online", "sClientsOnline");
        dane2Uzupelnij("Default Server Group", "sDefaultServerGroup");
        dane2Uzupelnij("Default Channel Group", "sDefaultChannelGroup");
        dane2Uzupelnij("Priority Speaker Dimm", "sPrioritySpeakerDimmModificator");
        this.adapter_virtuals2 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList2);
        this.listInfo2.setAdapter(this.adapter_virtuals2);
        setListViewHeightBasedOnItems(this.listInfo2);
        dane3Uzupelnij("Banner URL", "sHostbannerUrl");
        dane3Uzupelnij("Gfx URL", "sHostbannerGfxUrl");
        dane3Uzupelnij("ToolTip", "sHostbuttonTooltip");
        dane3Uzupelnij("Button URL", "sHostbuttonUrl");
        this.adapter_virtuals3 = new ClientServerInfoArrayAdapter(getApplicationContext(), this.daneList3);
        this.listHost.setAdapter(this.adapter_virtuals3);
        setListViewHeightBasedOnItems(this.listHost);
        if (((String) this.wartosciServera.get("sHostbannerGfxUrl")).length() > 1) {
            new DownloadImageTask((ImageView) findViewById(R.id.imgServerGFX)).execute(new String[]{(String) this.wartosciServera.get("sHostbannerGfxUrl")});
            return;
        }
        imgGFX.setImageResource(R.drawable.ic_menu_gallery);
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
        this.listInfo1.setAdapter(null);
        this.listInfo2.setAdapter(null);
        this.listHost.setAdapter(null);
        super.onDestroy();
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }
}
