package club.clonechat.clonechat.ui.authentication.login;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.databinding.FragmentLoginBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> {

    @Inject
    @Named("LoginFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private LoginViewModel mLoginViewModel;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(getBaseActivity(), mViewModelFactory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp();
    }

    private void setUp() {
        watchLoading();
    }

    private void watchLoading() {
        getViewModel().getLoginResource().observe(this, res -> {
            if (res.status == NetworkResource.Status.LOADING) {
                getViewModel().getLoading().setValue(true);
            } else {
                getViewModel().getLoading().setValue(false);
            }
        });
    }

}
