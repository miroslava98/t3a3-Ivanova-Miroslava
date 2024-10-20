package com.example.t3a3_ivanova_miroslava

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.databinding.ActivityMainBinding
import com.example.t3a3_ivanova_miroslava.databinding.ActivitySaludoBinding

class SaludoActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySaludoBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySaludoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dniRecibido = intent.getStringExtra("dniUsuario")
        binding.textoUsuarioDNI.text =
            binding.textoUsuarioDNI.text.toString() + dniRecibido.toString()


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}