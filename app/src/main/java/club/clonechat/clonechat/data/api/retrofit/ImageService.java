package club.clonechat.clonechat.data.api.retrofit;

import club.clonechat.clonechat.data.api.model.ImageResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {

    @Multipart
    @POST("/images")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part image);

}
