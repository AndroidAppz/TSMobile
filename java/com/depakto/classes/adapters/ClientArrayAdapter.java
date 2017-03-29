package com.depakto.classes.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.classes.construktor.Student;
import com.depakto.tsmobile.ClientActivity;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientArrayAdapter extends ArrayAdapter<String> {
    public final ClientActivity act;
    public Builder builder;
    public final Context context;
    AlertDialog dialog2;
    private ImageView imageIcon;
    public EditText ipInput;
    public final List<Student> osobyLista;

    public ClientArrayAdapter(Context context, List osobyLista, ClientActivity act) {
        super(context, R.layout.listview_client_clients, osobyLista);
        this.context = context;
        this.osobyLista = osobyLista;
        this.act = act;
        this.builder = new Builder(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_client_clients, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        try {
            ((TextView) rowView.findViewById(R.id.txt_country)).setText(((Student) this.osobyLista.get(position)).country);
            final ImageView imageIcon2 = (ImageView) rowView.findViewById(R.id.icon);
            if (((Student) this.osobyLista.get(position)).mutedByMe) {
                imageIcon2.setImageResource(R.drawable.c_be_quiet);
            }
            final int i = position;
            ((ImageView) rowView.findViewById(R.id.imagePoke)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(ClientArrayAdapter.this.getContext(), R.anim.image_click));
                    try {
                        ClientArrayAdapter.this.act.callPoke(((Student) ClientArrayAdapter.this.osobyLista.get(i)).getId(), 0);
                    } catch (Exception e) {
                    }
                }
            });
            i = position;
            ((ImageView) rowView.findViewById(R.id.imageMute)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(ClientArrayAdapter.this.getContext(), R.anim.image_click));
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                if (((Student) ClientArrayAdapter.this.osobyLista.get(i)).mutedByMe) {
                                    ClientArrayAdapter.this.act.callUnMute(((Student) ClientArrayAdapter.this.osobyLista.get(i)).id, imageIcon2);
                                } else {
                                    ClientArrayAdapter.this.act.callMute(((Student) ClientArrayAdapter.this.osobyLista.get(i)).id, imageIcon2);
                                }
                            } catch (Exception e) {
                            }
                        }
                    }).start();
                }
            });
            i = position;
            ((ImageView) rowView.findViewById(R.id.imageKick)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    v.startAnimation(AnimationUtils.loadAnimation(ClientArrayAdapter.this.getContext(), R.anim.image_click));
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                ClientArrayAdapter.this.act.callKickCh(((Student) ClientArrayAdapter.this.osobyLista.get(i)).id, 0);
                            } catch (Exception e) {
                            }
                        }
                    }).start();
                }
            });
            String nameOsoby = ((Student) this.osobyLista.get(position)).name;
            if (!((Student) this.osobyLista.get(position)).away || ((Student) this.osobyLista.get(position)).awayMessage.length() <= 0) {
                textView.setText(nameOsoby);
            } else {
                textView.setText(nameOsoby + " (" + ((Student) this.osobyLista.get(position)).awayMessage + ")");
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
