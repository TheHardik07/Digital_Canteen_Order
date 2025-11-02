package com.example.smartcanteen

import android.app.Application

class AppConfig : Application() {
    override fun onCreate() {
        super.onCreate()
        // Any Kotlin-side setup (e.g., SharedPrefs, Theme)
    }
}
