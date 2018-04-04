package club.clonechat.clonechat.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wonderkiln.camerakit.CameraKit;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.ActivityMainBinding;
import club.clonechat.clonechat.ui.authentication.AuthenticationActivity;
import club.clonechat.clonechat.ui.base.BaseActivity;
import club.clonechat.clonechat.ui.main.camera.imagePreview.ImagePreviewFragment;
import club.clonechat.clonechat.ui.main.friends.addFriend.AddFriendFragment;

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
    public void onResume() {
        super.onResume();
        mActivityMainBinding.camera.start();
    }

    @Override
    public void onPause() {
        mActivityMainBinding.camera.stop();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mActivityMainBinding.camera.setMethod(CameraKit.Constants.METHOD_STILL);
        setUp();
    }

    private void setUp() {
        bindPagerAdapter();
        observeLoggedIn();
        bindCameraListener();
        observeTakePhoto();
        observeUploadStart();
    }

    private void bindPagerAdapter() {
        mActivityMainBinding.mainViewpager.setAdapter(mPagerAdapter);
        mActivityMainBinding.mainViewpager.setCurrentItem(1);
        mActivityMainBinding.mainViewpager.setPageTransformer(true, mDepthPageTransformer);

    }

    private void bindCameraListener() {
        mActivityMainBinding.camera.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                Log.d("frag", "Image captured");
                mMainViewModel.takePhotoEventCaught();

                Log.d("frag", String.valueOf(cameraKitImage.getJpeg().length));
                mMainViewModel.setPhotoBytes(cameraKitImage.getJpeg());
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
    }

    private void observeLoggedIn() {
        mMainViewModel.getIsLoggedIn().observe(this, data -> {
            if (!data) {
                openAuthenticationActivity();
            }
        });
    }

    private void observeTakePhoto() {
        mMainViewModel.getTakePhoto().observe(this, data -> {
            Log.d("frag", String.valueOf(data));
            if (data) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_overlay, ImagePreviewFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                mActivityMainBinding.camera.captureImage();
            }
        });
    }

    private void observeUploadStart() {
        mMainViewModel.getUploadStart().observe(this, data -> {
            Log.d("frag", "her");
            if (data) {
                Log.d("frag", "was true");
                mMainViewModel.setUploadStartFalse();
                getSupportFragmentManager().popBackStackImmediate();
                getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }

    @Override
    public void openAuthenticationActivity() {
        startActivity(new Intent(this, AuthenticationActivity.class));
        finishAffinity();
    }
}
