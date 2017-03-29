package com.depakto.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.help.api.wrapper.Complaint;
import com.depakto.tsmobile.BuildConfig;
import com.depakto.tsmobile.ClientActivity;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientComplainsArrayAdapter extends ArrayAdapter<String> {
    public final ClientActivity act;
    public int active = 0;
    public final List<Complaint> bots;
    public final Context context;

    public ClientComplainsArrayAdapter(Context context, List bots, ClientActivity act) {
        super(context, R.layout.listview_complains_clietns, bots);
        this.context = context;
        this.bots = bots;
        this.act = act;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_complains_clietns, parent, false);
        TextView txtName = (TextView) rowView.findViewById(R.id.labelTrescComplain);
        TextView txtID = (TextView) rowView.findViewById(R.id.txtTargetName);
        ((ImageView) rowView.findViewById(R.id.dellButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ClientComplainsArrayAdapter.this.act.callComplainDialogDell(((Complaint) ClientComplainsArrayAdapter.this.bots.get(position)).getTargetClientDatabaseId(), ((Complaint) ClientComplainsArrayAdapter.this.bots.get(position)).getSourceClientDatabaseId());
            }
        });
        txtID.setText(((Complaint) this.bots.get(position)).getTargetName() + BuildConfig.FLAVOR);
        txtName.setText(((Complaint) this.bots.get(position)).getMessage() + BuildConfig.FLAVOR);
        return rowView;
    }
}
