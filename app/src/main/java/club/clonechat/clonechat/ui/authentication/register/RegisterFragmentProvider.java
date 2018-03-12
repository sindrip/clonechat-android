package club.clonechat.clonechat.ui.authentication.register;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RegisterFragmentProvider {

    @ContributesAndroidInjector(modules = RegisterFragmentModule.class)
    abstract RegisterFragment provideRegisterFragmentFactory();
}