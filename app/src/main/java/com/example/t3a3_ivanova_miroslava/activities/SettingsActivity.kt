package com.example.t3a3_ivanova_miroslava.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.t3a3_ivanova_miroslava.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, true)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity)
        val selectedLanguage =
            sharedPreferences.getString("idioma", "es")

        val selectedData =
            sharedPreferences.getString("bbdd", "local")



        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment())
                .commit()


        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val sharedPreferences = preferenceManager.sharedPreferences
            sharedPreferences?.registerOnSharedPreferenceChangeListener { prefs, key ->
                if (key == "idioma") {
                    val selectedLanguage = prefs.getString("idioma", "es") ?: "es"
                }
            }
        }
    }
}
