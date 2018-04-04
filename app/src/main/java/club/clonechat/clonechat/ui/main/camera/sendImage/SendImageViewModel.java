package club.clonechat.clonechat.ui.main.camera.sendImage;

import android.arch.lifecycle.LiveData;

import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class SendImageViewModel extends BaseViewModel {

    private final LiveData<List<User>> friendlist;

    public SendImageViewModel(DataManager dataManager) {
        super(dataManager);
        friendlist = getDataManager().getFriendList();
    }

    public LiveData<List<User>> getFriendlist() {
        return friendlist;
    }
}
