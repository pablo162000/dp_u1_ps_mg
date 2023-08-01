package com.example.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityNotificationBinding
import com.example.dispositivosmoviles.ui.utilities.BroadcasterNotification
import java.util.Calendar

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        binding.btnNotificacion.setOnClickListener {
            sendNotification()
        }

        binding.btnNotificacionProgramada.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hora=binding.timePicker.hour
            val minutes = binding.timePicker.minute
            Toast.makeText(this,"La notificacion se activara a las: $hora con $minutes",Toast.LENGTH_LONG).show()
            calendar.set(Calendar.HOUR,hora)
            calendar.set(Calendar.MINUTE,minutes)
            calendar.set(Calendar.SECOND,0)
            sendNotificationTimePicker(calendar.timeInMillis)
        }
    }

    private fun sendNotificationTimePicker(time: Long) {
        val myIntent = Intent(applicationContext, BroadcasterNotification::class.java)
        val myPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, myPendingIntent)

    }


    val CHANNEL: String = "Notificaciones"

    //creacion del canal
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Variedades"
            val descriptionText = "Notificaciones simples de variedades"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    //creacion de la notificacion
    @SuppressLint("MissingPermission")
    fun sendNotification() {
        val noti = NotificationCompat.Builder(this, CHANNEL)
        noti.setContentTitle("Primera Notificacion")
        noti.setContentText("Tienes una notificacion")
        noti.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Esta es una notificacion para recordar que estamos trabajando en android")
        )
        noti.setSmallIcon(R.drawable.baseline_home_24)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1, noti.build())
        }
    }


}