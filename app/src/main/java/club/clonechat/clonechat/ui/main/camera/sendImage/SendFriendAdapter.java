package club.clonechat.clonechat.ui.main.camera.sendImage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.databinding.ItemFriendViewBinding;
import club.clonechat.clonechat.databinding.ItemSendFriendViewBinding;
import club.clonechat.clonechat.ui.base.BaseViewHolder;
import club.clonechat.clonechat.ui.main.friends.FriendItemViewModel;

public class SendFriendAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<User> mFriendList;
    private boolean[] sentImageFriendList;
    private DataManager mDataManager;

    public SendFriendAdapter(DataManager dataManager, List<User> friendList) {
        this.mFriendList = friendList;
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
        ItemSendFriendViewBinding sendFriendViewBinding = ItemSendFriendViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new SendFriendViewHolder(sendFriendViewBinding);
    }

    public void newItems(List<User> friendList) {
        mFriendList.clear();
        mFriendList.addAll(friendList);
        sentImageFriendList = new boolean[mFriendList.size()];
        notifyDataSetChanged();
    }

    public class SendFriendViewHolder extends BaseViewHolder implements SendFriendItemViewModel.SendFriendItemViewModelListener {

        private ItemSendFriendViewBinding mBinding;
        private SendFriendItemViewModel mSendFriendItemViewModel;

        public SendFriendViewHolder(ItemSendFriendViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final User user = mFriendList.get(position);
            mSendFriendItemViewModel = new SendFriendItemViewModel(user, sentImageFriendList[position], this);
            mBinding.setViewModel(mSendFriendItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onSendButtonClick(User user) {
            if (user != null) {
                sentImageFriendList[mFriendList.indexOf(user)] = true;
                notifyDataSetChanged();
                //mDataManager.uploadPhoto(user.getUsername());
            }
        }

        @Override
        public void onUnsendButtonClick(User user) {
            if (user != null) {
                sentImageFriendList[mFriendList.indexOf(user)] = false;
                notifyDataSetChanged();
            }
        }

        @Override
        public void onSendImageButtonClick(User user) {
            if (user != null) {
                List<User> recipientUserList = new ArrayList<>();
                for (int i = 0; i < mFriendList.size(); i++) {
                    if (sentImageFriendList[i]) {
                        recipientUserList.add(mFriendList.get(i));
                    }
                }
                mDataManager.uploadPhoto(recipientUserList);
            }
        }


    }
}
