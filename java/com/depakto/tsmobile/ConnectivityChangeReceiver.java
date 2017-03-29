package com.depakto.tsmobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    private NetworkInfo activeNetInfo;
    private ConnectivityManager connectivityManager;

    public void onReceive(Context context, Intent intent) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.activeNetInfo = this.connectivityManager.getActiveNetworkInfo();
        if (this.activeNetInfo == null || this.activeNetInfo.getType() != 1) {
            Toast.makeText(context, "Wifi Not Connected!", 0).show();
            Intent intent2open = new Intent(context, ClientActivity.class);
            intent2open.addFlags(268435456);
            intent2open.addFlags(536870912);
            intent2open.putExtra("KEY", "String you want to pass");
            context.startActivity(intent2open);
        }
    }
}
