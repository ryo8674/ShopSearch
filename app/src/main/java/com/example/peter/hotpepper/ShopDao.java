package com.example.peter.hotpepper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

class ShopDao {

    private static final String SELECTION_ID = "shop_id=";
    private static final String TABLE_NAME = "bookmark";
    private static final String COLUMN_ID = "shop_id";
    private static final String COLUMN_DATE = "date";

    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DATE};

    private final SQLiteDatabase db;

    ShopDao(SQLiteDatabase db){
        this.db = db;
    }

    void insert(ShopDto shopDto){
        ContentValues value = new ContentValues();
        value.put(COLUMN_ID, shopDto.getId());
        value.put(COLUMN_DATE, System.currentTimeMillis());
        db.insert(TABLE_NAME, null, value);
    }

    List<ShopDto> findAll() {
        List<ShopDto> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS,null,null,null,null,COLUMN_DATE);

        while (cursor.moveToNext()){
            ShopDto shopDto = new ShopDto();
            shopDto.setId(cursor.getString(0));
            list.add(shopDto);
        }
        return list;
    }

    ShopDto findById(ShopDto shopDto){
        String[] selection = new String[]{shopDto.getId()};
        Cursor cursor = db.query(TABLE_NAME, COLUMNS,SELECTION_ID,selection,null,null,COLUMN_DATE);

        cursor.moveToNext();
        ShopDto shop = new ShopDto();
        shop.setId(cursor.getString(0));

        return shop;
    }

    void delete(ShopDto shopDto){
        db.delete(TABLE_NAME, SELECTION_ID + "'" +shopDto.getId()+"'", null);
    }
}
