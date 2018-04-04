package club.clonechat.clonechat.ui.main.messages.imageView;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ImageViewFragmentProvider {

    @ContributesAndroidInjector(modules = ImageViewFragmentModule.class)
    abstract ImageViewFragment provideImageViewFragmentFactory();
}
