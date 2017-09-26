package com.example.peter.hotpepper.async;

import android.os.AsyncTask;

import com.example.peter.hotpepper.dto.AreaResultApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.peter.hotpepper.util.Constants.ERROR_MESSAGE;

/**
 * 大エリアを取得する非同期処理を行うクラス
 */
public class AreaAsyncTask extends AsyncTask<String, Void, String> {

    private final AreaTaskCallback callback;

    /**
     * コンストラクタ
     */
    public AreaAsyncTask(AreaTaskCallback callback) {
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

        AreaResultApi resultApi = gson.fromJson(result, new TypeToken<AreaResultApi>() {
        }.getType());

        callback.onSuccess(resultApi);
    }

    /**
     * コールバックインタフェース
     */
    public interface AreaTaskCallback {
        void onSuccess(AreaResultApi areaResultApi);

        void onError(String message);
    }
}
