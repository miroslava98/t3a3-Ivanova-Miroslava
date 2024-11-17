package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.adapters.MovementAdapter
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityMovementBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta
import com.example.t3a3_ivanova_miroslava.pojo.Movimiento

class MovementActivity : AppCompatActivity() {

    private lateinit var movementAdapter: MovementAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMovementBinding

    private var listaMovimientos: ArrayList<Movimiento> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMovementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        var listaNumeroCuentas: ArrayList<String> = arrayListOf()
        val listaCuentas = intent.getSerializableExtra("ListaCuentas") as ArrayList<Cuenta>
        for (cuenta in listaCuentas) {
            var numeroCuenta: String = cuenta.getNumeroCuenta().toString()
            listaNumeroCuentas.add(numeroCuenta)
        }


        if (listaCuentas != null && listaNumeroCuentas != null) {
            val spCuentas: Spinner = findViewById(R.id.spinnerMovements)

            if (spCuentas != null) {
                val spinnerAdapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_item, listaNumeroCuentas)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                spCuentas.adapter = spinnerAdapter

                spCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val cuentaSeleccionada = listaCuentas[position]
                        if (mbo != null) {
                            listaMovimientos =
                                mbo.getMovimientos(cuentaSeleccionada) as ArrayList<Movimiento>

                            movementAdapter = MovementAdapter(listaMovimientos)
                            linearLayoutManager = LinearLayoutManager(this@MovementActivity)
                            binding.recylclerViewMovement.apply {
                                layoutManager = linearLayoutManager
                                adapter = movementAdapter
                            }
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            }
        }
        binding.botonVolverAtras.setOnClickListener {
            val intent = Intent(this, SaludoActivity::class.java)
            startActivity(intent)
        }

    }

}
