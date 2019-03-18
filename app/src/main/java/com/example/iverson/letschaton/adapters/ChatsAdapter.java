package com.example.iverson.letschaton.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iverson.letschaton.R;
import com.example.iverson.letschaton.models.Chat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.siyamed.shapeimageview.BubbleImageView;
import com.squareup.picasso.Picasso;

public class ChatsAdapter extends FirebaseRecyclerAdapter<Chat, ChatsAdapter.ChatsViewHolder>{

    private ChatsListener listener;

    public ChatsAdapter(@NonNull FirebaseRecyclerOptions<Chat> options, ChatsListener chatsListener) {
        super(options);
        this.listener = chatsListener;
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat, parent, false);
        return new ChatsViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ChatsViewHolder holder, int position, @NonNull Chat model) {
        Picasso.get().load(model.getPhoto()).fit().centerCrop().into(holder.photoBiv);
        holder.emailTv.setText(model.getReceiver());

        if (model.isNotification()){
            holder.notificationV.setVisibility(android.view.View.VISIBLE);
        } else {
            holder.notificationV.setVisibility(android.view.View.INVISIBLE);
        }

        holder.lyt_chat.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Chat auxChat = getItem(holder.getAdapterPosition());
                listener.chatClicked(auxChat);
            }
        });
    }

    public static class ChatsViewHolder extends RecyclerView.ViewHolder{

        private BubbleImageView photoBiv;
        private TextView emailTv;
        private android.view.View notificationV;
        private LinearLayout lyt_chat;

        public ChatsViewHolder(android.view.View itemView) {
            super(itemView);
            photoBiv = itemView.findViewById(R.id.photoBiv);
            emailTv = itemView.findViewById(R.id.emailTv);
            notificationV = itemView.findViewById(R.id.notificationV);
            lyt_chat = itemView.findViewById(R.id.lyt_chat);
        }
    }
}
