package com.mouris.mario.newspaper.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mouris.mario.newspaper.R;
import com.mouris.mario.newspaper.UI.CategoriesFragment.CategoriesFragment;
import com.mouris.mario.newspaper.UI.NewsFragment.NewsFragment;

public class MainPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private Context mContext;

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewsFragment();
        } else {
            return new CategoriesFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.recent_news_title);
            case 1:
                return mContext.getString(R.string.categories_title);
            default:
                    return null;
        }
    }
}
