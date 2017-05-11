package com.nuriuzunoglu.kths;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabani extends SQLiteOpenHelper {
    private static final String dbName = "KTHS";
    private static final int version=1;

    public Veritabani(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Kisiler (id INTEGER PRIMARY KEY, adSoyad TEXT, kullaniciAdi TEXT, sifre TEXT)";
        String sql2 = "CREATE TABLE Hatirlaticilar (id INTEGER PRIMARY KEY, konum TEXT, hatirlatma TEXT, saat TEXT)";
        String sql3 = "INSERT INTO Hatirlaticilar Values(1, 'BOK', 'KOK', 'ŞOK')";
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Kisiler");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Hatirlaticilar");

        onCreate(sqLiteDatabase);
    }


}


