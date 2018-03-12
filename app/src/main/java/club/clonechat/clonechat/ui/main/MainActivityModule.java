package club.clonechat.clonechat.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import club.clonechat.clonechat.ui.authentication.AuthenticationViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainPagerAdapter provideMainPagerAdapter(MainActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    @Named("MainActivity")
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager) {
        return new MainViewModel(dataManager);
    }

    @Provides
    DepthPageTransformer provideDepthPageTransformer() {
        return new DepthPageTransformer();
    }
}