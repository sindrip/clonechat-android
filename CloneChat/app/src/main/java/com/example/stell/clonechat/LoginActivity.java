package com.example.stell.clonechat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private EditText mUsername;
    private EditText mPassword;
    private OkHttpClient client;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        baseUrl = getString(R.string.APIURL);

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        mUsername = findViewById(R.id.edittext_username);
        mPassword = findViewById(R.id.edittext_password);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mUsername.getText().toString().equals("")) {
                    mUsername.setError(getString(R.string.username_cant_be_empty));
                }

                if(mPassword.getText().toString().equals("")) {
                    mPassword.setError(getString(R.string.password_cant_be_empty));
                }

                mButtonLogin.setEnabled(false);
                mButtonLogin.setText(getString(R.string.working));
                mButtonLogin.setBackgroundColor(Color.parseColor("#BCB7E1"));

                Call logincall = client.newCall(loginRequest());
                logincall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            Toast.makeText(getApplicationContext(),
                                "Connection error",
                                Toast.LENGTH_SHORT).show();

                            mButtonLogin.setEnabled(true);
                            mButtonLogin.setBackgroundColor(Color.parseColor("#6960A9"));
                            mButtonLogin.setText(R.string.action_sign_in);
                            }
                    });
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final boolean success = response.isSuccessful();
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (success) {
                                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(myIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                                    mButtonLogin.setEnabled(true);
                                    mButtonLogin.setBackgroundColor(Color.parseColor("#6960A9"));
                                    mButtonLogin.setText(R.string.action_sign_in);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public Request loginRequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", mUsername.getText());
            jsonObject.put("password", mPassword.getText());
            System.out.println(mUsername.getText());
            System.out.println(mPassword.getText());
            System.out.println(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        System.out.println(baseUrl + "/login");
        return new Request.Builder()
                .url(baseUrl + "/login")
                .post(body)
                .build();
    }
}
