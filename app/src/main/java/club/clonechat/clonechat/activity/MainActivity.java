package club.clonechat.clonechat.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import club.clonechat.clonechat.R;
import club.clonechat.clonechat.activity.main.CameraFragment;
import club.clonechat.clonechat.activity.main.FriendFragment;
import club.clonechat.clonechat.activity.main.SettingsFragment;
import club.clonechat.clonechat.network.AuthService;
import club.clonechat.clonechat.network.RetrofitInstance;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private AuthService mAuthService;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthService = RetrofitInstance.getRetrofitInstance(getApplicationContext()).create(AuthService.class);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.fragment_container_main);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FriendFragment.newInstance();
                case 1:
                    return CameraFragment.newInstance();
                case 2:
                    return SettingsFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
