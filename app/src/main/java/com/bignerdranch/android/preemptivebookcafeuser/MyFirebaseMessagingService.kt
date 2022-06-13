package com.bignerdranch.android.preemptivebookcafeuser

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("FCM Log", "Refreshed token: $token")
        MyApplication.prefs.setString("fcmToken", "${token}")
    }
}
