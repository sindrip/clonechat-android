package club.clonechat.clonechat.data.api.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageService {

    @Multipart
    @POST("images")
    Call<Void> uploadImage(@Part("image")RequestBody image);
}
