package com.example.kukkik.healthy.sleep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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
        Sleep _row = sleeps.get(position);
        _date.setText(_row.getDate());
        _sleepTime.setText(String.valueOf(_row.getSleepTime()));
        _wakeupTime.setText(_row.getWakeupTime());
        return _sleepItem;
    }
}
