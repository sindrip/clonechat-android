package club.clonechat.clonechat.ui.main.messages;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentMessageBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment<FragmentMessageBinding, MessageViewModel> implements MessageNavigator {

    @Inject
    @Named("MessageFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private MessageViewModel mMessageViewModel;
    private FragmentMessageBinding mFragmentMessageBinding;
    @Inject
    @Named("MessageFragment")
    MessageAdapter mMessageAdapter;
    private LinearLayoutManager mLayoutManager;

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public MessageViewModel getViewModel() {
        mMessageViewModel = ViewModelProviders.of(getBaseActivity(), mViewModelFactory).get(MessageViewModel.class);
        return mMessageViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessageViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(0);
        mFragmentMessageBinding = getViewDataBinding();
        mLayoutManager = new LinearLayoutManager(getBaseActivity());
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentMessageBinding.messageRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentMessageBinding.messageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentMessageBinding.messageRecyclerView.setAdapter(mMessageAdapter);
        observeMessageList();
    }

    private void observeMessageList() {
        mMessageViewModel.getMessagelist().observe(this, m -> {
            mMessageAdapter.newItems(m);
            mFragmentMessageBinding.messageSwipeRefresh.setRefreshing(false);
        });
    }

    @Override
    public void showMessage() {
        // open message overlay
    }
}
