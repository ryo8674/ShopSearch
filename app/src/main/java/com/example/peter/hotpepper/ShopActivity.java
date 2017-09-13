package com.example.peter.hotpepper;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

        //URIを生成
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(HTTP_PROTOCOL);
        uriBuilder.authority(AUTHORITY);
        uriBuilder.path(PATH);
        uriBuilder.appendQueryParameter(USER_PARAMETER, USER_KEY);
        uriBuilder.appendQueryParameter(FORMAT_PARAMETER, FORMAT_KEY);
        uriBuilder.appendQueryParameter(LARGE_AREA_PARAMETER, areaCode);

        String utiStr = uriBuilder.toString();

        final ShopAsyncTask task = new ShopAsyncTask(this);
        task.execute(utiStr);

    }
}
