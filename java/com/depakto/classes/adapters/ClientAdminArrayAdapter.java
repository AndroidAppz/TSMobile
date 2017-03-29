package com.depakto.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.construktor.Student;
import com.depakto.classes.help.api.wrapper.Channel;
import com.depakto.tsmobile.AdminActivity;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientAdminArrayAdapter extends ArrayAdapter<String> {
    public final AdminActivity act;
    public final List<Channel> channels;
    public final Context context;
    public final List<Student> osobyLista;

    public ClientAdminArrayAdapter(Context context, List osobyLista, List channels, AdminActivity act) {
        super(context, R.layout.listview_client_admin, osobyLista);
        this.context = context;
        this.osobyLista = osobyLista;
        this.act = act;
        this.channels = channels;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_client_admin, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        TextView channel_name = (TextView) rowView.findViewById(R.id.channel_name1);
        try {
            ((TextView) rowView.findViewById(R.id.txt_country)).setText(((Student) this.osobyLista.get(position)).country);
            textView.setText(((Student) this.osobyLista.get(position)).name);
            for (Channel cr : this.channels) {
                if (cr.getId() == ((Student) this.osobyLista.get(position)).channel) {
                    channel_name.setText("Channel: " + cr.getName());
                    break;
                }
            }
            ImageView awayImage = (ImageView) rowView.findViewById(R.id.awayIcon);
            ImageView inputImage = (ImageView) rowView.findViewById(R.id.inputIcon);
            ImageView outputImage = (ImageView) rowView.findViewById(R.id.outputIcon);
            if (!((Student) this.osobyLista.get(position)).away) {
                awayImage.setVisibility(8);
            }
            if (!((Student) this.osobyLista.get(position)).input) {
                inputImage.setVisibility(8);
            }
            if (!((Student) this.osobyLista.get(position)).output) {
                outputImage.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
