package com.leafy

import android.app.Application
import android.content.Context

class LeafyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        LeafyApplication.application = this
    }

    companion object {
        val context: Context
            get() = application.applicationContext
        private lateinit var application: Application

        fun getApplication(): Application = application
    }
}