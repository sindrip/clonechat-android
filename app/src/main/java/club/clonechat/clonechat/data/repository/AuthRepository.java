package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.data.api.model.AuthUser;
import club.clonechat.clonechat.data.api.retrofit.AuthService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthService mAuthService;

    // Status of the network resource
    private final MutableLiveData<NetworkResource<Boolean>> loginResource;
    private final MutableLiveData<Boolean> isLoggedIn;

    // Status of the network resource
    private final MutableLiveData<NetworkResource<Boolean>> registerResource;
    private final MutableLiveData<Boolean> registerSuccess;

    public AuthRepository(AuthService authService) {
        this.mAuthService = authService;

        loginResource = new MutableLiveData<>();
        isLoggedIn = new MutableLiveData<>();
        registerSuccess = new MutableLiveData<>();
        registerResource = new MutableLiveData<>();
    }

    public void login(String username, String password) {
        loginResource.postValue(NetworkResource.loading(null));

        AuthUser au = new AuthUser(username, password);
        mAuthService.login(au).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    loginResource.setValue(NetworkResource.success(true));
                    isLoggedIn.setValue(true);
                } else {
                    loginResource.setValue(NetworkResource.success(false));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                loginResource.setValue(NetworkResource.error(t.getMessage(), null));
            }
        });
    }

    public LiveData<NetworkResource<Boolean>> getLoginResource() {
        return loginResource;
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public void register(String username, String password) {
        registerResource.postValue(NetworkResource.loading(null));

        AuthUser au = new AuthUser(username, password);
        mAuthService.register(au).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    registerResource.setValue(NetworkResource.success(true));
                    registerSuccess.setValue(true);
                } else {
                    registerResource.setValue(NetworkResource.success(false));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                loginResource.setValue(NetworkResource.error(t.getMessage(), null));
            }
        });
    }

    public LiveData<NetworkResource<Boolean>> getRegisterResource() {
        return registerResource;
    }

    public LiveData<Boolean> getRegisterSuccess() {
        return registerSuccess;
    }

    public void testLogin() {
        mAuthService.testLogin().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    isLoggedIn.setValue(true);
                    Log.d("frag", "logged in");
                } else {
                    isLoggedIn.setValue(false);
                    Log.d("frag", "not logged in");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                isLoggedIn.setValue(false);
                Log.d("frag", "some failure");
            }
        });
    }

    public void logout() {
        mAuthService.logout().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    isLoggedIn.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
