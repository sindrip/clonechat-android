package club.clonechat.clonechat_android.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import club.clonechat.clonechat_android.R;
import club.clonechat.clonechat_android.model.AuthUser;
import club.clonechat.clonechat_android.network.AuthService;
import club.clonechat.clonechat_android.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private EditText mUsername;
    private EditText mPassword;
    private AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuthService = RetrofitInstance.getRetrofitInstance(getApplicationContext()).create(AuthService.class);

        mUsername = findViewById(R.id.edittext_login_username);
        mPassword = findViewById(R.id.edittext_login_password);

        mButtonLogin = findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUsername.getText().toString().equals("")) {
                    mUsername.setError(getString(R.string.auth_error_username_empty));
                    return;
                }

                if (mPassword.getText().toString().equals("")) {
                    mPassword.setError(getString(R.string.auth_error_password_empty));
                    return;
                }

                mButtonLogin.setEnabled(false);
                mButtonLogin.setText(getString(R.string.networking_working));
                mButtonLogin.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonDisabled));

                AuthUser loginuser = new AuthUser(mUsername.getText().toString(), mPassword.getText().toString());
                Call call = mAuthService.login(loginuser);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finishAffinity();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                            mButtonLogin.setEnabled(true);
                            mButtonLogin.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonBackground));
                            mButtonLogin.setText(R.string.action_login);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        mButtonLogin.setEnabled(true);
                        mButtonLogin.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonBackground));
                        mButtonLogin.setText(R.string.action_login);
                    }
                });
            }
        });
    }
}
