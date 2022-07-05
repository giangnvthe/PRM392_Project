package com.fptu.android.userinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class change_password extends AppCompatActivity {
    private EditText emailEdit;
    private Button resetPassBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        bindingView();
        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email = emailEdit.getText().toString().trim();
        if (email.isEmpty()){
            emailEdit.setError("Email required");
            emailEdit.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdit.setError("Please provide a valid email!");
            emailEdit.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(change_password.this,"Please check your email",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(change_password.this, Login.class));
                }else Toast.makeText(change_password.this,"some thing went wrong!Please try again!",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void bindingView() {
        emailEdit= findViewById(R.id.email2);
        resetPassBtn = findViewById(R.id.btnResetPass);
        mAuth =FirebaseAuth.getInstance();

    }

}