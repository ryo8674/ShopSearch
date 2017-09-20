package com.example.peter.hotpepper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.async.AreaAsyncTask;
import com.example.peter.hotpepper.dto.LargeAreaDto;
import com.example.peter.hotpepper.util.UriUtil;

import static com.example.peter.hotpepper.util.Constants.AREA_ACTIVITY;
import static com.example.peter.hotpepper.util.Constants.AREA_CODE;
import static com.example.peter.hotpepper.util.Constants.LARGE_AREA;

/**
 * エリア一覧画面のActivity
 */
public class AreaActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private AreaAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_area);
        super.onCreate(savedInstanceState);
        super.setTitle(AREA_ACTIVITY);

        ListView areaList = (ListView) findViewById(R.id.area_list);
        areaList.setOnItemClickListener(this);

        // 非同期処理
        task = new AreaAsyncTask(this);
        task.execute(UriUtil.createUri(LARGE_AREA));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        LargeAreaDto largeAreaDto = task.getLargeAreaList().get(position);
        Intent intent = new Intent(AreaActivity.this, ShopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.putExtra(AREA_CODE, largeAreaDto.getCode());
        startActivity(intent);
    }
}
