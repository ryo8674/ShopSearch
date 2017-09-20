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
import com.example.peter.hotpepper.async.ShopAsyncTask;
import com.example.peter.hotpepper.db.ShopDBHelper;
import com.example.peter.hotpepper.db.ShopDao;
import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.util.UriUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.BUNDLE_KEY;
import static com.example.peter.hotpepper.util.Constants.GOURMET;
import static com.example.peter.hotpepper.util.Constants.LARGE_AREA;
import static com.example.peter.hotpepper.util.Constants.SEPARATE;
import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;
import static com.example.peter.hotpepper.util.Constants.SHOP_ID;

/**
 * 店舗一覧を表示するFragment
 */
public class ShopFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ShopAsyncTask task;
    private View view;
    private ShopDao shopDao;

    /**
     * コンストラクタ
     */
    public ShopFragment() {
    }

    public static ShopFragment newInstance(int page) {
        ShopFragment fragment = new ShopFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY, page);
        fragment.setArguments(bundle);
        return fragment;
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

        ListView shopList = view.findViewById(R.id.shop_list);
        shopList.setOnItemClickListener(this);

        ShopDBHelper helper = new ShopDBHelper(view.getContext());
        shopDao = new ShopDao(helper.getReadableDatabase());
    }

    @Override
    public void onResume() {
        super.onResume();
        List<ShopDto> dtoList = null;
        Bundle bundle = getArguments();
        Map<String, String> param = new HashMap<>();
        if (bundle != null) {
            if (bundle.getString(BUNDLE_KEY) instanceof String) {
                param.put(LARGE_AREA, bundle.getString(BUNDLE_KEY));

            } else {
                // bookmark DBの読み込み
                dtoList = shopDao.findAll();
                StringBuilder builder = new StringBuilder();
                for (ShopDto shopDto : dtoList) {
                    builder.append(shopDto.getId());
                    if (!shopDto.equals(dtoList.get(dtoList.size() - 1))) {
                        builder.append(SEPARATE);
                    }
                }
                param.put(SHOP_ID, builder.toString());

            }

            task = new ShopAsyncTask(view, dtoList);
            task.execute(UriUtil.createUri(GOURMET, param));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), ShopDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        if (task.getShopList().size() != 0) {
            intent.putExtra(SHOP_CODE, task.getShopList().get(position).getId());
        }
        startActivity(intent);
    }
}

/*
 * @Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
super.onViewCreated(view, savedInstanceState);
this.view = view;
ListView shopList = view.findViewById(R.id.shop_list);
shopList.setOnItemClickListener(this);

ShopDBHelper helper = new ShopDBHelper(view.getContext());
shopDao = new ShopDao(helper.getReadableDatabase());
}

 @Override
 public void onResume() {
 super.onResume();
 List<ShopDto> dtoList = null;
 Bundle bundle = getArguments();
 Map<String, String> param = new HashMap<>();
 if (bundle != null) {
 param.put(LARGE_AREA, bundle.getString(BUNDLE_KEY));
 } else {
 // bookmark DBの読み込み
 dtoList = shopDao.findAll();
 StringBuilder builder = new StringBuilder();
 for (ShopDto shopDto : dtoList) {
 builder.append(shopDto.getId());
 if (!shopDto.equals(dtoList.get(dtoList.size() - 1))) {
 builder.append(SEPARATE);
 }
 }
 param.put(SHOP_ID, builder.toString());
 }

 task = new ShopAsyncTask(view, dtoList);
 task.execute(UriUtil.createUri(GOURMET, param));
 }
 */
