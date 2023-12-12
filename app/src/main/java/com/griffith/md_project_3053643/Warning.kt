package com.griffith.md_project_3053643

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Warning : ComponentActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private var countdownFinished = false
    private lateinit var database: DatabaseReference
    private var name = ""
    private var phone = ""
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.warn_layout)
        //receive location from speedDrive page
        val latitude = intent.getDoubleExtra("latitude",0.0)
        val longitude = intent.getDoubleExtra("longitude",0.0)

        // Initialize the Firebase Realtime Database reference
        database = FirebaseDatabase.getInstance().getReference().child("contacts")
        // Retrieve the contact details from the database
        retrieveContactDetails()


        //for debug
/*        Toast.makeText(
            applicationContext,
            "${latitude} ${longitude}",
            Toast.LENGTH_SHORT
        ).show()*/

        //find button and view in xml by id
        countdownTextView = findViewById(R.id.countdownTextView)
        val buttonOk: Button = findViewById(R.id.button_ok)
        val buttonCall: Button = findViewById(R.id.call)

        //implemented countdown timer
        countdownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!countdownFinished) {
                    val secondsRemaining = (millisUntilFinished / 1000).toInt()
                    countdownTextView.text = secondsRemaining.toString()
                }
            }
            override fun onFinish() {
                countdownTextView.text = "0"
                // Timer finished, meaning accident happened because user didn't
                //press I'm Okay button, it will navigate user to send an emergency email to emergency contact
                if (!countdownFinished) {
                    sendEmergencyEmail(email,name,latitude,longitude)
            }
        }}

        // Start the countdown timer
        countdownTimer.start()

        //when this button is clicked, stop the timer and bring the user back to main activity
        buttonOk.setOnClickListener {
            countdownFinished = true
            countdownTextView.text = "Stopped"
            countdownTimer.cancel()
            val intent = Intent(this@Warning, MainActivity::class.java)
            startActivity(intent)
        }

        //when this button is clicked, navigate user to phone call to dial 911
        buttonCall.setOnClickListener {
            val phoneNumber = "911"
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }


    }

    // Function to send the email
    fun sendEmergencyEmail(email: String, name: String, latitude: Double, longitude: Double) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, "Urgent!! Car Crash happened!")
            putExtra(
                Intent.EXTRA_TEXT,
                "Mr.$name, your acquaintance has been involved " +
                        "in a car crash at location: $latitude, $longitude"
            )
        }
        // Set the intent flags to ensure it is sent without user interaction
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.addFlags(Intent.FLAG_FROM_BACKGROUND)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }


    //retrieve emergency details from database for later use
    private fun retrieveContactDetails() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    email  = snapshot.child("email").getValue().toString()
                    name = snapshot.child("name").getValue().toString()
                    phone = snapshot.child("phone").getValue().toString()
                }}

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })}}









