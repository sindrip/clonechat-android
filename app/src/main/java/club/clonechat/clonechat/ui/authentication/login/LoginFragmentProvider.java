package club.clonechat.clonechat.ui.authentication.login;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginFragmentProvider {

    @ContributesAndroidInjector(modules = LoginFragmentModule.class)
    abstract LoginFragment provideLoginFragmentFactory();
}
