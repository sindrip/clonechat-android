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

public class RegisterActivity extends AppCompatActivity {

    private Button mButtonRegister;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordRepeat;
    private AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuthService = RetrofitInstance.getRetrofitInstance(getApplicationContext()).create(AuthService.class);

        mUsername = findViewById(R.id.edittext_register_username);
        mPassword = findViewById(R.id.edittext_register_password);
        mPasswordRepeat = findViewById(R.id.edittext_register_password_confirm);

        mButtonRegister = (Button) findViewById(R.id.button_register);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
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

                if (!mPassword.getText().toString().equals(mPasswordRepeat.getText().toString())) {
                    mPasswordRepeat.setError(getString(R.string.auth_error_password_not_match));
                    return;
                }

                mButtonRegister.setEnabled(false);
                mButtonRegister.setText(getString(R.string.networking_working));
                mButtonRegister.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonDisabled));

                AuthUser registeruser = new AuthUser(mUsername.getText().toString(), mPassword.getText().toString());
                Call call = mAuthService.register(registeruser);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        } else{
                            Toast.makeText(getApplicationContext(), "Error In Registration", Toast.LENGTH_SHORT).show();
                            mButtonRegister.setEnabled(true);
                            mButtonRegister.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonBackground));
                            mButtonRegister.setText(R.string.action_register);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                        mButtonRegister.setEnabled(true);
                        mButtonRegister.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonBackground));
                        mButtonRegister.setText(R.string.action_register);
                    }
                });
            }
        });
    }
}
