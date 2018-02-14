package com.example.stell.clonechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashScreen extends AppCompatActivity {

    private OkHttpClient client;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseUrl = getString(R.string.APIURL);

        client = new OkHttpClient.Builder()
                .cookieJar(new WebviewCookieHandler())
                .build();

        Call testlogincall = client.newCall(testloginRequest());
        testlogincall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                SplashScreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
                    finish();
                    }
                });
                System.out.println(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean success = response.isSuccessful();
                final String test = response.toString();
                SplashScreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("here");
                        System.out.println(test);
                        System.out.println(success);
                        System.out.println("below here");
                        if (success) {
                            startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
                        }
                        finish();
                    }
                });
            }
        });
    }

    public Request testloginRequest() {
        System.out.println(baseUrl + "/testlogin");
        return new Request.Builder()
                .url(baseUrl + "/testlogin")
                .get()
                .build();
    }
}
