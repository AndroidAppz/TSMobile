package com.depakto.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.help.api.wrapper.Ban;
import com.depakto.tsmobile.BuildConfig;
import com.depakto.tsmobile.ClientActivity;
import com.depakto.tsmobile.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientBansArrayAdapter extends ArrayAdapter<String> {
    public final ClientActivity act;
    public int active = 0;
    public final List<Ban> bots;
    public final Context context;

    public ClientBansArrayAdapter(Context context, List bots, ClientActivity act) {
        super(context, R.layout.listview_bans_clietns, bots);
        this.context = context;
        this.bots = bots;
        this.act = act;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_bans_clietns, parent, false);
        TextView txtName = (TextView) rowView.findViewById(R.id.labelTargetBan);
        TextView txtID = (TextView) rowView.findViewById(R.id.txtTargetName);
        TextView txtExpires = (TextView) rowView.findViewById(R.id.textBanExpires);
        final int i = position;
        ((ImageView) rowView.findViewById(R.id.dellButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ClientBansArrayAdapter.this.act.callBanDel(((Ban) ClientBansArrayAdapter.this.bots.get(i)).getId());
            }
        });
        txtID.setText(((Ban) this.bots.get(position)).getId() + BuildConfig.FLAVOR);
        String nazwaBanu = BuildConfig.FLAVOR;
        boolean nazwaCzlonowa = false;
        if (((Ban) this.bots.get(position)).getBannedName().length() > 0) {
            nazwaBanu = "Name=" + ((Ban) this.bots.get(position)).getBannedName();
            nazwaCzlonowa = true;
        }
        if (((Ban) this.bots.get(position)).getBannedIp().length() > 0) {
            if (nazwaCzlonowa) {
                nazwaBanu = nazwaBanu + ", ";
            }
            nazwaBanu = nazwaBanu + "IP=" + ((Ban) this.bots.get(position)).getBannedIp();
            nazwaCzlonowa = true;
        }
        if (((Ban) this.bots.get(position)).getBannedUId().length() > 0) {
            if (nazwaCzlonowa) {
                nazwaBanu = nazwaBanu + ", ";
            }
            nazwaBanu = nazwaBanu + "UID=" + ((Ban) this.bots.get(position)).getBannedUId();
        }
        txtName.setText(nazwaBanu);
        if (((Ban) this.bots.get(position)).getDuration() == 0) {
            txtExpires.setText("infinity");
        } else {
            txtExpires.setText("to " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(new Date(((Ban) this.bots.get(position)).getCreatedDate().getTime() + (((Ban) this.bots.get(position)).getDuration() * 1000)).getTime())));
        }
        return rowView;
    }
}
