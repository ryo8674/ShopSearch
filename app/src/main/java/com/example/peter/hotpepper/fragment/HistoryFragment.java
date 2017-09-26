package com.example.peter.hotpepper.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.activity.ShopDetailActivity;
import com.example.peter.hotpepper.adapter.ShopAdapter;
import com.example.peter.hotpepper.async.ShopAsyncTask;
import com.example.peter.hotpepper.db.ShopDao;
import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.dto.ShopResultApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;

/**
 * 履歴一覧を表示する Fragment
 */
public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener, ShopAsyncTask.ShopTaskCallback {

    private View view;
    private ListView shopList;
    private ShopDao shopDao;
    private List<ShopDto> shopDtoList;
    private ShopResultApi shopResultApi;

    /**
     * コンストラクタ
     */
    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        shopList = view.findViewById(R.id.shop_list);
        shopList.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO:履歴のプリファレンスの読み込み
        // paramの作成

//        ShopAsyncTask task = new ShopAsyncTask(view,this);
//        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), ShopDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.putExtra(SHOP_CODE, shopDtoList.get(position).getId());

        startActivity(intent);
    }

    @Override
    public void onSuccess(String result) {

        createObject(result);
        shopDtoList = shopResultApi.getResults().getShop();
//        shopDtoList = sortList(shopDtoList,);
        ShopAdapter adapter = new ShopAdapter(getContext(), shopDtoList);
        shopList.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
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

    private void createObject(String result) {
        Gson gson = new GsonBuilder().create();

        shopResultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());
    }

    private void readAllPreferences(SharedPreferences preferences) {
        Map<String, ?> map = preferences.getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();

            Object value = entry.getValue();
            String msg = String.format("%s=%s", key, value);
            Log.v("LogSample", msg);
        }
    }
}
