package com.example.iverson.letschaton.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iverson.letschaton.R;
import com.example.iverson.letschaton.data.CurrentUser;
import com.example.iverson.letschaton.models.Message;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MessageAdapter extends FirebaseRecyclerAdapter<Message, MessageAdapter.MessageViewHolder> {

    private MessageCallback messageCallback;
    private static final String CURRENT_EMAIL = new CurrentUser().email();

    public MessageAdapter(@NonNull FirebaseRecyclerOptions<Message> options, MessageCallback messageCallback) {
        super(options);
        this.messageCallback = messageCallback;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Message model) {
//        holder.messageTextView.setText(model.getContent());
        if (CURRENT_EMAIL.equals(model.getOwner())){
            holder.messageTextView.setGravity(Gravity.LEFT);
            holder.messageTextView.setText(model.getContent());
        } else {
            holder.messageTextView.setGravity(Gravity.RIGHT);
            holder.messageTextView.setText(model.getContent());
        }
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        messageCallback.update();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{

        private TextView messageTextView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}
