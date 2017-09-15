package com.example.peter.hotpepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class ShopFragment extends Fragment {

    private static final String GOURMET = "gourmet";

    private ListView shopList;
    private View rootView;
    private Bundle bundle;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        bundle = getArguments();
        Map<String, String> param = new HashMap<>();
        param.put("large_area", bundle.getString("bundle"));
        final ShopAsyncTask task = new ShopAsyncTask(rootView,0);
        task.execute(UrlUtils.createUri(GOURMET, param));

        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),ShopDetailActivity.class);
                intent.putExtra("shopCode", task.getShopList().get(position).getId());
                startActivity(intent);
            }
        });
    }

}
