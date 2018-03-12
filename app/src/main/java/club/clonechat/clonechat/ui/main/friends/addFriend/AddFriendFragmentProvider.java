package club.clonechat.clonechat.ui.main.friends.addFriend;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AddFriendFragmentProvider {

    @ContributesAndroidInjector(modules = AddFriendFragmentModule.class)
    abstract AddFriendFragment provideAddFriendFragmentFactory();
}
