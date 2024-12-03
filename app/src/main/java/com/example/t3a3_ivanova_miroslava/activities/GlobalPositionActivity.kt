package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.adapters.AccountsAdapter
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityGlobalPositionBinding
import com.example.t3a3_ivanova_miroslava.fragments.AccountsFragment
import com.example.t3a3_ivanova_miroslava.fragments.AccountsListener
import com.example.t3a3_ivanova_miroslava.fragments.AccountsMovementsFragment
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta


class GlobalPositionActivity : AppCompatActivity(), AccountsListener {

    private lateinit var binding: ActivityGlobalPositionBinding
    private lateinit var accountsFragment: AccountsFragment
    private lateinit var movementsFragment: AccountsMovementsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        if (cliente != null) {

            val frgCuenta = AccountsFragment.newInstance(cliente)
            supportFragmentManager.beginTransaction()
                .add(R.id.frgAccounts, frgCuenta).commit()
            frgCuenta.setAccountsListener(this)



            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }


            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (savedInstanceState == null) {
                    accountsFragment = frgCuenta
                    loadFragment(accountsFragment)
                    accountsFragment.setAccountsListener(this)
                }
            }
        }
        binding.botonVolverAtras.setOnClickListener {
            val intent = Intent(this, SaludoActivity::class.java)
            startActivity(intent)
        }
    }


    private fun loadFragment(fragment: Fragment, containerId: Int = R.id.frgAccounts) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        if (cuenta != null) {
            val hayMovimiento = supportFragmentManager.findFragmentById(R.id.frgMovements) != null
            if (hayMovimiento) {
                accountsFragment = AccountsFragment()
                val transaction = supportFragmentManager.beginTransaction()

            }
        } else {
            //    movementsFragment = AccountsMovementsFragment.newInstance
        }
    }
}