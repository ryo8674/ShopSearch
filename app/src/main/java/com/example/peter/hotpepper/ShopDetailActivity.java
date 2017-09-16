package com.example.peter.hotpepper;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopDetailActivity extends AppCompatActivity {
    private static final String SHOP_ID = "id";
    private static final String GOURMET = "gourmet";


    ShopAsyncTask task;
    String shopId;
    ShopDao shopDao;
    SQLiteDatabase db;
    ShopDBHelper shopDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteil);
        shopDBHelper = new ShopDBHelper(this);
        db = shopDBHelper.getWritableDatabase();
        shopDao = new ShopDao(db);

        ListView detailList = (ListView) findViewById(R.id.shop_detail_list);
        detailList.setClickable(false);

        Map<String, String> param = new HashMap<>();
        shopId = getIntent().getStringExtra("shopCode");
        param.put(SHOP_ID, shopId);

        task = new ShopAsyncTask(this);
        task.execute(UrlUtils.createUri(GOURMET, param));

    }

    public void registBookmark(View view) {
        db.beginTransaction();
        if (((ToggleButton) view).isChecked()) {
            shopDao.insert(task.getShopList().get(0));
            Toast.makeText(this, "ブックマークに登録しました", Toast.LENGTH_SHORT).show();
        } else {
            shopDao.delete(task.getShopList().get(0));
            Toast.makeText(this, "ブックマークから削除しました", Toast.LENGTH_SHORT).show();
        }
        db.setTransactionSuccessful();
        List<ShopDto> list = shopDao.findAll();
        db.endTransaction();

    }
}
