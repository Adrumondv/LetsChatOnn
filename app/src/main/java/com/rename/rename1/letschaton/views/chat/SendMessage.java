package com.rename.rename1.letschaton.views.chat;

import com.rename.rename1.letschaton.data.CurrentUser;
import com.rename.rename1.letschaton.data.Nodes;
import com.rename.rename1.letschaton.models.Chat;
import com.rename.rename1.letschaton.models.Message;

public class SendMessage {

    public void ValidateMessage(String message, Chat chat){
        if (message.trim().length()>0){
            String email = new CurrentUser().email();
            Message msg = new Message();
            msg.setContent(message);
            msg.setOwner(email);

            new Nodes().messages(chat.getKey()).push().setValue(msg);
            new Nodes().userChat(chat.getUid()).child(chat.getKey()).child("notification").setValue(true);

        }

    }

}
