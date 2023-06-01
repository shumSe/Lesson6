package ru.mirea.shumikhin.internalfilestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ru.mirea.shumikhin.internalfilestorage.databinding.ActivityMainBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fileName = "mirea.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val text = binding.etData.text.toString()
            setTextToFile(fileName, text)
        }
        postToTextView(fileName)
    }

    private fun setTextToFile(fileName: String, text: String) {
        var outputStream: FileOutputStream
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.toByteArray());
            outputStream.close();
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    private fun postToTextView(fileName: String) {
        Thread {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            binding.etData.post { binding.etData.setText(getTextFromFile(fileName)) }
        }.start()
    }

    private fun getTextFromFile(fileName: String): String? {
        var fin: FileInputStream? = null
        try {
            fin = openFileInput(fileName)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            val text = String(bytes)
            Log.d("MainActivity", text)
            return text
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            try {
                fin?.close()
            } catch (ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
        return null
    }
}