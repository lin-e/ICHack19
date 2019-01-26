package com.ichack.educate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class HomeActivity extends AppCompatActivity {

    static GoogleSignInAccount GOOGLE_ACCOUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}
