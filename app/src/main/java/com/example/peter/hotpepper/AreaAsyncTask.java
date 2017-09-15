package com.example.peter.hotpepper;

import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttpでJSONを取得し、Gsonでパースする
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class AreaAsyncTask extends AsyncTask<String, Void, String> {

    private final AreaActivity mActivity;
    private List<LargeAreaDto> largeAreaDto;

    /**
     * コンストラクタ
     *
     * @param mActivity Activity
     */
    AreaAsyncTask(AreaActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 非同期で行う処理
     *
     * @param uri uri
     * @return 処理結果
     */
    @Override
    protected String doInBackground(String... uri) {
        String result;

        // リクエストオブジェクトの生成
        Request request = new Request.Builder()
                .url(uri[0])
                .get()
                .build();

        // クライアントオブジェクトの生成
        OkHttpClient client = new OkHttpClient();

        // リクエストして結果を受け取る
        try {
            Response response = client.newCall(request).execute();
            //noinspection ConstantConditions
            result = response.body().string();
        } catch (IOException e) {
            return null;
        }
        return result;
    }

    /**
     * doInBackgroundの後の処理
     *
     * @param result 非同期処理の結果
     */
    @Override
    protected void onPostExecute(String result) {
        // Gsonの生成
        Gson gson = new GsonBuilder().create();
        // デシリアライズ
        AreaResultApi areaResultApi = gson.fromJson(result, new TypeToken<AreaResultApi>() {
        }.getType());

        largeAreaDto = new ArrayList<>();
        if (areaResultApi != null) {
            largeAreaDto = areaResultApi.getResults().getLargeAreaDto();
        }

        AreaArrayAdapter adapter = new AreaArrayAdapter(mActivity, largeAreaDto);
        ListView listView = (ListView) mActivity.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    List<LargeAreaDto> getAreaList() {
        return largeAreaDto;
    }

}
