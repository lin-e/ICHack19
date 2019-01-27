package com.ichack.educate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "ICHACK";
  private SignInButton googleSignInButton;
  private GoogleSignInClient googleSignInClient;

  private RequestQueue requestQueue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    Log.d(TAG, "ughhhhhhh1234");

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
          } catch (Exception e) {
            e.printStackTrace();
          }

          break;
      }
    }
  }

  private void onLoggedIn(GoogleSignInAccount googleSignInAccount) throws JSONException{

    Log.d(TAG, "ughhhhhhh123");

    final Intent intent = new Intent(this, HomeActivity.class);
    intent.putExtra(String.valueOf(HomeActivity.GOOGLE_ACCOUNT), googleSignInAccount);
    startActivity(intent);
    finish();

  }

  @Override
  public void onStart() {
    super.onStart();

    GoogleSignIn.silentSignIn()
            .addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
              @Override
              public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                handleSignInResult(task);
              }

              private void handleSignInResult(Task<GoogleSignInAccount> task) {
              }
            });
    
    GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);

    if (lastSignedInAccount != null) {
      try {
        onLoggedIn(lastSignedInAccount);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    } else {
      Log.d(TAG, "Not logged in");
    }
  }



  public RequestQueue getRequestQueue() {
    if (requestQueue == null)
      requestQueue = Volley.newRequestQueue(getApplicationContext());
    return requestQueue;
  }

  public void addToRequestQueue(Request<JSONObject> request, String tag) {
    request.setTag(tag);
    getRequestQueue().add(request);
  }

  public void cancelAllRequests(String tag) {
    getRequestQueue().cancelAll(tag);
  }

}
