package com.example.counter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.counter.databinding.ActivityMainBinding
import androidx.core.content.edit


class MainActivity : AppCompatActivity() {

    private var count: Int = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using the binding class and get an instance
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the content view to the root of the binding object
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)

        sharedPreferences.edit {
            putInt("counter", count)
        }

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

        Log.d("TAG","onCreate: ")
    }

    override fun onPause() {
        super.onPause()

        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        sharedPreferences.edit {
            putInt("counter", count)
        }
        Log.d("TAG","onPause: ")
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        count = sharedPreferences.getInt("counter", 0)
        updateCounterText()
        Log.d("TAG","onResume: ")
    }

    override fun onStop() {
        super.onStop()
        if (isFinishing) { // vero solo se l’activity sta per essere distrutta definitivamente
            count = 0
            val sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
            sharedPreferences.edit {
                putInt("counter", count)
                apply()
            }
        }
            Log.d("TAG","onStop: $isFinishing")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("TAG", "onDestroy:$count")
    }


    private fun decreaseCounter() {
        count--
        if (count < 0) count = 0
        updateCounterText()
    }

    private fun increaseCounter() {
        count++
        updateCounterText()
    }

    private fun resetCounter() {
        count = 0
        updateCounterText()
    }

    private fun updateCounterText() {
        // Access TextView directly via binding
        binding.countText.text = count.toString() // Assuming your TextView has id 'counterText'
    }
}
