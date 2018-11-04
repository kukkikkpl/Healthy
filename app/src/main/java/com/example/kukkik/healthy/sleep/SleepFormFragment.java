package com.example.kukkik.healthy.sleep;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kukkik.healthy.DBHelper;
import com.example.kukkik.healthy.R;

public class SleepFormFragment extends Fragment {

    private DBHelper helper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper =  new DBHelper(getContext());
        initSaveBtn();
        initBackBtn();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    void initBackBtn(){
        Button _backBtn = (Button) getView().findViewById(R.id.sleep_form_back_btn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new SleepFragment())
                        .addToBackStack(null).commit();
            }
        });
    }

    void initSaveBtn(){
        Button _saveBtn = (Button) getView().findViewById(R.id.sleep_form_save_btn);
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _date = (EditText) getView().findViewById(R.id.sleep_form_date);
                EditText _sleepTime = (EditText) getView().findViewById(R.id.sleep_form_sleep_time);
                EditText _wakeupTime = (EditText) getView().findViewById(R.id.sleep_form_wakeup_time);
                String _dateString = _date.getText().toString();
                String _sleepTimeString = _sleepTime.getText().toString();
                String _wakeupTimeString = _wakeupTime.getText().toString();
                Log.e("SLEEPFORM", _dateString+_sleepTimeString+_wakeupTimeString);
                Sleep sleep = new Sleep();
                sleep.setDate(_dateString);
                sleep.setWakeupTime(_wakeupTimeString);
                sleep.setSleepTime(_sleepTimeString);
                helper.addSleep(sleep);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null).commit();
            }
        });
    }





}
