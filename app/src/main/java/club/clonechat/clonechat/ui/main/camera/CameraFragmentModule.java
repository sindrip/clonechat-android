package club.clonechat.clonechat.ui.main.camera;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class CameraFragmentModule {

    @Provides
    CameraViewModel provideCameraViewModel(DataManager dataManager) {
        return new CameraViewModel(dataManager);
    }

    @Provides
    @Named("CameraFragment")
    ViewModelProvider.Factory provideCameraViewModelFactory(CameraViewModel cameraViewMOdel) {
        return new ViewModelProviderFactory<>(cameraViewMOdel);
    }
}
