package club.clonechat.clonechat.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import club.clonechat.clonechat.data.ui.model.Message;

public class MessageList {
    @SerializedName("messagelist")
    private List<Message> mMessageList;

    public List<Message> getMessageList() {
        return mMessageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.mMessageList = messageList;
    }
}
