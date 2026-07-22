package com.moodtools.crisis.app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Created by eddie_000 on 3/31/2015.
 */
public class SampleFragmentPagerAdapter extends FragmentStateAdapter {
    private static final int PAGE_COUNT = 3;

    public SampleFragmentPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
