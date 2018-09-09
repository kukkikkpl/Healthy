package com.example.lab203_40.healthy.weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lab203_40.healthy.R;

import java.util.ArrayList;

/**
 * Created by LAB203_40 on 27/8/2561.
 */

public class WeightFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weights.add(new Weight("01 Jan 2018", 63, "UP"));
        weights.add(new Weight("02 Jan 2018", 62, "DOWN"));
        weights.add(new Weight("03 Jan 2018", 64, "UP"));
        ListView _weightList = (ListView) getView().findViewById(R.id.weight_list);
        WeightAdapter _weightAdapter = new WeightAdapter(getActivity(), R.layout.fragment_weight_item, weights);
        _weightList.setAdapter(_weightAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
