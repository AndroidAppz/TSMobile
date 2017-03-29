package com.depakto.classes.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.help.api.wrapper.VirtualServer;
import com.depakto.tsmobile.R;
import java.util.List;

public class AdminVirtualServersArrayAdapter extends ArrayAdapter<String> {
    public final Activity act;
    public final Context context;
    public final List<VirtualServer> pcs;

    public AdminVirtualServersArrayAdapter(Context context, List komp, Activity act) {
        super(context, R.layout.listview_admin_virtual_servers, komp);
        this.context = context;
        this.pcs = komp;
        this.act = act;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_admin_virtual_servers, parent, false);
        ImageView imageIcon = (ImageView) rowView.findViewById(R.id.icon);
        ((TextView) rowView.findViewById(R.id.label)).setText(((VirtualServer) this.pcs.get(position)).getName() + " | " + ((VirtualServer) this.pcs.get(position)).getPort() + " | " + ((VirtualServer) this.pcs.get(position)).getStatus() + " | " + ((VirtualServer) this.pcs.get(position)).getClientsOnline() + "/" + ((VirtualServer) this.pcs.get(position)).getMaxClients());
        return rowView;
    }
}
