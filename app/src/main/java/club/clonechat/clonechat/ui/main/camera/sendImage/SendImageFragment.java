package club.clonechat.clonechat.ui.main.camera.sendImage;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentSendImageBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendImageFragment extends BaseFragment<FragmentSendImageBinding, SendImageViewModel> {

    @Inject
    @Named("SendImageFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private SendImageViewModel mSendImageViewModel;
    private FragmentSendImageBinding mFragmentSendImageBinding;
    @Inject
    @Named("SendImageFragment")
    SendFriendAdapter mSendFriendAdapter;
    private LinearLayoutManager mLayoutManager;

    public static SendImageFragment newInstance() {
        Bundle args = new Bundle();
        SendImageFragment fragment = new SendImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_send_image;
    }

    @Override
    public SendImageViewModel getViewModel() {
        mSendImageViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SendImageViewModel.class);
        return mSendImageViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentSendImageBinding = getViewDataBinding();
        mLayoutManager = new LinearLayoutManager(getBaseActivity());
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentSendImageBinding.sendFriendRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentSendImageBinding.sendFriendRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentSendImageBinding.sendFriendRecyclerView.setAdapter(mSendFriendAdapter);
        observeFriendList();
    }

    private void observeFriendList() {
        mSendImageViewModel.getFriendlist().observe(this, f -> {
            mSendFriendAdapter.newItems(f);
        });
    }

}
