package com.depakto.classes.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.depakto.tsmobile.R;
import java.util.List;

public class StartClientIPArrayAdapter extends ArrayAdapter<String> {
    public final Activity act;
    public final Context context;
    public final List<String> pcs;

    public StartClientIPArrayAdapter(Context context, List<String> komp, Activity act) {
        super(context, R.layout.listview_start_client_ip, komp);
        this.context = context;
        this.pcs = komp;
        this.act = act;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_start_client_ip, parent, false);
        ((TextView) rowView.findViewById(R.id.label)).setText(((String) this.pcs.get(position)).toString());
        return rowView;
    }
}
