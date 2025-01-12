package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivitySaludoBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta
import com.google.android.material.navigation.NavigationView

class SaludoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivitySaludoBinding
    private var cliente: Cliente? = null
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySaludoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        cliente = intent.getSerializableExtra("Cliente") as? Cliente

        cliente?.let {
            binding.textoUsuarioDNI.text = it.getNombre() ?: "Error"
        } ?: run {
            binding.textoUsuarioDNI.text = "Error"
        }

        //BOTON CUENTAS

        binding.btnPosicion.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        //BOTÓN CONTRASEÑA ̣
        binding.btnContrasenya.setOnClickListener {
            val intent = Intent(this, PasswordActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        //RECUPERAR CUENTAS DE UN CLIENTE
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        var cuentasCliente: ArrayList<Cuenta>? = null
        if (mbo != null) {
            cuentasCliente = mbo.getCuentas(cliente) as ArrayList<Cuenta>
            for (cuenta in cuentasCliente) {
                print("CUENTAS DEL CLIENTE ACTUAL " + cuenta.toString())
            }

        }
        //BOTON MOVIMIENTOS

        binding.btnMovimientos.setOnClickListener {
            val intent = Intent(this, MovementActivity::class.java)
            intent.putExtra("ListaCuentas", cuentasCliente)
            println("CUENTAS ENVIADAS " + cuentasCliente.toString())
            startActivity(intent)
        }

        //BOTON TRANSFER

        binding.btnTransf.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                //redundancia si ya estamos en saludo activity
                // no es necesario solo hay que cerrar el DRAWER
                if (this !is SaludoActivity) {
                    val intent = Intent(this, SaludoActivity::class.java)
                    startActivity(intent)
                }
            }

            R.id.nav_globalpos -> {
                val intent = Intent(this, GlobalPositionActivity::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
            }

            R.id.nav_config -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START) // Cierra el menú después de la selección
        return true
    }
}