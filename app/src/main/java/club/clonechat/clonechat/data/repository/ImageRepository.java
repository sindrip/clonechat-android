package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import club.clonechat.clonechat.data.api.retrofit.ImageService;
import club.clonechat.clonechat.data.ui.model.User;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private final ImageService mImageService;

    private final MutableLiveData<Boolean> mTakePhoto;

    private final MutableLiveData<byte[]> mPhotoBytes;

    public ImageRepository(ImageService imageService) {
        this.mImageService = imageService;

        this.mTakePhoto = new MutableLiveData<>();
        this.mTakePhoto.setValue(false);

        this.mPhotoBytes = new MutableLiveData<>();

    }

    public void setPhotoBytes(byte[] photo) {
        this.mPhotoBytes.setValue(photo);
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

    public void uploadPhoto() {
        RequestBody image = RequestBody
                .create(MediaType.parse("image/*"), mPhotoBytes.getValue());

        mImageService.uploadImage(image).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("frag", "is done");
                } else {
                    // error handling ?
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // error handling?
            }
        });
    }
}
