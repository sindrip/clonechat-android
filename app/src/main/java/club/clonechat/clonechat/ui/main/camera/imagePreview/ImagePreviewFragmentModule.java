package club.clonechat.clonechat.ui.main.camera.imagePreview;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ImagePreviewFragmentModule {

    @Provides
    ImagePreviewViewModel provideImagePreviewViewModel(DataManager dataManager) {
        return new ImagePreviewViewModel(dataManager);
    }

    @Provides
    @Named("ImagePreviewFragment")
    ViewModelProvider.Factory provideImagePreviewViewModelFactory(ImagePreviewViewModel imagePreviewViewModel) {
        return new ViewModelProviderFactory<>(imagePreviewViewModel);
    }
}
