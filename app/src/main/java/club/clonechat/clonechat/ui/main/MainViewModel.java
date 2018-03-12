package club.clonechat.clonechat.ui.main;

import android.arch.lifecycle.LiveData;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    private LiveData<Boolean> isLoggedIn;

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
        isLoggedIn = getDataManager().getIsLoggedIn();
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }
}
