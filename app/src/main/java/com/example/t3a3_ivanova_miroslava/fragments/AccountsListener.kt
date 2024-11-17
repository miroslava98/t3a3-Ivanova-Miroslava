package com.example.t3a3_ivanova_miroslava.fragments

import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

interface AccountsListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}