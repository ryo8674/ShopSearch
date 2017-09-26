package com.example.peter.hotpepper.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.adapter.ShopDetailAdapter;
import com.example.peter.hotpepper.async.ShopAsyncTask;
import com.example.peter.hotpepper.db.ShopDao;
import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.dto.ShopResultApi;
import com.example.peter.hotpepper.util.UriUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.DELETE_BOOKMARK;
import static com.example.peter.hotpepper.util.Constants.DELETE_BUTTON;
import static com.example.peter.hotpepper.util.Constants.GOURMET;
import static com.example.peter.hotpepper.util.Constants.REGISTER_BOOKMARK;
import static com.example.peter.hotpepper.util.Constants.REGISTER_BUTTON;
import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;
import static com.example.peter.hotpepper.util.Constants.SHOP_ID;


/**
 * 店舗詳細画面のActivity
 */
public class ShopDetailActivity extends AppCompatActivity implements ShopAsyncTask.ShopTaskCallback {

    private ShopDao shopDao;
    public static boolean flag;
    private Toolbar toolbar;
    private List<ShopDto> shopDtoList;
    private ShopResultApi shopResultApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // 非同期に渡す値の設定
        if (getIntent() != null) {
            String shopId = getIntent().getStringExtra(SHOP_CODE);
            if (shopId != null) {
                Map<String, String> param = new HashMap<>();
                param.put(SHOP_ID, shopId);
                flag = isButtonPressed(shopId);

                ShopAsyncTask task = new ShopAsyncTask(this);
                task.execute(UriUtil.createUri(GOURMET, param));
            }
        }
    }


    /**
     * ブックマークボタンの処理
     */
    public void registerBookmark(View view) {
        Button button = (Button) view;
        flag = !flag;
        shopDao = new ShopDao(this);

        if (flag) {
            shopDao.insert(shopDtoList.get(0));
            button.setText(DELETE_BUTTON);
            Toast.makeText(this, REGISTER_BOOKMARK, Toast.LENGTH_SHORT).show();
        } else {
            shopDao.delete(shopDtoList
                    .get(0));
            button.setText(REGISTER_BUTTON);
            Toast.makeText(this, DELETE_BOOKMARK, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * buttonの状態を取得
     */
    private boolean isButtonPressed(String id) {
        shopDao = new ShopDao(this);
        return shopDao.findById(id) != null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String result) {
        createObject(result);
        shopDtoList = shopResultApi.getResults().getShop();
        ShopDetailAdapter adapter = new ShopDetailAdapter(this, shopDtoList.get(0));
        ListView listView = (ListView) findViewById(R.id.shop_detail_list);
        listView.setAdapter(adapter);
        toolbar.setTitle(shopDtoList.get(0).getName());
    }

    @Override
    public void onError(String message) {
    }

    private void createObject(String result) {
        Gson gson = new GsonBuilder().create();

        shopResultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());
    }

    private void registerPreference(String result) {
        SharedPreferences sharedPreferences = getSharedPreferences("file", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("", result);
        editor.apply();
    }
}
