package com.example.peter.hotpepper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    private static final String HTTP_PROTOCOL = "http";
    private static final String AUTHORITY = "webservice.recruit.co.jp";
    private static final String PATH = "hotpepper/gourmet/v1";
    private static final String USER_PARAMETER = "key";
    private static final String USER_KEY = "8928fba69a934d6e";
    private static final String FORMAT_PARAMETER = "format";
    private static final String FORMAT_KEY = "json";
    private static final String LARGE_AREA_PARAMETER = "large_area";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        String areaCode = getIntent().getStringExtra("areaCode");
        Bundle bundle = new Bundle();
        bundle.putString("bundle",areaCode);

        // ListView表示用のフラグメントをセット
        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, shopFragment);
        transaction.commit();

    }
}
