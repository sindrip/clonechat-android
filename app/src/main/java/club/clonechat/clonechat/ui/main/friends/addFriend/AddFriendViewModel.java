package club.clonechat.clonechat.ui.main.friends.addFriend;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class AddFriendViewModel extends BaseViewModel {

    private MutableLiveData<String> searchString = new MutableLiveData<>();

    private final LiveData<List<User>> searchList;

    public AddFriendViewModel(DataManager dataManager) {
        super(dataManager);
        searchList = getDataManager().getSearchList();
    }

    public void refreshSearchList(String query) {
        getDataManager().refreshSearchList(query);
    }

    public LiveData<List<User>> getSearchList() {
        return searchList;
    }

    public MutableLiveData<String> getSearchString() {
        return searchString;
    }

    public void setSearchString(MutableLiveData<String> searchString) {
        this.searchString = searchString;
    }
}
