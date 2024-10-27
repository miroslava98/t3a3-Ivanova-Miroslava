package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.databinding.ActivityMainBinding
import com.example.t3a3_ivanova_miroslava.databinding.ActivityPasswordBinding
import com.example.t3a3_ivanova_miroslava.databinding.ActivitySaludoBinding

class SaludoActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySaludoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaludoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dniRecibido = intent.getStringExtra("dniUsuario")
        binding.textoUsuarioDNI.text =
            binding.textoUsuarioDNI.text.toString() + dniRecibido.toString()


        //BOTÓN CONTRASEÑA

        binding.btnContrasenya?.setOnClickListener {
            val intent = Intent(this, PasswordActivity::class.java)
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