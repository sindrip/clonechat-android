package club.clonechat.clonechat.data.api.retrofit;

import club.clonechat.clonechat.data.api.model.UserList;
import club.clonechat.clonechat.data.ui.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FriendService {
    @GET("/users")
    Call<UserList>  getUsersByQuery(@Query("username") String username);

    @GET("/users/me/friends")
    Call<UserList> getMyFriends();

    @POST("/users/me/friends")
    Call<Void> addFriend(@Body User user);

    @HTTP(method = "DELETE", path="/users/me/friends", hasBody = true)
    Call<Void> deleteFriend(@Body User user);
}