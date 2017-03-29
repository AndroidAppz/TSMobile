package com.depakto.classes.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.depakto.classes.construktor.ServerInfo;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientServerInfoArrayAdapter extends ArrayAdapter<String> {
    public final Context context;
    public final List<ServerInfo> pcs;

    public ClientServerInfoArrayAdapter(Context context, List komp) {
        super(context, R.layout.listview_client_server_info, komp);
        this.context = context;
        this.pcs = komp;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_client_server_info, parent, false);
        TextView textView2 = (TextView) rowView.findViewById(R.id.txtDetails);
        ((TextView) rowView.findViewById(R.id.txtLabel)).setText(((ServerInfo) this.pcs.get(position)).id);
        textView2.setText(((ServerInfo) this.pcs.get(position)).details);
        if (((ServerInfo) this.pcs.get(position)).details.contains("true")) {
            textView2.setTextColor(Color.parseColor("#df5745"));
        } else if (((ServerInfo) this.pcs.get(position)).details.contains("false")) {
            textView2.setTextColor(Color.parseColor("#1ca037"));
        }
        return rowView;
    }
}
