package com.example.peter.hotpepper.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.peter.hotpepper.dto.ShopDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.peter.hotpepper.util.Constants.PREFERENCE_MAX_SIZE;
import static com.example.peter.hotpepper.util.Constants.PREFERENCE_NAME;
import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;

/**
 * 履歴一覧を表示する Fragment
 */
public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView shopList;
    private List<ShopDto> shopDtoList;
    private Gson gson;

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
        shopList = view.findViewById(R.id.shop_list);
        shopList.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        gson = new Gson();
        shopDtoList = readAllPreferences();

        ShopAdapter adapter = new ShopAdapter(getContext(), shopDtoList);
        shopList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), ShopDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.putExtra(SHOP_CODE, shopDtoList.get(position).getId());

        startActivity(intent);
    }

    /**
     * プリファレンスの全件読み込み
     * 件数が20件より多い場合、20件以降を削除する
     */
    private List<ShopDto> readAllPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<ShopDto> list = new ArrayList<>();
        Map<String, ?> map = sharedPreferences.getAll();
        for (String key : map.keySet()) {
            ShopDto value = gson.fromJson(sharedPreferences.getString(key, null), ShopDto.class);
            list.add(value);
        }

        sortDate(list);

        // 20件より多い場合、削除
        while (list.size() > PREFERENCE_MAX_SIZE) {
            list.remove(list.size() - 1);
            editor.remove(list.get(list.size() - 1).getId());
        }
        editor.apply();

        return list;
    }

    /**
     * リストを閲覧時間の最新順にソート
     */
    private void sortDate(List<ShopDto> list) {
        Collections.sort(list, new Comparator<ShopDto>() {
            @Override
            public int compare(ShopDto shopDto1, ShopDto shopDto2) {
                if (shopDto1.getDate() == null || shopDto2.getDate() == null)
                    return 0;
                if (shopDto1.getDate().getTime() > shopDto2.getDate().getTime())
                    return -1;
                if (shopDto1.getDate().getTime() < shopDto2.getDate().getTime())
                    return 1;
                return shopDto1.getDate().compareTo(shopDto2.getDate());
            }
        });
    }
}
