package club.clonechat.clonechat.ui.main.messages.imageView;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ImageViewFragmentModule {

    @Provides
    ImageViewViewModel provideImageViewViewModel(DataManager dataManager) {
        return new ImageViewViewModel(dataManager);
    }

    @Provides
    @Named("ImageViewFragment")
    ViewModelProvider.Factory provideImageViewModelFactory(ImageViewViewModel imageViewViewModel) {
        return new ViewModelProviderFactory<>(imageViewViewModel);
    }
}
