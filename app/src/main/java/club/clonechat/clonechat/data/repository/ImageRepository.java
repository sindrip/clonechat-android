package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import club.clonechat.clonechat.data.api.model.ImageResponse;
import club.clonechat.clonechat.data.api.model.MessageRequest;
import club.clonechat.clonechat.data.api.retrofit.ImageService;
import club.clonechat.clonechat.data.api.retrofit.MessageService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private final ImageService mImageService;
    private final MessageService mMessageService;

    private final MutableLiveData<Boolean> mTakePhoto;

    private final MutableLiveData<byte[]> mPhotoBytes;

    private final MutableLiveData<Boolean> uploadStart;

    private byte[] imageCache;

    public ImageRepository(ImageService imageService, MessageService messageService) {
        this.mImageService = imageService;
        this.mMessageService = messageService;

        this.mTakePhoto = new MutableLiveData<>();
        this.mTakePhoto.setValue(false);

        this.mPhotoBytes = new MutableLiveData<>();
        this.uploadStart = new MutableLiveData<>();
    }

    public void setPhotoBytes(byte[] photo) {
        this.mPhotoBytes.setValue(photo);
        this.imageCache = photo;
    }

    public void setTakePhotoValue(boolean v) {
        this.mTakePhoto.setValue(v);
    }

    public LiveData<Boolean> getTakePhoto() {
        return mTakePhoto;
    }

    public LiveData<byte[]> getPhotoBytes() {
        return mPhotoBytes;
    }

    public LiveData<Boolean> getUploadStart() {
        return uploadStart;
    }

    public void setUploadStartFalse() {
        this.uploadStart.setValue(false);
    }

    public void uploadPhoto(String username) {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", "coolio", RequestBody.create(MediaType.parse("image/*"), mPhotoBytes.getValue()));

        this.uploadStart.setValue(true);
        mImageService.uploadImage(filePart).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("frag", "is done");
                    Log.d("frag", response.body().getId());
                    Log.d("frag", response.toString());
                    MessageRequest msg = new MessageRequest(username, response.body().getUuid());

                    mMessageService.sendMessageToUser(msg).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                } else {
                    // error handling?
                    Log.d("frag", String.valueOf(response.raw().code()));
                    Log.d("frag", "some fail");
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                // error handling?
                Log.d("frag", "some other fail");
            }
        });
    }

}
