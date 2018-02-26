package club.clonechat.clonechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stell on 26/02/18.
 */

public class User {
    @SerializedName("username")
    private String mUsername;

    public User(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }
}
