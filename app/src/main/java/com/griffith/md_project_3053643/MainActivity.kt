package com.griffith.md_project_3053643

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme

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
                mainPage(navController)

                }

            }
        }
    }
}

@Composable
fun mainPage(navController: NavController){

    Image(painter = painterResource(id = R.drawable.mainbg),
        contentDescription = "mainBackground",
        contentScale = ContentScale.FillBounds,
    )
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Surface(color = Color(150,0,0)) {
            Text("Car Accident Detection App ",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 26.dp, top = 26.dp)
                    .fillMaxWidth()
                    .offset(x = 85.dp),
                color = Color.White

                )
        }

        Button(
            onClick = { navController.navigate("Contact") },
            modifier = Modifier
                .padding(bottom = 16.dp, top = 20.dp)
                .width(150.dp)
        )
        {
            Text("Contact Details")
        }


        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(bottom = 16.dp, top = 20.dp).
            width(150.dp)
        ) {
            Text("Check Driving")

        }

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(bottom = 16.dp, top = 20.dp).
            width(150.dp)
        ) {
            Text("About")

        }

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(bottom = 16.dp, top = 20.dp).
            width(150.dp)
        ) {
            Text("Terms")

        }

    }
}



