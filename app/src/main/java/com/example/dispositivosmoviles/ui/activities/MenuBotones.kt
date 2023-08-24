package com.example.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityBiometricBinding
import com.example.dispositivosmoviles.databinding.ActivityMenuBotonesBinding

class MenuBotones : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBotonesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBotonesBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        initClass()
    }

    private fun initClass(){


        binding.buttonCamara.setOnClickListener{
            var intent = Intent(this@MenuBotones, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.buttonNavigation.setOnClickListener {
            var intent = Intent(this@MenuBotones, PrincipalActivity::class.java)
            startActivity(intent)
        }
        binding.buttonNotification.setOnClickListener {
            var intent = Intent(this@MenuBotones, NotificationActivity::class.java)
            startActivity(intent)
        }

    }



}