package com.depakto.classes.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.depakto.classes.construktor.ChatMessage;
import com.depakto.tsmobile.R;
import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter {
    private ImageView chatIconLeft;
    private ImageView chatIconRight;
    private List chatMessageList = new ArrayList();
    private TextView chatText;
    private LinearLayout singleMessageContainer;

    public void add(ChatMessage object) {
        this.chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return (ChatMessage) this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.listview_client_chat_singlemessage, parent, false);
        }
        this.singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
        ChatMessage chatMessageObj = getItem(position);
        this.chatText = (TextView) row.findViewById(R.id.singleMessage);
        this.chatIconLeft = (ImageView) row.findViewById(R.id.avatar_chat_left);
        this.chatIconRight = (ImageView) row.findViewById(R.id.avatar_chat_right);
        try {
            if (chatMessageObj.left) {
                this.chatIconLeft.setVisibility(0);
                this.chatIconRight.setVisibility(8);
            } else {
                this.chatIconLeft.setVisibility(8);
                this.chatIconRight.setVisibility(0);
            }
            this.chatText.setText(chatMessageObj.message);
            this.chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.chmurkaleft : R.drawable.chmurkaright);
            this.singleMessageContainer.setGravity(chatMessageObj.left ? 3 : 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
