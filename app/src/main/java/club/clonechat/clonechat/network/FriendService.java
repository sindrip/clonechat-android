package club.clonechat.clonechat.network;

import club.clonechat.clonechat.model.User;
import club.clonechat.clonechat.model.UserList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by stell on 26/02/18.
 */

public interface FriendService {
    @GET("/users")
    Call<UserList>  getUsersByQuery(@Query("username") String username);

    @GET("/users/me/friends")
    Call<UserList> getMyFriends();

    @POST("/users/me/friends")
    Call<Void> addFriend(@Body User user);
}
