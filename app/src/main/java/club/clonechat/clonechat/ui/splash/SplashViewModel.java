package club.clonechat.clonechat.ui.splash;

import android.arch.lifecycle.LiveData;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel {

    private LiveData<Boolean> isLoggedIn;


    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
        this.isLoggedIn = getDataManager().getIsLoggedIn();
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public void testLogin() {
        getDataManager().testLogin();
    }
}
