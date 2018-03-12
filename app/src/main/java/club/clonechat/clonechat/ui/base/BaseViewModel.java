package club.clonechat.clonechat.ui.base;

import android.arch.lifecycle.ViewModel;

import club.clonechat.clonechat.data.DataManager;

public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    private N mNavigator;

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
