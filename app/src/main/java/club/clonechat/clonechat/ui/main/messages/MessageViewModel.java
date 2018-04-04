package club.clonechat.clonechat.ui.main.messages;

import android.arch.lifecycle.LiveData;

import java.util.List;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.data.ui.model.Message;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class MessageViewModel extends BaseViewModel {

    private final LiveData<List<Message>> messagelist;

    public MessageViewModel(DataManager dataManager) {
        super(dataManager);
        messagelist = getDataManager().getMessageList();
    }

    public void refreshMessageList() {
        getDataManager().refreshMessageList();
    }

    public LiveData<List<Message>> getMessagelist() {
        return messagelist;
    }

    public void logout() {
        getDataManager().logout();
    }
}
