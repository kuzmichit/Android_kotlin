package com.example.counter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val plus = findViewById<ImageView>(R.id.plus)
        val minus = findViewById<ImageView>(R.id.minus)

        plus.setOnClickListener {
            increaseCounter()
        }
        minus.setOnClickListener {
           decreaseCounter()
        }
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

    private fun updateCounterText() {
        val counterTextView = findViewById<TextView>(R.id.counterText)
        counterTextView.text = counter.toString()
    }
}
