package com.bersyte.settingpreferences

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val darkModeString = getString(R.string.dark_mode)

        if (key == darkModeString) {

            val pref = sharedPreferences?.getString(key, "1")

            when (pref?.toInt()) {
                1 ->{ AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )
                }

                2 -> AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                3 -> AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                4 -> AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    )
            }
        }

    }


    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }
}