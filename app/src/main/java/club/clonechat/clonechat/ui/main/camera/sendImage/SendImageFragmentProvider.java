package club.clonechat.clonechat.ui.main.camera.sendImage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SendImageFragmentProvider {
    @ContributesAndroidInjector(modules = SendImageFragmentModule.class)
    abstract SendImageFragment provideSendImageFragmentFactory();
}
