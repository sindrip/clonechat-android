package club.clonechat.clonechat.ui.authentication.register;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Button;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {

    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> passwordConfirm = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private LiveData<NetworkResource<Boolean>> registerResource;

    public RegisterViewModel(DataManager dataManager) {
        super(dataManager);
        this.registerResource = getDataManager().getRegisterResource();
    }


    public void register() {
        // =====================================================
        // ===Implement validation for registration data here===
        // =====================================================
        getDataManager().register(username.getValue(), password.getValue());
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

    public MutableLiveData<String> getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(MutableLiveData<String> passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<NetworkResource<Boolean>> getRegisterResource() {
        return registerResource;
    }


}
