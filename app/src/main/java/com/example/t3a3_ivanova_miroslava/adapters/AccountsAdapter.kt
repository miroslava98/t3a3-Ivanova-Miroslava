package com.example.t3a3_ivanova_miroslava.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.databinding.ItemPositionBinding
import com.example.t3a3_ivanova_miroslava.fragments.AccountsFragment
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

class AccountsAdapter(
    private val cuentas: ArrayList<Cuenta>, private val listener: OnClickAccountListener
) : RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_position, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuenta = cuentas.get(position)
        with(holder) {
            setListener(cuenta)
            if (cuenta != null) {
                binding.txtCuenta.text = cuenta.getNumeroCuenta()
                binding.txtCantidad.text = cuenta.getSaldoActual().toString()
            }
        }
    }
    override fun getItemCount(): Int {
        return cuentas.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPositionBinding.bind(view) //Vinculamos la vista a nuestro adapter

        fun setListener(cuenta: Cuenta) {
            binding.root.setOnClickListener {
                listener.onClick(cuenta)
            }
        }
    }
}