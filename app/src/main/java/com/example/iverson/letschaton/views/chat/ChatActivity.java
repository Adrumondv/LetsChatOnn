package com.example.iverson.letschaton.views.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.iverson.letschaton.R;
import com.example.iverson.letschaton.data.CurrentUser;
import com.example.iverson.letschaton.data.Nodes;
import com.example.iverson.letschaton.models.Chat;
import com.example.iverson.letschaton.views.main.chats.ChatsFragment;
import com.google.firebase.database.ServerValue;

public class ChatActivity extends AppCompatActivity {

    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Chat chat = (Chat) getIntent().getSerializableExtra(ChatsFragment.CHAT);
        key = chat.getKey();


        new Nodes().userChat(new CurrentUser().uid()).child(key).child("timestamp").setValue(ServerValue.TIMESTAMP);

        getSupportActionBar().setTitle(chat.getReceiver());

    }


    @Override
    protected void onPause() {
        super.onPause();
        new Nodes().userChat(new CurrentUser().uid()).child(key).child("notification").setValue(false);
    }
}
