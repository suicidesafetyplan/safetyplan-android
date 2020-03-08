package com.moodtools.crisis.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by eddie_000 on 3/31/2015.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    Context mContext;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FragmentB.newInstance(0);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return FragmentC.newInstance(1);
            case 2: // Fragment # 1 - This will show SecondFragment
                return FragmentA.newInstance(2);
            default:
                return null;
        }
    }

        @Override
        public CharSequence getPageTitle ( int position){
            // Generate title based on item position
            String tabTitles[] = new String[]{mContext.getString(R.string.plan), mContext.getString(R.string.guide), mContext.getString(R.string.crisis)};
            return tabTitles[position];
        }
    }
