package club.clonechat.clonechat.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import club.clonechat.clonechat.ui.main.camera.CameraFragment;
import club.clonechat.clonechat.ui.main.friends.FriendFragment;
import club.clonechat.clonechat.ui.main.messages.MessageFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int mCurrentPage;

    public MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("frag", "here frag");
        switch (position) {
            case 0:
                return MessageFragment.newInstance();
            case 1:
                return CameraFragment.newInstance();
            case 2:
                return FriendFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
