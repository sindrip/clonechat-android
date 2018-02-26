package club.clonechat.clonechat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.model.User;

/**
 * Created by stell on 26/02/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> dataList;

    public UserAdapter(List<User> dataList) {
        this.setDataList(dataList);
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

        UserViewHolder(View itemView) {
            super(itemView);
            mUsername = (TextView) itemView.findViewById(R.id.card_username);
        }
    }
}