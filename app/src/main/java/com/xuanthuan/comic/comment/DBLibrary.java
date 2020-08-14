package com.xuanthuan.comic.comment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBLibrary extends SQLiteOpenHelper {
    public DBLibrary(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryDATA(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void  insertDATA(String ten, String idtruyen, String img){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO LIBRARY VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, ten);
        statement.bindString(2, idtruyen);
        statement.bindString(3, img);

        statement.executeInsert();
    }

    public Cursor getDATA(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = String.format("CREATE TABLE IF NOT EXISTS LIBRARY(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, IDtruyen VARCHAR, IMG VARCHAR)");
        sqLiteDatabase.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = String.format("DROP TABLE IF EXISTS LIBRARY");
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }

}
