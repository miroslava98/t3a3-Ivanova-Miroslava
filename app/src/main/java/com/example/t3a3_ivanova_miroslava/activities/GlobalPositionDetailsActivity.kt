package com.example.t3a3_ivanova_miroslava.activities

import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityGlobalPositionDetailsBinding
import com.example.t3a3_ivanova_miroslava.databinding.FragmentAccountsMovementsBinding
import com.example.t3a3_ivanova_miroslava.fragments.AccountsMovementsFragment
import com.example.t3a3_ivanova_miroslava.fragments.MovementsListener
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta
import com.example.t3a3_ivanova_miroslava.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.w3c.dom.Text

class GlobalPositionDetailsActivity : AppCompatActivity(), MovementsListener {
    private lateinit var binding: ActivityGlobalPositionDetailsBinding
    private lateinit var movementsFragment: AccountsMovementsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuenta = intent.getSerializableExtra("Cuenta") as Cuenta

        if (cuenta != null) {
            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
            val movimientosCuenta = mbo?.getMovimientos(cuenta) as ArrayList<Movimiento>

            val frgMovimiento = AccountsMovementsFragment.newInstance(movimientosCuenta)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frgMovements, frgMovimiento).commit()
            frgMovimiento.setMovementsListener(this)




            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (savedInstanceState == null) {
                    movementsFragment = frgMovimiento
                    loadFragment(movementsFragment, R.id.frgMovements)
                    movementsFragment.mostrarDetalle(movimientosCuenta)
                    movementsFragment.setMovementsListener(this)
                }

            }
        }

    }


    private fun loadFragment(fragment: Fragment, containerId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_movimiento, null)

        //Encontrar elementos de la vista dialogo
        val txtMovimientoId = dialogView.findViewById<TextView>(R.id.idMovimiento)
        val txtMovimientoTipo = dialogView.findViewById<TextView>(R.id.tipoMovimiento)
        val txtMovimientoFecha = dialogView.findViewById<TextView>(R.id.fechaMovimiento)
        val txtMovimientoDescripcion = dialogView.findViewById<TextView>(R.id.descripcionMovimiento)


        //asignar valores de movimientos a elementos de dialogo

        txtMovimientoId.text = movimiento.getId().toString()
        txtMovimientoTipo.text = movimiento.getTipo().toString()
        txtMovimientoFecha.text = movimiento.getFechaOperacion().toString()
        txtMovimientoDescripcion.text = movimiento.getDescripcion()

        MaterialAlertDialogBuilder(this)
            .setTitle("")
            .setView(dialogView)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()
            })
            .show()
    }
}

