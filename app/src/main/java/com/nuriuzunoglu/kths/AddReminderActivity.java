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

public class AddReminderActivity extends AppCompatActivity {

    EditText location, info, time;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreminder);

        db = new Database(AddReminderActivity.this);
        location = (EditText) findViewById(R.id.konum);
        info = (EditText) findViewById(R.id.hatirlat);
        time = (EditText) findViewById(R.id.saat);

    }
    public void AddReminder(View v)
    {
        System.out.println(" ");
        String loc = location.getText().toString();
        String t = time.getText().toString();
        String i = info.getText().toString();

        SQLiteDatabase db = this.db.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Hatirlaticilar WHERE location=?", new String[]{loc});
        if (c.getCount() > 0) {
            Toast.makeText(AddReminderActivity.this, "Böyle bir kayıt var", Toast.LENGTH_SHORT).show();
            c.close();
            db.close();
        } else {
            ContentValues cv = new ContentValues();
            cv.put("location", loc);
            cv.put("hatirlatma", i);
            cv.put("time", t);

            db.insert("Hatirlaticilar",null,cv);

            c.close();
            db.close();

            Toast.makeText(AddReminderActivity.this, "Başarı ile eklendi", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), RemindersActivity.class);
            startActivity(intent);
        }

    }
}
