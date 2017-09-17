package com.example.peter.hotpepper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopFragment extends Fragment {

    private static final String GOURMET = "gourmet";
    private static final String SHOP_CODE = "shop_code";
    private static final String SEPARATE = ",";
    private static final String ID = "id";
    private static final String LARGE_AREA = "large_area";
    private static final String BUNDLE = "bundle";

    private ListView shopList;
    private View rootView;
    private ShopDao shopDao;

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
        shopList = view.findViewById(R.id.shop_list);
        rootView = view;
        ShopDBHelper helper = new ShopDBHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        shopDao = new ShopDao(db);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<ShopDto> list = null;
        Map<String, String> param = new HashMap<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            param.put(LARGE_AREA, bundle.getString(BUNDLE));
        } else {
            StringBuilder builder = new StringBuilder();
            list = shopDao.findAll();
            for (ShopDto shopDto : list) {
                builder.append(shopDto.getId());
                if (!shopDto.equals(list.get(list.size() - 1))) {
                    builder.append(SEPARATE);
                }
            }
            param.put(ID, builder.toString());
        }
        final ShopAsyncTask task = new ShopAsyncTask(rootView, list);
        task.execute(UrlUtils.createUri(GOURMET, param));

        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ShopDetailActivity.class);
                intent.putExtra(SHOP_CODE, task.getShopList().get(position).getId());
                startActivity(intent);
            }
        });
    }

}
