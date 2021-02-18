package com.nalazoocare.threadanimauto;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-05-07
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments = new Fragment[3];

    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0] = new FragmentA();

        fragments[1] = new FragmentB();

        fragments[2] = new FragmentC();



    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
