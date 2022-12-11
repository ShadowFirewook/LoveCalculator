package com.example.lovecalculator

import android.app.Application
import androidx.room.Room
import com.example.lovecalculator.remote.LoveApi
import com.example.lovecalculator.room.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object{
        lateinit var appDatabase: AppDatabase
        lateinit var api:LoveApi
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"Love").allowMainThreadQueries().build()
        api = RetrofitService().api
    }

}