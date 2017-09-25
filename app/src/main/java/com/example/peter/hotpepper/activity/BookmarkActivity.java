package com.example.peter.hotpepper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.fragment.ShopFragment;

import static com.example.peter.hotpepper.util.Constants.BOOKMARK_ACTIVITY;

/**
 * ブックマーク一覧画面のActivity
 */
public class BookmarkActivity extends BaseActivity {

    @Override
    int setLayoutResourceId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(BOOKMARK_ACTIVITY);

        ShopFragment shopFragment = new ShopFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, shopFragment);
        transaction.commit();

    }
}
