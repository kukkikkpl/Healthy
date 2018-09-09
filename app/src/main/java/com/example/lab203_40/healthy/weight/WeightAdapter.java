package com.example.lab203_40.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_40.healthy.R;
import com.example.lab203_40.healthy.weight.Weight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAB203_40 on 27/8/2561.
 */

public class WeightAdapter extends ArrayAdapter<Weight> {
    List<Weight> weights = new ArrayList<Weight>();
    Context context;
    public WeightAdapter(Context context, int resource, List<Weight> objects){
        super(context,resource,objects);
        this.weights = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View _weightItem = LayoutInflater.from(context).inflate(R.layout.fragment_weight_item, parent, false);
        TextView _date = (TextView) _weightItem.findViewById(R.id.weight_item_date);
        TextView _weight = (TextView) _weightItem.findViewById(R.id.weight_item_weight);
        Weight _row = weights.get(position);
        _date.setText(_row.getDate());
        _weight.setText(String.valueOf(_row.getWeight()));
        return _weightItem;
    }
}
