package com.depakto.tsmobile;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HelpAdminActivity extends AppCompatActivity {
    private Bitmap bmfb;
    private Bitmap bmplay;
    private ImageView fb;
    private ImageView play;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_admin);
        this.play = (ImageView) findViewById(R.id.play_image);
        this.fb = (ImageView) findViewById(R.id.fb_img);
        this.fb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("https://www.facebook.com/TS3MobileApp"));
                HelpAdminActivity.this.startActivity(intent);
            }
        });
        this.play.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=EZt_2Lyhtq0"));
                HelpAdminActivity.this.startActivity(intent);
            }
        });
        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(view, "e-mail: developer.dep@gmail.com | www: tsmobile.eu", 0).setAction("Action", null).show();
            }
        });
        this.bmplay = decodeSampledBitmapFromResource(getResources(), R.drawable.play_me, 1, 1);
        this.bmfb = decodeSampledBitmapFromResource(getResources(), R.drawable.fb, 1, 1);
        Runtime.getRuntime().gc();
        System.gc();
    }

    protected void onResume() {
        super.onResume();
        System.gc();
        this.play.setImageBitmap(this.bmplay);
        this.fb.setImageBitmap(this.bmfb);
    }

    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
        System.gc();
    }

    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        System.gc();
        this.play.destroyDrawingCache();
        this.fb.destroyDrawingCache();
        try {
            this.bmplay.recycle();
            this.bmfb.recycle();
        } catch (Exception e) {
        }
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, options.outWidth, options.outHeight);
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
}
