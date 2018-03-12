package club.clonechat.clonechat.data.ui.model;

import com.google.gson.annotations.SerializedName;

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