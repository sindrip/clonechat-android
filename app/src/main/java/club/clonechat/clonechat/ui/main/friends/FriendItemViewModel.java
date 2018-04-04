package club.clonechat.clonechat.ui.main.friends;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class FriendItemViewModel {

    private final MutableLiveData<User> user = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isFriend = new MutableLiveData<>();

    private final FriendItemViewModelListener mListener;

    public FriendItemViewModel(User user, boolean isFriend, FriendItemViewModelListener listener) {
        this.user.setValue(user);
        this.isFriend.setValue(isFriend);
        this.mListener = listener;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<Boolean> getIsFriend() {
        return isFriend;
    }

    public void onButtonClick() {
        mListener.onButtonClick(user.getValue().getUsername());
    }

    public interface FriendItemViewModelListener {

        void onButtonClick(String username);
    }

}
