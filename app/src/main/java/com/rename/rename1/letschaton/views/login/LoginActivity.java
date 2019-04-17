package com.rename.rename1.letschaton.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.firebase.ui.auth.AuthUI;
import com.rename.rename1.letschaton.BuildConfig;
import com.rename.rename1.letschaton.R;
import com.rename.rename1.letschaton.data.CurrentUser;
import com.rename.rename1.letschaton.views.main.MainActivity;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_login);

        if (new CurrentUser().getCurrentUser() !=null){
            logged();
        }else {
            signUp();
        }


    }

    private void signUp(){

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build()
                        ))
                        .setTheme(R.style.LoginTheme)
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode){
            if (resultCode == RESULT_OK){
                logged();
            } else{
                signUp();
            }
        }
    }

    private void logged(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
