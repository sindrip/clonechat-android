package com.example.stell.clonechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    private String baseUrl;
    private Button mLogoutButton;
    private Button mTestLoginCheckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseUrl = getString(R.string.APIURL);

        client = new OkHttpClient.Builder()
                .cookieJar(new WebviewCookieHandler())
                .build();
        mTestLoginCheckButton = (Button) findViewById(R.id.button_test_login_check);
        mTestLoginCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call testlogincall = client.newCall(testloginRequest());
                testlogincall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Network error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final boolean success = response.isSuccessful();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (success) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });

        mLogoutButton = (Button) findViewById(R.id.button_sign_out);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call logoutcall = client.newCall(logoutRequest());
                logoutcall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Network error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final boolean success = response.isSuccessful();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (success) {
                                    startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public Request logoutRequest() {
        System.out.println(baseUrl + "/logout");
        return new Request.Builder()
                .url(baseUrl + "/logout")
                .delete()
                .build();
    }

    public Request testloginRequest() {
        System.out.println(baseUrl + "/testlogin");
        return new Request.Builder()
                .url(baseUrl + "/testlogin")
                .get()
                .build();
    }
}
