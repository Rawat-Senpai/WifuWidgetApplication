package com.example.animewifuapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import isNight


@HiltAndroidApp
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        // change the app theme on the basis of time
        val mode = if(isNight()){
            AppCompatDelegate.MODE_NIGHT_YES
        }else{
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)

    }

}