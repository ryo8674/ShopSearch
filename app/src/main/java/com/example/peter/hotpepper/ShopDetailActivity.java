package com.example.peter.hotpepper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class ShopDetailActivity extends AppCompatActivity{
    private static final String SHOP_ID = "id";
    private static final String GOURMET = "gourmet";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteil);

        ListView detailList = (ListView) findViewById(R.id.shop_detail_list);
        detailList.setClickable(false);

        Map<String, String> param = new HashMap<>();
        param.put(SHOP_ID, getIntent().getStringExtra("shopCode"));

        ShopAsyncTask task = new ShopAsyncTask(this);
        task.execute(UrlUtils.createUri(GOURMET,param));
    }

}
