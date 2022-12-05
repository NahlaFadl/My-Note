package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        Splash_image.animate().apply {
//            duration=1000
//            //   rotationXBy(360f)
//            rotationY(360f)
//        }.start()

        Timer().schedule(object : TimerTask(){
            override fun run() {
                val intent= Intent(this@SplashActivity,ShowMyNotes::class.java)
                startActivity(intent)
                finish()
            }

        },5000L)
    }
}