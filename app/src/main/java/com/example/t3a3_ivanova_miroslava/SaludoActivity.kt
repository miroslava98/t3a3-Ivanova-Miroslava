package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.databinding.ActivitySaludoBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente

class SaludoActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySaludoBinding
    private lateinit var cliente: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaludoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cliente = intent.getSerializableExtra("Cliente") as Cliente

        println(cliente?.getNif())

        if (cliente != null) {

            binding.textoUsuarioDNI.text = "${cliente.getNif()}"
        } else {
            binding.textoUsuarioDNI.text = "Error"
        }

        //BOTON CUENTAS

        binding.btnPosicion.setOnClickListener {
            val intent = Intent(this, GlobalPosition::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }


        //BOTÓN CONTRASEÑA

        binding.btnContrasenya.setOnClickListener {
            val intent = Intent(this, PasswordActivity::class.java)
            intent.putExtra("Cliente", cliente)
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