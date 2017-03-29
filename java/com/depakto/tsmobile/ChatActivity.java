package com.depakto.tsmobile;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.depakto.classes.adapters.ChatArrayAdapter;
import com.depakto.classes.construktor.ChatMessage;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private Button buttonSend;
    private ChatArrayAdapter chatArrayAdapter;
    private EditText chatText;
    private ListView listView;
    private boolean side = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bubble);
        getWindow().setSoftInputMode(3);
        this.buttonSend = (Button) findViewById(R.id.buttonSend);
        this.listView = (ListView) findViewById(R.id.listView1);
        this.chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.listview_client_chat_singlemessage);
        this.listView.setAdapter(this.chatArrayAdapter);
        this.chatText = (EditText) findViewById(R.id.chatText);
        this.chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 0 && keyCode == 66) {
                    return ChatActivity.this.sendChatMessage();
                }
                return false;
            }
        });
        this.chatText.setSelected(false);
        this.buttonSend.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                ChatActivity.this.sendChatMessage();
            }
        });
        this.listView.setTranscriptMode(2);
        this.listView.setAdapter(this.chatArrayAdapter);
        this.chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                ChatActivity.this.listView.setSelection(ChatActivity.this.chatArrayAdapter.getCount() - 1);
            }
        });
    }

    private boolean sendChatMessage() {
        this.chatArrayAdapter.add(new ChatMessage(this.side, this.chatText.getText().toString()));
        this.chatText.setText(BuildConfig.FLAVOR);
        this.side = !this.side;
        return true;
    }
}
