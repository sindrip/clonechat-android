package club.clonechat.clonechat.ui.authentication.register;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class RegisterFragmentModule {

    @Provides
    RegisterViewModel registerViewModel(DataManager dataManager) {
        return new RegisterViewModel(dataManager);
    }

    @Provides
    @Named("RegisterFragment")
    ViewModelProvider.Factory provideRegisterViewModel(RegisterViewModel registerViewModel) {
        return new ViewModelProviderFactory<>(registerViewModel);
    }
}