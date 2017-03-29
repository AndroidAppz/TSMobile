package com.depakto.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.help.api.wrapper.Message;
import com.depakto.tsmobile.ClientActivity;
import com.depakto.tsmobile.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientOfflineMessageArrayAdapter extends ArrayAdapter<String> {
    public final ClientActivity act;
    public int active = 0;
    public final List<Message> bots;
    public final Context context;

    public ClientOfflineMessageArrayAdapter(Context context, List bots, ClientActivity act) {
        super(context, R.layout.listview_offlinemessages_clietns, bots);
        this.context = context;
        this.bots = bots;
        this.act = act;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_offlinemessages_clietns, parent, false);
        final CheckBox readBox = (CheckBox) rowView.findViewById(R.id.checkBoxRead);
        final TextView txtName = (TextView) rowView.findViewById(R.id.labelSubject);
        final TextView txtExpires = (TextView) rowView.findViewById(R.id.textDate);
        ImageView activeButton = (ImageView) rowView.findViewById(R.id.dellButtonMessage);
        if (((Message) this.bots.get(position)).hasRead()) {
            readBox.setChecked(true);
            txtName.setTypeface(null, 0);
            txtExpires.setTypeface(null, 2);
        } else {
            readBox.setChecked(false);
        }
        final int i = position;
        readBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                new Thread(new Runnable() {
                    public void run() {
                        if (readBox.isChecked()) {
                            ClientOfflineMessageArrayAdapter.this.act.callOfflineMessageRead(((Message) ClientOfflineMessageArrayAdapter.this.bots.get(i)).getId(), true, txtName, txtExpires);
                        } else {
                            ClientOfflineMessageArrayAdapter.this.act.callOfflineMessageRead(((Message) ClientOfflineMessageArrayAdapter.this.bots.get(i)).getId(), false, txtName, txtExpires);
                        }
                    }
                }).start();
            }
        });
        final int i2 = position;
        activeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ClientOfflineMessageArrayAdapter.this.act.callOfflineMessageDel(((Message) ClientOfflineMessageArrayAdapter.this.bots.get(i2)).getId());
            }
        });
        txtName.setText(((Message) this.bots.get(position)).getSubject());
        txtExpires.setText("Received: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(new Date(((Message) this.bots.get(position)).getReceivedDate().getTime()).getTime())));
        return rowView;
    }
}
