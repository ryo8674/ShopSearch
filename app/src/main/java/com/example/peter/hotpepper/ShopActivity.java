package com.example.peter.hotpepper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

public class ShopActivity extends BaseActivity {

    private static final String SHOP_LIST = "店舗一覧";
    private static final String AREA_CODE = "area_code";
    private static final String BUNDLE = "bundle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_shop);
        super.onCreate(savedInstanceState);
        super.setToolbarTitle(SHOP_LIST);

        String areaCode = getIntent().getStringExtra(AREA_CODE);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE, areaCode);

        // ListView表示用のフラグメントをセット
        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, shopFragment);
        transaction.commit();

    }
}
