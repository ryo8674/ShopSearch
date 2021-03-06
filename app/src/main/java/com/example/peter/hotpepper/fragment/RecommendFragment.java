package com.example.peter.hotpepper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.activity.ShopDetailActivity;
import com.example.peter.hotpepper.adapter.ShopAdapter;
import com.example.peter.hotpepper.async.Task;
import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.dto.ShopResultApi;
import com.example.peter.hotpepper.util.UriUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.GOURMET;
import static com.example.peter.hotpepper.util.Constants.LARGE_AREA;
import static com.example.peter.hotpepper.util.Constants.LARGE_AREA_DEFAULT;
import static com.example.peter.hotpepper.util.Constants.ORDER;
import static com.example.peter.hotpepper.util.Constants.ORDER_VALUE;
import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;

/**
 * おすすめ一覧を表示する Fragment
 */
public class RecommendFragment extends Fragment implements AdapterView.OnItemClickListener, Task.ShopTaskCallback {

    private ListView shopList;
    private List<ShopDto> shopDtoList;

    /**
     * コンストラクタ
     */
    public RecommendFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopList = view.findViewById(R.id.shop_list);
        shopList.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        // paramの作成
        Map<String, String> param = new HashMap<>();
        param.put(ORDER, ORDER_VALUE);
        param.put(LARGE_AREA, LARGE_AREA_DEFAULT);

        Task task = new Task(this);
        task.execute(UriUtil.createUri(GOURMET, param));
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
        ShopAdapter adapter = new ShopAdapter(getContext(), shopDtoList);
        shopList.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
    }

    /**
     * JsonをShopDtoのリストに変換
     * @param result Json
     */
    private void createObject(String result){
        Gson gson = new GsonBuilder().create();

        ShopResultApi shopResultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());
        shopDtoList = shopResultApi.getResults().getShop();
    }
}
