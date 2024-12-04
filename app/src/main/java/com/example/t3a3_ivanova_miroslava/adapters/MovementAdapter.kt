package com.example.t3a3_ivanova_miroslava.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t3a3_ivanova_miroslava.pojo.Movimiento
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.databinding.ItemMovementBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

class MovementAdapter(
    private val movimientos: ArrayList<Movimiento>,
    private val listener: OnClickMovementListener
) :
    RecyclerView.Adapter<MovementAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movement, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movimientos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientos.get(position)
        with(holder) {
            setListener(movimiento)
            if (movimiento != null) {
                binding.txtMovimiento.text = movimiento.getDescripcion()
                binding.txtDetalleMovimiento.text =
                    movimiento.getFechaOperacion().toString() + " " + movimiento.getImporte()
            }
        }
    }


    // ViewHolder class to hold the views for each item
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovementBinding.bind(view)


        fun setListener(movimiento: Movimiento) {
            binding.root.setOnClickListener {
                listener.onClick(movimiento)
            }
        }
    }
}