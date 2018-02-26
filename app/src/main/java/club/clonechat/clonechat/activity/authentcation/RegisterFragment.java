package club.clonechat.clonechat.activity.authentcation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.model.AuthUser;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Button mButtonRegister;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordRepeat;
    private AuthService mAuthService;

    public RegisterFragment() {
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
        View rootview = inflater.inflate(R.layout.fragment_register, container, false);

        mUsername = rootview.findViewById(R.id.edittext_register_username);
        mPassword = rootview.findViewById(R.id.edittext_register_password);
        mPasswordRepeat = rootview.findViewById(R.id.edittext_register_password_confirm);

        mButtonRegister = (Button) rootview.findViewById(R.id.button_register);
        mButtonRegister.setOnClickListener(v -> {
            if (mUsername.getText().toString().equals("")) {
                mUsername.setError(getString(R.string.auth_error_username_empty));
                return;
            }
            if (mPassword.getText().toString().equals("")) {
                mPassword.setError(getString(R.string.auth_error_password_empty));
                return;
            }
            if (!mPassword.getText().toString().equals(mPasswordRepeat.getText().toString())) {
                mPasswordRepeat.setError(getString(R.string.auth_error_password_not_match));
                return;
            }

            disableRegisterButton();

            AuthUser registeruser = new AuthUser(mUsername.getText().toString(), mPassword.getText().toString());
            Call call = mAuthService.register(registeruser);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        FragmentManager activity = getActivity().getSupportFragmentManager();
                        activity.popBackStackImmediate();
                        activity
                                .beginTransaction()
                                .replace(R.id.fragment_container, new LoginFragment())
                                .addToBackStack(null)
                                .commit();
//                        getFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.fragment_container, new LoginFragment())
////                                .addToBackStack(null)
//                                .commit();
                    } else{
                        Toast.makeText(getContext(), "Error In Registration", Toast.LENGTH_SHORT).show();
                        enableRegisterButton();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                    enableRegisterButton();
                }
            });

        });

        return rootview;
    }

    private void disableRegisterButton() {
        mButtonRegister.setEnabled(false);
        mButtonRegister.setText(getString(R.string.networking_working));
        mButtonRegister.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonDisabled));
    }

    private void enableRegisterButton() {
        mButtonRegister.setEnabled(true);
        mButtonRegister.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorButtonBackground));
        mButtonRegister.setText(R.string.action_register);
    }
}
