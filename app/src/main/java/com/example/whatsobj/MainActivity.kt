package com.example.whatsobj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object{
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Btn_camera.setOnClickListener{

        }
    }
}