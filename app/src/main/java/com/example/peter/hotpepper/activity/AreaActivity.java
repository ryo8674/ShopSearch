package com.example.peter.hotpepper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.adapter.AreaAdapter;
import com.example.peter.hotpepper.async.Task;
import com.example.peter.hotpepper.dto.AreaResultApi;
import com.example.peter.hotpepper.dto.LargeAreaDto;
import com.example.peter.hotpepper.util.UriUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static com.example.peter.hotpepper.util.Constants.AREA_ACTIVITY;
import static com.example.peter.hotpepper.util.Constants.AREA_CODE;
import static com.example.peter.hotpepper.util.Constants.LARGE_AREA;

/**
 * エリア一覧画面のActivity
 */
public class AreaActivity extends BaseActivity implements AdapterView.OnItemClickListener,Task.ShopTaskCallback {

    private ListView areaList;
    private List<LargeAreaDto> largeAreaDtoList;

    @Override
    int setLayoutResourceId() {
        return R.layout.activity_area;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(AREA_ACTIVITY);

        areaList = (ListView) findViewById(R.id.area_list);
        areaList.setOnItemClickListener(this);

        // 非同期処理
        Task task = new Task(this);
        task.execute(UriUtil.createUri(LARGE_AREA));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        LargeAreaDto largeAreaDto = largeAreaDtoList.get(position);
        Intent intent = new Intent(AreaActivity.this, ShopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.putExtra(AREA_CODE, largeAreaDto.getCode());
        startActivity(intent);
    }

    @Override
    public void onSuccess(String result) {
        createObject(result);
        AreaAdapter adapter = new AreaAdapter(this, android.R.layout.simple_list_item_1, largeAreaDtoList);
        areaList.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
    }

    /**
     * JSONをオブジェクトに変換
     */
    private void createObject(String result) {
        Gson gson = new GsonBuilder().create();

        AreaResultApi resultApi = gson.fromJson(result, new TypeToken<AreaResultApi>() {
        }.getType());
        largeAreaDtoList = resultApi.getResults().getLargeAreaDto();
    }
}
