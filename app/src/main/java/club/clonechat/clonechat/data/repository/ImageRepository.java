package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import club.clonechat.clonechat.data.ui.model.User;

public class ImageRepository {

    private final MutableLiveData<Boolean> mTakePhoto;

    private final MutableLiveData<byte[]> mPhotoBytes;

    private final MutableLiveData<List<User>> mSendList;

    public ImageRepository() {
        this.mTakePhoto = new MutableLiveData<>();
        this.mTakePhoto.setValue(false);

        this.mPhotoBytes = new MutableLiveData<>();

        this.mSendList = new MutableLiveData<>();
        this.mSendList.setValue(new ArrayList<>());
    }

    public void setPhotoBytes(byte[] photo) {
        this.mPhotoBytes.setValue(photo);
    }

    public void setTakePhotoValue(boolean v) {
        this.mTakePhoto.setValue(v);
    }

    public void setSendList(List<User> sendlist) {
        this.mSendList.setValue(sendlist);
    }

    public LiveData<List<User>> getSendList() {
        return mSendList;
    }

    public LiveData<Boolean> getTakePhoto() {
        return mTakePhoto;
    }

    public LiveData<byte[]> getPhotoBytes() {
        return mPhotoBytes;
    }
}
