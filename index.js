const functions = require("firebase-functions");

// The Firebase Admin SDK to access Firestore.
const admin = require('firebase-admin');
const { messaging } = require("firebase-admin");
admin.initializeApp();

exports.myFunctionTwo = functions.firestore
  .document('checkInCounter/SecondThreshold')
  .onUpdate((change, context) => { 
    
    admin,messaging().sendToTopic(
      "change_in_population_filling",
      {
        notification: {
          title: "FIU GYM",
          body: "The gym is filling up"
          
        }
      }

    );
    


   });


   // // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });