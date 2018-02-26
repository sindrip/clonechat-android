package club.clonechat.clonechat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stell on 26/02/18.
 */

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
