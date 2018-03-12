package club.clonechat.clonechat.ui.authentication.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import club.clonechat.clonechat.data.DataManager;

import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private LiveData<NetworkResource<Boolean>> loginResource;

    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
        this.loginResource = getDataManager().getLoginResource();
    }

    public void login() {
        getDataManager().login(username.getValue(), password.getValue());
    }

    public void testLogin() {
        getDataManager().testLogin();
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(MutableLiveData<String> username) {
        this.username = username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<NetworkResource<Boolean>> getLoginResource() {
        return loginResource;
    }
}
