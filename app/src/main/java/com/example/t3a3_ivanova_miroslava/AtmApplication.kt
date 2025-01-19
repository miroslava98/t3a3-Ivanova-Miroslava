package com.example.t3a3_ivanova_miroslava

import android.app.Application
import androidx.room.Room
import com.example.t3a3_ivanova_miroslava.bd.AtmDatabase

class AtmApplication : Application() {

    companion object {
        lateinit var database: AtmDatabase
    }


    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, AtmDatabase::class.java, "AtmDatabase").build()
    }
}