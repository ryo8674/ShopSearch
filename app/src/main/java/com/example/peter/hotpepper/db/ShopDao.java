package com.example.peter.hotpepper.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.peter.hotpepper.dto.ShopDto;

import java.util.ArrayList;
import java.util.List;

import static com.example.peter.hotpepper.util.Constants.COLUMNS;
import static com.example.peter.hotpepper.util.Constants.COLUMN_DATE;
import static com.example.peter.hotpepper.util.Constants.COLUMN_ID;
import static com.example.peter.hotpepper.util.Constants.SELECTION_ID;
import static com.example.peter.hotpepper.util.Constants.TABLE_NAME;

/**
 * ShopDao
 */
public class ShopDao {

    private final SQLiteDatabase db;

    /**
     * コンストラクタ
     */
    public ShopDao(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * insert
     */
    public void insert(ShopDto shopDto) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, shopDto.getId());
        values.put(COLUMN_DATE, System.currentTimeMillis());
        db.insert(TABLE_NAME, null, values);
    }

    /**
     * 全件検索
     */
    public List<ShopDto> findAll() {
        List<ShopDto> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_DATE);

        while (cursor.moveToNext()) {
            ShopDto shopDto = new ShopDto();
            shopDto.setId(cursor.getString(0));
            list.add(shopDto);
        }
        cursor.close();
        return list;
    }

    /**
     * ID検索
     */
    public ShopDto findById(String id) {
        String[] selectionArgs = {id};
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, SELECTION_ID, selectionArgs, null, null, COLUMN_DATE);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToNext();
        ShopDto shop = new ShopDto();
        shop.setId(cursor.getString(0));

        cursor.close();
        return shop;
    }

    /**
     * delete
     */
    public void delete(ShopDto shopDto) {
        String[] whereArgs = {shopDto.getId()};
        db.delete(TABLE_NAME, SELECTION_ID, whereArgs);
    }
}
