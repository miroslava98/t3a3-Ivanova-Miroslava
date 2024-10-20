package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {

            val passwd = binding.inputPassword.text.toString()
            val dni = binding.inputDNI.text.toString()


            if (dni.isEmpty()) {
                binding.layoutInputDNI.error = "Campo DNI no puede estar vacío "
            } else if (passwd.isEmpty()) {
                binding.layoutInputPassword.error = "La contraseña no puede estar vacía"
            } else if (dni.length > 9 || dni.length < 9) {
                binding.layoutInputDNI.error = "Máx 9 caracteres"
            } else if (passwd.length < 6) {
                binding.layoutInputPassword.error = "Mínimo 6 caracteres"
            } else {
                binding.layoutInputDNI.error = null
                val intent = Intent(this, SaludoActivity::class.java)
                intent.putExtra("dniUsuario", binding.inputDNI.text.toString())
                startActivity(intent)
            }
        }

        binding.exit.setOnClickListener {
            finishAffinity()
        }



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}