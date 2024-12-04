package com.example.t3a3_ivanova_miroslava.fragments

import com.example.t3a3_ivanova_miroslava.pojo.Movimiento

interface MovementsListener {
    fun onMovimientoSeleccionado(movimiento: Movimiento)
}