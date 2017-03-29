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

public class HelpActivity extends AppCompatActivity {
    private Bitmap bmfb;
    private Bitmap bmhelp1;
    private Bitmap bmhelp2;
    private Bitmap bmhelp3;
    private Bitmap bmplay;
    private ImageView fb;
    private ImageView help1;
    private ImageView help2;
    private ImageView help3;
    private ImageView play;
    private ImageView play0;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Runtime.getRuntime().gc();
        System.gc();
        setContentView(R.layout.activity_help);
        this.play = (ImageView) findViewById(R.id.play_image);
        this.help1 = (ImageView) findViewById(R.id.help1);
        this.help2 = (ImageView) findViewById(R.id.help2);
        this.help3 = (ImageView) findViewById(R.id.help3);
        this.play0 = (ImageView) findViewById(R.id.play0);
        this.fb = (ImageView) findViewById(R.id.fb_img);
        this.fb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("https://www.facebook.com/TS3MobileApp"));
                HelpActivity.this.startActivity(intent);
            }
        });
        this.play.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=qCbjUsctP-g"));
                HelpActivity.this.startActivity(intent);
            }
        });
        this.play0.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Roe-El6nhtU"));
                HelpActivity.this.startActivity(intent);
            }
        });
        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(view, "e-mail: developer.dep@gmail.com | www: tsmobile.eu", 0).setAction("Action", null).show();
            }
        });
        this.bmplay = decodeSampledBitmapFromResource(getResources(), R.drawable.play_me, 1, 1);
        this.bmhelp1 = decodeSampledBitmapFromResource(getResources(), R.drawable.as, 1, 1);
        this.bmhelp2 = decodeSampledBitmapFromResource(getResources(), R.drawable.help_plug_a, 1, 1);
        this.bmhelp3 = decodeSampledBitmapFromResource(getResources(), R.drawable.help_plug_b, 1, 1);
        this.bmfb = decodeSampledBitmapFromResource(getResources(), R.drawable.fb, 1, 1);
        Runtime.getRuntime().gc();
        System.gc();
    }

    protected void onResume() {
        super.onResume();
        Runtime.getRuntime().gc();
        System.gc();
        this.play.setImageBitmap(this.bmplay);
        this.play0.setImageBitmap(this.bmplay);
        this.help1.setImageBitmap(this.bmhelp1);
        this.help2.setImageBitmap(this.bmhelp2);
        this.help3.setImageBitmap(this.bmhelp3);
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
        this.play0.destroyDrawingCache();
        this.help1.destroyDrawingCache();
        this.help2.destroyDrawingCache();
        this.help3.destroyDrawingCache();
        this.fb.destroyDrawingCache();
        try {
            this.bmplay.recycle();
            this.bmhelp1.recycle();
            this.bmhelp2.recycle();
            this.bmhelp3.recycle();
            this.bmfb.recycle();
        } catch (Exception e) {
        }
        Runtime.getRuntime().gc();
        System.gc();
        finish();
    }
}
