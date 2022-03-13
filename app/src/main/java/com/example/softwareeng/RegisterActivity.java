package com.example.softwareeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextView tv_TitleUsername;
    TextView tv_TitlePassword;
    EditText et_Email;
    EditText et_Password;
    Button btn_Login;
    Button btn_Register;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //setting elements to actual layout objects
        tv_TitleUsername = findViewById(R.id.tv_Email_RegisterActivity);
        tv_TitlePassword = findViewById(R.id.tv_Password_RegisterActivity);
        et_Email = findViewById(R.id.et_Email_RegisterActivity);
        et_Password = findViewById(R.id.et_Password_RegisterActivity);
        btn_Login = findViewById(R.id.btn_Login_RegisterActivity);
        btn_Register = findViewById(R.id.btn_Register_RegisterActivity);

        //Initialize instance of firebaseAuth
        mAuth = FirebaseAuth.getInstance();


        //Set on click listener for login button
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //testing click-ability with a toast
                createUser();

            }
        });

        btn_Login.setOnClickListener(view -> {

            startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });


    }

    private void createUser() {
        String email = et_Email.getText().toString();
        String password = et_Password.getText().toString();
        //verify that there is valid content in the edit text
        if(TextUtils.isEmpty(email)){
            et_Email.setError("Email cannot be empty");
            et_Email.requestFocus();
        }else if( TextUtils.isEmpty(password)){
            et_Password.setError("Password cannot be empty");
            et_Password.requestFocus();
        }else{
            //call firebase method to create user
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mAuth.signInWithEmailAndPassword(email,password);
                        Toast.makeText(RegisterActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        //finish is called so activity ends and user can't go back to it.
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}