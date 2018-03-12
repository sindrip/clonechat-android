package club.clonechat.clonechat.ui.main.friends;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class FriendViewModel extends BaseViewModel<FriendNavigator> {

    private final LiveData<List<User>> friendList;

    public FriendViewModel(DataManager dataManager) {
        super(dataManager);
        friendList = getDataManager().getFriendList();
    }

    public void refreshFriendList() {
        getDataManager().refreshFriendList();
    }

    public void openAddFriend() {
        getNavigator().openAddFriend();
    }

    public LiveData<List<User>> getFriendList() {
        return friendList;
    }
}
