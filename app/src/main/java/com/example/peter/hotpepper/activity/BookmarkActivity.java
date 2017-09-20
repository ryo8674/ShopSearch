package com.example.peter.hotpepper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.adapter.ShopFragmentPagerAdapter;

import static com.example.peter.hotpepper.util.Constants.BOOKMARK_ACTIVITY;

/**
 * ブックマーク一覧画面のActivity
 */
public class BookmarkActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_bookmark);
        super.onCreate(savedInstanceState);
        super.setTitle(BOOKMARK_ACTIVITY);

//        ShopFragment shopFragment = new ShopFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, shopFragment);
//        transaction.commit();

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Adapterの準備
        FragmentManager fragmentManager = getSupportFragmentManager();
        ShopFragmentPagerAdapter adapter = new ShopFragmentPagerAdapter(fragmentManager);
        // ViewPagerにAdapterをセット
        viewPager.setAdapter(adapter);

        // TabLayoutをViewPagerと紐付け
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
