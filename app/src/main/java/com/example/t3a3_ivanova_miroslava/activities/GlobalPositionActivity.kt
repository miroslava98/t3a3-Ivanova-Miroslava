package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.adapters.AccountsAdapter
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityGlobalPositionBinding
import com.example.t3a3_ivanova_miroslava.fragments.AccountsFragment
import com.example.t3a3_ivanova_miroslava.fragments.AccountsListener
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity(), AccountsListener {

    private lateinit var positionAdaptador: AccountsAdapter
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

        val frgCuenta: AccountsFragment = AccountsFragment.newInstance(cliente as Cliente)


        //supportFragmentManager.beginTransaction().add(R.id.frgAccounts).commit()

        frgCuenta.setCuentasListener(this)



        binding.botonVolverAtras.setOnClickListener {
            val intent = Intent(this, SaludoActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {

    }
}