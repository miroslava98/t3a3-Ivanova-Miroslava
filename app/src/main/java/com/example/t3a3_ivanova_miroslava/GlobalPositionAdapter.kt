package com.example.t3a3_ivanova_miroslava

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t3a3_ivanova_miroslava.databinding.ItemPositionBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

class GlobalPositionAdapter(
    private val cuentas: ArrayList<Cuenta>
) :
    RecyclerView.Adapter<GlobalPositionAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPositionBinding.bind(view) //Vinculamos la vista a nuestro adapter
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GlobalPositionAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_position, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GlobalPositionAdapter.ViewHolder, position: Int) {
        val cuenta = cuentas[position]
        with(holder) {
            if (cuenta != null) {
                binding.txtCuenta.text = cuenta.getNumeroCuenta()
                binding.txtCantidad.text = cuenta.getSaldoActual().toString()

            }
        }

    }

    override fun getItemCount(): Int {
        return cuentas.size
    }

}