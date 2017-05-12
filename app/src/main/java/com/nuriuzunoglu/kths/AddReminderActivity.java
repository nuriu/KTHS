package com.nuriuzunoglu.kths;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddReminderActivity extends AppCompatActivity {

    EditText location, info, time;
    Database db;
    NotificationCompat.Builder notification;
    private  static final int uniqueID=1526;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreminder);

        db = new Database(AddReminderActivity.this);
        location = (EditText) findViewById(R.id.konum);
        info = (EditText) findViewById(R.id.hatirlat);
        time = (EditText) findViewById(R.id.saat);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        Intent intent = getIntent();
        String adres = intent.getStringExtra("adres");
        location.setText(adres);
    }
    public void AddReminder(View v)
    {
        System.out.println(" ");
        String loc = location.getText().toString();
        String t = time.getText().toString();
        String i = info.getText().toString();

        SQLiteDatabase db = this.db.getWritableDatabase();

       try{
           ContentValues cv = new ContentValues();
           cv.put("location", loc);
           cv.put("hatirlatma", i);
           cv.put("time", t);

           db.insert("Hatirlaticilar",null,cv);
           db.close();

           //Toast.makeText(AddReminderActivity.this, "Başarı ile eklendi.", Toast.LENGTH_SHORT).show();
           notification.setSmallIcon(R.drawable.info);
           notification.setTicker("Konum:"+loc);
           notification.setWhen(System.currentTimeMillis());
           notification.setContentTitle("Saat:"+t);
           notification.setContentText("Hatırlatma:"+i);


           Intent intent = new Intent(this,AddReminderActivity.class);
           PendingIntent pendingIntent = PendingIntent.getActivities(this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
           notification.setContentIntent(pendingIntent);


           NotificationManager nm =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
           nm.notify(uniqueID,notification.build());

           intent = new Intent(getApplicationContext(), RemindersActivity.class);
           startActivity(intent);
       } catch (Exception e) {
           Toast.makeText(this, "HATA! Veritabanına kayıt sırasında bir hata oluştu.", Toast.LENGTH_SHORT).show();
       }
    }
}

