package club.clonechat.clonechat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.model.User;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.FriendService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by stell on 26/02/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> dataList;
    private boolean addUser;
    private FriendService mFriendService;

    public UserAdapter(List<User> dataList) {
        this.setDataList(dataList);
        this.addUser = false;
        this.mFriendService = RetrofitInstance.getRetrofitInstance(null).create(FriendService.class);
    }

    public UserAdapter(List<User> dataList, boolean addUser) {
        this.setDataList(dataList);
        this.addUser = addUser;
        this.mFriendService = RetrofitInstance.getRetrofitInstance(null).create(FriendService.class);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.mUsername.setText(getDataList().get(position).getUsername());
        if (this.addUser) {
            // Implement onclick listener for button
            holder.mAddUser.setOnClickListener(v -> {
                System.out.println(holder.mUsername.getText());
                Call call = mFriendService.addFriend(new User((String) holder.mUsername.getText()));

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        System.out.println(response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            });
        } else {
            holder.mAddUser.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return getDataList().size();
    }

    public List<User> getDataList() {
        return dataList;
    }

    public void setDataList(List<User> dataList) {
        this.dataList = dataList;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView mUsername;
        Button mAddUser;

        UserViewHolder(View itemView) {
            super(itemView);
            mUsername = (TextView) itemView.findViewById(R.id.card_username);
            mAddUser = (Button) itemView.findViewById(R.id.card_button);
        }
    }
}