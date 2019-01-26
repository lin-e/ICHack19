package com.ichack.educate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "ICHACK";
  private SignInButton googleSignInButton;
  private GoogleSignInClient googleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    googleSignInButton = findViewById(R.id.sign_in_button);
    GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(
                "258775497785-p455232de9ot6asva9dh9k1o03mte93f.apps.googleusercontent.com")
            .build();
    googleSignInClient = GoogleSignIn.getClient(this, gso);

    googleSignInButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent signInIntent = googleSignInClient.getSignInIntent();

            startActivityForResult(signInIntent, 101);
          }
        });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
      switch (requestCode) {
        case 101:
          try {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = task.getResult(ApiException.class);

            onLoggedIn(account);
          } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
          }

          break;
      }
    }
  }

  private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
    Intent intent = new Intent(this, HomeActivity.class);

    intent.putExtra(HomeActivity.GOOGLE_ACCOUNT, googleSignInAccount);
    startActivity(intent);
    finish();
  }

  @Override
  public void onStart() {
    super.onStart();

    GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);

    if (lastSignedInAccount != null) {
      onLoggedIn(lastSignedInAccount);
    } else {
      Log.d(TAG, "Not logged in");
    }
  }
}
