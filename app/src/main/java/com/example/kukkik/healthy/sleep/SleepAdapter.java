package com.example.kukkik.healthy.sleep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.kukkik.healthy.R;

public class SleepAdapter extends ArrayAdapter<Sleep> {
    List<Sleep> sleeps;
    Context context;

    public SleepAdapter(Context context, int resource, List<Sleep> objects) {
        super(context, resource, objects);
        this.sleeps = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View _sleepItem = LayoutInflater.from(context).inflate(R.layout.fragment_sleep_item, parent, false);
        TextView _date = (TextView) _sleepItem.findViewById(R.id.sleep_item_date);
        TextView _sleepTime = (TextView) _sleepItem.findViewById(R.id.sleep_item_sleep_time);
        TextView _wakeupTime = (TextView) _sleepItem.findViewById(R.id.sleep_item_wakeup_time);
        TextView _hours = (TextView) _sleepItem.findViewById(R.id.sleep_item_hours);
        Sleep _row = sleeps.get(position);
        _date.setText(_row.getDate());
        _sleepTime.setText(String.valueOf(_row.getSleepTime()));
        _wakeupTime.setText(_row.getWakeupTime());
        String hours = calculateTime(_row.getSleepTime(), _row.getWakeupTime());
        _hours.setText(hours);
        return _sleepItem;
    }

    String calculateTime(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);
            long diff = 0;
            if (d1.getTime() > d2.getTime()){
                diff = (86400000 - d1.getTime()) + d2.getTime();
            } else {
                diff = d2.getTime() - d1.getTime();
            }
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffMinutes = diff / (60 * 1000) % 60;
            Date hours = format.parse(String.valueOf(diffHours)+":"+String.valueOf(diffMinutes));
            return format.format(hours);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
