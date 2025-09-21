package com.example.hellocollegeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            val textView = findViewById<TextView>(R.id.myTextView)
            textView.text = getString(R.string.welcome_message)

            return@setOnApplyWindowInsetsListener insets
        }
    }
    //This is the function which triggers the text change.
    fun onButtonClick(view: View) {
        val textView = findViewById<TextView>(R.id.myTextView)
        val button = view as Button
        val imageView = findViewById<ImageView>(R.id.imageView)
        textView.text = getString(R.string.change_text)
        button.text = getString(R.string.button_clicked)
        imageView.setImageResource(R.drawable.second_image)
    }

}