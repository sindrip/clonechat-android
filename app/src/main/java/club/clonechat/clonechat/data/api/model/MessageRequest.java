package club.clonechat.clonechat.data.api.model;

import com.google.gson.annotations.SerializedName;

public class MessageRequest {
    @SerializedName("recipient")
    private
    String recipient;
    @SerializedName("uuid")
    private
    String uuid;

    public MessageRequest(String recipient, String uuid) {
        this.recipient = recipient;
        this.uuid = uuid;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
