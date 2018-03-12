package club.clonechat.clonechat.ui.authentication;

import android.arch.lifecycle.LiveData;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class AuthenticationViewModel extends BaseViewModel {

    private LiveData<Boolean> isLoggedIn;
    private LiveData<Boolean> registerSuccess;

    public AuthenticationViewModel(DataManager dataManager) {
        super(dataManager);
        this.isLoggedIn = getDataManager().getIsLoggedIn();
        this.registerSuccess = getDataManager().getRegisterSuccess();
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public LiveData<Boolean> getRegisterSuccess() {
        return registerSuccess;
    }
}
