package com.example.exam_2_b18dccn100;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.exam_2_b18dccn100.model.Items;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME="Exam.db";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql;
        sql = "CREATE TABLE items(id INTEGER PRIMARY KEY,name TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public long addItem(Items items){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",items.getId());
        values.put("name",items.getName());

        return db.insert("items",null,values);

    }

    public List<Items> getList(){
        List<Items> itemsList = new ArrayList<>();

        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.query("items",null,null,null,null,null,null);

        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            itemsList.add(new Items(name,id));
        }
        return  itemsList;
    }

    public int delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        return db.delete("items",whereClause,whereArgs);
    }

    public int editItem(Items items) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(items.getId())};
        ContentValues values = new ContentValues();
        values.put("id",items.getId());
        values.put("name",items.getName());

        return db.update("items",values,whereClause,whereArgs);
    }

    public List<Items> getListByName(String n) {
        List<Items> itemsList = new ArrayList<>();
            SQLiteDatabase db =getReadableDatabase();
        String whereClause = "name LIKE ?";
        String[] whereArgs = {"%"+n+"%"};
        Cursor cursor = db.query("items",null,whereClause,whereArgs,null,null,null);
        while(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            itemsList.add(new Items(name,id));
        }
        return itemsList;
    }

    public long getCount(){
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,"items");

    }
}
