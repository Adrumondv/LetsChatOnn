package com.example.iverson.letschaton.views.chat;

import com.example.iverson.letschaton.data.CurrentUser;
import com.example.iverson.letschaton.data.Nodes;
import com.example.iverson.letschaton.models.Chat;
import com.example.iverson.letschaton.models.Message;

public class SendMessage {

    public void ValidateMessage(String message, Chat chat){
        if (message.trim().length()>0){
            String email = new CurrentUser().email();
            Message msg = new Message();
            msg.setContent(message);
            msg.setOwner(email);

            new Nodes().messages(chat.getKey()).push().setValue(msg);
            new Nodes().userChat(chat.getUid()).child("notification").setValue(true);

        }

    }

}
