package com.example.peter.hotpepper.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

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

import static com.example.peter.hotpepper.util.Constants.ERROR_MESSAGE;

/**
 * 店舗情報を取得する非同期処理を行うクラス
 */
public class ShopAsyncTask extends AsyncTask<String, Void, String> {

    private View rootView;
    private List<ShopDto> shopDtoList;
    private Activity activity;
    private final ShopTaskCallback callback;

    /**
     * コンストラクタ
     */
    public ShopAsyncTask(View view, ShopTaskCallback callback) {
        rootView = view;
        this.callback = callback;
    }

    /**
     * ブックマーク画面用コンストラクタ
     */
    public ShopAsyncTask(Activity activity, ShopTaskCallback callback) {
        this.activity = activity;
        this.callback = callback;
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
        if (result.isEmpty()) {
            callback.onError(ERROR_MESSAGE);
            return;
        }

        Gson gson = new GsonBuilder().create();
        shopDtoList = new ArrayList<>();

        ShopResultApi resultApi = gson.fromJson(result, new TypeToken<ShopResultApi>() {
        }.getType());

        callback.onSuccess(resultApi);
    }

    /**
     * コールバックインタフェース
     */
    public interface ShopTaskCallback {
        void onSuccess(ShopResultApi shopResultApi);

        void onError(String message);
    }
}
