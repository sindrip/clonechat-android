package club.clonechat.clonechat.ui.main.friends.addFriend;

import android.arch.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import club.clonechat.clonechat.ui.main.friends.FriendAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class AddFriendFragmentModule {

    @Provides
    AddFriendViewModel provideAddFriendViewModel(DataManager dataManager) {
        return new AddFriendViewModel(dataManager);
    }

    @Provides
    @Named("AddFriendFragment")
    ViewModelProvider.Factory provideAddFriendViewModelFactory(AddFriendViewModel addFriendViewModel) {
        return new ViewModelProviderFactory<>(addFriendViewModel);
    }

    @Provides
    @Named("AddFriendFragment")
    FriendAdapter provideFriendAdapter(DataManager dataManager) {
        return new FriendAdapter(dataManager, new ArrayList<>(), false);
    }
}
