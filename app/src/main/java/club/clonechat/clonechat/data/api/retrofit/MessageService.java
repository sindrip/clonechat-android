package club.clonechat.clonechat.data.api.retrofit;

import club.clonechat.clonechat.data.api.model.ImageResponse;
import club.clonechat.clonechat.data.api.model.MessageRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageService {

    @POST("/messages")
    Call<Void> sendMessageToUser(@Body MessageRequest messageRequest);
}
