package club.clonechat.clonechat.ui.authentication.welcome;

import club.clonechat.clonechat.data.DataManager;
import dagger.Module;
import dagger.Provides;

@Module
public class WelcomeFragmentModule {

    @Provides
    WelcomeViewModel provideWelcomeViewModel(DataManager dataManager) {
        return new WelcomeViewModel(dataManager);
    }
}
