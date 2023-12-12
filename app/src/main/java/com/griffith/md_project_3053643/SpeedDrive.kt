package com.griffith.md_project_3053643

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.griffith.md_project_3053643.R
import org.w3c.dom.Text



class SpeedDrive : ComponentActivity() {
    lateinit var fusedLocationProviderClinet: FusedLocationProviderClient
    private lateinit var speedText:TextView
    private lateinit var locationText: TextView // Declare the variable as a property
    var lat = 0.0
    var long = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //implemented accelerometer sensor
        val sensorManager: SensorManager =
            getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(
            sensorEventListener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        //set layout view
        setContentView(R.layout.speed_layout)

        //created variable for GPS use
        fusedLocationProviderClinet = LocationServices.getFusedLocationProviderClient(this)

        //when button is clicked, call checkLocationPermission function
        findViewById<Button>(R.id.button_get_lo).setOnClickListener {
            checkLocationPermission()
        }

        //when button is clicked, call checkLocationPermission function
        findViewById<Button>(R.id.back_main).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Find text by view id
        locationText = findViewById<TextView>(R.id.text_location)
        speedText = findViewById<TextView>(R.id.text_speed)

    }

    private fun checkLocationPermission() {
        val task = fusedLocationProviderClinet.lastLocation
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                val latitude = it.latitude
                val longitude = it.longitude
                locationText.text =
                    "Location: $latitude,$longitude" // Update the locationText

                //save lat and long for passing to other class later
               lat = it.latitude
                long = it.longitude

                //using toast to debug
                Toast.makeText(
                    applicationContext,
                    "${it.latitude} ${it.longitude}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    //function for sensor to get values
    private val sensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {

            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                var x = event!!.values[0]
                speedText.text= "Accelerometer: $x"
                val deceleration = Math.abs(x)
                val threshold = 7.0 //threshold value can be adjusted

                //when sudden stop detected, warning page pops up and location will be collected
                //and send to the warning page for future use.
                if(deceleration > threshold){
                    val intent = Intent(this@SpeedDrive, Warning::class.java).apply {
                        putExtra("latitude", lat)
                        putExtra("longitude", long)
                    }
                    startActivity(intent)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            print(accuracy)
        }
    }
}