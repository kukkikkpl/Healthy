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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LAB203_40 on 20/8/2561.
 */

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @Nullable LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLoginBtn();
        initRegisterBtn();
    }

    void initLoginBtn(){
        Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn); //
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();
                if (_userIdStr.isEmpty() || _passwordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุ User หรือ Password",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                } else if (_userIdStr.equals("admin") && _passwordStr.equals("admin")) {
                    Log.d("USER","GO TO MENU");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
                } else {
                    Toast.makeText(
                            getActivity(),
                            "User หรือ Password ไม่ถูกต้อง",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER","INVALID USER OR PASSWORD");
                }
            }
        });
    }

    void initRegisterBtn(){
        TextView _registerBtn = (TextView) getView().findViewById(R.id.login_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
                Log.d("USER","GO TO REGISTER");
            }
        });

    }
}
