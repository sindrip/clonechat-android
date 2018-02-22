package club.clonechat.clonechat_android.network;

import club.clonechat.clonechat_android.model.AuthUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sindrip on 21.2.2018.
 */

public interface AuthService {
    @POST("/login")
    Call<Void> login(@Body AuthUser user);

    @GET("/testlogin")
    Call<Void> testLogin();

    @DELETE("/logout")
    Call<Void> logout();

    @POST("/register")
    Call<Void> register(@Body AuthUser user);
}