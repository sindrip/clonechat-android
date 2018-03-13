package club.clonechat.clonechat.ui.main.camera.sendImage;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class SendImageFragmentModule {

    @Provides
    SendImageViewModel provideSendImageViewMdel(DataManager dataManager) {
        return new SendImageViewModel(dataManager);
    }

    @Provides
    @Named("SendImageFragment")
    ViewModelProvider.Factory provideSendImageViewModelFactory(SendImageViewModel sendImageViewModel) {
        return new ViewModelProviderFactory<>(sendImageViewModel);
    }
}
