package club.clonechat.clonechat.ui.main.messages;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import club.clonechat.clonechat.data.ui.model.Message;

public class MessageItemViewModel {

    private final MutableLiveData<Message> message = new MutableLiveData<>();

    private final MessageItemViewModelListener mListener;

    public MessageItemViewModel(Message message, MessageItemViewModelListener listener) {
        this.message.setValue(message);
        this.mListener = listener;
    }

    public LiveData<Message> getMessage() {
        return message;
    }

    public void onButtonClick() {
        mListener.onButtonClick();
    }

    public interface MessageItemViewModelListener {

        void onButtonClick();
    }
}
