package club.clonechat.clonechat.ui.main.camera.sendImage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.ui.main.friends.FriendItemViewModel;

public class SendFriendItemViewModel {

    private final MutableLiveData<User> user = new MutableLiveData<>();

    private final SendFriendItemViewModel.SendFriendItemViewModelListener mListener;

    public SendFriendItemViewModel(User user, SendFriendItemViewModelListener listener) {
        this.user.setValue(user);
        this.mListener = listener;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void onButtonClick() {
        mListener.onButtonClick(user.getValue().getUsername());
    }

    public interface SendFriendItemViewModelListener {

        void onButtonClick(String username);
    }

}
