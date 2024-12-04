package com.example.t3a3_ivanova_miroslava.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.adapters.MovementAdapter
import com.example.t3a3_ivanova_miroslava.adapters.OnClickMovementListener
import com.example.t3a3_ivanova_miroslava.databinding.FragmentAccountsMovementsBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Movimiento

private const val ARG_MOVIMIENTOS = "movimientos"

class AccountsMovementsFragment : Fragment(), OnClickMovementListener {

    private lateinit var movementAdapter: MovementAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var binding: FragmentAccountsMovementsBinding
    private lateinit var listener: MovementsListener

    private var movimientos: ArrayList<Movimiento> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movimientos = it.getSerializable(ARG_MOVIMIENTOS) as ArrayList<Movimiento>
            println("ACCOUNTSFRAGMENT :  ${movimientos.size}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)
        Log.d("AccountsMovementsFragment", "onCreateView called")
        if (movimientos.isEmpty()) {
            movementAdapter = MovementAdapter(ArrayList(), this)
            Toast.makeText(context, "No hay movimientos disponibles", Toast.LENGTH_LONG).show()
        } else {
            movementAdapter = MovementAdapter(movimientos, this)

        }
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recylclerViewMovement.apply {
            layoutManager = linearLayoutManager
            adapter = movementAdapter
            addItemDecoration(itemDecoration)
        }

        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance(movimientos: ArrayList<Movimiento>) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIMIENTOS, movimientos)
                }
            }
    }

    fun mostrarDetalle(movimientos: ArrayList<Movimiento>) {
        this.movimientos = movimientos
        if (::movementAdapter.isInitialized) {
            movementAdapter.notifyDataSetChanged()
        }
    }

    fun setMovementsListener(listener: MovementsListener) {
        this.listener = listener
    }

    override fun onClick(movimiento: Movimiento) {
        if (listener != null) {
            listener.onMovimientoSeleccionado(movimiento)
        }
    }
}