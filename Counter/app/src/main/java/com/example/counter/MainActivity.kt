package com.example.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.counter.databinding.ActivityMainBinding
import androidx.core.content.edit


class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using the binding class and get an instance
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the content view to the root of the binding object
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        sharedPreferences.edit {
        }
        counter = sharedPreferences.getInt("counter", 0)

        binding.plus.setOnClickListener {
            increaseCounter()
        }

        binding.minus.setOnClickListener {
            decreaseCounter()
        }

        binding.buttonReset.setOnClickListener { // Assuming you have a resetButton with this ID
            resetCounter()
        }

        updateCounterText() // Initial update
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        sharedPreferences.edit {
            putInt("counter", counter)
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        counter = sharedPreferences.getInt("counter", 0)
        updateCounterText()
    }


    private fun decreaseCounter() {
        counter--
        if (counter < 0) counter = 0
        updateCounterText()
    }

    private fun increaseCounter() {
        counter++
        updateCounterText()
    }

    private fun resetCounter() {
        counter = 0
        updateCounterText()
    }

    private fun updateCounterText() {
        // Access TextView directly via binding
        binding.countText.text = counter.toString() // Assuming your TextView has id 'counterText'
    }
}
