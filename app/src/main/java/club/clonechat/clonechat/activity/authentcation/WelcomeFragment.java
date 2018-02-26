package club.clonechat.clonechat.activity.authentcation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;

public class WelcomeFragment extends Fragment {

    private Button mButtonLogin;
    private Button mButtonRegister;

    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_welcome, container, false);

        mButtonLogin = (Button) rootview.findViewById(R.id.button_open_login);
        mButtonLogin.setOnClickListener(v -> {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .addToBackStack(null)
                    .commit();
        });

        mButtonRegister = (Button) rootview.findViewById(R.id.button_open_register);
        mButtonRegister.setOnClickListener(v -> {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return rootview;
    }

}
