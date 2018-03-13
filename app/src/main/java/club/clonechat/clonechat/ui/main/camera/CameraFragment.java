package club.clonechat.clonechat.ui.main.camera;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonderkiln.camerakit.CameraView;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentCameraBinding;
import club.clonechat.clonechat.ui.authentication.login.LoginFragment;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends BaseFragment<FragmentCameraBinding, CameraViewModel>{

    @Inject
    @Named("CameraFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private CameraViewModel mCameraViewModel;

    public static CameraFragment newInstance() {
        Bundle args = new Bundle();
        CameraFragment fragment = new CameraFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_camera;
    }

    @Override
    public CameraViewModel getViewModel() {
        mCameraViewModel = ViewModelProviders.of(getBaseActivity(), mViewModelFactory).get(CameraViewModel.class);
        return mCameraViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(1);
//        setUp();
    }
}
