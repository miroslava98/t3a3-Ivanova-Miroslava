package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.adapters.AtmListAdapter
import com.example.t3a3_ivanova_miroslava.adapters.OnClickAtmListener
import com.example.t3a3_ivanova_miroslava.bd.AtmDatabase
import com.example.t3a3_ivanova_miroslava.databinding.ActivityAtmListBinding
import com.example.t3a3_ivanova_miroslava.pojo.AtmEntity

class AtmListActivity : AppCompatActivity(), OnClickAtmListener {
    private lateinit var binding: ActivityAtmListBinding

    private lateinit var atmListAdapter: AtmListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    //private lateinit var atmDatabase: AtmDatabase
    private var listaCajeros: MutableList<AtmEntity> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaCajeros = intent.getSerializableExtra("listaCajeros") as MutableList<AtmEntity>
        if (listaCajeros.isNotEmpty()) {

            atmListAdapter = AtmListAdapter(listaCajeros, this)
            linearLayoutManager = LinearLayoutManager(this)
            itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

            binding.recyclerViewAtm.apply {
                layoutManager = linearLayoutManager
                adapter = atmListAdapter
                addItemDecoration(itemDecoration)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Método para actualizar la lista del RecyclerView
    fun actualizarLista(nuevaLista: MutableList<AtmEntity>) {
        listaCajeros.clear()
        listaCajeros.addAll(nuevaLista)
        atmListAdapter.notifyDataSetChanged() // Actualiza el RecyclerView
    }

    override fun onClick(atm: AtmEntity) {
        val intent = Intent(this, AtmFormActivity::class.java).apply {
            putExtra("MODO", "VISUALIZAR")
            putExtra("ATM_ID", atm.id)
        }
        startActivity(intent)
    }


}