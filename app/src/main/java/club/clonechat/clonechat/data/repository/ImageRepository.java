package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

public class ImageRepository {

    private final MutableLiveData<Boolean> mTakePhoto;

    private final MutableLiveData<byte[]> mPhotoBytes;

    public ImageRepository() {
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
}
