package com.ichack.educate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    // Creating objects for the View
    Button gSignInBtn = findViewById(R.id.login_googleSignInBtn);
  }
}
