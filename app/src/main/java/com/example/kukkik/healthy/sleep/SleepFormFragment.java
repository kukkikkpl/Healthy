package com.example.kukkik.healthy.sleep;

import android.os.Bundle;
import android.provider.BaseColumns;
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
    private int ID;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper =  new DBHelper(getContext());

        initBackBtn();
        Bundle bundle = getArguments();

        if (bundle != null) {
            Log.d("SLEEPFORM", "bundle is not null");
            EditText _date = (EditText) getView().findViewById(R.id.sleep_form_date);
            EditText _sleepTime = (EditText) getView().findViewById(R.id.sleep_form_sleep_time);
            EditText _wakeupTime = (EditText) getView().findViewById(R.id.sleep_form_wakeup_time);
            initUpdateBtn();
            ID = bundle.getInt("id");
            String date = bundle.getString("date");
            String sleepTime = bundle.getString("sleep_time");
            String wakeupTime = bundle.getString("wakeup_time");
            _date.setText(date);
            _sleepTime.setText(sleepTime);
            _wakeupTime.setText(wakeupTime);
        } else {
            initSaveBtn();
        }
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

    void initUpdateBtn() {
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
                Sleep sleep = new Sleep();
                sleep.setId(String.valueOf(ID));
                sleep.setDate(_dateString);
                sleep.setWakeupTime(_wakeupTimeString);
                sleep.setSleepTime(_sleepTimeString);
                helper.updateSleep(sleep);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null).commit();
            }
        });
    }



}
