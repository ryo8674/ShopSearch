package com.example.peter.hotpepper;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ShopDetailActivity extends BaseActivity {
    private static final String SHOP_ID = "id";
    private static final String GOURMET = "gourmet";
    private static final String REGISTER_BOOKMARK = "ブックマークに登録しました。";
    private static final String DELETE_BOOKMARK = "ブックマークから削除しました。";
    private static final String SHOP_CODE = "shop_code";

    private ShopAsyncTask task;
    private ShopDao shopDao;
    private SQLiteDatabase db;
    private Boolean flag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_deteil);
        super.onCreate(savedInstanceState);

        ShopDBHelper shopDBHelper = new ShopDBHelper(this);
        db = shopDBHelper.getWritableDatabase();
        shopDao = new ShopDao(db);

        Map<String, String> param = new HashMap<>();
        String shopId = getIntent().getStringExtra(SHOP_CODE);
        param.put(SHOP_ID, shopId);

        task = new ShopAsyncTask(ShopDetailActivity.this);
        task.execute(UrlUtils.createUri(GOURMET, param));
    }

    public void registerBookmark(View view) {
        Button button = (Button)view;
        flag = !flag;
        db.beginTransaction();
        if (flag) {
            shopDao.insert(task.getShopList().get(0));
            button.setText(getResources().getString(R.string.delete_bookmark));
            Toast.makeText(this, REGISTER_BOOKMARK, Toast.LENGTH_SHORT).show();
        } else {
            shopDao.delete(task.getShopList().get(0));
            button.setText(getResources().getString(R.string.register_bookmark));
            Toast.makeText(this, DELETE_BOOKMARK, Toast.LENGTH_SHORT).show();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

}

