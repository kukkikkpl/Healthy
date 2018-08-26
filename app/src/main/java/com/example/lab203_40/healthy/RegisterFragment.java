package com.example.lab203_40.healthy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by LAB203_40 on 20/8/2561.
 */

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            @Nullable LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRegisterNewAccountBtn();
    }

    void initRegisterNewAccountBtn(){
        Button _registerBtn = (Button) getView().findViewById(R.id.register_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = (EditText) getView().findViewById(R.id.register_user_id);
                EditText _name = (EditText) getView().findViewById(R.id.register_name);
                EditText _password = (EditText) getView().findViewById(R.id.register_password);
                EditText _age = (EditText) getView().findViewById(R.id.register_age);

                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _nameStr = _name.getText().toString();
                String _ageStr = _age.getText().toString();
                if (_userIdStr.isEmpty() || _passwordStr.isEmpty() || _nameStr.isEmpty() || _ageStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("REGISTER", "FIELD NAME IS EMPTY");
                } else if (_userIdStr.equals("admin")) {
                    Toast.makeText(
                            getActivity(),
                            "User นี้มีอยู่ในระบบแล้ว",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("REGISTER","USER ALREADY EXIST");
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null).commit();
                } else {
                    Log.d("REGISTER","GO TO BMI");
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null).commit();

                }
            }
        });
    }
}
