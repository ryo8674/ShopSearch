package com.example.peter.hotpepper.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.adapter.ShopAdapter;
import com.example.peter.hotpepper.adapter.ShopDetailAdapter;
import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.dto.ShopResultApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.peter.hotpepper.util.Constants.ACTIVITY_FLAG;
import static com.example.peter.hotpepper.util.Constants.ROOT_VIEW_FLAG;
import static com.example.peter.hotpepper.util.Constants.SHOP_ACTIVITY;

/**
 * 店舗情報を取得する非同期処理を行うクラス
 */
public class ShopAsyncTask extends AsyncTask<String, Void, String> {

    private View rootView;
    private List<ShopDto> shopDtoList;
    private Activity activity;
    private List<ShopDto> idList;
    private final int flag;

    /**
     * コンストラクタ
     *
     * @param idList ブックマークしているidのリスト
     */
    public ShopAsyncTask(View view, List<ShopDto> idList) {
        rootView = view;
        this.idList = idList;
        flag = ROOT_VIEW_FLAG;
    }

    /**
     * ブックマーク画面用コンストラクタ
     */
    public ShopAsyncTask(Activity activity) {
        this.activity = activity;
        flag = ACTIVITY_FLAG;
    }

    @Override
    protected String doInBackground(String... uri) {
        String result = null;

        Request request = new Request.Builder()
                .url(uri[0])
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Gson gson = new GsonBuilder().create();
        shopDtoList = new ArrayList<>();

        ShopResultApi resultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());

        if (resultApi != null) {
            shopDtoList = resultApi.getResults().getShop();
        }


        ListView listView;
        Toolbar toolbar;
        if (flag == ROOT_VIEW_FLAG) {
            if (shopDtoList == null) {
                shopDtoList = new ArrayList<>();
            }
            if (idList != null) {
                shopDtoList = sortList(shopDtoList, idList);
            } else {
                toolbar = rootView.getRootView().getRootView().findViewById(R.id.toolbar);
                toolbar.setTitle(shopDtoList.get(0).getLargeArea().getName() + SHOP_ACTIVITY);
            }
            ShopAdapter adapter = new ShopAdapter(rootView.getContext(), shopDtoList);
            listView = rootView.getRootView().findViewById(R.id.shop_list);
            listView.setAdapter(adapter);
        } else if (flag == ACTIVITY_FLAG) {
            if(shopDtoList.size() != 0) {
                ShopDetailAdapter adapter = new ShopDetailAdapter(activity, shopDtoList.get(0));
                listView = activity.findViewById(R.id.shop_detail_list);
                listView.setClickable(false);
                listView.setAdapter(adapter);
                toolbar = activity.findViewById(R.id.toolbar);
                toolbar.setTitle(shopDtoList.get(0).getName());
            }
        }
    }

    /**
     * 店舗情報のリストを取得
     */
    public List<ShopDto> getShopList() {
        return shopDtoList;
    }

    /**
     * リストを登録時間順にソート
     */
    private List<ShopDto> sortList(List<ShopDto> targetList, List<ShopDto> idList) {
        List<ShopDto> result = new ArrayList<>();
        for (ShopDto id : idList) {
            for (ShopDto target : targetList) {
                if (target.getId().equals(id.getId())) {
                    result.add(target);
                }
            }
        }
        return result;
    }
}
