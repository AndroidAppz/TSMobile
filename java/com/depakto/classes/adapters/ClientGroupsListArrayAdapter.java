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
import com.depakto.classes.help.api.wrapper.ServerGroup;
import com.depakto.tsmobile.R;
import java.util.List;

public class ClientGroupsListArrayAdapter extends ArrayAdapter<String> {
    public final Context context;
    public final List<ServerGroup> pcs;

    public ClientGroupsListArrayAdapter(Context context, List komp) {
        super(context, R.layout.listview_client_leftlist_menu, komp);
        this.context = context;
        this.pcs = komp;
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
        String tytul = "(" + ((ServerGroup) this.pcs.get(position)).getId() + ") " + ((ServerGroup) this.pcs.get(position)).getName();
        ImageView imageIcon = (ImageView) rowView.findViewById(R.id.icon);
        boolean z = true;
        switch (tytul.hashCode()) {
            case -1633790154:
                if (tytul.equals("TS3mobile")) {
                    z = false;
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
        }
        switch (z) {
            case R.styleable.View_android_theme /*0*/:
                imageIcon.setImageResource(R.drawable.me_no_talk);
                break;
            case R.styleable.View_android_focusable /*1*/:
                imageIcon.setImageResource(R.drawable.give_review_white);
                break;
            case R.styleable.View_paddingStart /*2*/:
                imageIcon.setImageResource(R.drawable.fb_icon2);
                break;
        }
        textView.setText(tytul);
        return rowView;
    }
}
