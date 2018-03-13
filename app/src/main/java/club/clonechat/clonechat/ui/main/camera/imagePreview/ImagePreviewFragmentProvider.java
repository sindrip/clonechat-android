package club.clonechat.clonechat.ui.main.camera.imagePreview;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ImagePreviewFragmentProvider {

    @ContributesAndroidInjector(modules = ImagePreviewFragmentModule.class)
    abstract ImagePreviewFragment provideImagePreviewFragmentFactory();
}
