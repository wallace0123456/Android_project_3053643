package com.griffith.md_project_3053643

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.griffith.md_project_3053643.ui.theme.MD_project_3053643Theme

class Terms : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set layout
        setContentView(R.layout.terms_layout)

        //implemented function to the button, when on clicked go back to main activity
        findViewById<Button>(R.id.back_main).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //implemented function to the button, when on clicked go to email
        findViewById<Button>(R.id.contact_us).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayListOf("hinhin42446@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Enquiries related to Car Detection Plus App ")

            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}

