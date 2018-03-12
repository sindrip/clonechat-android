package club.clonechat.clonechat.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import club.clonechat.clonechat.data.ui.model.User;

public class UserList {
    @SerializedName("userlist")
    private List<User> mUserlist;


    public List<User> getUserlist() {
        return mUserlist;
    }

    public void setUserlist(List<User> userlist) {
        mUserlist = userlist;
    }
}