package club.clonechat.clonechat.ui.authentication.register;


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
import club.clonechat.clonechat.databinding.FragmentRegisterBinding;
import club.clonechat.clonechat.ui.authentication.login.LoginFragment;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment  extends BaseFragment<FragmentRegisterBinding, RegisterViewModel> {

    @Inject
    @Named("RegisterFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private RegisterViewModel mRegisterViewModel;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterViewModel = ViewModelProviders.of(getBaseActivity(), mViewModelFactory).get(RegisterViewModel.class);
        return mRegisterViewModel;
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
        mRegisterViewModel.getRegisterResource().observe(this, res -> {
            if (res.status == NetworkResource.Status.LOADING) {
                mRegisterViewModel.getLoading().setValue(true);
            } else {
                mRegisterViewModel.getLoading().setValue(false);
            }
        });
    }

}
