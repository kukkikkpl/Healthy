package com.example.kukkik.healthy;

import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by LAB203_40 on 20/8/2561.
 */

public class RegisterFragment extends Fragment {

    private FirebaseAuth fbAuth;

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
        fbAuth = FirebaseAuth.getInstance();
        initRegisterNewAccountBtn();
    }

    void initRegisterNewAccountBtn(){
        Button _registerBtn = (Button) getView().findViewById(R.id.register_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    void registerNewUser(){
        EditText _email = (EditText) getView().findViewById(R.id.register_email);
        EditText _password = (EditText) getView().findViewById(R.id.register_password);
        EditText _re_password = (EditText) getView().findViewById(R.id.register_re_password);

        String _emailStr = _email.getText().toString();
        String _passwordStr = _password.getText().toString();
        String _re_passwordStr = _re_password.getText().toString();

        if (_emailStr.isEmpty() || _passwordStr.isEmpty() || _re_passwordStr.isEmpty()) {
            Toast.makeText(
                    getActivity(),
                    "กรุณากรอกข้อมูลให้ครบถ้วน",
                    Toast.LENGTH_SHORT
            ).show();
            Log.d("REGISTER", "SOME FIELDS ARE EMPTY");
        } else if (_passwordStr.length() < 6){
            Toast.makeText(
                    getActivity(),
                    "รหัสผ่านต้องมีความยาวตั้งแต่ 6 ตัวอักษรขึ้นไป",
                    Toast.LENGTH_SHORT
            ).show();
            Log.d("REGISTER", "PASSWORD IS TOO SHORT");
        } else if (!_passwordStr.equals(_re_passwordStr)) {
            Toast.makeText(
                    getActivity(),
                    "รหัสผ่านไม่ตรงกัน",
                    Toast.LENGTH_SHORT
            ).show();
            Log.d("REGISTER","PASSWORD AND RE-PASSWORD ARE NOT EQUAL");
        } else {
            fbAuth.createUserWithEmailAndPassword(_emailStr, _passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sendVerifiedEmail(authResult.getUser());

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e){
                    Toast.makeText(getActivity(), "ERROR = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendVerifiedEmail(FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("REGISTER","GO TO LOG IN");
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null).commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "ERROR = " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
