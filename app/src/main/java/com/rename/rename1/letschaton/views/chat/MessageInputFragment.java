package com.rename.rename1.letschaton.views.chat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.rename.rename1.letschaton.R;
import com.rename.rename1.letschaton.models.Chat;
import com.rename.rename1.letschaton.views.main.chats.ChatsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageInputFragment extends Fragment {


    public MessageInputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Chat chat = (Chat) getActivity().getIntent().getSerializableExtra(ChatsFragment.CHAT);

        final EditText editText = view.findViewById(R.id.userInputEt);
        editText.setHint("Escribe un mensaje...");

        ImageButton btn = view.findViewById(R.id.sendBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                new SendMessage().ValidateMessage(message, chat);
                editText.setText("");
            }
        });
    }
}

