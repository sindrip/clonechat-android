package club.clonechat.clonechat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.activity.authentcation.WelcomeFragment;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new WelcomeFragment())
                .commit();
    }
}
