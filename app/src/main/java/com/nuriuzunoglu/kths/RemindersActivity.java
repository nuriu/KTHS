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

    List<Reminder> reminder= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        reminder.add(new Reminder("Ankara","makarna all","20:40"));
        reminder.add(new Reminder("Sakarya,mama","bebek bezi al","12:44"));
        reminder.add(new Reminder("Manisa","Mesir macunu almayı unutma","10:20"));

        ListView list = (ListView) findViewById(R.id.customList);
        CustomAdapter adapter = new CustomAdapter(this,reminder);
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
