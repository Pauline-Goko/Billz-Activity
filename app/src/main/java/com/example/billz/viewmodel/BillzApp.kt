package com.example.billz.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class BillzApp: Application() {
    companion object{
      @SuppressLint("StaticFieldLeak")
      lateinit  var appContaext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContaext = applicationContext
    }
}