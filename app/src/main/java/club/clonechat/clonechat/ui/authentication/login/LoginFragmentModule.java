package club.clonechat.clonechat.ui.authentication.login;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import club.clonechat.clonechat.data.DataManager;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginFragmentModule {

    @Provides
    LoginViewModel loginViewModel(DataManager dataManager) {
        return new LoginViewModel(dataManager);
    }

    @Provides
    @Named("LoginFragment")
    ViewModelProvider.Factory provideLoginViewModel(LoginViewModel loginViewModel) {
        return new ViewModelProviderFactory<>(loginViewModel);
    }
}
