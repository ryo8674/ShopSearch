package com.example.peter.hotpepper;

import android.content.Intent;
import android.net.Uri;
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

    private static final String HTTP_PROTOCOL = "http";
    private static final String AUTHORITY = "webservice.recruit.co.jp";
    private static final String PATH = "hotpepper/large_area/v1";
    private static final String USER_PARAMETER = "key";
    private static final String USER_KEY = "8928fba69a934d6e";
    private static final String FORMAT_PARAMETER = "format";
    private static final String FORMAT_KEY = "json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("エリア一覧");
        setSupportActionBar(toolbar);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        //URIを生成
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(HTTP_PROTOCOL);
        uriBuilder.authority(AUTHORITY);
        uriBuilder.path(PATH);
        uriBuilder.appendQueryParameter(USER_PARAMETER, USER_KEY);
        uriBuilder.appendQueryParameter(FORMAT_PARAMETER, FORMAT_KEY);

        String utiStr = uriBuilder.toString();

        final AreaAsyncTask task = new AreaAsyncTask(this);
        task.execute(utiStr);

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
