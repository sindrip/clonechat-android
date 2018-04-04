package club.clonechat.clonechat.ui.main.camera.sendImage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.ui.main.friends.FriendItemViewModel;

public class SendFriendItemViewModel {

    private final MutableLiveData<User> user = new MutableLiveData<>();

    private boolean isSent;

    private final SendFriendItemViewModel.SendFriendItemViewModelListener mListener;

    public SendFriendItemViewModel(User user, boolean isSent, SendFriendItemViewModelListener listener) {
        this.user.setValue(user);
        this.isSent = isSent;
        this.mListener = listener;

        Log.d("frag", "New model here!!!!");
    }

    public LiveData<User> getUser() {
        return user;
    }

    public boolean getIsSent() {return isSent; }

    public void onSendButtonClick() {
        mListener.onSendButtonClick(user.getValue());
    }

    public void onUnsendButtonClick() {
        mListener.onUnsendButtonClick(user.getValue());
    }

    public void onSendImageButtonClick() {
        mListener.onSendImageButtonClick(user.getValue());
    }

    public interface SendFriendItemViewModelListener {

        void onSendButtonClick(User user);
        void onUnsendButtonClick(User user);
        void onSendImageButtonClick(User user);
    }

}
