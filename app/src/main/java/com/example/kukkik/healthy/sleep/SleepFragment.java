package com.example.kukkik.healthy.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
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
            _sleepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int sleepSize = sleeps.size();
                    Bundle bundle = new Bundle();
                    int columnId = position*(-1)+sleepSize;
                    bundle.putInt("id", columnId);
                    bundle.putString("date", sleeps.get(position).getDate());
                    bundle.putString("sleep_time", sleeps.get(position).getSleepTime());
                    bundle.putString("wakeup_time", sleeps.get(position).getWakeupTime());
                    SleepFormFragment sleepForm = new SleepFormFragment();
                    sleepForm.setArguments(bundle);
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, sleepForm)
                            .addToBackStack(null)
                            .commit();
                }
            });
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
