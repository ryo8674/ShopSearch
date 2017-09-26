package com.example.peter.hotpepper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.peter.hotpepper.fragment.RecommendFragment;
import com.example.peter.hotpepper.fragment.ShopFragment;


public class ShopPagerAdapter extends FragmentPagerAdapter {
    private static final int RECOMMEND_PAGE = 0;
    private static final int FAVORITE_PAGE = 1;
    private static final int HISTORY_PAGE = 2;
    private static final String[] TAB_NAME = {"おすすめ", "お気に入り", "履歴"};

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
