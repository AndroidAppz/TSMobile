package com.depakto.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.depakto.classes.construktor.OsobyIchannel;
import com.depakto.tsmobile.AdminActivity;
import com.depakto.tsmobile.BuildConfig;
import com.depakto.tsmobile.R;
import java.util.List;

public class ChannelAdminArrayAdapter extends ArrayAdapter<String> {
    public final AdminActivity act;
    public final Context context;
    public final List<OsobyIchannel> pcs;
    private final float scale = getContext().getResources().getDisplayMetrics().density;

    public ChannelAdminArrayAdapter(Context context, List komp, AdminActivity act) {
        super(context, R.layout.listview_client_channels, komp);
        this.context = context;
        this.pcs = komp;
        this.act = act;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_client_channels, parent, false);
        ImageView image_rozwin = (ImageView) rowView.findViewById(R.id.image_rozwin);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageIcon = (ImageView) rowView.findViewById(R.id.icon);
        try {
            if (((OsobyIchannel) this.pcs.get(position)).idChannel > -1) {
                if (((OsobyIchannel) this.pcs.get(position)).hasPasswordChannel) {
                    imageIcon.setImageResource(R.drawable.pass_ch);
                }
                if (((OsobyIchannel) this.pcs.get(position)).totalClientsChannel == ((OsobyIchannel) this.pcs.get(position)).maxClientsChannel) {
                    imageIcon.setImageResource(R.drawable.full_ch);
                }
                if (((OsobyIchannel) this.pcs.get(position)).maxClientsChannel == 0) {
                    imageIcon.setVisibility(8);
                    image_rozwin.setVisibility(8);
                }
                if (((OsobyIchannel) this.pcs.get(position)).totalClientsChannel > 0) {
                    image_rozwin.setVisibility(0);
                }
                if (((OsobyIchannel) this.pcs.get(position)).parentIdChannel > 0) {
                    image_rozwin.setPadding((int) ((35.0f * this.scale) + 0.5f), 0, 0, 0);
                }
                String regex = "\\[[^\\]]*\\]";
                if (((OsobyIchannel) this.pcs.get(position)).nameChannel.contains("spacer")) {
                    textView.setText(((OsobyIchannel) this.pcs.get(position)).nameChannel.replaceAll(regex, BuildConfig.FLAVOR));
                    textView.setGravity(17);
                } else {
                    textView.setText(((OsobyIchannel) this.pcs.get(position)).nameChannel);
                }
            } else {
                imageIcon.setImageResource(R.drawable.c_quiet);
                textView.setText(((OsobyIchannel) this.pcs.get(position)).nameClient);
                int pixels2 = (int) ((60.0f * this.scale) + 0.5f);
                int size = (int) ((25.0f * this.scale) + 0.5f);
                LayoutParams p = new LayoutParams(size, size);
                p.setMargins(pixels2, 0, 0, 0);
                imageIcon.setLayoutParams(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
