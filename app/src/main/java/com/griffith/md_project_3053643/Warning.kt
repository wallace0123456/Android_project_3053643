package com.griffith.md_project_3053643

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
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

class Warning : ComponentActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private var countdownFinished = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.warn_layout)
        countdownTextView = findViewById(R.id.countdownTextView)
        val buttonOk: Button = findViewById(R.id.button_ok)

        countdownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!countdownFinished) {
                    val secondsRemaining = (millisUntilFinished / 1000).toInt()
                    countdownTextView.text = secondsRemaining.toString()
                }
            }
            override fun onFinish() {
                countdownTextView.text = "0"
                // Timer finished, perform any actions you need here
                if (!countdownFinished) {
                    Toast.makeText(
                        applicationContext,
                        "Call 911",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }}

        // Start the countdown timer
        countdownTimer.start()

        buttonOk.setOnClickListener {
            countdownFinished = true
            countdownTextView.text = "Stopped"
            countdownTimer.cancel()
            val intent = Intent(this@Warning, MainActivity::class.java)
            startActivity(intent)
        }

    }

}



