package com.example.peter.hotpepper.usecase;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.dto.ShopResultApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.PREFERENCE_MAX_SIZE;
import static com.example.peter.hotpepper.util.Constants.PREFERENCE_NAME;

public class ShopListUseCase {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private List<ShopDto> shopDtoList;

    /** コンストラクタ */
    public ShopListUseCase(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * JSONをPreferenceに登録
     * <br> key: shopId
     * <br> value: Json
     */
    public void registerPreference(String result) {
        editor = sharedPreferences.edit();
        createObject(result);

        // 保存されているshopIdの場合、削除
        for (String id : sharedPreferences.getAll().keySet()) {
            if (id.equals(shopDtoList.get(0).getId())) {
                removeShopId(id);
            }
        }

        for (ShopDto shopDto : shopDtoList) {
            shopDto.setDate(new Date());
            editor.putString(shopDto.getId(), gson.toJson(shopDto));
        }
        editor.apply();

        removePreference();
    }

    /**
     * プリファレンスの全件読み込み
     */
    public List<ShopDto> loadPreferences() {
        List<ShopDto> list = new ArrayList<>();
        gson = new Gson();
        Map<String, ?> map = sharedPreferences.getAll();
        for (String key : map.keySet()) {
            ShopDto value = gson.fromJson(sharedPreferences.getString(key, null), ShopDto.class);
            list.add(value);
        }

        sortDate(list);

        return list;
    }

    /**
     * 件数が20件より多い場合、21件目以降を削除する
     */
    private void removePreference() {
        List<ShopDto> list = loadPreferences();
        sortDate(list);

        // 20件より多い場合、削除
        while (list.size() > PREFERENCE_MAX_SIZE) {
            removeShopId(list.get(list.size() - 1).getId());
            list.remove(list.size() - 1);
        }
    }

    /**
     * preferenceからshopIdのレコードを削除
     */
    private void removeShopId(String shopId) {
        editor = sharedPreferences.edit();
        editor.remove(shopId);
        editor.apply();
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

    /**
     * JSONをオブジェクトに変換
     */
    private void createObject(String result) {
        gson = new GsonBuilder().create();

        ShopResultApi shopResultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());
        shopDtoList = shopResultApi.getResults().getShop();
    }
}
