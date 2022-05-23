package com.bignerdranch.android.preemptivebookcafeuser

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        lateinit var prefs: PrefsManager
    }

    override fun onCreate() {
        prefs = PrefsManager(applicationContext)
        super.onCreate()
    }


}

class PrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences("pref_name", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String) : String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}