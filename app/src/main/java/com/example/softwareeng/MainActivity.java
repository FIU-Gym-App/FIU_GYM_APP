package com.example.softwareeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MAIN ACTIVITY";
    private FirebaseAuth mAuth;
    Button btn_Logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize the FireBaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        btn_Logout = findViewById(R.id.btn_Logout_MainActivity);

        //on click listener for btn
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logout method with firebase
                FirebaseAuth.getInstance().signOut();
                //take user to login screen
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });



    }


    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        //Check if user is already logged in (debugging)
        Log.d(TAG, "USER IS ALREADY LOGGED IN");

        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }





}