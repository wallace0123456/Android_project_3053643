package com.griffith.md_project_3053643

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme

class Contact : ComponentActivity() {
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
                    ContactPage(navController)

                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactPage(navController: NavController){
    var contactName = remember { mutableStateOf("") }
    var contactPhone = remember { mutableStateOf("") }
    var contactEmail = remember { mutableStateOf("") }
    Image(painter = painterResource(id = R.drawable.emer),
        contentDescription = "emergencyBackground",
        contentScale = ContentScale.FillBounds,
    )
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Emergency Contact Details",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 26.dp,top = 100.dp)
            )
        TextField(value = contactName.value,
            onValueChange ={contactName.value = it},
            label ={ Text("Emergency Contact Name")},
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextField(value = contactPhone.value,
            onValueChange ={contactPhone.value = it},
            label ={ Text("Emergency Contact phone")},
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextField(value = contactEmail.value,
            onValueChange ={contactEmail.value = it},
            label ={ Text("Emergency Contact Email")},
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(
            onClick = {
                if (contactName.value != "" && contactPhone.value != "" && contactEmail.value !="") {
                    navController.navigate("Main")
                }},
            modifier = Modifier
                .width(300.dp)
                .padding(bottom = 16.dp)
        )
        {
            Text("Save Emergency Contact")
        }
    }
}