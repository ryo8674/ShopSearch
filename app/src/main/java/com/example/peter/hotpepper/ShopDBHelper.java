package com.example.peter.hotpepper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class ShopDBHelper extends SQLiteOpenHelper {

    /**
     * DB名
     */
    private static final String DB_NAME = "bookmark";

    /**
     * DB Version
     */
    private static final int DB_VERSION = 1;

    /**
     * テーブルを作成するSQL
     */
    private static final String CREATE_TABLE_SQL = "create table bookmark"
            + "(shop_id text primary key not null,"
            + "date integer not null)";

    /**
     * テーブルを削除するSQL
     */
    private static final String DROP_TABLE_SQL = "drop table if exists bookmark";

    ShopDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }
}
