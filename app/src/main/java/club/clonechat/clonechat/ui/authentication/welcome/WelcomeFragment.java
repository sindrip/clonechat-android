package club.clonechat.clonechat.ui.authentication.welcome;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentWelcomeBinding;
import club.clonechat.clonechat.ui.authentication.login.LoginFragment;
import club.clonechat.clonechat.ui.authentication.register.RegisterFragment;
import club.clonechat.clonechat.ui.base.BaseFragment;


public class WelcomeFragment extends BaseFragment<FragmentWelcomeBinding, WelcomeViewModel> implements WelcomeNavigator {

    @Inject WelcomeViewModel mWelcomeViewModel;

    public static WelcomeFragment newInstance() {
        Bundle args = new Bundle();
        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    public WelcomeViewModel getViewModel() {
        return mWelcomeViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelcomeViewModel.setNavigator(this);
    }

    @Override
    public void goToLogin() {
        Log.d("frag", "flot");
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.auth_container, LoginFragment.newInstance())
                .addToBackStack("login")
                .commit();
    }

    @Override
    public void goToRegister() {
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.auth_container, RegisterFragment.newInstance())
                .addToBackStack("register")
                .commit();
    }
}
