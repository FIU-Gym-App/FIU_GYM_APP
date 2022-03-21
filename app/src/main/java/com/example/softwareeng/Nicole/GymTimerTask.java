package com.example.softwareeng.Nicole;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Timer;
import java.util.TimerTask;

public class GymTimerTask extends TimerTask{


     Timer timer = new Timer();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void run(){
        GymUser.setCheckedIn(false);
        System.out.println(GymUser.getCheckedIn());
        GymUser.setNumberOfCheckedIn(GymUser.getNumberOfCheckedIn()- 1);
        timer.cancel();
        //after time has finished checkout the user
        //get collection and document path
        DocumentReference checkIn = db.collection("checkInCounter").document("checkIn");
        // Atomically increment the population by 1
        checkIn.update("checkIn", FieldValue.increment(-1));

    }
}
