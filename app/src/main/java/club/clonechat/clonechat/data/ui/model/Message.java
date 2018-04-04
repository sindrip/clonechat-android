package club.clonechat.clonechat.data.ui.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Date;

public class Message {
    @SerializedName("username")
    private String mUsername;
    @SerializedName("image_id")
    private String mImageId;
    @SerializedName("created_at")
    private Date mCreatedAt;

    public Message(String username, String image_id, Date createdAt) {
        this.mUsername = username;
        this.mImageId = image_id;
        this.mCreatedAt = createdAt;
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

    public long getCreatedAt() {
        return mCreatedAt.getTime() - 5000;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }
}
