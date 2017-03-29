package com.depakto.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.construktor.MyChannelClass;
import com.depakto.tsmobile.AdminActivity;
import com.depakto.tsmobile.R;
import java.util.List;

public class ChannelListAdminArrayAdapter extends ArrayAdapter<String> {
    public final AdminActivity act;
    public final List<MyChannelClass> channels;
    public final Context context;

    public ChannelListAdminArrayAdapter(Context context, List channels, AdminActivity act) {
        super(context, R.layout.listview_channel_list_admin, channels);
        this.context = context;
        this.act = act;
        this.channels = channels;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_channel_list_admin, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.labelChannels);
        TextView channel_name = (TextView) rowView.findViewById(R.id.channel_name1AndType);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        try {
            String zmMax;
            if (((MyChannelClass) this.channels.get(position)).maxClients == -1) {
                zmMax = "Unlimited";
            } else {
                zmMax = String.valueOf(((MyChannelClass) this.channels.get(position)).maxClients);
            }
            textView.setText(((MyChannelClass) this.channels.get(position)).name);
            channel_name.setText("Clients: " + ((MyChannelClass) this.channels.get(position)).totalClients + "/" + zmMax + "   Type: " + ((MyChannelClass) this.channels.get(position)).type);
            if (((MyChannelClass) this.channels.get(position)).totalClients == ((MyChannelClass) this.channels.get(position)).maxClients) {
                icon.setImageResource(R.drawable.full_ch);
            } else if (((MyChannelClass) this.channels.get(position)).hasPassword) {
                icon.setImageResource(R.drawable.pass_ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
