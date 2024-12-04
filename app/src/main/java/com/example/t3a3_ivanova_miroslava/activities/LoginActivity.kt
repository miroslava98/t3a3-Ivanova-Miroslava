package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityLoginBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        // Set default credentials
//        binding.inputDNI.setText("22222222B")
//        // Replace with your default DNI
//        binding.inputPassword.setText("1234")

        binding.inputDNI.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (binding.inputDNI.text?.isEmpty() == true) {
                    binding.layoutInputDNI.error = "Campo DNI no puede estar vacío "
                } else if (binding.inputDNI.text!!.length < 9) {
                    binding.layoutInputDNI.error = "Longitud inferior a 9 carac. "
                } else {
                    binding.layoutInputDNI.error = null
                }
            }
        }
        binding.inputPassword.onFocusChangeListener =
            View.OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (binding.inputPassword.text?.isEmpty() == true) {
                        binding.layoutInputPassword.error = "Contraseña vacía"
                    } else {
                        binding.layoutInputPassword.error = null
                    }
                }
            }
        binding.login.setOnClickListener {
            val dni = binding.inputDNI.text.toString()
            val contrasneya = binding.inputPassword.text.toString()

            if (dni.isNotEmpty() && contrasneya.isNotEmpty()) {
                var cliente = Cliente()
                cliente.setNif(binding.inputDNI.text.toString())
                cliente.setClaveSeguridad(binding.inputPassword.text.toString())

                val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

                var clienteLogueado: Cliente? = mbo?.login(cliente)
                if (clienteLogueado != null) {
                    println("Loginactivity Cliente logueado: ${clienteLogueado.getNif()}")
                    val intent = Intent(this, SaludoActivity::class.java)
                    intent.putExtra("Cliente", clienteLogueado)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "El cliente no existe en la BBDD", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_LONG).show()
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