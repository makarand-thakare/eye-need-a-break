package `in`.dazzlingapps.eyeneedabreak.services

import `in`.dazzlingapps.eyeneedabreak.MainActivity
import `in`.dazzlingapps.eyeneedabreak.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyForegroundService : Service() {

    val ONGOING_NOTIFICATION_ID = 111
    val CHANNEL_ID = "CHANNELIDHERE"


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (intent?.getStringExtra("start_time_for") == "work") {
                showNotification("Work timer", "This is work timer")
            } else if (intent?.getStringExtra("start_time_for") == "break") {

                showNotification("Break timer", "This is Break timer")
            }
        }

        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification(title: String, description: String) {

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(
                    this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }

        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        // Notification ID cannot be 0.
        startForeground(ONGOING_NOTIFICATION_ID, notification)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(ONGOING_NOTIFICATION_ID, notification)
        }

    }


}