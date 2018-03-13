package club.clonechat.clonechat.ui.main.camera.imagePreview;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ImagePreviewFragmentProvider {

    @ContributesAndroidInjector(modules = ImagePreviewModule.class)
    abstract ImagePreviewFragment provideImagePreviewFragmentFactory();
}
