package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityGlobalPositionBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

class GlobalPosition : AppCompatActivity() {

    private lateinit var positionAdaptador: GlobalPositionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityGlobalPositionBinding

    private lateinit var cliente: Cliente


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        cliente = intent.getSerializableExtra("Cliente") as Cliente

        if (mbo != null && cliente != null) {

            var listaCuentasCliente = mbo?.getCuentas(cliente) as ArrayList<Cuenta>


            positionAdaptador = GlobalPositionAdapter(listaCuentasCliente)
            linearLayoutManager = LinearLayoutManager(this)

            binding.recyclerviewPosition.apply {
                layoutManager = linearLayoutManager
                adapter = positionAdaptador
            }

        }
        binding.botonVolverAtras.setOnClickListener {
            val intent = Intent(this, SaludoActivity::class.java)
            startActivity(intent)
        }

    }
}