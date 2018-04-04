package club.clonechat.clonechat.ui.main.messages.imageView;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class ImageViewViewModel extends BaseViewModel<ImageViewNavigator> {

    private String mImageURL;

    public ImageViewViewModel(DataManager dataManager) {
        super(dataManager);
        mImageURL = getDataManager().getImageURL().getValue();
        Log.d("frag", "her: " + getDataManager().getImageURL().getValue());
    }

    public String getImageURL() {
        return this.mImageURL;
    }

    public void resetImageURL() {
        getDataManager().setImageURL("");
    }

}
