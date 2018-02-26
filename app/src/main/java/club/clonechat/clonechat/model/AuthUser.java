package club.clonechat.clonechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sindrip on 26.2.2018.
 */

public class AuthUser {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public AuthUser(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
