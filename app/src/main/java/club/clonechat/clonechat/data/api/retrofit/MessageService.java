package club.clonechat.clonechat.data.api.retrofit;

import club.clonechat.clonechat.data.api.model.ImageResponse;
import club.clonechat.clonechat.data.api.model.MessageList;
import club.clonechat.clonechat.data.api.model.MessageRequest;
import club.clonechat.clonechat.data.api.model.UserList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessageService {

    @POST("/messages")
    Call<Void> sendMessageToUser(@Body MessageRequest messageRequest);

    @GET("/messages")
    Call<MessageList> getMyMessages();
}
