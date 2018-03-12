package club.clonechat.clonechat.ui.main.friends;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FriendFragmentProvider {

    @ContributesAndroidInjector(modules = FriendFragmentModule.class)
    abstract FriendFragment provideFriendFragmentFactory();
}