package com.example.peter.hotpepper.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.peter.hotpepper.util.Constants.CREATE_TABLE_SQL;
import static com.example.peter.hotpepper.util.Constants.DB_NAME;
import static com.example.peter.hotpepper.util.Constants.DB_VERSION;
import static com.example.peter.hotpepper.util.Constants.DROP_TABLE_SQL;

/**
 * DBの生成、管理を行うクラス
 */
public class ShopDBHelper extends SQLiteOpenHelper {


    /**
     * コンストラクタ
     */
    public ShopDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
