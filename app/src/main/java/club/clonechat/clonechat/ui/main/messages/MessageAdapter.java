package club.clonechat.clonechat.ui.main.messages;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import club.clonechat.clonechat.BuildConfig;
import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.Message;
import club.clonechat.clonechat.databinding.ItemMessageViewBinding;
import club.clonechat.clonechat.ui.base.BaseViewHolder;

public class MessageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Message> mMessageList;
    private DataManager mDataManager;

    public MessageAdapter(DataManager dataManager, List<Message> messageList) {
        this.mMessageList = messageList;
        this.mDataManager = dataManager;
    }

    @Override
    public int getItemCount() {
        if (mMessageList == null) {
            return 0;
        }
        return mMessageList.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMessageViewBinding messageViewBinding = ItemMessageViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MessageAdapter.MessageViewHolder(messageViewBinding);
    }

    public void newItems(List<Message> messageList) {
        mMessageList.clear();
        mMessageList.addAll(messageList);
        notifyDataSetChanged();
    }

    public class MessageViewHolder extends BaseViewHolder implements MessageItemViewModel.MessageItemViewModelListener {
        private ItemMessageViewBinding mBinding;
        private MessageItemViewModel mMessageItemViewModel;

        public MessageViewHolder(ItemMessageViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Message message = mMessageList.get(position);
            mMessageItemViewModel = new MessageItemViewModel(message, this);
            mBinding.setViewModel(mMessageItemViewModel);
            mBinding.timestamp.setReferenceTime(mMessageItemViewModel.getMessage().getValue().getCreatedAt());
            mBinding.executePendingBindings();
        }

        @Override
        public void onButtonClick(String id) {
            mDataManager.setImageURL(BuildConfig.APIURL + "/images/" + id);
        }
    }


}
