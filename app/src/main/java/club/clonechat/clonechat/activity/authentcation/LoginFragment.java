package club.clonechat.clonechat.activity.authentcation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.activity.MainActivity;
import club.clonechat.clonechat.model.AuthUser;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button mButtonLogin;
    private EditText mUsername;
    private EditText mPassword;
    private AuthService mAuthService;

    public LoginFragment() {
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
        View rootview = inflater.inflate(R.layout.fragment_login, container, false);

        mUsername = rootview.findViewById(R.id.edittext_login_username);
        mPassword = rootview.findViewById(R.id.edittext_login_password);

        mButtonLogin = rootview.findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(v -> {
            if (mUsername.getText().toString().equals("")) {
                mUsername.setError(getString(R.string.auth_error_username_empty));
                return;
            }
            if (mPassword.getText().toString().equals("")) {
                mPassword.setError(getString(R.string.auth_error_password_empty));
                return;
            }

            disableLoginButton();

            AuthUser loginuser = new AuthUser(mUsername.getText().toString(), mPassword.getText().toString());
            Call call = mAuthService.login(loginuser);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        getActivity().finishAffinity();
                    } else {
                        Toast.makeText(getContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                        enableLoginButton();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    enableLoginButton();
                }
            });
        });

        return rootview;
    }

    private void disableLoginButton() {
        mButtonLogin.setEnabled(false);
        mButtonLogin.setText(getString(R.string.networking_working));
        mButtonLogin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonDisabled));
    }

    private void enableLoginButton() {
        mButtonLogin.setEnabled(true);
        mButtonLogin.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonBackground));
        mButtonLogin.setText(R.string.action_login);
    }

}
