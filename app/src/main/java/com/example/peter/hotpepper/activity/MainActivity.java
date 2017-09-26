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

//        toolbar.setTitle("aaa");

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Tab 1").setIcon(R.drawable.tab_recommend_selector));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2").setIcon(R.drawable.tab_favorite_selector));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3").setIcon(R.drawable.tab_history_selector));

        FragmentManager fragmentManager = getSupportFragmentManager();
        ShopPagerAdapter adapter = new ShopPagerAdapter(fragmentManager);
        // ViewPagerにAdapterをセット
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
