package com.rename.rename1.letschaton.views.main.finder;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.rename.rename1.letschaton.data.CurrentUser;
import com.rename.rename1.letschaton.data.EmailProcessor;
import com.rename.rename1.letschaton.data.Nodes;
import com.rename.rename1.letschaton.data.PhotoPreference;
import com.rename.rename1.letschaton.models.Chat;
import com.rename.rename1.letschaton.models.User;

public class UserValidation {

    private FinderCallback callback;
    private Context context;

    public UserValidation(FinderCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void init(String email){
        if (email.trim().length()>0){
            if (email.contains("@")){
                String currentEmail = new CurrentUser().email();
                if (!email.equals(currentEmail)){
                    findUser(email);
                }else{
                    callback.error("¿Charla personal?");

                }
            }else{
                callback.error("Email mal escrito");
            }
        }else {
            callback.error("Se necesita Email");

        }

    }

    private void findUser(String email){

        new Nodes().user(new EmailProcessor().sanitizedEmail(email)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User otherUser = dataSnapshot.getValue(User.class);
                if (otherUser !=null){
                    createChats(otherUser);
                }else {
                    callback.notFound();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void createChats(User otherUser){
        FirebaseUser currentUser = new CurrentUser().getCurrentUser();
        String photo = new PhotoPreference(context).getPhoto();

        String key = new EmailProcessor().keyEmails(otherUser.getEmail());

        Chat currentChat = new Chat();
        currentChat.setKey(key);
        currentChat.setPhoto(otherUser.getPhoto());
        currentChat.setNotification(true);
        currentChat.setReceiver(otherUser.getEmail());
        currentChat.setUid(otherUser.getUid());

        Chat otherChat = new Chat();
        otherChat.setKey(key);
        otherChat.setPhoto(photo);
        otherChat.setNotification(true);
        otherChat.setReceiver(currentUser.getEmail());
        otherChat.setUid(currentUser.getUid());

        new Nodes().userChat(currentUser.getUid()).child(key).setValue(currentChat);
        new Nodes().userChat(otherUser.getUid()).child(key).setValue(otherChat);

        callback.success();

    }



}
