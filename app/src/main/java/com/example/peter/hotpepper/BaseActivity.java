package com.example.peter.hotpepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private int layoutResource;
    private Toolbar toolbar;

    void setLayout(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarTitle("");
        setSupportActionBar(toolbar);

        if (layoutResource == R.layout.activity_deteil) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
            //noinspection deprecation
            drawerLayout.setDrawerListener(toggle);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

            NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
            navigation.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_area:
                intent = new Intent(this, AreaActivity.class);
                break;
            case R.id.nav_bookmark:
                intent = new Intent(this, BookmarkActivity.class);
                break;
        }
        startActivity(intent);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void setToolbarTitle(String toolbarTitle) {
        toolbar.setTitle(toolbarTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
