package com.example.weatherapp.Music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.R;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SongActivity extends AppCompatActivity {
    //https://developer.spotify.com/dashboard -> goto this url and create web app .on login we get client id and credentials
    private static final String CLIENT_ID = "105bbda722f64d88b907c0d73762c50c";
    private static final String REDIRECT_URI = "com.example.weatherapp.Music://callback";
    private static final int REQUEST_CODE = 1337;
    private static final String SCOPES = "user-read-recently-played,user-library-modify,user-read-email,user-read-private";
SharedPreferences msharedPreferences;
    SharedPreferences.Editor   editor;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        authenticateSpotify();
        msharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        queue = Volley.newRequestQueue(this);
    }
    private void authenticateSpotify() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{SCOPES});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    private void waitForUserInfo() {
        UserService userService = new UserService(queue, msharedPreferences);
        userService.get(() -> {
            User user = userService.getUser();
//            SharedPreferences sh=getApplicationContext().getSharedPreferences("SPOTIFY", Context.MODE_PRIVATE);
          editor = getSharedPreferences("SPOTIFY", 0).edit();
            editor.putString("userid", user.id);
            Log.d("STARTING", "GOT USER INFORMATION");
            // We use commit instead of apply because we need the information stored immediately
            editor.commit();
            startMainActivity();
        });
    }

    private void startMainActivity() {
        Intent newintent = new Intent(SongActivity.this, SongActivity2.class);
        startActivity(newintent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    editor = getSharedPreferences("SPOTIFY", 0).edit();
                    editor.putString("token", response.getAccessToken());
                    Log.d("STARTING", "GOT AUTH TOKEN");
                    editor.apply();
                    waitForUserInfo();
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
}