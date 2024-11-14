package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.bd.MiBD
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.ActivityPasswordBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.google.android.material.snackbar.Snackbar

class PasswordActivity : AppCompatActivity() {
    private lateinit var cliente: Cliente
    private lateinit var binding: ActivityPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputPassword.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->

            if (!hasFocus) {
                if (binding.inputPassword.text?.isEmpty() == true) {
                    binding.layoutInputPassword.error = "Indica la contraseña actual"
                }
            }
        }

        binding.inputPassword1.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->

            if (!hasFocus) {
                if (binding.inputPassword1.text?.isEmpty() == true) {
                    binding.layoutInputPassword1.error = "Indica la contraseña nueva"
                } else if (binding.inputPassword1.text!!.length < 5) {
                    binding.layoutInputPassword2.error = "La longitud mínima es de 5 caracteres"

                }
            }
        }
        binding.inputPassword2.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->

            if (!hasFocus) {
                if (binding.inputPassword2.text?.isEmpty() == true) {
                    binding.layoutInputPassword2.error = "Repite la contraseña nueva"
                } else if (binding.inputPassword2.text.toString() != binding.inputPassword1.text.toString()) {
                    Snackbar.make(
                        binding.root,
                        "Las contraseñas no coinciden",
                        Snackbar.LENGTH_SHORT
                    )
                        .setAnchorView(binding.layoutInputPassword2)
                        .show()
                }
            }
        }

        binding.btnCambiarContrasenya.setOnClickListener {

            var contrasenyaActual = binding.inputPassword.text.toString()
            val contrasenyaNueva = binding.inputPassword1.text.toString()
            val contrasenyaNuevaRepetir = binding.inputPassword2.text.toString()

            if (contrasenyaActual.isNotEmpty()
                && contrasenyaNueva.isNotEmpty()
                && contrasenyaNuevaRepetir.isNotEmpty()
            ) {
                val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
                cliente = intent.getSerializableExtra("Cliente") as Cliente
                cliente.setClaveSeguridad(contrasenyaNueva)
                val contrasenyaCambiada = mbo?.changePassword(cliente)

                if (contrasenyaCambiada == 1) {
                    Toast.makeText(
                        this,
                        "Contraseña cambiada con exito ${cliente.getClaveSeguridad()}",
                        Toast.LENGTH_LONG
                    ).show()

                    binding.inputPassword.text?.clear()
                    binding.inputPassword1.text?.clear()
                    binding.inputPassword2.text?.clear()

                    val intent = Intent(this, SaludoActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "La contraseña no se ha cambiado",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
        binding.btnCancelarCambioContrasenya.setOnClickListener {
            val intent = Intent(this, SaludoActivity::class.java)
            startActivity(intent)
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