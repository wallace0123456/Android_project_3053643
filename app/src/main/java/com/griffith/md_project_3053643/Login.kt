package com.griffith.md_project_3053643

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme

class Login : ComponentActivity() {
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
                    LoginPage(navController)
                    NavigationScreen()

                    }
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    var userName = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    Image(painter = painterResource(id = R.drawable.bg),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
      Text(text = "Login CADA",
           style = MaterialTheme.typography.bodyLarge,
           modifier = Modifier.padding(bottom = 26.dp, top = 26.dp)
      )
      TextField(value =userName.value ,
          onValueChange ={ userName.value = it} ,
          label = { Text("Username")},
          modifier = Modifier.padding(bottom = 20.dp)
      )
      TextField(value = password.value,
          onValueChange ={password.value = it},
          label={ Text("Password")},
          modifier = Modifier.padding(bottom = 20.dp)
      )
      Button(
          onClick = {
              if(userName.value == "user" && password.value == "password") {
                  navController.navigate("Main")
              }},

          modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 16.dp)
      )
      {
          Text("Login")
      }
    }
}

@Composable
fun NavigationScreen(){
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController,
            startDestination = "Login")
        {
            composable("Main"){
                mainPage(navController)
            }
            composable("Contact"){
                ContactPage(navController)
            }
            composable("Login"){
                LoginPage(navController)
            }
        }
    }
}


