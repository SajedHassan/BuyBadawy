package com.example.user.eshtri_first_pafge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStrip.IconTabProvider;
import com.example.user.eshtri_first_pafge.R.drawable;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements IconTabProvider {
	final int PAGE_COUNT = 2;
	private final String[] tabTitles = { "Tab1", "Tab2" };
	private final int[] tabIcons = { drawable.ic_home_black_24dp, drawable.ic_add_shopping_cart_black_24dp };

	public SampleFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getPageIconResId(int position) {
		return this.tabIcons[position];
	}

	@Override
	public int getCount() {
		return this.PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		position++;
		switch (position) {
		case 1:
			return MainFragment.newInstance(position);

		case 2:
			return MyProductsFragment.newInstance(position);
		default:
			// unreachable case
			return null;
		}
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// Generate title based on item position
		return this.tabTitles[position];
	}

}