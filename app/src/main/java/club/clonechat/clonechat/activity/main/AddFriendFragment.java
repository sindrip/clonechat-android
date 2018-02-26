package club.clonechat.clonechat.activity.main;


import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import java.util.ArrayList;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.adapter.UserAdapter;
import club.clonechat.clonechat.model.User;
import club.clonechat.clonechat.model.UserList;
import club.clonechat.clonechat.network.FriendService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment {

    private FriendService mFriendService;
    private RecyclerView mUserRecyclerView;
    private UserAdapter mUserAdapter;



    public AddFriendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_searchusers, menu);
        /////
        SearchView search = (SearchView) menu.findItem(R.id.search_users).getActionView();
        // 9
        search.setOnQueryTextListener(new OnQueryTextListener() {
            /*/*/

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Msg",query);
                getUsersByQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return true;

            }

        });
    }

    private void getUsersByQuery(String query) {
        Call<UserList> call = mFriendService.getUsersByQuery(query);

        String x = "dd";
        Log.d("Msg", "c");
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.d("Msg", "a");
                mUserAdapter.setDataList(response.body().getUserlist());
                mUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("Msg", "b");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFriendService = RetrofitInstance.getRetrofitInstance(getContext()).create(FriendService.class);
        mUserAdapter = new UserAdapter(new ArrayList<User>());

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_add_friend, container, false);

        mUserRecyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view_friend_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootview.getContext());
        mUserRecyclerView.setLayoutManager(layoutManager);
        mUserRecyclerView.setAdapter(mUserAdapter);

        getFriendList();

        return rootview;
    }

    private void getFriendList() {
        Call<UserList> call = mFriendService.getMyFriends();

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                mUserAdapter.setDataList(response.body().getUserlist());
                mUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });
    }

}
