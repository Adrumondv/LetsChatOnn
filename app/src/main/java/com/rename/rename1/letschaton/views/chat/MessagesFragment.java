package com.rename.rename1.letschaton.views.chat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.rename.rename1.letschaton.R;
import com.rename.rename1.letschaton.adapters.MessageAdapter;
import com.rename.rename1.letschaton.adapters.MessageCallback;
import com.rename.rename1.letschaton.data.Nodes;
import com.rename.rename1.letschaton.models.Chat;
import com.rename.rename1.letschaton.models.Message;
import com.rename.rename1.letschaton.views.main.chats.ChatsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment implements MessageCallback {

    private MessageAdapter messagesAdapter;
    private RecyclerView messageRecyclerView;


    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Chat chat = (Chat) getActivity().getIntent().getSerializableExtra(ChatsFragment.CHAT);

        messageRecyclerView = view.findViewById(R.id.messageRv);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageRecyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Message> options = new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(new Nodes().messages(chat.getKey()), Message.class)
                .setLifecycleOwner(this)
                .build();

        messagesAdapter = new MessageAdapter(options, this);
        messageRecyclerView.setAdapter(messagesAdapter);

    }

    @Override
    public void update() {
        messageRecyclerView.scrollToPosition(messagesAdapter.getItemCount() -1);
    }

    @Override
    public void onStop() {
        super.onStop();
        messagesAdapter.stopListening();
    }
}
