package com.example.t3a3_ivanova_miroslava.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class AtmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val direccion: String?,
    val latitud: Double?,
    val longitud: Double?,
    val zoom: String? = " "
) : Serializable {


}