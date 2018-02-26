package club.clonechat.clonechat.activity.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FriendFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefresh;
    private FriendService mFriendService;
    private RecyclerView mUserRecyclerView;
    private UserAdapter mUserAdapter;

    public FriendFragment() {
        // Required empty public constructor
    }

    public static FriendFragment newInstance() {
        FriendFragment fragment = new FriendFragment();
        Bundle args = new Bundle();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFriendService = RetrofitInstance.getRetrofitInstance(getContext()).create(FriendService.class);
        mUserAdapter = new UserAdapter(new ArrayList<User>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_friend, container, false);

        mUserRecyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view_friend_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootview.getContext());
        mUserRecyclerView.setLayoutManager(layoutManager);
        mUserRecyclerView.setAdapter(mUserAdapter);

        getFriendList();

        mSwipeRefresh = rootview.findViewById(R.id.friend_refreshswipe);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFriendList();
                Toast.makeText(getContext(), "Friendlist refreshed...", Toast.LENGTH_SHORT).show();
                mSwipeRefresh.setRefreshing(false);
            }
        });

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
