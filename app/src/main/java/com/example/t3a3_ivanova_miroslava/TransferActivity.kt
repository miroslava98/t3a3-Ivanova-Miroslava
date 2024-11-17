package com.example.t3a3_ivanova_miroslava

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.text.Layout
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t3a3_ivanova_miroslava.databinding.ActivityTransferBinding


class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var esCuentaDestinoSeleccionada = false

        //Accedemos a RadioGroup para cambiar componentes en base a RB seleccionado

        val radioGroup: RadioGroup = findViewById(R.id.radioButtons)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var radioButton: RadioButton = findViewById(checkedId)

            //si id seleccionado es rButton2
            if (radioButton.id == R.id.rButton2) {
                //accedemos al spinner2 mediante su id y cambiamos su visibilidad
                val spCuentaPropia: Spinner = findViewById(R.id.spCuenta2)
                spCuentaPropia.visibility = View.INVISIBLE
                //accedemos al layoutinput mediante su id y cambiamos visibilidad

                val lCuentaExterna: View = findViewById(R.id.lInputCuentaExterna)
                lCuentaExterna.visibility = View.VISIBLE
                esCuentaDestinoSeleccionada = true

            } else if (radioButton.id == R.id.rButton1) {
                //accedemos al layoutText mediante su id y cambiamos su visibilidad

                val lcuentaExterna: View = findViewById(R.id.lInputCuentaExterna)
                lcuentaExterna.visibility = View.INVISIBLE
                //accedemos al layoutinput mediante su id y cambiamos visibilidad
                val spCuentaPropia: Spinner = findViewById(R.id.spCuenta2)
                spCuentaPropia.visibility = View.VISIBLE
            }
        }


        //ADAPTER SPCUENTAS
        var cuentaOrigenSeleccionada: String? = null;
        //forma obsoleta de usar binding de forma más directa, pero puede dar PROBLEMAS
        //recuperamos el spinner
        val spCuentas: Spinner = findViewById<Spinner>(R.id.spCuenta)
        //recuperamos el array de strings
        val cuentas = resources.getStringArray(R.array.cuentas)
        //creamos una variable adaptador de tipo arrayAdapter que es necesaria para los spinner y recicleView?
        //para conectar el layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        //desplegar el dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        //asociar el spinnerCiudades al adaptador
        spCuentas.adapter = adapter
        //listener para saber que elemento se ha seleccionado del spinner, nos hace implementar dos metodos que sobreescribe
        spCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cuentaOrigenSeleccionada = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@TransferActivity,
                    "Seleccionaste: $cuentaOrigenSeleccionada",
                    Toast.LENGTH_SHORT
                ).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //ADAPTER SPCUENTAS2 DESTINO
        var cuentaDestinoSeleccionada: String? = null;
        //forma obsoleta de usar binding de forma más directa, pero puede dar PROBLEMAS
        //recuperamos el spinner
        val spCuentas2: Spinner = findViewById<Spinner>(R.id.spCuenta2)
        //recuperamos el array de strings
        val cuentas2 = resources.getStringArray(R.array.cuentas)
        //creamos una variable adaptador de tipo arrayAdapter que es necesaria para los spinner y recicleView?
        //para conectar el layout
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas2)
        //desplegar el dropdown
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        //asociar el spinnerCiudades al adaptador
        spCuentas2.adapter = adapter2
        //listener para saber que elemento se ha seleccionado del spinner, nos hace implementar dos metodos que sobreescribe
        spCuentas2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cuentaDestinoSeleccionada = parent?.getItemAtPosition(position).toString()
                Toast.makeText(
                    this@TransferActivity,
                    "Seleccionaste: $cuentaDestinoSeleccionada",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //TEXTLAYOUT CUENTA DESTINO EXTERNA
        val cuentaIntroducidaEditText: EditText = findViewById(R.id.tEditCuentaExterna)
        var cuentaExternaIntroducida = cuentaIntroducidaEditText.text.toString()

        //LAYOUTEDIT CANTIDAD
        val cantidadIntroducidaEditText: EditText = findViewById(R.id.editCantidad)
        var cantidadIntroducida = cantidadIntroducidaEditText.text.toString()

        //ADAPTER SPDIVISAS
        var divisaSeleccionada: String? = null
        val spDivisas: Spinner = findViewById<Spinner>(R.id.spDivisas)
        val divisas = resources.getStringArray(R.array.divisas)

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spDivisas.adapter = adapter1
        val opcionDefault = adapter1.getPosition("€")
        spDivisas.setSelection(opcionDefault)

        spDivisas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                divisaSeleccionada = parent?.getItemAtPosition(position).toString()

                Toast.makeText(
                    this@TransferActivity,
                    "Seleccionaste: $divisaSeleccionada",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //CHECKBOX JUSTIFICANTE
        var estaCheckeado = false
        var checkboxJustiicante: CheckBox = findViewById(R.id.checkBox)
        if (checkboxJustiicante.isChecked) {
            estaCheckeado = true
        }

        var cuentaDestinoFinal =
            if (esCuentaDestinoSeleccionada) cuentaExternaIntroducida else cuentaDestinoSeleccionada

        //BOTON ENVIAR
        val btnEnviar: Button = findViewById(R.id.benviar)
        btnEnviar.setOnClickListener {

            cuentaOrigenSeleccionada = spCuentas.selectedItem.toString()
            cuentaDestinoSeleccionada = if (esCuentaDestinoSeleccionada) {
                cuentaIntroducidaEditText.text.toString()
            } else {
                spCuentas2.selectedItem.toString()
            }

            cantidadIntroducida = cantidadIntroducida.toString()
            divisaSeleccionada = spDivisas.selectedItem.toString()
            estaCheckeado = checkboxJustiicante.isChecked


            val mensaje = "Cuenta origen: $cuentaOrigenSeleccionada\n" +
                    "                    \"Cuenta destino: $cuentaDestinoFinal\n" +
                    "                    \"Cantidad: $cantidadIntroducida\n" +
                    "                    \"Divisa: $divisaSeleccionada\n" +
                    "                    \"Justificante: $estaCheckeado"

            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()

        }


        //BOTON CANCELAR

        binding.bcancelar.setOnClickListener {
            val intent = Intent(this, SaludoActivity::class.java)
            startActivity(intent)
        }


    }
}