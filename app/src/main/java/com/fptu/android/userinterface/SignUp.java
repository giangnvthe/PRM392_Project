package com.fptu.android.userinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private TextView banner;
    public Button registerUser, signinUser;
    private FirebaseAuth mAth;
    private EditText editUsername, editPassword, editEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAth = FirebaseAuth.getInstance();

        signinUser = findViewById(R.id.signinuser);
        signinUser.setOnClickListener(this);

        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = findViewById(R.id.registeruser);
        registerUser.setOnClickListener(this);

        editUsername = findViewById(R.id.username2);
        editPassword = findViewById(R.id.password2);
        editEmail = findViewById(R.id.email);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signinuser:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.registeruser:
                RegisterUser();
                break;
        }
    }

    private void RegisterUser() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        String userName = editUsername.getText().toString().trim();
        if (userName.isEmpty()) {
            editUsername.setError("Name is Required");
            editUsername.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editEmail.setError("Email is Required");
            editEmail.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("please provide correct email");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Password is Required");
            editPassword.requestFocus();
        }
        if (password.length() < 6) {
            editPassword.setError("Password should longer than 6 bro");
            editPassword.requestFocus();
            return;

        }
        mAth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(userName, email);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, "signup successfully", Toast.LENGTH_LONG).show();

                                            } else
                                                Toast.makeText(SignUp.this, "signup fail", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(SignUp.this, "signup fail", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}