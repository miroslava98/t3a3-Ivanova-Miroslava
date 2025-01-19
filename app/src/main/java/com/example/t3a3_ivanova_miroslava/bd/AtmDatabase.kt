package com.example.t3a3_ivanova_miroslava.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.t3a3_ivanova_miroslava.dao.AtmDAO
import com.example.t3a3_ivanova_miroslava.pojo.AtmEntity

@Database(entities = [AtmEntity::class], version = 1)
abstract class AtmDatabase : RoomDatabase() {

    abstract fun AtmDAO(): AtmDAO
}