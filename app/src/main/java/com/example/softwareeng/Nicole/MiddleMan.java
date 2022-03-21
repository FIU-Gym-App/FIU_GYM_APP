package com.example.softwareeng.Nicole;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class MiddleMan {


    public static void checkOutTimer(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        GymUser user = new GymUser();
        GymTimer timer = new GymTimer(5);




    }
}
