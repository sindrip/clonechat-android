package club.clonechat.clonechat.data.api.model;

import com.google.gson.annotations.SerializedName;

public class ImageResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("uuid")
    private String uuid;

    public ImageResponse(String id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

