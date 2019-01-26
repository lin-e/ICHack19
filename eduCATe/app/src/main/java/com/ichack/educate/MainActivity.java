package com.ichack.educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    Intent changeActivity;
    changeActivity = new Intent(MainActivity.this, HomeActivity.class);
    /*
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

    if (account != null) {
      changeActivity = new Intent(this, HomeActivity.class);
    } else {
      changeActivity = new Intent(this, LoginActivity.class);
    }
    */
    startActivity(changeActivity);

  }
}
