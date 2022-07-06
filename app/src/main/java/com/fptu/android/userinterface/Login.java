package com.fptu.android.userinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public Button signUp, signIn, btnChange;
    private EditText editEmail, editPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindingView();
        bindingAction();



    }

    public void bindingView() {
        signUp = findViewById(R.id.btnsignup);
        signIn = findViewById(R.id.btsignin);
        btnChange = findViewById(R.id.btnchange);
        editEmail = findViewById(R.id.email1);
        editPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();


    }

    public void bindingAction() {
        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
        btnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsignup:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.btsignin:
                userLogin();
                break;
            case R.id.btnchange:
                startActivity(new Intent(this, change_password.class));
        }
    }



    private void userLogin() {
        String email = editEmail.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Email required");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("pls enter valid Email!");
            editEmail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            editPassword.setError("Email required");
            editPassword.requestFocus();
            return;

        }
        if (pass.length() < 6) {
            editPassword.setError("Password should longer than 6 bro");
            editPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Fail to login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}