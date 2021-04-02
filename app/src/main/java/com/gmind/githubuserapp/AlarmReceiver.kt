package com.gmind.githubuserapp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        const val ALARM = "Repeating Alarm"
        const val EXTRA_MESSAGE = "message"
//        const val EXTRA_TYPE ="type"

        private const val ID_REPEATING = 101
    }

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val title = ALARM
        val notifId = ID_REPEATING

        if (message != null){
            showAlarmNotification(context, title, message, notifId)
        }

    }

    fun setAlarm(context: Context, type:String, message: String){

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)

        //        val putExtra = intent.putExtra(EXTRA_TYPE, type)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

//        Toast.makeText(context, "Repeating fixed alarm set up", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, context.getString(R.string.alarm_on), Toast.LENGTH_SHORT).show()
        Log.d("Alarm", "On")
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

//        Toast.makeText(context, "Repeating alarm dibatalkan", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, context.getString(R.string.alarm_off), Toast.LENGTH_SHORT).show()
        Log.d("Alarm", "Off")
    }

    private fun showAlarmNotification(context: Context, title: String, message: String, notifId: Int) {

        val channelId = "Channel_0"
        val channelName = "Repeating Channel"

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(channelId)

            notificationManagerCompat.createNotificationChannel(channel)

        }
        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)
    }
}

//class AlarmReceiver : BroadcastReceiver() {
//
//    companion object{
//        const val ALARM = "Repeating Alarm"
//        const val EXTRA_MESSAGE = "message"
//
//        private const val ID_REPEATING = 101
//    }
//
//    override fun onReceive(context: Context, intent: Intent) {
//        val message = intent.getStringExtra(EXTRA_MESSAGE)
//
//        val title = ALARM
//        val notifId = ID_REPEATING
//
//        if (message != null){
//            showAlarmNotification(context, title, message, notifId)
//        }
//
//    }
//
//    fun setAlarm(context: Context, message: String){
//
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val intent = Intent(context, AlarmReceiver::class.java)
//        intent.putExtra(EXTRA_MESSAGE, message)
//
//
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, 7)
//        calendar.set(Calendar.MINUTE, 27)
//        calendar.set(Calendar.SECOND, 0)
//
//        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
//
//        Toast.makeText(context, "Alarm On", Toast.LENGTH_SHORT).show()
//        Log.d("Alarm", "On")
//    }
//
//    fun cancelAlarm(context: Context) {
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val intent = Intent(context, AlarmReceiver::class.java)
//        val requestCode = ID_REPEATING
//        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
//        pendingIntent.cancel()
//
//        alarmManager.cancel(pendingIntent)
//
//        Toast.makeText(context, "Alarm Off", Toast.LENGTH_SHORT).show()
//        Log.d("Alarm", "Off")
//    }
//
//    private fun showAlarmNotification(context: Context, title: String, message: String, notifId: Int) {
//
//        val channelId = "Channel_0"
//        val channelName = "Repeating Channel"
//
//        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val builder = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(R.drawable.ic_notification)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
//            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
//            .setSound(alarmSound)
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//
//            val channel = NotificationChannel(channelId,
//                channelName,
//                NotificationManager.IMPORTANCE_DEFAULT)
//
//            channel.enableVibration(true)
//            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
//
//            builder.setChannelId(channelId)
//
//            notificationManagerCompat.createNotificationChannel(channel)
//
//        }
//        val notification = builder.build()
//
//        notificationManagerCompat.notify(notifId, notification)
//    }
//}