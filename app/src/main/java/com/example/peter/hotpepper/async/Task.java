package com.example.peter.hotpepper.async;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.peter.hotpepper.util.Constants.ERROR_MESSAGE;

/**
 * 非同期処理を行うクラス
 * 通信成功：Jsonを返す
 */
public class Task extends AsyncTask<String, Void, String> {

    private final ShopTaskCallback callback;

    /**
     * コンストラクタ
     */
    public Task(ShopTaskCallback callback) {
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
