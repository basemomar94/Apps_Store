package com.bassem.appstore.utilities

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bassem.appstore.R

fun Context.createNotificationChannel() {
    val channelId = getString(R.string.default_notification_channel_id)
    val channelName = getString(R.string.default_notification_channel_name)

    val notificationManager = getSystemService(NotificationManager::class.java)
    if (notificationManager != null) {
        val existingChannel = notificationManager.getNotificationChannel(channelId)
        if (existingChannel == null) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "New apps Alert"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}


@SuppressLint("MissingPermission")
fun Context.sendBackgroundNotification() {
    val notification = NotificationCompat.Builder(
        this,
        getString(R.string.default_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("App Store")
        .setOnlyAlertOnce(true)
        .setOngoing(false)
        .setContentText("New Apps have been released \uD83C\uDF89")
        .setAutoCancel(false)
        .build()
    val notificationManger = NotificationManagerCompat.from(this)
    notificationManger.notify(5, notification)
}