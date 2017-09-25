package com.example.peter.hotpepper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.fragment.ShopFragment;

import static com.example.peter.hotpepper.util.Constants.AREA_CODE;
import static com.example.peter.hotpepper.util.Constants.BUNDLE_KEY;

/**
 * 店舗一覧画面のActivity
 */
public class ShopActivity extends BaseActivity {

    @Override
    int setLayoutResourceId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String areaCode = getIntent().getStringExtra(AREA_CODE);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, areaCode);

        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, shopFragment);
        transaction.commit();
    }
}
