package com.depakto.tsmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PermisionListActivity extends AppCompatActivity {
    private TextView lista;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_permision_list);
        this.t1 = (TextView) findViewById(R.id.textView47);
        this.t2 = (TextView) findViewById(R.id.textView48);
        this.t3 = (TextView) findViewById(R.id.textView49);
        this.t4 = (TextView) findViewById(R.id.textView50);
        this.lista = (TextView) findViewById(R.id.textView46);
        this.lista.setText(getResources().getString(R.string.permissionList1));
        this.t1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PermisionListActivity.this.lista.setText(PermisionListActivity.this.getResources().getString(R.string.permissionList1));
                PermisionListActivity.this.t1.setTypeface(null, 1);
                PermisionListActivity.this.t2.setTypeface(null, 0);
                PermisionListActivity.this.t3.setTypeface(null, 0);
                PermisionListActivity.this.t4.setTypeface(null, 0);
            }
        });
        this.t2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PermisionListActivity.this.lista.setText(PermisionListActivity.this.getResources().getString(R.string.permissionList2));
                PermisionListActivity.this.t2.setTypeface(null, 1);
                PermisionListActivity.this.t1.setTypeface(null, 0);
                PermisionListActivity.this.t3.setTypeface(null, 0);
                PermisionListActivity.this.t4.setTypeface(null, 0);
            }
        });
        this.t3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PermisionListActivity.this.lista.setText(PermisionListActivity.this.getResources().getString(R.string.permissionList3));
                PermisionListActivity.this.t3.setTypeface(null, 1);
                PermisionListActivity.this.t1.setTypeface(null, 0);
                PermisionListActivity.this.t2.setTypeface(null, 0);
                PermisionListActivity.this.t4.setTypeface(null, 0);
            }
        });
        this.t4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PermisionListActivity.this.lista.setText(PermisionListActivity.this.getResources().getString(R.string.permissionList4));
                PermisionListActivity.this.t4.setTypeface(null, 1);
                PermisionListActivity.this.t3.setTypeface(null, 0);
                PermisionListActivity.this.t2.setTypeface(null, 0);
                PermisionListActivity.this.t1.setTypeface(null, 0);
            }
        });
    }
}
