package com.bersyte.settingpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.bersyte.settingpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = PreferenceManager
            .getDefaultSharedPreferences(this)

        val name = prefs.getString(
            "signature", ""
        )

        val text = prefs.getString("text", "")
        val checkbox = prefs.getBoolean("checkbox", false)
        val list = prefs.getString("list", "")
        val switch = prefs.getBoolean("switch", false)


        binding.apply {
            tvSignature.text = name
            tvList.text = list.toString()
            tvText.text = text

            if (switch) {
                tvSwitch.text = "switch is on"
            } else {
                tvSwitch.text = "switch is off"
            }

            if (checkbox) {
                tvCheckbox.text = "Checkbox is Checked\nand this text will be visible"
            } else {
                tvCheckbox.visibility = View.GONE
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(
                    this,
                    SettingsActivity::class.java
                )
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}