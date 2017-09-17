package com.example.peter.hotpepper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

public class BookmarkActivity extends BaseActivity {
    private static final String BOOKMARK_LIST = "ブックマーク一覧";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_bookmark);
        super.onCreate(savedInstanceState);
        super.setToolbarTitle(BOOKMARK_LIST);

        // ListView表示用のフラグメントをセット
        ShopFragment shopFragment = new ShopFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, shopFragment);
        transaction.commit();

    }
}
