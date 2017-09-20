package com.example.peter.hotpepper.async;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.activity.AreaActivity;
import com.example.peter.hotpepper.adapter.AreaAdapter;
import com.example.peter.hotpepper.dto.AreaResultApi;
import com.example.peter.hotpepper.dto.LargeAreaDto;
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
 * 大エリアを取得する非同期処理を行うクラス
 */
public class AreaAsyncTask extends AsyncTask<String, Void, String> {

    private final AreaActivity areaActivity;
    private List<LargeAreaDto> largeAreaDtoList;

    /**
     * コンストラクタ
     */
    public AreaAsyncTask(AreaActivity areaActivity) {
        this.areaActivity = areaActivity;
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
        Gson gson = new GsonBuilder().create();
        largeAreaDtoList = new ArrayList<>();

        AreaResultApi resultApi = gson.fromJson(result, new TypeToken<AreaResultApi>() {
        }.getType());

        if (resultApi != null) {
            largeAreaDtoList = resultApi.getResults().getLargeAreaDto();

            AreaAdapter adapter = new AreaAdapter(areaActivity, android.R.layout.simple_list_item_1, largeAreaDtoList);
            ListView listView = (ListView) areaActivity.findViewById(R.id.area_list);
            listView.setAdapter(adapter);
        }
    }

    /**
     * 大エリアのリストを取得
     */
    public List<LargeAreaDto> getLargeAreaList() {
        return largeAreaDtoList;
    }


}
