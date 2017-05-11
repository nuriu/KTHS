package com.nuriuzunoglu.kths;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Database vt;
    EditText mLoginUsername, mLoginPassword, mUsername, mPassword;
    LinearLayout lLogin,lRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        vt = new Database(LoginActivity.this);
        mLoginUsername = (EditText) findViewById(R.id.girisKulAdi);
        mLoginPassword = (EditText) findViewById(R.id.girisSifre);
        mUsername =(EditText) findViewById(R.id.kayitKulAdi);
        mPassword =(EditText) findViewById(R.id.kayitSifre);

        lLogin = (LinearLayout) findViewById(R.id.giris);
        lRegister= (LinearLayout) findViewById(R.id.kayit);
    }
    public void Register(View view){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Kisiler WHERE kullaniciAdi=?",new String[]{username});

        if (c.getCount() > 0) {
            Toast.makeText(LoginActivity.this, "Böyle bir kıllanıcı zaten var", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
        } else {
            ContentValues cv = new ContentValues();
            cv.put("kullaniciAdi", username);
            cv.put("sifre", password);

            db.insert("Kisiler", null, cv);
            c.close();
            db.close();
            lRegister.setVisibility(LinearLayout.GONE);
            lLogin.setVisibility(LinearLayout.SHOW_DIVIDER_BEGINNING);

        }
    }

    public void Login(View view) {
        String username = mLoginUsername.getText().toString();
        String password = mLoginPassword.getText().toString();

        SQLiteDatabase db = vt.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Kisiler WHERE kullaniciAdi=? AND sifre=?", new String[]{username, password});

        if (c.getCount() > 0) {
            c.moveToFirst();
            Toast.makeText(LoginActivity.this, "Hoşgeldin " + c.getString(1), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this,RemindersActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(LoginActivity.this, "Kayıt Bulunamadı", Toast.LENGTH_SHORT).show();

        }
        c.close();
        db.close();
    }

    public void RegisterOpen(View v){
        lLogin.setVisibility(LinearLayout.GONE);
    }
    public void Back(View v)
    {
        lRegister.setVisibility(LinearLayout.GONE);
        lLogin.setVisibility(LinearLayout.SHOW_DIVIDER_BEGINNING);
    }


}
