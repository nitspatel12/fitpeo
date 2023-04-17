package com.fitpeo.assignment

import android.app.Application
import com.fitpeo.assignment.di.ApplicationComponent
import com.fitpeo.assignment.di.DaggerApplicationComponent

class PhotoApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}