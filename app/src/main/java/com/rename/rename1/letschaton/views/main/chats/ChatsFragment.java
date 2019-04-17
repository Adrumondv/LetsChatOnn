package com.rename.rename1.letschaton.views.main.chats;


import android.content.Intent;
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
import com.rename.rename1.letschaton.adapters.ChatsAdapter;
import com.rename.rename1.letschaton.adapters.ChatsListener;
import com.rename.rename1.letschaton.data.CurrentUser;
import com.rename.rename1.letschaton.data.Nodes;
import com.rename.rename1.letschaton.models.Chat;
import com.rename.rename1.letschaton.views.chat.ChatActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment implements ChatsListener {



    private RecyclerView chatsRecyclerView;
    public static final String CHAT = "com.rename.rename1.letschaton.views.main.chats.KEY.CHAT";

    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chatsRecyclerView = view.findViewById(R.id.chatsRecyclerView);
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatsRecyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>()
                .setQuery(new Nodes().userChat(new CurrentUser().uid()), Chat.class)
                .setLifecycleOwner(this)
                .build();

        ChatsAdapter chatsAdapter = new ChatsAdapter(options, this);
        chatsRecyclerView.setAdapter(chatsAdapter);

    }

    @Override
    public void chatClicked(Chat chat) {
        Intent goToDetail = new Intent(getActivity(), ChatActivity.class);
        goToDetail.putExtra(CHAT, chat);
        startActivity(goToDetail);

    }

}
