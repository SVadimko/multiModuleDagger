package com.example.multi

import android.app.Application
import android.util.Log
import com.example.multi.details.DetailsDep
import com.example.multi.details.DetailsDepProvider
import com.example.multi.di.AppComponent
import com.example.multi.di.DaggerAppComponent

class MyApp:Application(), DetailsDepProvider {

    override fun onCreate() {
        super.onCreate()
        Log.wtf("myApp","myApp")
    }

    private val appComponent: AppComponent by lazy{
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override val dep: DetailsDep = appComponent
}