package com.example.t3a3_ivanova_miroslava.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.t3a3_ivanova_miroslava.AtmApplication
import com.example.t3a3_ivanova_miroslava.R
import com.example.t3a3_ivanova_miroslava.bd.AtmDatabase
import com.example.t3a3_ivanova_miroslava.databinding.ActivityAtmManagmentBinding
import com.example.t3a3_ivanova_miroslava.pojo.AtmEntity


class AtmManagmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmManagmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAtmManagmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AtmApplication.database.AtmDAO()
        var listaCajeros: MutableList<AtmEntity> = mutableListOf()

        Thread {
            listaCajeros = database.getAllCajeros()

            runOnUiThread {
                binding.btnLista.setOnClickListener {
                    val intent = Intent(this, AtmListActivity::class.java)
                    intent.putExtra("listaCajeros", ArrayList(listaCajeros))
                    startActivity(intent)
                }
            }
        }.start()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAnyadir.setOnClickListener {
            val intent = Intent(this, AtmFormActivity::class.java)
            intent.putExtra("MODO", "INSERTAR")
            startActivity(intent)
        }
    }
}