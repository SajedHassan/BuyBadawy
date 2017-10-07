package com.example.user.eshtri_first_pafge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Tab1", "Tab2"};
    private int[] tabIcons = new int[]{R.drawable.ic_home_black_24dp, R.drawable.ic_add_shopping_cart_black_24dp};

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        position++;
        switch (position) {
            case 1: {
                return MainFragment.newInstance(position);

            }
            case 2: {
                return MyProductsFragment.newInstance(position);
            }
            default: {
                //unreachable case
                return null;
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


}