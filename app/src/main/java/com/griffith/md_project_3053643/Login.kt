package com.griffith.md_project_3053643

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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
                    //call the loginPage with navController
                    LoginPage(navController)
                    //call the function to make sure navigation works as it is the starting
                    //activity.
                    NavigationScreen()

                    }
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    //variable for user to login
    var userName = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    //variable to work with firebase authtication
    val auth = FirebaseAuth.getInstance()

    //change the background of this page
    Image(painter = painterResource(id = R.drawable.bg),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //title of this page
      Text(text = "Login CADA",
           style = MaterialTheme.typography.bodyLarge,
           modifier = Modifier.padding(bottom = 26.dp, top = 26.dp)
      )

        //textfield for user to input their username which will be check late.
      TextField(value =userName.value ,
          onValueChange ={ userName.value = it} ,
          label = { Text("Username")},
          modifier = Modifier.padding(bottom = 20.dp)
      )

        //textfield for user to input their password which will be check late.
        TextField(value = password.value,
          onValueChange ={password.value = it},
          label={ Text("Password")},
          modifier = Modifier.padding(bottom = 20.dp)
      )

        //button for login, when the button is click, it will start authenticate with function
        //tryLogin with the variable user enters in the textfeild
      Button(
          onClick = {
              tryLogin(userName.value,password.value,auth,navController)
              },

          modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 16.dp)
      )
      {
          Text("Login")
      }

        //button for register, when the button is clicked, it will bring users to register page
        Button(
            onClick = {
                navController.navigate("Register")
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        {
            Text("Register")
        }

    }
}


//navigation logic use for every page, add the route when a new page is add to ensure it navigates
//to the right page.
@SuppressLint("UnrememberedMutableState")
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

            composable("Register"){
                RegisterPage(navController)
            }

        }
    }
}

//firebase authentication, try to use the login variables above username and password to login,
//if the username and password match with the info in firebase database, the user will be
//navigate to the main page
private fun tryLogin(userName:String,password:String,auth:FirebaseAuth,navController: NavController){
        auth.signInWithEmailAndPassword(userName,password)
            .addOnCompleteListener{
                    task->
                if(task.isSuccessful){
                    navController.navigate("main")
                }
            }

}

