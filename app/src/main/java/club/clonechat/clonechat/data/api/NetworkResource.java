package club.clonechat.clonechat.data.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static club.clonechat.clonechat.data.api.NetworkResource.Status.ERROR;
import static club.clonechat.clonechat.data.api.NetworkResource.Status.LOADING;
import static club.clonechat.clonechat.data.api.NetworkResource.Status.SUCCESS;

public class NetworkResource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable public final String message;
    private NetworkResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> NetworkResource<T> success(@NonNull T data) {
        return new NetworkResource<>(SUCCESS, data, null);
    }

    public static <T> NetworkResource<T> error(String msg, @Nullable T data) {
        return new NetworkResource<>(ERROR, data, msg);
    }

    public static <T> NetworkResource<T> loading(@Nullable T data) {
        return new NetworkResource<>(LOADING, data, null);
    }

    public enum Status{
        SUCCESS,
        ERROR,
        LOADING
    }
}