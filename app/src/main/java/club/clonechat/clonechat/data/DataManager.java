package club.clonechat.clonechat.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.data.ui.model.Message;
import club.clonechat.clonechat.data.ui.model.User;

public interface DataManager {

    // ======================================================
    // =================== AuthService ======================
    // ======================================================
    void login(String username, String password);
    LiveData<NetworkResource<Boolean>> getLoginResource();
    LiveData<Boolean> getIsLoggedIn();

    void register(String username, String password);
    LiveData<NetworkResource<Boolean>> getRegisterResource();

    LiveData<Boolean> getRegisterSuccess();

    void testLogin();

    void logout();

    // ======================================================
    // =================== FriendService ====================
    // ======================================================
    LiveData<List<User>> getFriendList();
    void refreshFriendList();

    LiveData<List<User>> getSearchList();
    void refreshSearchList(String username);

    void deleteFriend(String username);
    void addFriend(String username);

    // ======================================================
    // =================== ImageRepository ==================
    // ======================================================
    LiveData<Boolean> getTakePhoto();
    void setTakePhotoValue(boolean v);

    LiveData<byte[]> getPhotoBytes();

    void setPhotoBytes(byte[] photo);

    void uploadPhoto(List<User> users);

    LiveData<Boolean> getUploadStart();
    void setUploadStartFalse();

    // ======================================================
    // =================== MessageRepository ================
    // ======================================================
    LiveData<List<Message>> getMessageList();
    void refreshMessageList();

    LiveData<String> getImageURL();
    void setImageURL(String imageURL);

}
