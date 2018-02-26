package club.clonechat.clonechat.activity.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.activity.AuthenticationActivity;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Button mButtonLogout;
    private Button mButtonTestLogin;
    private AuthService mAuthService;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuthService = RetrofitInstance.getRetrofitInstance(getContext()).create(AuthService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_settings, container, false);

        mButtonLogout = (Button) rootview.findViewById(R.id.button_sign_out);
        mButtonLogout.setOnClickListener(v -> {

            disableLogoutButton();

            Call call = mAuthService.logout();

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                        getActivity().finishAffinity();
                    } else {
                        Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                        enableLogoutButton();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                    enableLogoutButton();
                }
            });
        });

        mButtonTestLogin = (Button) rootview.findViewById(R.id.button_test_login_check);
        mButtonTestLogin.setOnClickListener(v -> {
            Call call = mAuthService.testLogin();

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return rootview;
    }

    private void disableLogoutButton() {
        mButtonLogout.setEnabled(false);
        mButtonLogout.setText(getString(R.string.networking_working));
        mButtonLogout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonDisabled));
    }

    private void enableLogoutButton() {
        mButtonLogout.setEnabled(true);
        mButtonLogout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonBackground));
        mButtonLogout.setText(R.string.action_sign_out);
    }

}
