package com.example.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityBiometricBinding
import com.example.dispositivosmoviles.ui.viewmodels.BiometricViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class BiometricActivity : AppCompatActivity() {



    private lateinit var binding: ActivityBiometricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAutentication.setOnClickListener {
            autenticacionBiometrica()
        }
    }

    private fun autenticacionBiometrica(){
        if(checkBiometric()){
            val executor=ContextCompat.getMainExecutor(this)
            val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticacion requerida")
                .setSubtitle("Ingrese su huella digital")
                .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                .build()
            val biometricManager = BiometricPrompt(this
                , executor
                , object :BiometricPrompt.AuthenticationCallback(){
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        val miIntent = Intent(this@BiometricActivity,MenuBotones::class.java)
                        startActivity(miIntent)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                    }
                })
            biometricManager.authenticate(biometricPrompt)
        }else{
            Snackbar.make(binding.root, "No se tiene los requisitos para hacer esto", Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun checkBiometric():Boolean{
        var comprobar: Boolean=false
        val biometricManager=BiometricManager.from(this)
        when(biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)){
            BiometricManager.BIOMETRIC_SUCCESS->{
                comprobar = true
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->{
                comprobar= false
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE->{
                comprobar= false
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{
                /*val intent=Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                intent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED
                    , BIOMETRIC_STRONG or DEVICE_CREDENTIAL)*/

                startActivity(Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                    .putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED
                        , BIOMETRIC_STRONG or DEVICE_CREDENTIAL))
                comprobar= true
            }
        }
        return comprobar
    }
}