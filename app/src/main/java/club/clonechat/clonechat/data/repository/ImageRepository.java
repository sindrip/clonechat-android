package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import club.clonechat.clonechat.data.api.model.ImageResponse;
import club.clonechat.clonechat.data.api.model.MessageRequest;
import club.clonechat.clonechat.data.api.retrofit.ImageService;
import club.clonechat.clonechat.data.api.retrofit.MessageService;
import club.clonechat.clonechat.data.ui.model.User;
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

    public void uploadPhoto(List<User> users) {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", "coolio", RequestBody.create(MediaType.parse("image/*"), mPhotoBytes.getValue()));

        this.uploadStart.setValue(true);
        mImageService.uploadImage(filePart).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("frag", "Image has been uploaded, sending to users");
                    sendPhotoToUsers(users, response.body().getUuid());
                } else {
                    Log.d("frag", "Uploading photo was not successful");
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                // error handling?
                Log.d("frag", "Error uploading photo");
            }
        });
    }

    private void sendPhotoToUsers(List<User> users, String uuid) {
        Log.d("frag", "Sending image to users");
        for (User user : users) {
            MessageRequest msg = new MessageRequest(user.getUsername(), uuid);

            mMessageService.sendMessageToUser(msg).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("frag", "Image send to user: " + user.getUsername());

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("frag", "Failed to send image to user: " + user.getUsername());

                }
            });
        }
    }

}
