package com.nuriuzunoglu.kths;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

public class GirisActivity extends AppCompatActivity {
    Veritabani vt;
    EditText edtKulAdi, edtSifre,kayitKulAdi, kayitSifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        vt = new Veritabani(GirisActivity.this);
        edtKulAdi = (EditText) findViewById(R.id.girisKulAdi);
        edtSifre = (EditText) findViewById(R.id.girisSifre);
        kayitKulAdi=(EditText) findViewById(R.id.kayitKulAdi);
        kayitSifre=(EditText) findViewById(R.id.kayitSifre);
    }
    public void KayitOl(View view){
        String kulAdi = kayitKulAdi.getText().toString();
        String sifre = kayitSifre.getText().toString();


        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Kisiler WHERE kullaniciAdi=?",new String[]{kulAdi});
        if(c.getCount()>0){
            Toast.makeText(GirisActivity.this, "Böyle bir kıllanıcı zaten var", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
        }else {
            ContentValues cv = new ContentValues();
            cv.put("kullaniciAdi",kulAdi);
            cv.put("sifre",sifre);


            db.insert("Kisiler",null,cv);
            c.close();
            db.close();

        }

    }

    public void GirisYap(View view) {
        String kulAdi = edtKulAdi.getText().toString();
        String sifre = edtSifre.getText().toString();

        SQLiteDatabase db = vt.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Kisiler WHERE kullaniciAdi=? AND sifre=?", new String[]{kulAdi, sifre});

        if (c.getCount() > 0) {
            c.moveToFirst();
            Toast.makeText(GirisActivity.this, "Hoşgeldin " + c.getString(1), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(GirisActivity.this,RemindersActivity.class);
            startActivity(i);
        } else{
            Toast.makeText(GirisActivity.this, "Kayıt Bulunamadı", Toast.LENGTH_SHORT).show();

        }
        c.close();
        db.close();

    }
}
