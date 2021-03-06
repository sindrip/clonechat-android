package club.clonechat.clonechat.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import club.clonechat.clonechat.data.api.NetworkResource;
import club.clonechat.clonechat.data.repository.AuthRepository;
import club.clonechat.clonechat.data.repository.FriendRepository;
import club.clonechat.clonechat.data.repository.ImageRepository;
import club.clonechat.clonechat.data.repository.MessageRepository;
import club.clonechat.clonechat.data.ui.model.Message;
import club.clonechat.clonechat.data.ui.model.User;

public class AppDataManager implements DataManager {

    private final AuthRepository mAuthRepository;
    private final FriendRepository mFriendRepository;
    private final ImageRepository mImageRepository;
    private final MessageRepository mMessageRepository;

    @Inject
    public AppDataManager(AuthRepository authRepository,
                          FriendRepository friendRepository,
                          ImageRepository imageRepository,
                          MessageRepository messageRepository) {
        this.mAuthRepository = authRepository;
        this.mFriendRepository = friendRepository;
        this.mImageRepository = imageRepository;
        this.mMessageRepository = messageRepository;
    }

    @Override
    public void login(String username, String password) {
        mAuthRepository.login(username, password);
    }

    @Override
    public LiveData<NetworkResource<Boolean>> getLoginResource() {
        return mAuthRepository.getLoginResource();
    }

    @Override
    public LiveData<Boolean> getIsLoggedIn() {
        return mAuthRepository.getIsLoggedIn();
    }

    @Override
    public LiveData<Boolean> getRegisterSuccess() {
        return mAuthRepository.getRegisterSuccess();
    }

    @Override
    public void register(String username, String password) {
        mAuthRepository.register(username, password);
    }

    @Override
    public LiveData<NetworkResource<Boolean>> getRegisterResource() {
        return mAuthRepository.getRegisterResource();
    }

    @Override
    public void testLogin() {
        mAuthRepository.testLogin();
    }

    @Override
    public void logout() {
        mAuthRepository.logout();
    }

    @Override
    public LiveData<List<User>> getFriendList() {
        return mFriendRepository.getFriendList();
    }

    @Override
    public void refreshFriendList() {
        mFriendRepository.refreshFriendList();
    }

    @Override
    public LiveData<List<User>> getSearchList() {
        return mFriendRepository.getSearchList();
    }

    @Override
    public void refreshSearchList(String username) {
        mFriendRepository.refreshSearchList(username);
    }

    @Override
    public void deleteFriend(String username) {
        mFriendRepository.deleteFriend(username);
    }

    @Override
    public void addFriend(String username) {
        mFriendRepository.addFriend(username);
    }

    @Override
    public LiveData<Boolean> getTakePhoto() {
        return mImageRepository.getTakePhoto();
    }

    @Override
    public void setTakePhotoValue(boolean v) {
        mImageRepository.setTakePhotoValue(v);
    }

    @Override
    public LiveData<byte[]> getPhotoBytes() {
        return mImageRepository.getPhotoBytes();
    }

    @Override
    public void setPhotoBytes(byte[] photo) {
        mImageRepository.setPhotoBytes(photo);
    }

    @Override
    public void uploadPhoto(String username) {
        mImageRepository.uploadPhoto(username);
    }

    @Override
    public LiveData<Boolean> getUploadStart() {
        return mImageRepository.getUploadStart();
    }

    @Override
    public void setUploadStartFalse() {
        mImageRepository.setUploadStartFalse();
    }

    @Override
    public LiveData<List<Message>> getMessageList() {
        return mMessageRepository.getMessageList();
    }

    @Override
    public void refreshMessageList() {
        mMessageRepository.refreshMessageList();
    }

    @Override
    public LiveData<String> getImageURL() {
        return mMessageRepository.getImageURL();
    }

    @Override
    public void setImageURL(String imageURL) {
        mMessageRepository.setImageURL(imageURL);
    }

}
