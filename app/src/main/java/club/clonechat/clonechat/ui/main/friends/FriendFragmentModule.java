package club.clonechat.clonechat.ui.main.friends;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import javax.inject.Named;
import javax.inject.Singleton;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class FriendFragmentModule {

    @Provides
    FriendViewModel provideFriendViewModel(DataManager dataManager) {
        return new FriendViewModel(dataManager);
    }

    @Provides
    @Named("FriendFragment")
    ViewModelProvider.Factory provideFriendViewModelFactory(FriendViewModel friendViewModel) {
        return new ViewModelProviderFactory<>(friendViewModel);
    }

    @Provides
    @Named("FriendFragment")
    FriendAdapter provideFriendAdapter(DataManager dataManager) {
        return new FriendAdapter(dataManager, new ArrayList<>(), true);
    }

}