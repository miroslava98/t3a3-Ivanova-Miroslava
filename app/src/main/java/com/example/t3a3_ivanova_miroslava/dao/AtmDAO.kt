package com.example.t3a3_ivanova_miroslava.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.t3a3_ivanova_miroslava.pojo.AtmEntity

@Dao
interface AtmDAO {

    @Query("SELECT * FROM AtmEntity")
    fun getAllCajeros(): MutableList<AtmEntity>


    @Insert
    fun insertAll(AtmEntityList: List<AtmEntity>)

    @Insert
    fun addCajero(AtmEntity: AtmEntity)

    @Update
    fun updateCajero(AtmEntity: AtmEntity)

    @Delete
    fun deleteCajero(AtmEntity: AtmEntity)


}