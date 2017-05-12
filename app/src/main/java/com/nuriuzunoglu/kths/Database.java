package com.nuriuzunoglu.kths;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String dbName = "KTHS";
    private static final int version=1;

    public Database(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Kisiler (id INTEGER PRIMARY KEY, adSoyad TEXT, kullaniciAdi TEXT, sifre TEXT)";
        String sql2 = "CREATE TABLE Hatirlaticilar (id INTEGER PRIMARY KEY, location TEXT, hatirlatma TEXT, time TEXT)";
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Kisiler");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Hatirlaticilar");

        onCreate(sqLiteDatabase);
    }


}


