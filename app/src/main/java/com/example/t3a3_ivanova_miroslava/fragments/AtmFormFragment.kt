package com.example.t3a3_ivanova_miroslava.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.t3a3_ivanova_miroslava.AtmApplication
import com.example.t3a3_ivanova_miroslava.AtmApplication.Companion.database
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.activities.AtmFormActivity
import com.example.t3a3_ivanova_miroslava.activities.AtmListActivity
import com.example.t3a3_ivanova_miroslava.databinding.FragmentAtmFormBinding
import com.example.t3a3_ivanova_miroslava.pojo.AtmEntity

private const val ARG_MODO = "MODO"

class AtmFormFragment : Fragment() {

    private lateinit var binding: FragmentAtmFormBinding

    private lateinit var modo: String

    private lateinit var activity: AtmListActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            modo = it.getString(ARG_MODO, "INSERTAR")


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAtmFormBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if (modo == "VISUALIZAR") {
            bloquearCampos()
        } else if (modo == "ACTUALIZAR") {
            desbloquearCampos()

        } else if (modo == "INSERTAR") {
            val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbarAtm)
            toolbar.setTitle("Insertar ATM")
            desbloquearCampos()
            limpiarCampos()

            val database = AtmApplication.database.AtmDAO()

            //COGER DATOS ESCRITOS

            binding.btnGuardar.setOnClickListener {
                val direccion = binding.txtinputDireccion.text.toString()
                val latitud = binding.txtInputLatitud1.text.toString().toDouble()
                val longitud = binding.txtInputLatitud2.text.toString().toDouble()

                if (direccion.isNotEmpty() && latitud != null && longitud != null) {

                    val nuevoCajero =
                        AtmEntity(direccion = direccion, latitud = latitud, longitud = longitud)

                    Thread {
                        database.addCajero(nuevoCajero)

                        activity.runOnUiThread {
                            val nuevaLista = database.getAllCajeros()
                            activity.actualizarLista(nuevaLista)
                            requireActivity().onBackPressed()
                        }
                    }.start()

                }
                Toast.makeText(context, "Anyadir atm", Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun bloquearCampos() {
        binding.txtinputDireccion.isEnabled = false
        binding.txtInputLatitud1.isEnabled = false
        binding.txtInputLatitud2.isEnabled = false
    }


    private fun desbloquearCampos() {
        binding.txtinputDireccion.isEnabled = true
        binding.txtInputLatitud1.isEnabled = true
        binding.txtInputLatitud2.isEnabled = true
    }

    private fun limpiarCampos() {
        binding.txtinputDireccion.text?.clear()
        binding.txtInputLatitud1.text?.clear()
        binding.txtInputLatitud2.text?.clear()

    }

    companion object {

        @JvmStatic
        fun newInstance(modo: String) =
            AtmFormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MODO, modo)
                }
            }
    }
}