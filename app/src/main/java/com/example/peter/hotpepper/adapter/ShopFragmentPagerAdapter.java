package com.example.peter.hotpepper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.peter.hotpepper.fragment.ShopFragment;

/**
 * ブックマークと履歴を切り替えるアダプター
 */
public class ShopFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String BOOKMARK = "ブックマーク";
    private static final String HISTORY = "履歴";
    private static final String[] TAB_NAME = {BOOKMARK, HISTORY};

    /**
     * コンストラクタ
     */
    public ShopFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ShopFragment.newInstance(position);

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
