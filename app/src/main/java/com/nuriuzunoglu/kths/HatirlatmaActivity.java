package com.nuriuzunoglu.kths;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HatirlatmaActivity extends AppCompatActivity {

    EditText konum,bilgi,saat;
    Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatma);

        vt = new Veritabani(HatirlatmaActivity.this);
        konum = (EditText) findViewById(R.id.konum);
        bilgi = (EditText)  findViewById(R.id.hatirlat);
        saat = (EditText) findViewById(R.id.saat);

    }
    public  void  hEkle(View v)
    {
        System.out.println(" ");
        String knm = konum.getText().toString();
        String zmn= saat.getText().toString();
        String blg = bilgi.getText().toString();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Hatirlaticilar WHERE konum=?",new String[]{knm});
        if(c.getCount()>0){
            Toast.makeText(HatirlatmaActivity.this, "Böyle bir kayıt var", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
        }else {
            ContentValues cv = new ContentValues();
            cv.put("konum",knm);
            cv.put("hatirlatma",blg);
            cv.put("saat",zmn);

            db.insert("Hatirlaticilar",null,cv);
            c.close();
            db.close();
            Toast.makeText(HatirlatmaActivity.this, "Başarı ile eklendi", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), RemindersActivity.class);
            startActivity(i);

        }

    }
}
