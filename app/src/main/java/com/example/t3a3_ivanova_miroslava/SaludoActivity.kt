package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivitySaludoBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

class SaludoActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySaludoBinding
    private var cliente: Cliente? = null
    private var cuenta: Cuenta? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaludoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cliente = intent.getSerializableExtra("Cliente") as Cliente

        println(cliente?.getNif())

        if (cliente != null) {
            binding.textoUsuarioDNI.text = "${cliente!!.getNif()}"
        } else {
            binding.textoUsuarioDNI.text = "Error"
        }

        //BOTON CUENTAS

        binding.btnPosicion.setOnClickListener {
            if (cliente != null) {
                val intent = Intent(this, GlobalPosition::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
            }
        }

        //BOTÓN CONTRASEÑA

        binding.btnContrasenya.setOnClickListener {
            if (cliente != null) {
                val intent = Intent(this, PasswordActivity::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
            }
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
            if (cliente != null) {
                val intent = Intent(this, Movement::class.java)
                intent.putExtra("ListaCuentas", cuentasCliente)
                println("CUENTAS ENVIADAS " + cuentasCliente.toString())
                startActivity(intent)
            }
        }

        //BOTON TRANSFER

        binding.btnTransf.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}