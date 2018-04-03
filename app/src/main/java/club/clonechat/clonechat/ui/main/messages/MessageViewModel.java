package club.clonechat.clonechat.ui.main.messages;

import android.arch.lifecycle.LiveData;

import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.Message;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class MessageViewModel extends BaseViewModel {

    private final LiveData<List<Message>> messagelist;
    private final LiveData<String> imageurl;

    public MessageViewModel(DataManager dataManager) {
        super(dataManager);
        messagelist = getDataManager().getMessageList();
        imageurl = getDataManager().getImageURL();
    }

    public void refreshMessageList() {
        getDataManager().refreshMessageList();
    }

    public LiveData<List<Message>> getMessagelist() {
        return messagelist;
    }

    public LiveData<String> getImageUrl() {
        return imageurl;
    }
}
