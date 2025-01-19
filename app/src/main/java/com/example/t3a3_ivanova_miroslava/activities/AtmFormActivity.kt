package com.example.t3a3_ivanova_miroslava.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.replace
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.databinding.ActivityAtmFormBinding
import com.example.t3a3_ivanova_miroslava.fragments.AtmFormFragment

class AtmFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAtmFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val toolbar = findViewById<Toolbar>(R.id.toolbarAtm)
        setSupportActionBar(toolbar)

        val modo = intent.getStringExtra("MODO") ?: "INSERTAR"

        val fragment = AtmFormFragment.newInstance(modo)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.atm_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_actualizar -> {
                binding.toolbarAtm.setTitle("Actualizar ATM")
                val frgActualizar = AtmFormFragment.newInstance("ACTUALIZAR")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main, frgActualizar)
                    .commit()
                return true
            }

            R.id.nav_borrar -> {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Eliminar Cajero")
                builder.setMessage("¿Estás seguro que quieres eliminar el cajero?")

                builder.setPositiveButton("Ok") { dialog, which ->


                }

                builder.setNegativeButton("Cancelar") { dialog, which ->
                    dialog.dismiss()
                }

                builder.show()
                return true

            }
        }
        return super.onOptionsItemSelected(item)
    }
}