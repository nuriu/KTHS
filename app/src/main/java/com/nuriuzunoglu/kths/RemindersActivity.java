package com.nuriuzunoglu.kths;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RemindersActivity extends AppCompatActivity {

    Database vt;
    List<Reminder> reminders= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        vt = new Database(RemindersActivity.this);
        DBHelper dbHelper = new DBHelper();

        reminders = dbHelper.GetReminders(this);

        ListView list = (ListView) findViewById(R.id.customList);
        CustomAdapter adapter = new CustomAdapter(this,reminders);
        list.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });
    }

}
