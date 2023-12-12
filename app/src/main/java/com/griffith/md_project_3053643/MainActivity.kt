package com.griffith.md_project_3053643

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.service.controls.templates.TemperatureControlTemplate
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme
import androidx.compose.ui.platform.LocalContext
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MD_project_3053643Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                //call the mainPage with navController
                mainPage(navController)

                }

            }
        }
    }
}

//main page
@Composable
fun mainPage(navController: NavController) {
    val context = LocalContext.current
    //import background of the main page
    Image(
        painter = painterResource(id = R.drawable.mainbg),
        contentDescription = "mainBackground",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()

    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    )

    {
        Surface(color = Color(150, 0, 0)) {
            Text(
                "Car Accident Detection App ",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 26.dp, top = 26.dp)
                    .fillMaxWidth()
                    .offset(x = 85.dp),
                color = Color.White

            )
        }

        //main page buttons use to intent to different activities
        Button(
            onClick = { val intent = Intent(context, Contact::class.java)
                context.startActivity(intent) },
            modifier = Modifier
                .padding(bottom = 16.dp, top = 50.dp)
                .width(300.dp)
                .height(50.dp)
        )
        {
            Text("Contact Details")
        }

        //button to intent to SpeedDrive activities
        Button(
            onClick = {
                val intent = Intent(context, SpeedDrive::class.java)
                context.startActivity(intent)},
            modifier = Modifier
                .padding(bottom = 16.dp, top = 50.dp)
                .width(300.dp)
                .height(50.dp)
        ) {
            Text("Check Driving")

        }

        //button to About activities
        Button(
            onClick = {  val intent = Intent(context, About::class.java)
                context.startActivity(intent) },
            modifier = Modifier.padding(bottom = 16.dp, top = 50.dp)
                .width(300.dp)
                .height(50.dp)
        ) {
            Text("About")

        }

        //button to Terms activities
        Button(
            onClick = { val intent = Intent(context, Terms::class.java)
                context.startActivity(intent) },
            modifier = Modifier.padding(bottom = 16.dp, top = 50.dp)
                .width(300.dp)
                .height(50.dp)

        ) {
            Text("Terms")

        }

    }
}




