package club.clonechat.clonechat.ui.main.friends.addFriend;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import club.clonechat.clonechat.BR;
import club.clonechat.clonechat.R;
import club.clonechat.clonechat.databinding.FragmentAddFriendBinding;
import club.clonechat.clonechat.ui.base.BaseFragment;
import club.clonechat.clonechat.ui.main.friends.FriendAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends BaseFragment<FragmentAddFriendBinding, AddFriendViewModel> {

    @Inject
    @Named("AddFriendFragment")
    ViewModelProvider.Factory mViewModelFactory;
    private AddFriendViewModel mAddFriendViewModel;
    private FragmentAddFriendBinding mFragmentAddFriendBinding;
    // Need to change this a bit probably
    @Inject
    @Named("AddFriendFragment")
    FriendAdapter mFriendAdapter;
    private LinearLayoutManager mLayoutManager;

    public static AddFriendFragment newInstance() {
        Bundle args = new Bundle();
        AddFriendFragment fragment = new AddFriendFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_friend;
    }

    @Override
    public AddFriendViewModel getViewModel() {
        mAddFriendViewModel = ViewModelProviders.of(getBaseActivity(), mViewModelFactory).get(AddFriendViewModel.class);
        return mAddFriendViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAddFriendBinding = getViewDataBinding();
        mLayoutManager = new LinearLayoutManager(getBaseActivity());
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentAddFriendBinding.addFriendRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentAddFriendBinding.addFriendRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentAddFriendBinding.addFriendRecyclerView.setAdapter(mFriendAdapter);
        observeSearchList();
        searchQueryListener();
    }

    private void searchQueryListener() {
        mFragmentAddFriendBinding.addFriendSearchView
                .setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
                   @Override
                   public boolean onQueryTextSubmit(String query) {
                       return false;
                   }

                   @Override
                    public boolean onQueryTextChange(String query) {
                       getViewModel().refreshSearchList(query);
                       return false;
                   }
                });
    }

   private void observeSearchList() {
        mAddFriendViewModel.getSearchList().observe(this, u -> {
            Log.d("frag", String.valueOf(u.size()));
            mFriendAdapter.newItems(u);
        });
   }

}
