package com.nuriuzunoglu.kths;

public class Reminder {
    private  String location;
    private  String reminder;
    private  String time;

    public Reminder(String location,String reminder, String time){
        this.location = location;
        this.reminder = reminder;
        this.time=time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
