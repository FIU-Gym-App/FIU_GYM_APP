package com.example.softwareeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softwareeng.Nicole.MiddleMan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends BaseMenu {
    final String TAG = "MAIN ACTIVITY";
    private FirebaseAuth mAuth;
    //Button btn_Logout;
    Button btn_CheckIn;
    Button btn_Bar;
    TextView txt_gymSchedule;

    FirebaseFirestore db = FirebaseFirestore.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize the FireBaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        //btn_Logout = findViewById(R.id.btn_Logout_MainActivity);
        btn_CheckIn = findViewById(R.id.btn_CheckIn_MainActivity);
        btn_Bar = findViewById(R.id.btn_Bar_MainActivity);
        txt_gymSchedule = findViewById(R.id.txt_GymSchedule_MainActivity);




        txt_gymSchedule.setText(
                "Monday \t\t\t\t6AM–11PM\n" +
                "Tuesday \t\t\t\t6AM–11PM\n" +
                "Wednesday\t\t6AM–11PM\n" +
                "Thursday \t\t\t6AM–11PM\n" +
                "Friday     \t\t\t\t\t6AM–9PM\n" +
                "Saturday  \t\t\t10AM–7PM\n" +
                "Sunday     \t\t\t10AM–7PM");



        btn_CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make this happen when check in is clicked
                DocumentReference checkIn = db.collection("checkInCounter").document("checkIn");
                // Atomically increment the population by 1
                Date currentTime = Calendar.getInstance().getTime();
                Log.d("Main", "Current time:" + currentTime.toString());


                checkIn.update("checkIn", FieldValue.increment(1));
                MiddleMan.checkOutTimer();
            }
        });

        btn_Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarChartActivity.class));


            }
        });




        //on click listener for btn
//        btn_Logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //logout method with firebase
//                FirebaseAuth.getInstance().signOut();
//                //take user to login screen
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });


    }


    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        //Check if user is already logged in (debugging)
        //Log.d(TAG, "USER IS ALREADY LOGGED IN");

        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

}