package com.example.peter.hotpepper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.adapter.ShopPagerAdapter;

public class MainActivity extends BaseActivity {
    private static final int[] ICONS = {R.drawable.tab_recommend_selector, R.drawable.tab_favorite_selector, R.drawable.tab_history_selector};

    @Override
    int setLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitleEnabled(false);
        super.setTitle(getResources().getString(R.string.app_name));

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ShopPagerAdapter adapter = new ShopPagerAdapter(fragmentManager);
        // ViewPagerにAdapterをセット
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        // tabにセレクタを設定
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(ICONS[i]);
        }

    }
}
