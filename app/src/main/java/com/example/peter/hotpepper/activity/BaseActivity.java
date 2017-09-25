package com.example.peter.hotpepper.activity;

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

import com.example.peter.hotpepper.R;

/**
 * ベースとなるActivity
 */
abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    abstract int setLayoutResourceId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceId());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //noinspection deprecation
        drawerLayout.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        navigation.setNavigationItemSelectedListener(this);
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
            case R.id.nav_history:
                intent = new Intent(this, HistoryActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
