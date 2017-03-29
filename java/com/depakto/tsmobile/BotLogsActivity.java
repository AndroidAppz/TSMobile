package com.depakto.tsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BotLogsActivity extends AppCompatActivity {
    private List<String> listBotJoinServerLog = new ArrayList();
    private TextView txtInfoLogs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_logs);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ((FloatingActionButton) findViewById(R.id.fab)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BotLogsActivity.this.startActivity(new Intent(BotLogsActivity.this, PermisionListActivity.class));
                Runtime.getRuntime().gc();
                System.gc();
            }
        });
        this.txtInfoLogs = (TextView) findViewById(R.id.txtInfoLogs);
        Type listOfObjectsLog = new TypeToken<List<String>>() {
        }.getType();
        Gson gsonLog = new Gson();
        String jsonLog = getSharedPreferences("BotListLog", 0).getString("JoinBotsListLog", BuildConfig.FLAVOR);
        if (jsonLog.length() > 0) {
            this.listBotJoinServerLog = (List) gsonLog.fromJson(jsonLog, listOfObjectsLog);
            if (this.listBotJoinServerLog.size() > 0) {
                this.txtInfoLogs.setText("Beginning of logs...\n");
                for (int i = 0; i < this.listBotJoinServerLog.size(); i++) {
                    this.txtInfoLogs.append("\n" + ((String) this.listBotJoinServerLog.get(i)).toString());
                }
            }
        }
    }
}
