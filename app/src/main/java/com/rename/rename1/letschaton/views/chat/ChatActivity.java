package com.rename.rename1.letschaton.views.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.ServerValue;
import com.rename.rename1.letschaton.R;
import com.rename.rename1.letschaton.data.CurrentUser;
import com.rename.rename1.letschaton.data.Nodes;
import com.rename.rename1.letschaton.models.Chat;
import com.rename.rename1.letschaton.views.main.chats.ChatsFragment;

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
