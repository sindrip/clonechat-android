package club.clonechat.clonechat.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import club.clonechat.clonechat.data.api.retrofit.FriendService;
import club.clonechat.clonechat.data.ui.model.User;
import club.clonechat.clonechat.data.api.model.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepository {

    private final FriendService mFriendService;

    private final MutableLiveData<List<User>> friendList;

    private final MutableLiveData<List<User>> searchList;

    public FriendRepository(FriendService friendService) {
        this.mFriendService = friendService;

        this.friendList = new MutableLiveData<>();
        this.friendList.setValue(new ArrayList<>());

        this.searchList = new MutableLiveData<>();
        this.searchList.setValue(new ArrayList<>());
    }

    public LiveData<List<User>> getFriendList() {
        if (friendList.getValue().size() == 0) {
            refreshFriendList();
        }
        return this.friendList;
    }

    public LiveData<List<User>> getSearchList() {
        if (searchList.getValue().size() == 0) {
            refreshSearchList("");
        }
        return this.searchList;
    }

    public void refreshFriendList() {
        mFriendService.getMyFriends().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful()) {
                    friendList.setValue(response.body().getUserlist());
                } else {
                    friendList.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                // error handling with NetworkResource?
            }
        });
    }

    public void refreshSearchList(String username) {
        if (username.equals("")) {
            searchList.setValue(new ArrayList<>());
            return;
        }
        mFriendService.getUsersByQuery(username).enqueue((new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful()) {
                    searchList.setValue(response.body().getUserlist());
                } else {
                    // error handling ?
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                // error handling?
            }
        }));
    }

    public void deleteFriend(String username) {
        User user = new User(username);

        mFriendService.deleteFriend(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                refreshFriendList();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addFriend(String username) {
        User user = new User(username);
        Log.d("frag", "herna");

        mFriendService.addFriend(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                refreshFriendList();
                Log.d("frag", String.valueOf(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
