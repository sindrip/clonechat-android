package club.clonechat.clonechat.ui.main.messages;

import club.clonechat.clonechat.data.DataManager;
import club.clonechat.clonechat.ui.base.BaseViewModel;

public class MessageViewModel extends BaseViewModel {

    public MessageViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void logout() {
        getDataManager().logout();
    }
}
