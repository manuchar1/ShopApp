package com.tbcacademy.shopapp.app

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tbcacademy.shopapp.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopApp : Application() {

    override fun onCreate() {
        super.onCreate()
        notifications()

    }

    private fun notifications() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                d("FirebaseMessaging", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("FirebaseMessaging", "$token")
        })
    }

}