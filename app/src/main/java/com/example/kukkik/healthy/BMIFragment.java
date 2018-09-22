package com.example.kukkik.healthy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by LAB203_40 on 20/8/2561.
 */

public class BMIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            @Nullable LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCalculateBtn();
        initBackBtn();
    }

    void initCalculateBtn(){
        Button _calculateBtn = (Button) getView().findViewById(R.id.bmi_calculate_btn);
        _calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _height = (EditText) getView().findViewById(R.id.bmi_height);
                EditText _weight = (EditText) getView().findViewById(R.id.bmi_weight);
                String _heightStr = _height.getText().toString();
                String _weightStr = _weight.getText().toString();
                if (_heightStr.isEmpty() || _weightStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("BMI", "FIELD NAME IS EMPTY");
                } else {
                    double _heightDouble = Double.parseDouble(_heightStr);
                    double _weightDouble = Double.parseDouble(_weightStr);
                    double bmi = _weightDouble/(_heightDouble*_heightDouble);
                    bmi = Math.round(bmi * 100d)/100d;
                    TextView bmiText = (TextView) getView().findViewById(R.id.bmi_bmi_value);
                    bmiText.setText(String.valueOf(bmi));
                    Log.d("BMI","BMI IS VALUE");

                }
            }
        });

    }

    void initBackBtn(){
        Button backBtn = (Button) getView().findViewById(R.id.bmi_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
            }
        });
    }
}
