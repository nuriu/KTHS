package com.nuriuzunoglu.kths;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater inf;
    private List<Reminder> reminders;

     public CustomAdapter(Activity activity, List<Reminder> reminders){
        inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.reminders =reminders;
    }


    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int i) {
        return reminders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View line;

        line = inf.inflate(R.layout.reminders_line,null);
        TextView txtlocation = (TextView) line.findViewById(R.id.location);
        TextView txtreminder = (TextView) line.findViewById(R.id.reminder);
        TextView txttime = (TextView) line.findViewById(R.id.reminder_time);

        Reminder r = reminders.get(i);

        txtlocation.setText(r.getLocation());
        txtreminder.setText(r.getReminder());
        txttime.setText(r.getTime());

        return line;
    }
}