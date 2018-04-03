package club.clonechat.clonechat.di;

import android.app.Application;
import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import javax.inject.Singleton;

import club.clonechat.clonechat.BuildConfig;
import club.clonechat.clonechat.ClonechatApp;
import club.clonechat.clonechat.data.AppDataManager;
import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.api.retrofit.AuthService;
import club.clonechat.clonechat.data.api.retrofit.FriendService;
import club.clonechat.clonechat.data.api.retrofit.ImageService;
import club.clonechat.clonechat.data.api.retrofit.MessageService;
import club.clonechat.clonechat.data.repository.AuthRepository;
import club.clonechat.clonechat.data.repository.FriendRepository;
import club.clonechat.clonechat.data.repository.ImageRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract Application bindApplication(ClonechatApp app);

    @Binds
    @Singleton
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Application application) {
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(application));

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.APIURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    static AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    static AuthRepository provideAuthRepository(AuthService authService) {
        return new AuthRepository(authService);
    }

    @Provides
    @Singleton
    static FriendService provideFriendService(Retrofit retrofit) {
        return retrofit.create(FriendService.class);
    }

    @Provides
    @Singleton
    static FriendRepository provideFriendRepository(FriendService friendService) {
        return new FriendRepository(friendService);
    }

    @Provides
    @Singleton
    static ImageService provideImageService(Retrofit retrofit) {
        return retrofit.create(ImageService.class);
    }

    @Provides
    @Singleton
    static ImageRepository provideImageRepository(ImageService imageService, MessageService messageService) {
        return new ImageRepository(imageService, messageService);
    }

    @Provides
    @Singleton
    static MessageService provideMessageService(Retrofit retrofit) {
        return retrofit.create(MessageService.class);
    }

    @Provides
    @Singleton
    static DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
