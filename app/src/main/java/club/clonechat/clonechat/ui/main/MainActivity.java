package club.clonechat.clonechat.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.ActivityMainBinding;
import club.clonechat.clonechat.ui.authentication.AuthenticationActivity;
import club.clonechat.clonechat.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator{

    @Inject
    @Named("MainActivity")
    ViewModelProvider.Factory mViewModelFactory;
    @Inject
    MainPagerAdapter mPagerAdapter;
    @Inject
    DepthPageTransformer mDepthPageTransformer;
    private ActivityMainBinding mActivityMainBinding;
    private MainViewModel mMainViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        bindPagerAdapter();
        observeLoggedIn();
    }

    private void bindPagerAdapter() {
        mActivityMainBinding.mainViewpager.setAdapter(mPagerAdapter);
        mActivityMainBinding.mainViewpager.setCurrentItem(1);
        mActivityMainBinding.mainViewpager.setPageTransformer(true, mDepthPageTransformer);
    }

    private void observeLoggedIn() {
        mMainViewModel.getIsLoggedIn().observe(this, data -> {
            if (!data) {
                openAuthenticationActivity();
            }
        });
    }

    @Override
    public void openAuthenticationActivity() {
        startActivity(new Intent(this, AuthenticationActivity.class));
        finishAffinity();
    }
}
