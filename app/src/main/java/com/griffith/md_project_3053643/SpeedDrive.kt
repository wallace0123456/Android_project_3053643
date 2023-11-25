    package com.griffith.md_project_3053643

    import android.Manifest
    import android.content.Context
    import android.content.pm.PackageManager
    import android.hardware.Sensor
    import android.hardware.SensorManager
    import android.location.Location
    import android.location.LocationListener
    import android.location.LocationManager
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.material3.Button
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextField
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.core.content.ContextCompat
    import androidx.core.content.PackageManagerCompat
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme
    import com.google.android.gms.location.FusedLocationProviderClient
    import com.google.android.gms.location.LocationServices
    import androidx.compose.runtime.getValue
    import android.hardware.SensorEvent
    import android.hardware.SensorEventListener
    import androidx.compose.runtime.MutableState
    import android.content.Context.SENSOR_SERVICE
    import android.util.Log
    import androidx.core.content.getSystemService

    class SpeedDrive : ComponentActivity()  {
        private val xSpeed = mutableStateOf(0f)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                //variable for sensor
                val context = LocalContext.current
                val navController = rememberNavController()
                val sensorManager : SensorManager =
                    context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
                val accelrometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                sensorManager.registerListener(
                    sensorEventListener,
                    accelrometer,
                    SensorManager.SENSOR_DELAY_NORMAL)

                MD_project_3053643Theme {

                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        SpeedPage(navController)
                    }
                }

            }
        }
        //function for sensor to get values
        private val sensorEventListener: SensorEventListener = object :SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                if(event?.sensor?.type ==  Sensor.TYPE_ACCELEROMETER) {
                    xSpeed.value = event.values[0]
                    Log.d("SpeedDrive", "xSpeed: ${xSpeed.value}")
                    var y = event!!.values[1]
                    var z = event!!.values[2]
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                print(accuracy)
            }
        }
    }








    @Composable
    fun SpeedPage(navController: NavController){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Current Speed: ",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 26.dp, top = 26.dp)
            )

            Button(onClick = {



            }, modifier = Modifier
                .width(300.dp)
                .padding(bottom = 16.dp))

            {
                Text("Show current location")

            }




            Button(onClick = { navController.navigate("Main") },
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 16.dp))

            {
                Text("Back to main")
            }


    }
    }



