package club.clonechat.clonechat.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button mButtonLogout;
    private Button mButtonTestLogin;
    private AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthService = RetrofitInstance.getRetrofitInstance(getApplicationContext()).create(AuthService.class);

        mButtonLogout = (Button) findViewById(R.id.button_sign_out);
        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mButtonLogout.setEnabled(false);
                mButtonLogout.setText(getString(R.string.networking_working));
                mButtonLogout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonDisabled));

                Call call = mAuthService.logout();

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, AuthenticationActivity.class));
                            finishAffinity();
                        } else {
                            Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                            mButtonLogout.setEnabled(true);
                            mButtonLogout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonBackground));
                            mButtonLogout.setText(R.string.action_sign_out);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                        mButtonLogout.setEnabled(true);
                        mButtonLogout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorButtonBackground));
                        mButtonLogout.setText(R.string.action_sign_out);
                    }
                });
            }
        });

        mButtonTestLogin = (Button) findViewById(R.id.button_test_login_check);
        mButtonTestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call call = mAuthService.testLogin();

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
