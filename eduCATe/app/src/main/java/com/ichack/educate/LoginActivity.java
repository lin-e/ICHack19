package com.ichack.educate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(
                "258775497785-p455232de9ot6asva9dh9k1o03mte93f.apps.googleusercontent.com")
            .build();
    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    // Button gSignInBtn = new Button(R.id.login_googleSignInBtn);
  }
}
