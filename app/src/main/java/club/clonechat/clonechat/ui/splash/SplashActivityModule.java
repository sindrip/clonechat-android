package club.clonechat.clonechat.ui.splash;

import club.clonechat.clonechat.data.DataManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager) {
        return new SplashViewModel(dataManager);
    }
}
