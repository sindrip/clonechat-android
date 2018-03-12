package club.clonechat.clonechat.ui.authentication;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class AuthenticationActivityModule {

    @Provides
    @Named("AuthenticationActivity")
    ViewModelProvider.Factory authenticationViewModelProvider(AuthenticationViewModel authenticationViewModel) {
        return new ViewModelProviderFactory<>(authenticationViewModel);
    }

    @Provides
    AuthenticationViewModel provideAuthenticationViewModel(DataManager dataManager) {
        return new AuthenticationViewModel(dataManager);
    }
}
