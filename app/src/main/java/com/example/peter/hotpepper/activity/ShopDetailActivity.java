package com.example.peter.hotpepper.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.async.ShopAsyncTask;
import com.example.peter.hotpepper.db.ShopDBHelper;
import com.example.peter.hotpepper.db.ShopDao;
import com.example.peter.hotpepper.util.UriUtil;

import java.util.HashMap;
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
public class ShopDetailActivity extends BaseActivity {

    private ShopDao shopDao;
    private SQLiteDatabase db;
    private ShopAsyncTask task;
    public static boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_detail);
        super.onCreate(savedInstanceState);

        initDao();

        // 非同期に渡す値の設定
        if (getIntent() != null) {
            String shopId = getIntent().getStringExtra(SHOP_CODE);
            Map<String, String> param = new HashMap<>();
            param.put(SHOP_ID, shopId);
            flag = buttonFlag(shopId);

            task = new ShopAsyncTask(ShopDetailActivity.this);
            task.execute(UriUtil.createUri(GOURMET, param));
        }
    }

    /**
     * ブックマークボタンの処理
     */
    public void registerBookmark(View view) {
        Button button = (Button) view;
        flag = !flag;
        db.beginTransaction();
        if (flag) {
            shopDao.insert(task.getShopList().get(0));
            button.setText(REGISTER_BUTTON);
            Toast.makeText(this, REGISTER_BOOKMARK, Toast.LENGTH_SHORT).show();
        } else {
            shopDao.delete(task.getShopList().get(0));
            button.setText(DELETE_BUTTON);
            Toast.makeText(this, DELETE_BOOKMARK, Toast.LENGTH_SHORT).show();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * buttonの状態を取得
     */
    private boolean buttonFlag(String id) {
        initDao();
        return shopDao.findById(id) != null;
    }

    /**
     * Dao初期化
     */
    private void initDao() {
        ShopDBHelper helper = new ShopDBHelper(this);
        db = helper.getWritableDatabase();
        shopDao = new ShopDao(db);
    }
}
