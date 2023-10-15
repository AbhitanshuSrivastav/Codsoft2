package com.example.memeapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
//channel ids are use for notification channel creating
    val CHANNEL_ID = "Channel_id"
    val channelname = "channelName"
    val notificationID = 0


    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //notification buildng and calling by Button..

        createNotificationChannel()

        //Pending Intent
        val intent=Intent(this,MainActivity::class.java)
        val pendingIntent=PendingIntent.getActivities(this,0, arrayOf(intent), FLAG_MUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setContentTitle("My notification")
            .setContentText("this is my notificaton").setStyle(NotificationCompat.BigTextStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)


        val btn = findViewById<Button>(R.id.Notify)
        btn.setOnClickListener {

            notificationManager.notify(notificationID, builder)

        }
    }



        //Notification Channel(For above oreo version)

     fun   createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, channelname,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                    .apply {
                        description = "This is my notification Channel"
                        lightColor = Color.BLUE
                        enableLights(true)
                    }
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
            }

        }
    }










