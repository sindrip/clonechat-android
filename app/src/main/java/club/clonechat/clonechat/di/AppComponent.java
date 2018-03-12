package club.clonechat.clonechat.di;

import android.app.Application;

import javax.inject.Singleton;

import club.clonechat.clonechat.ClonechatApp;
import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class})
interface AppComponent extends AndroidInjector<ClonechatApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<ClonechatApp> {}
}