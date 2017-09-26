package com.example.peter.hotpepper.async;

import android.os.AsyncTask;

import com.example.peter.hotpepper.dto.ShopDto;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.peter.hotpepper.util.Constants.ERROR_MESSAGE;

/**
 * 店舗情報を取得する非同期処理を行うクラス
 */
public class ShopAsyncTask extends AsyncTask<String, Void, String> {

    private List<ShopDto> shopDtoList;
    private final ShopTaskCallback callback;

    /**
     * コンストラクタ
     */
    public ShopAsyncTask(ShopTaskCallback callback) {
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

        callback.onSuccess(result);
    }

    /**
     * コールバックインタフェース
     */
    public interface ShopTaskCallback {
        void onSuccess(String result);

        void onError(String message);
    }
}
