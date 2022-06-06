package com.bignerdranch.android.preemptivebookcafeuser

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService() {
    
    override fun onNewToken(token: String) {
        val fbToken = token
        MyApplication.prefs.setString("fbToken", "${fbToken}")
        Log.d("FCM Log", "token: $fbToken")
    }

}