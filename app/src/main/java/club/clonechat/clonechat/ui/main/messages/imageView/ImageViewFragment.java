package club.clonechat.clonechat.ui.main.messages.imageView;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentImagePreviewBinding;
import club.clonechat.clonechat.databinding.FragmentImageViewBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewFragment extends BaseFragment<FragmentImageViewBinding, ImageViewViewModel> implements ImageViewNavigator {

    @Inject
    @Named("ImageViewFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private ImageViewViewModel mImageViewViewModel;
    private FragmentImageViewBinding mFragmentImageViewBinding;
    @Inject
    Picasso mPicasso;

    public static ImageViewFragment newInstance() {
        Bundle args = new Bundle();
        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_image_view;
    }

    @Override
    public ImageViewViewModel getViewModel() {
        mImageViewViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ImageViewViewModel.class);
        return mImageViewViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageViewViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentImageViewBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        ImageView iv = mFragmentImageViewBinding.imageview;
        Log.d("frag", "setup " + mImageViewViewModel.getImageURL());
        mPicasso.load(mImageViewViewModel.getImageURL()).into(iv, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                Log.d("frag", "success");
                mFragmentImageViewBinding.imageprogressbar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Log.d("frag", e.getMessage());
            }
        });
    }

    @Override
    public void goBack() {
        if (!mImageViewViewModel.getImageURL().equals("")) {
            getBaseActivity().getSupportFragmentManager()
                    .popBackStackImmediate();
            mImageViewViewModel.resetImageURL();
        }

    }
}
