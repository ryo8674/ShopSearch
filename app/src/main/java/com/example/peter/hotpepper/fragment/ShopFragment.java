package com.example.peter.hotpepper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
import com.example.peter.hotpepper.util.UriUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.BUNDLE_KEY;
import static com.example.peter.hotpepper.util.Constants.GOURMET;
import static com.example.peter.hotpepper.util.Constants.LARGE_AREA;
import static com.example.peter.hotpepper.util.Constants.SEPARATE;
import static com.example.peter.hotpepper.util.Constants.SHOP_ACTIVITY;
import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;
import static com.example.peter.hotpepper.util.Constants.SHOP_ID;

/**
 * 店舗一覧を表示するFragment
 */
public class ShopFragment extends Fragment implements AdapterView.OnItemClickListener,ShopAsyncTask.ShopTaskCallback {

    private View view;
    private ShopDao shopDao;
    private ListView shopList;
    private List<ShopDto> shopDtoList;
    private List<ShopDto> idList;
    private ShopResultApi shopResultApi;

    /**
     * コンストラクタ
     */
    public ShopFragment() {
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

        shopDao = new ShopDao(view.getContext());
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        Map<String, String> param = new HashMap<>();
        if (bundle != null) {
            param.put(LARGE_AREA, bundle.getString(BUNDLE_KEY));
        } else {
            // bookmark DBの読み込み
            idList = shopDao.findAll();
            StringBuilder builder = new StringBuilder();
            for (ShopDto shopDto : idList) {
                builder.append(shopDto.getId());
                if (!shopDto.equals(idList.get(idList.size() - 1))) {
                    builder.append(SEPARATE);
                }
            }
            param.put(SHOP_ID, builder.toString());
        }

        ShopAsyncTask task = new ShopAsyncTask(this);
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
        shopDtoList = shopResultApi.getResults().getShop();

        if (idList != null) {
            shopDtoList = sortList(shopDtoList, idList);
        } else {
            Toolbar toolbar = view.getRootView().findViewById(R.id.toolbar);
            toolbar.setTitle(shopDtoList.get(0).getLargeArea().getName() + SHOP_ACTIVITY);
        }

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

    private void createObject(String result){
        Gson gson = new GsonBuilder().create();

        shopResultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());
    }
}