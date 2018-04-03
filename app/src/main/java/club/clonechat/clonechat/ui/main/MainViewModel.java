package club.clonechat.clonechat.ui.main;

import android.arch.lifecycle.LiveData;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    private LiveData<Boolean> isLoggedIn;
    private LiveData<Boolean> takePhoto;
    private LiveData<Boolean> uploadStart;

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
        isLoggedIn = getDataManager().getIsLoggedIn();
        takePhoto = getDataManager().getTakePhoto();
        uploadStart = getDataManager().getUploadStart();
    }

    public void takePhotoEventCaught() {
        getDataManager().setTakePhotoValue(false);
    }

    public void setPhotoBytes(byte[] photoBytes) {
        getDataManager().setPhotoBytes(photoBytes);
    }

    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }
    public LiveData<Boolean> getTakePhoto() { return takePhoto; }
    public LiveData<Boolean> getUploadStart() { return uploadStart; }
    public void setUploadStartFalse() {
        getDataManager().setUploadStartFalse();
    }
}
