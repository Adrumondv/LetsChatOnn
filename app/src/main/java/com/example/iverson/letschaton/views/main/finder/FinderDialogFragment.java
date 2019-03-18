package com.example.iverson.letschaton.views.main.finder;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.iverson.letschaton.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.database.annotations.Nullable;

public class FinderDialogFragment extends DialogFragment implements FinderCallback {

    private EditText editText;
    private ImageButton imageButton;
    private SpinKitView loading;

    public static FinderDialogFragment newInstance() {
        return new FinderDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_finder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.userInputEt);
        imageButton = view.findViewById(R.id.sendBtn);
        loading = view.findViewById(R.id.loadingSkv);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCancelable(false);
                editText.setError(null);
                String email = editText.getText().toString();
                editText.setVisibility(View.GONE);
                imageButton.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                new  UserValidation(FinderDialogFragment.this, getContext()).init(email);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }


    @Override
    public void error(String error) {
        restoredViews();
        editText.setError(error);

    }

    @Override
    public void success() {
        dismiss();

    }

    @Override
    public void notFound() {
        restoredViews();
        Toast.makeText(getContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();

    }

    private void restoredViews(){

        editText.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        setCancelable(true);

    }

}
