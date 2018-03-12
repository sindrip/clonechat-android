package club.clonechat.clonechat.ui.main;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import club.clonechat.clonechat.R;

public class DepthPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageIndex = (int) page.getTag();

        if(pageIndex == 1) {
            page.setTranslationX(-1 * position * page.getWidth());
            page.setTranslationZ(-1);
            FrameLayout fl = page.findViewById(R.id.background_fader);
            if( position == 0.0F ) {
                fl.setAlpha(0.0F);
            } else {
                fl.setAlpha(Math.abs(position));
            }
            return;
        }
    }
}
