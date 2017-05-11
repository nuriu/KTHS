package com.nuriuzunoglu.kths;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
        Veritabani veritabanim;
        private SQLiteDatabase db;

        public List<Reminder> HatirlatmaListele(Context context){
            List<Reminder> reminders = new ArrayList<>();
            veritabanim = new Veritabani(context);
            //    veritabanim.createDataBase();

            SQLiteDatabase dbInstance = veritabanim.getReadableDatabase();
            Cursor cursor = null;
            try{
                cursor = dbInstance.rawQuery("SELECT * FROM Hatirlaticilar", null);

                while(cursor.moveToNext()){
                    Reminder r = new Reminder();
                    r.setLocation(cursor.getString((1)));
                    r.setReminder(cursor.getString(2));
                    r.setTime(cursor.getString(3));

                    reminders.add(r);
                }
            }finally {
                assert cursor != null;
                cursor.close();
            }
            System.out.print(reminders.toArray());
            return reminders;

        }

    }

