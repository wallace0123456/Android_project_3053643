    package com.griffith.md_project_3053643

    import android.app.ProgressDialog
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
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.google.firebase.auth.FirebaseAuth
    import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme



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
                        RegisterPage(navController)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterPage(navController: NavController){
        var email = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }
        val auth = FirebaseAuth.getInstance()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Login CADA",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 26.dp, top = 26.dp)
            )
            TextField(value =email.value,
                onValueChange ={ email.value = it} ,
                label = { Text("Email")},
                modifier = Modifier.padding(bottom = 20.dp)
            )
            TextField(value = password.value,
                onValueChange ={password.value = it},
                label={ Text("Password")},
                modifier = Modifier.padding(bottom = 20.dp)
            )
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
        }
    }

    private fun goAuth(email:String ,password:String,auth:FirebaseAuth,navController: NavController){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
            task-> if(task.isSuccessful){
                navController.navigate("main")

        }
            else{

        }
        }
    }