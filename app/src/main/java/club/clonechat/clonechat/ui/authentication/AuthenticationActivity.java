package club.clonechat.clonechat.ui.authentication;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.databinding.ActivityAuthenticationBinding;
import club.clonechat.clonechat.ui.authentication.login.LoginFragment;
import club.clonechat.clonechat.ui.authentication.welcome.WelcomeFragment;
import club.clonechat.clonechat.ui.base.BaseActivity;
import club.clonechat.clonechat.ui.main.MainActivity;

public class AuthenticationActivity extends BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel> implements AuthenticationNavigator {

    @Inject
    @Named("AuthenticationActivity")
    ViewModelProvider.Factory mViewModelFactory;
    private AuthenticationViewModel mAuthenticationViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public AuthenticationViewModel getViewModel() {
        mAuthenticationViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthenticationViewModel.class);
        return mAuthenticationViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        showWelcomeFragment();
        observeLoggedIn();
        observeRegisterSuccess();
    }

    private void showWelcomeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.auth_container, WelcomeFragment.newInstance())
                .commit();
    }

    private void observeLoggedIn() {
        mAuthenticationViewModel.getIsLoggedIn().observe(this, data -> {
            if (data) {
                openMainActivity();
            }
        });
    }

    private void observeRegisterSuccess() {
        mAuthenticationViewModel.getRegisterSuccess().observe(this, data -> {
            if (data) {
                goToLogin();
            }
        });
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    @Override
    public void goToLogin() {
        getSupportFragmentManager()
                .popBackStack();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.auth_container, LoginFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
