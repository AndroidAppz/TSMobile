package com.depakto.classes.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.depakto.tsmobile.ClientActivity;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientLeftListArrayAdapter extends ArrayAdapter<String> {
    public final ClientActivity act;
    public final Context context;
    public final List<String> pcs;

    public ClientLeftListArrayAdapter(Context context, List<String> komp, ClientActivity act) {
        super(context, R.layout.listview_client_leftlist_menu, komp);
        this.context = context;
        this.pcs = komp;
        this.act = act;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inPreferredConfig = Config.RGB_565;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_client_leftlist_menu, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        String tytul = ((String) this.pcs.get(position)).toString();
        ImageView imageIcon = (ImageView) rowView.findViewById(R.id.icon);
        boolean z = true;
        switch (tytul.hashCode()) {
            case -2132871082:
                if (tytul.equals("Check the Logs")) {
                    z = true;
                    break;
                }
                break;
            case -1909181292:
                if (tytul.equals("Offline Message List")) {
                    z = true;
                    break;
                }
                break;
            case -1863963057:
                if (tytul.equals("Ban List")) {
                    z = true;
                    break;
                }
                break;
            case -1221650213:
                if (tytul.equals("Give a review!")) {
                    z = true;
                    break;
                }
                break;
            case -777248364:
                if (tytul.equals("Join to Us!")) {
                    z = true;
                    break;
                }
                break;
            case 165521261:
                if (tytul.equals("Create & Run")) {
                    z = true;
                    break;
                }
                break;
            case 502018144:
                if (tytul.equals("Unlimited TSmobile")) {
                    z = false;
                    break;
                }
                break;
            case 1301878579:
                if (tytul.equals("Complaint List")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case R.styleable.View_android_theme /*0*/:
                imageIcon.setImageResource(R.drawable.don_project);
                break;
            case R.styleable.View_android_focusable /*1*/:
                imageIcon.setImageResource(R.drawable.give_review_white);
                break;
            case R.styleable.View_paddingStart /*2*/:
                imageIcon.setImageResource(R.drawable.fb_icon2);
                break;
            case R.styleable.View_paddingEnd /*3*/:
                imageIcon.setImageResource(R.drawable.c_block_white);
                break;
            case R.styleable.View_theme /*4*/:
                imageIcon.setImageResource(R.drawable.c_skarga);
                break;
            case R.styleable.Toolbar_contentInsetStart /*5*/:
                imageIcon.setImageResource(R.drawable.ic_menu_sort_by_size);
                this.act.textNumberOfflineMsg = (TextView) rowView.findViewById(R.id.textIconNumberLogs);
                break;
            case R.styleable.Toolbar_contentInsetEnd /*6*/:
                imageIcon.setImageResource(R.drawable.ic_menu_manage);
                this.act.textNumberCreateBots = (TextView) rowView.findViewById(R.id.textIconNumberLogs);
                break;
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                imageIcon.setImageResource(R.drawable.ic_menu_info_details);
                this.act.textNumberLogs = (TextView) rowView.findViewById(R.id.textIconNumberLogs);
                break;
            default:
                imageIcon.setImageResource(R.drawable.ic_menu_info_details);
                break;
        }
        textView.setText(tytul);
        return rowView;
    }
}
