package com.juanguicj.lab1_ejer1.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.juanguicj.lab1_ejer1.databinding.ActivitySplashBinding
import com.juanguicj.lab1_ejer1.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    private lateinit var splashActivity: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashActivity = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashActivity.root
        setContentView(view)

        val timer = Timer()

        timer.schedule(
            timerTask {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        , 1000
        )
    }
}