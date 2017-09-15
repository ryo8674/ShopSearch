package com.example.peter.hotpepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AreaActivity extends AppCompatActivity {

    private static final String LARGE_AREA = "large_area";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("エリア一覧");
        setSupportActionBar(toolbar);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //noinspection deprecation
        drawerLayout.setDrawerListener(toggle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        final AreaAsyncTask task = new AreaAsyncTask(this);
        task.execute(UrlUtils.createUri(LARGE_AREA));

        ListView list = (ListView) findViewById(R.id.list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {
                LargeAreaDto largeAreaDto = task.getAreaList().get(position);
                Intent intent = new Intent(AreaActivity.this, ShopActivity.class);
                intent.putExtra("areaCode", largeAreaDto.getCode());
                Toast.makeText(AreaActivity.this, largeAreaDto.getCode(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
