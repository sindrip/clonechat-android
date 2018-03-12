package club.clonechat.clonechat.ui.main.messages;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class MessageFragmentModule {

    @Provides
    MessageViewModel provideMessageViewModel(DataManager dataManager) {
        return new MessageViewModel(dataManager);
    }

    @Provides
    @Named("MessageFragment")
    ViewModelProvider.Factory provideCameraViewModelFactory(MessageViewModel messageViewModel) {
        return new ViewModelProviderFactory<>(messageViewModel);
    }
}
