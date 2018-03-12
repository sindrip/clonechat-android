package club.clonechat.clonechat.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.ActivitySplashBinding;
import club.clonechat.clonechat.ui.authentication.AuthenticationActivity;
import club.clonechat.clonechat.ui.base.BaseActivity;
import club.clonechat.clonechat.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeIsLoggedIn();
        mSplashViewModel.testLogin();
    }

    private void observeIsLoggedIn() {
        mSplashViewModel.getIsLoggedIn().observe(this, data -> {
            if (data) {
                openMainActivity();
            } else {
                openAuthenticationActivity();
            }
        });
    }

    @Override
    public void openAuthenticationActivity() {
        startActivity(new Intent(this, AuthenticationActivity.class));
        finishAffinity();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}
