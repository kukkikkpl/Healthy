package com.example.kukkik.healthy.sleep;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kukkik.healthy.DBHelper;
import com.example.kukkik.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class SleepFragment extends Fragment {
    List<Sleep> sleeps = new ArrayList<>();
    DBHelper helper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAddSleepBtn();
        helper = new DBHelper(getContext());
        sleeps = helper.getSleepList();
        if (!sleeps.isEmpty()) {
            SleepAdapter _sleepAdapter = new SleepAdapter(getActivity(), R.layout.fragment_sleep_item, sleeps);
            ListView _sleepList = (ListView) getView().findViewById(R.id.sleep_list);
            _sleepList.setAdapter(_sleepAdapter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    void initAddSleepBtn() {
        TextView _backBtn = (TextView) getView().findViewById(R.id.sleep_add_sleep_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
