package com.example.peter.hotpepper;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttpでJSONを取得し、Gsonでパースする
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class ShopAsyncTask extends AsyncTask<String, Void, String> {

    private static final int ROOT_VIEW_FLAG = 0;
    private static final int ACTIVITY_FLAG = 1;

    private List<ShopDto> shopList;
    private View rootView;
    private final int flag;
    private Activity activity;

    /**
     * コンストラクタ
     *
     * @param rootView rootView
     */
    ShopAsyncTask(View rootView) {
        this.rootView = rootView;
        this.flag = ROOT_VIEW_FLAG;
    }

    ShopAsyncTask(Activity activity) {
        this.activity = activity;
        this.flag = ACTIVITY_FLAG;
    }

    /**
     * 非同期で行う処理
     *
     * @param uri uri
     * @return 処理結果
     */
    @Override
    protected String doInBackground(String... uri) {
        String result;

        // リクエストオブジェクトの生成
        Request request = new Request.Builder()
                .url(uri[0])
                .get()
                .build();

        // クライアントオブジェクトの生成
        OkHttpClient client = new OkHttpClient();

        // リクエストして結果を受け取る
        try {
            Response response = client.newCall(request).execute();
            //noinspection ConstantConditions
            result = response.body().string();
        } catch (IOException e) {
            return null;
        }
        return result;
    }

    /**
     * doInBackgroundの後の処理
     *
     * @param result 非同期処理の結果
     */
    @Override
    protected void onPostExecute(String result) {
        // Gsonの生成
        Gson gson = new GsonBuilder().create();
        // デシリアライズ
        ShopResultApi shopResultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());
        shopList = new ArrayList<>();
        if (shopResultApi != null) {
            shopList = shopResultApi.getResults().getShop();
        }
        ListView listView;
        if (flag == ROOT_VIEW_FLAG) {
            ShopAdapter adapter = new ShopAdapter(rootView.getContext(), shopList);
            listView = rootView.findViewById(R.id.shop_list);
            listView.setAdapter(adapter);
        } else {
            if (shopList.size() != ROOT_VIEW_FLAG) {
                ShopDetailAdapter adapter = new ShopDetailAdapter(activity, shopList.get(0));
                listView = activity.findViewById(R.id.shop_detail_list);
                listView.setClickable(false);
                listView.setAdapter(adapter);
            }
        }

    }

    List<ShopDto> getShopList() {
        return shopList;
    }

}
