package com.example.peter.hotpepper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.fragment.HistoryFragment;

import static com.example.peter.hotpepper.util.Constants.HISTORY_ACTIVITY;

/**
 * 履歴一覧のActivity
 */
public class HistoryActivity extends BaseActivity {

    @Override
    int setLayoutResourceId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(HISTORY_ACTIVITY);

        HistoryFragment historyFragment = new HistoryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, historyFragment);
        transaction.commit();
    }
}
