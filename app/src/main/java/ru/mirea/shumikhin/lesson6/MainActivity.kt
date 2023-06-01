package ru.mirea.shumikhin.lesson6

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mirea.shumikhin.lesson6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)

        infoInit()

        binding.btnSave.setOnClickListener{
            saveData()
        }

        setContentView(binding.root)
    }

    private fun infoInit() {
        binding.etGroup.setText(sharedPrefs.getString(GROUP_KEY, ""))
        binding.etSpisok.setText(sharedPrefs.getString(POSITION_KEY, ""))
        binding.etFavFilm.setText(sharedPrefs.getString(FAVORITE_FILM_KEY, ""))
    }

    private fun saveData() {
        with(sharedPrefs.edit()) {
            putString(GROUP_KEY, binding.etGroup.text.toString())
            putString(POSITION_KEY, binding.etSpisok.text.toString())
            putString(FAVORITE_FILM_KEY, binding.etFavFilm.text.toString())
            apply()
        }
    }

    companion object {
        const val GROUP_KEY = "group"
        const val POSITION_KEY = "position"
        const val FAVORITE_FILM_KEY = "favoriteKey"
    }
}