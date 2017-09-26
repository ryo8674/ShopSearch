package com.example.peter.hotpepper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.peter.hotpepper.fragment.RecommendFragment;
import com.example.peter.hotpepper.fragment.ShopFragment;

import static com.example.peter.hotpepper.util.Constants.FAVORITE_PAGE;
import static com.example.peter.hotpepper.util.Constants.HISTORY_PAGE;
import static com.example.peter.hotpepper.util.Constants.RECOMMEND_PAGE;
import static com.example.peter.hotpepper.util.Constants.TAB_NAME;


public class ShopPagerAdapter extends FragmentPagerAdapter {

    public ShopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case RECOMMEND_PAGE:
                fragment = new RecommendFragment();
                break;
            case FAVORITE_PAGE:
                fragment = new ShopFragment();
                break;
            case HISTORY_PAGE:
                fragment = new ShopFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB_NAME.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_NAME[position];
    }
}
