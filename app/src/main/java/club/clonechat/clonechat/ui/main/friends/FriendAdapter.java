package club.clonechat.clonechat.ui.main.friends;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.databinding.ItemFriendViewBinding;
import club.clonechat.clonechat.ui.base.BaseViewHolder;

public class FriendAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<User> mFriendList;
    private boolean isFriend;
    private DataManager mDataManager;

    public FriendAdapter(DataManager dataManager, List<User> friendList, boolean isFriend) {
        this.mFriendList = friendList;
        this.isFriend = isFriend;
        this.mDataManager = dataManager;
    }

    @Override
    public int getItemCount() {
        if (mFriendList == null) {
            return 0;
        }
        return mFriendList.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFriendViewBinding friendViewBinding = ItemFriendViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new FriendViewHolder(friendViewBinding);
    }

    public void newItems(List<User> friendList) {
        mFriendList.clear();
        mFriendList.addAll(friendList);
        notifyDataSetChanged();
    }

    public class FriendViewHolder extends BaseViewHolder implements FriendItemViewModel.FriendItemViewModelListener {

        private ItemFriendViewBinding mBinding;
        private FriendItemViewModel mFriendItemViewModel;

        public FriendViewHolder(ItemFriendViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final User user = mFriendList.get(position);
            mFriendItemViewModel = new FriendItemViewModel(user, isFriend, this);
            mBinding.setViewModel(mFriendItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onButtonClick(String username) {
            if (username != null) {
                Log.d("frag", username);
                if (isFriend) {
                    Log.d("frag", "friend " + username);
                    mDataManager.deleteFriend(username);
                } else {
                    Log.d("frag", "nofriend " + username);
                    mDataManager.addFriend(username);
                }
            }
        }
    }
}
