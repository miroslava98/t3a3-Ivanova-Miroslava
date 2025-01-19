package com.example.t3a3_ivanova_miroslava.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.databinding.ItemAtmBinding
import com.example.t3a3_ivanova_miroslava.pojo.AtmEntity

class AtmListAdapter(
    private val atms: MutableList<AtmEntity>,
    private val listener: OnClickAtmListener
) :
    RecyclerView.Adapter<AtmListAdapter.ViewHolder>() {

    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtmListAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_atm, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AtmListAdapter.ViewHolder, position: Int) {
        val atm = atms.get(position)
        with(holder) {
            setListener(atm)
            binding.txtNumAtm.text = "ATM ${atm.id}"
            binding.txtAtmDireccion.text = atm.direccion.toString()
        }
    }

    override fun getItemCount(): Int {
        return atms.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAtmBinding.bind(view)


        fun setListener(atm: AtmEntity) {
            binding.root.setOnClickListener {
                listener.onClick(atm)
            }
        }
    }

}