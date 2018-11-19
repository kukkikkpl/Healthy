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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private FirebaseAuth fbAuth;

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
        fbAuth = FirebaseAuth.getInstance();
        initLoginBtn();
        initRegisterBtn();

    }

    void initLoginBtn() {
        Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn); //
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();
                if (_userIdStr.isEmpty() || _passwordStr.isEmpty()) {
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุ User หรือ Password",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                } else {
                    signIn(_userIdStr, _passwordStr);
                }
            }
        });
    }

    void initRegisterBtn() {
        TextView _registerBtn = (TextView) getView().findViewById(R.id.login_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null).commit();
                Log.d("USER", "GO TO REGISTER");
            }
        });

    }

    private void signIn(String email, String password) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            fbAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = fbAuth.getCurrentUser();
                    if (user.isEmailVerified()) {
                        Log.d("USER", "GO TO MENU");
                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new MenuFragment())
                                .addToBackStack(null).commit();
                    } else {
                        Toast.makeText(
                                getActivity(),
                                "User นี้ยังไม่ได้ทำการ Confirm Email",
                                Toast.LENGTH_SHORT
                        ).show();
                        Log.d("USER", "EMAIL IS NOT VERIFIED");
                        fbAuth.signOut();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(
                            getActivity(),
                            "ชื่อ User หรือรหัสผ่านไม่ถูกต้อง",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "ERROR IN SIGNIN");
                }
            });
        } else {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new MenuFragment())
                    .addToBackStack(null).commit();
        }
    }

}
