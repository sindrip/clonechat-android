package club.clonechat.clonechat.ui.main.friends;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.databinding.library.baseAdapters.BR;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentFriendBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;
import club.clonechat.clonechat.ui.main.camera.CameraFragment;
import club.clonechat.clonechat.ui.main.friends.addFriend.AddFriendFragment;
import club.clonechat.clonechat.ui.main.messages.MessageFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends BaseFragment<FragmentFriendBinding, FriendViewModel> implements FriendNavigator {

    @Inject
    @Named("FriendFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private FriendViewModel mFriendViewModel;
    private FragmentFriendBinding mFragmentFriendBinding;
    @Inject
    @Named("FriendFragment")
    FriendAdapter mFriendAdapter;
    private LinearLayoutManager mLayoutManager;


    public static FriendFragment newInstance() {
        Bundle args = new Bundle();
        FriendFragment fragment = new FriendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    public FriendViewModel getViewModel() {
        mFriendViewModel = ViewModelProviders.of(getBaseActivity(), mViewModelFactory).get(FriendViewModel.class);
        return mFriendViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFriendViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(2);
        mFragmentFriendBinding = getViewDataBinding();
        mLayoutManager = new LinearLayoutManager(getBaseActivity());
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentFriendBinding.friendRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentFriendBinding.friendRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentFriendBinding.friendRecyclerView.setAdapter(mFriendAdapter);
        observeFriendList();
    }

    private void observeFriendList() {
        mFriendViewModel.getFriendList().observe(this, f -> {
            Log.d("frag", String.valueOf(f.size()));
            mFriendAdapter.newItems(f);
            mFragmentFriendBinding.friendSwipeRefresh.setRefreshing(false);
        });
    }

    @Override
    public void openAddFriend() {
        Log.d("frag", "I am clickered");
        getBaseActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_overlay, AddFriendFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
