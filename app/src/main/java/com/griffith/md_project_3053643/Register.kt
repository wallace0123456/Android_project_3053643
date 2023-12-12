    package com.griffith.md_project_3053643

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
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
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.window.Dialog
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.google.firebase.auth.FirebaseAuth
    import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme
    import android.content.Context
    import androidx.compose.foundation.Image
    import androidx.compose.runtime.MutableState
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.painterResource


    class Register : ComponentActivity() {
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
                        //call the RegisterPage with navController
                        RegisterPage(navController)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterPage(navController: NavController){
        //variable for user to register
        var email = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }
        //variable to work with firebase
        val auth = FirebaseAuth.getInstance()
        val errorMessage = remember { mutableStateOf("") }
        //change the background of this page
        Image(painter = painterResource(id = R.drawable.bgres),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //title of this page
            Text(text = "Register CADA account",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 26.dp, top = 26.dp)
            )

            //textfield which let user to input their desire email to create an account
            TextField(value =email.value,
                onValueChange ={ email.value = it} ,
                label = { Text("Email")},
                modifier = Modifier.padding(bottom = 20.dp)
            )

            //textfield for user to input their desire password
            TextField(value = password.value,
                onValueChange ={password.value = it},
                label={ Text("Password")},
                modifier = Modifier.padding(bottom = 20.dp)
            )
        //button to register, if this button is being clicked, it will run the function goAuth to
            //create new user with its email and password
            Button(
                onClick = {
                          goAuth(email.value,password.value,auth,navController)
                   },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            {
                Text("Register")
            }

            //a button to return to login page
            Button(onClick = { navController.navigate("Login") },
                modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp))
            {
                Text("Back to login")

            }
        }
    }

    private fun goAuth(email:String ,password:String,auth:FirebaseAuth,navController: NavController
    ){
        //if the firebase is successfully create the account with user's email and password,
        //it will bring the user to the mainPage.
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
            task-> if(task.isSuccessful){

                navController.navigate("main")

        }
            else{

            }
        }
    }

