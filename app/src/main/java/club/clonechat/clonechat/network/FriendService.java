package club.clonechat.clonechat.network;

import club.clonechat.clonechat.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by stell on 26/02/18.
 */

public interface FriendService {
    @GET("/users/me/friends")
    Call<UserList> getMyFriends();

    @POST("/users/me/friends")
    Call<Void> addFriend(String username);
}
