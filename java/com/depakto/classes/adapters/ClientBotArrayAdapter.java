package com.depakto.classes.adapters;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.depakto.classes.construktor.BotJoinServer;
import com.depakto.tsmobile.BotClientActivity;
import com.depakto.tsmobile.BuildConfig;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientBotArrayAdapter extends ArrayAdapter<String> {
    public final BotClientActivity act;
    public int active = 0;
    public final List<BotJoinServer> bots;
    public final Context context;

    public ClientBotArrayAdapter(Context context, List bots, BotClientActivity act) {
        super(context, R.layout.listview_channel_list_admin, bots);
        this.context = context;
        this.bots = bots;
        this.act = act;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_bots_clietns, parent, false);
        TextView txtName = (TextView) rowView.findViewById(R.id.labelUser);
        TextView txtID = (TextView) rowView.findViewById(R.id.txtMove1);
        final ToggleButton activeButton = (ToggleButton) rowView.findViewById(R.id.toggleButton);
        activeButton.getBackground().setColorFilter(-65536, Mode.MULTIPLY);
        activeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int i;
                if (activeButton.isChecked()) {
                    ClientBotArrayAdapter.this.active = 0;
                    for (i = 1; i < ClientBotArrayAdapter.this.act.listBotJoinServer.size(); i++) {
                        if (((BotJoinServer) ClientBotArrayAdapter.this.act.listBotJoinServer.get(i)).ActiveStatus) {
                            ClientBotArrayAdapter clientBotArrayAdapter = ClientBotArrayAdapter.this;
                            clientBotArrayAdapter.active++;
                        }
                    }
                    if (ClientBotArrayAdapter.this.active < ClientBotArrayAdapter.this.act.activeSlots || ((BotJoinServer) ClientBotArrayAdapter.this.bots.get(position)).BotId == 1) {
                        activeButton.getBackground().setColorFilter(-16711936, Mode.MULTIPLY);
                        ClientBotArrayAdapter.this.act.txtInfoChanges.setVisibility(0);
                        for (i = 0; i < ClientBotArrayAdapter.this.act.listBotJoinServer.size(); i++) {
                            if (((BotJoinServer) ClientBotArrayAdapter.this.act.listBotJoinServer.get(i)).BotId == ((BotJoinServer) ClientBotArrayAdapter.this.bots.get(position)).BotId) {
                                ((BotJoinServer) ClientBotArrayAdapter.this.act.listBotJoinServer.get(i)).ActiveStatus = true;
                                break;
                            }
                        }
                        ClientBotArrayAdapter.this.act.savePrefers();
                        return;
                    }
                    Toast.makeText(ClientBotArrayAdapter.this.getContext(), "You doesn't have available slot!", 0).show();
                    activeButton.setChecked(false);
                    return;
                }
                activeButton.getBackground().setColorFilter(-65536, Mode.MULTIPLY);
                ClientBotArrayAdapter.this.act.txtInfoChanges.setVisibility(0);
                for (i = 0; i < ClientBotArrayAdapter.this.act.listBotJoinServer.size(); i++) {
                    if (((BotJoinServer) ClientBotArrayAdapter.this.act.listBotJoinServer.get(i)).BotId == ((BotJoinServer) ClientBotArrayAdapter.this.bots.get(position)).BotId) {
                        ((BotJoinServer) ClientBotArrayAdapter.this.act.listBotJoinServer.get(i)).ActiveStatus = false;
                        break;
                    }
                }
                ClientBotArrayAdapter.this.act.savePrefers();
            }
        });
        txtID.setText(((BotJoinServer) this.bots.get(position)).BotId + BuildConfig.FLAVOR);
        txtName.setText(((BotJoinServer) this.bots.get(position)).BotName + BuildConfig.FLAVOR);
        if (((BotJoinServer) this.bots.get(position)).ActiveStatus) {
            activeButton.setChecked(true);
            activeButton.getBackground().setColorFilter(-16711936, Mode.MULTIPLY);
        } else {
            activeButton.setChecked(false);
            activeButton.getBackground().setColorFilter(-65536, Mode.MULTIPLY);
        }
        return rowView;
    }
}
