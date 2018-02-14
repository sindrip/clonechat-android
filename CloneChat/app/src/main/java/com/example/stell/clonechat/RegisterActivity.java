package com.example.stell.clonechat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    private Button mButtonRegister;
    private EditText mUsername;
    private EditText mPassword1;
    private EditText mPassword2;
    private OkHttpClient client;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        baseUrl = getString(R.string.APIURL);

        client = new OkHttpClient.Builder()
                .cookieJar(new WebviewCookieHandler())
                .build();

        mUsername = findViewById(R.id.edittext_username_register);
        mPassword1 = findViewById(R.id.edittext_password1);
        mPassword2 = findViewById(R.id.edittext_password2);

        mButtonRegister = (Button) findViewById(R.id.button_new_register);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mPassword1.getText().toString().equals(mPassword2.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords must match",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mButtonRegister.setEnabled(false);
                mButtonRegister.setText(getString(R.string.working));
                mButtonRegister.setBackgroundColor(Color.parseColor("#BCB7E1"));

                Call registercall = client.newCall(registerRequest());
                registercall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(getApplicationContext(),
                                "Connection error",
                                Toast.LENGTH_SHORT).show();
                                mButtonRegister.setEnabled(true);
                                mButtonRegister.setBackgroundColor(Color.parseColor("#6960A9"));
                                mButtonRegister.setText(R.string.action_sign_in);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final boolean success = response.isSuccessful();
                        System.out.println(response.isSuccessful());
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (success) {
                                    Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(myIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error In Registration", Toast.LENGTH_SHORT).show();
                                    mButtonRegister.setEnabled(true);
                                    mButtonRegister.setBackgroundColor(Color.parseColor("#6960A9"));
                                    mButtonRegister.setText(R.string.action_sign_in);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    public Request registerRequest() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", mUsername.getText());
            jsonObject.put("password", mPassword1.getText());
            System.out.println(mUsername.getText());
            System.out.println(mPassword1.getText());
            System.out.println(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        System.out.println(baseUrl + "/register");
        return new Request.Builder()
                .url(baseUrl + "/register")
                .post(body)
                .build();
    }
}
