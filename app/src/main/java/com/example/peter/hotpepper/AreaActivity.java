package com.example.peter.hotpepper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AreaActivity extends BaseActivity {

    private static final String LARGE_AREA = "large_area";
    private static final String AREA_CODE = "area_code";
    private static final String AREA_LIST = "エリア一覧";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayout(R.layout.activity_area);
        super.onCreate(savedInstanceState);
        super.setToolbarTitle(AREA_LIST);


        final AreaAsyncTask task = new AreaAsyncTask(this);
        task.execute(UrlUtils.createUri(LARGE_AREA));

        ListView list = (ListView) findViewById(R.id.list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {
                LargeAreaDto largeAreaDto = task.getAreaList().get(position);
                Intent intent = new Intent(AreaActivity.this, ShopActivity.class);
                intent.putExtra(AREA_CODE, largeAreaDto.getCode());
                Toast.makeText(AreaActivity.this, largeAreaDto.getCode(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
