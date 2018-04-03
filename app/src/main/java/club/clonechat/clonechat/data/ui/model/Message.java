package club.clonechat.clonechat.data.ui.model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("username")
    private String mUsername;
    @SerializedName("image_id")
    private String mImageId;
    public Message(String username, String image_id) {
        this.mUsername = username;
        this.mImageId = image_id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getImageId() {
        return mImageId;
    }

    public void setImageId(String imageId) {
        mImageId = imageId;
    }
}
