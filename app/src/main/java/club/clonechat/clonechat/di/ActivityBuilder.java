package club.clonechat.clonechat.di;

import android.app.Application;

import club.clonechat.clonechat.ClonechatApp;
import club.clonechat.clonechat.ui.authentication.AuthenticationActivity;
import club.clonechat.clonechat.ui.authentication.AuthenticationActivityModule;
import club.clonechat.clonechat.ui.authentication.login.LoginFragmentProvider;
import club.clonechat.clonechat.ui.authentication.register.RegisterFragmentProvider;
import club.clonechat.clonechat.ui.authentication.welcome.WelcomeFragmentModule;
import club.clonechat.clonechat.ui.authentication.welcome.WelcomeFragmentProvider;
import club.clonechat.clonechat.ui.main.MainActivity;
import club.clonechat.clonechat.ui.main.MainActivityModule;
import club.clonechat.clonechat.ui.main.camera.CameraFragmentProvider;
import club.clonechat.clonechat.ui.main.camera.imagePreview.ImagePreviewFragment;
import club.clonechat.clonechat.ui.main.camera.imagePreview.ImagePreviewFragmentProvider;
import club.clonechat.clonechat.ui.main.camera.sendImage.SendImageFragmentProvider;
import club.clonechat.clonechat.ui.main.friends.FriendFragmentProvider;
import club.clonechat.clonechat.ui.main.friends.addFriend.AddFriendFragmentProvider;
import club.clonechat.clonechat.ui.main.messages.MessageFragmentProvider;
import club.clonechat.clonechat.ui.main.messages.imageView.ImageViewFragment;
import club.clonechat.clonechat.ui.main.messages.imageView.ImageViewFragmentProvider;
import club.clonechat.clonechat.ui.splash.SplashActivity;
import club.clonechat.clonechat.ui.splash.SplashActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            AuthenticationActivityModule.class,
            WelcomeFragmentProvider.class,
            LoginFragmentProvider.class,
            RegisterFragmentProvider.class})
    abstract AuthenticationActivity bindAuthenticationActivity();

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            CameraFragmentProvider.class,
            MessageFragmentProvider.class,
            FriendFragmentProvider.class,
            AddFriendFragmentProvider.class,
            ImagePreviewFragmentProvider.class,
            SendImageFragmentProvider.class,
            ImageViewFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            SplashActivityModule.class
    })
    abstract SplashActivity bindSplashActivity();
}
