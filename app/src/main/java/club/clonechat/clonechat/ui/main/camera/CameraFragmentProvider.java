package club.clonechat.clonechat.ui.main.camera;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CameraFragmentProvider {

    @ContributesAndroidInjector(modules = CameraFragmentModule.class)
    abstract CameraFragment provideCameraFragmentFactory();
}
