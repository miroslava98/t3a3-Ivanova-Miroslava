package com.example.t3a3_ivanova_miroslava

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
        setContentView(R.layout.activity_global_position)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        if (mbo != null && cliente != null) {
            cliente = intent.getSerializableExtra("Cliente") as Cliente

            var listaCuentasCliente = mbo?.getCuentas(cliente) as ArrayList<Cuenta>


            positionAdaptador = GlobalPositionAdapter(listaCuentasCliente)
            linearLayoutManager = LinearLayoutManager(this)

            binding.recyclerviewPosition.apply {
                layoutManager = linearLayoutManager
                adapter = positionAdaptador
            }

        }


    }
}