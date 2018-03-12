package club.clonechat.clonechat.ui.main.messages;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MessageFragmentProvider {

    @ContributesAndroidInjector(modules = MessageFragmentModule.class)
    abstract MessageFragment provideMessageFragmentFactory();
}