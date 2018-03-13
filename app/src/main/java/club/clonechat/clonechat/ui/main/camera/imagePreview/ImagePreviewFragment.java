package club.clonechat.clonechat.ui.main.camera.imagePreview;


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

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentImagePreviewBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagePreviewFragment extends BaseFragment<FragmentImagePreviewBinding, ImagePreviewViewModel> {

    @Inject
    @Named("ImagePreviewFragment")
    ViewModelProvider.Factory mViewMdoelFactory;
    private ImagePreviewViewModel mImagePreviewViewModel;
    private FragmentImagePreviewBinding mFragmentImagePreviewBinding;

    public static ImagePreviewFragment newInstance() {
        Bundle args = new Bundle();
        ImagePreviewFragment fragment = new ImagePreviewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_image_preview;
    }

    @Override
    public ImagePreviewViewModel getViewModel() {
        mImagePreviewViewModel = ViewModelProviders.of(this, mViewMdoelFactory).get(ImagePreviewViewModel.class);
        return mImagePreviewViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentImagePreviewBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void onDestroy() {
        mImagePreviewViewModel.deleteImage();
        super.onDestroy();
    }

    private void setUp() {
        observePhotoBytes();
    }

    private void observePhotoBytes() {
        mImagePreviewViewModel.getPhotoBytes().observe(this, (p) -> {
            if (p != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(p, 0, p.length);
                mFragmentImagePreviewBinding.imagepreview.setImageBitmap(bmp);
            }
        });
    }

}
