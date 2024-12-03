package com.example.t3a3_ivanova_miroslava.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.adapters.AccountsAdapter
import com.example.t3a3_ivanova_miroslava.adapters.OnClickAccountListener
import com.example.t3a3_ivanova_miroslava.bd.MiBancoOperacional
import com.example.t3a3_ivanova_miroslava.databinding.FragmentAccountsBinding
import com.example.t3a3_ivanova_miroslava.pojo.Cliente
import com.example.t3a3_ivanova_miroslava.pojo.Cuenta

private const val ARG_CLIENTE = "cliente"

class AccountsFragment : Fragment(), OnClickAccountListener {
    private lateinit var globalPositionAdapter: AccountsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var binding: FragmentAccountsBinding
    private lateinit var cliente: Cliente
    private lateinit var listener: AccountsListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)

        //Recuperamos el valor de cliente
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        val listaCuentas: ArrayList<Cuenta>? =
            mbo?.getCuentas(cliente as Cliente?) as ArrayList<Cuenta>?


        globalPositionAdapter = AccountsAdapter(listaCuentas!!, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerViewAccounts.apply {
            layoutManager = linearLayoutManager
            adapter = globalPositionAdapter
            addItemDecoration(itemDecoration)
        }
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(cliente: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, cliente)
                }
            }
    }

    fun setAccountsListener(listener: AccountsListener) {
        this.listener = listener
    }

    override fun onClick(cuenta: Cuenta) {
        if (listener != null) {
            listener.onCuentaSeleccionada(cuenta)
        }
    }

}