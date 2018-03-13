package club.clonechat.clonechat.ui.main.camera.imagePreview;

import android.arch.lifecycle.LiveData;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class ImagePreviewViewModel extends BaseViewModel {

    private LiveData<byte[]> photoBytes;

    public ImagePreviewViewModel(DataManager dataManager) {
        super(dataManager);

        photoBytes = getDataManager().getPhotoBytes();
    }

    public LiveData<byte[]> getPhotoBytes() {
        return photoBytes;
    }
}
