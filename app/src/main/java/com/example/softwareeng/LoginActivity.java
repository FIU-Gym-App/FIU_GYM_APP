package com.example.softwareeng;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.ImageView;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    final String TAG = "LOGIN ACTIVITY";


    FloatingActionButton GoogleLoginBtn;
    ImageView logo;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    private static final int RC_SIGN_IN = 9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo);
        int imageResource = getResources().getIdentifier("@drawable/fiugymapplogo", null , this.getPackageName());
        logo.setImageResource(imageResource);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //initialize firebase authentication
        mAuth = FirebaseAuth.getInstance();
//        checkUser();

        //setting elements to actual layout objects

        GoogleLoginBtn = findViewById(R.id.btn_GoogleSignIn_LoginActivity);

        GoogleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });





        //Set on click listener for login button
//        btn_Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loginUser();
//            }
//        });




    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        }else{
            //Toast.makeText(LoginActivity.this , "Sign in FAILED" , Toast.LENGTH_LONG).show();
            //Main already sends user to login screen

        }

    }




//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if(requestCode == RC_SIGN_IN ){
//            Log.d(TAG, "onActivityResult Google sign intent result");
//            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try{
//                //google sign in success, now auth with firebase
//                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
//                firebaseAuthWithGoogleAccount(account);
//            }catch(Exception e){
//                Log.d(TAG, "onActivityResult: "+ e.getMessage());
//
//            }
//        }
//    }

//    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account) {
//              Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account");
//              AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
//              mAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                  @Override
//                  public void onSuccess(AuthResult authResult) {
//                      //login success
//                      Log.d(TAG,"onSuccess: LoggedIn");
//                      //get logged user
//                      FirebaseUser firebaseUser = mAuth.getCurrentUser();
//
//                      //get user info
//                      String uid = firebaseUser.getUid();
//                      String email = firebaseUser.getEmail();
//                      //check if user is new or existing
//                      Log.d(TAG , "onSuccess Email " + email);
//                      Log.d(TAG , "onSuccess UID " + uid);
//
//
//
//                      //check if user is new or existing
//                      if(authResult.getAdditionalUserInfo().isNewUser()){
//                          //user is new -- Account Credential
//                          Toast.makeText(LoginActivity.this, "Account Created..\n" + email, Toast.LENGTH_SHORT).show();
//
//
//                      }else{
//                          //existing user -- Logged in
//                          Log.d(TAG , "onSuccess Existing User...\n" + email );
//                          Toast.makeText(LoginActivity.this, "Existing User...\n " + email, Toast.LENGTH_SHORT).show();
//                      }
//
//                      //start main activity
//                  }
//              }).addOnFailureListener(new OnFailureListener() {
//                  @Override
//                  public void onFailure(@NonNull Exception e) {
//                      //login failure
//                      Log.d(TAG,"onFailure: " + e.getMessage());
//                  }
//              });
//    }

    
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        //updateUI(currentUser);
//        //Check if user is already logged in (debugging)
//        Log.d(TAG, "USER IS ALREADY LOGGED IN");
//
//        if(currentUser != null){
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        }
//    }
//
//    private void loginUser() {
//        String email = et_Email.getText().toString();
//        String password = et_Password.getText().toString();
//
//        //verify that there is valid content in the edit text
//        if(TextUtils.isEmpty(email)){
//            et_Email.setError("Email cannot be empty");
//            et_Email.requestFocus();
//        }else if( TextUtils.isEmpty(password)){
//            et_Password.setError("Password cannot be empty");
//            et_Password.requestFocus();
//        }else{
//            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        //finish is called so activity ends and user can't go back to it.
//                        finish();
//                    }else{
//                        Toast.makeText(LoginActivity.this, "SignIn Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }


}
