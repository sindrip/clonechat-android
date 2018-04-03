package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import club.clonechat.clonechat.data.api.model.MessageList;
import club.clonechat.clonechat.data.api.retrofit.MessageService;
import club.clonechat.clonechat.data.ui.model.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageRepository {

    private final MessageService mMessageService;

    private final MutableLiveData<List<Message>> messagelist;

    public MessageRepository(MessageService messageService) {
        this.mMessageService = messageService;

        this.messagelist = new MutableLiveData<>();
        this.messagelist.setValue(new ArrayList<>());
    }

    public LiveData<List<Message>> getMessageList() {
        if (messagelist.getValue().size() == 0) {
            refreshMessageList();
        }
        return this.messagelist;
    }

    public void refreshMessageList() {
        mMessageService.getMyMessages().enqueue(new Callback<MessageList>() {
            @Override
            public void onResponse(Call<MessageList> call, Response<MessageList> response) {
                if (response.isSuccessful()) {
                    messagelist.setValue(response.body().getMessageList());
                } else {
                    messagelist.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<MessageList> call, Throwable t) {
                // error handling?
            }
        });
    }
}
