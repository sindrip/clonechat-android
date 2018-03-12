package club.clonechat.clonechat.ui.authentication.welcome;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class WelcomeFragmentProvider {

    @ContributesAndroidInjector(modules = WelcomeFragmentModule.class)
    abstract WelcomeFragment provideWelcomeFragmentFactory();
}
