package com.example.stell.clonechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);

        Button mLoginButton = (Button) findViewById(R.id.button_sign_in);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(WelcomeScreen.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.button_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(WelcomeScreen.this, RegisterActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
