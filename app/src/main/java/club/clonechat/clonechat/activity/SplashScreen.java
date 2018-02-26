package club.clonechat.clonechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sindrip on 26.2.2018.
 */

public class SplashScreen extends AppCompatActivity {

    private AuthService mAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuthService = RetrofitInstance.getRetrofitInstance(getApplicationContext())
                .create(AuthService.class);

        Call<Void> call = mAuthService.testLogin();

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreen.this, AuthenticationActivity.class));
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                startActivity(new Intent(SplashScreen.this, AuthenticationActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

}
