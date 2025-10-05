package com.example.valleycollegeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import com.example.valleycollegeapp.databinding.ActivityMainBinding
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.myWebView.webViewClient = WebViewClient()
        binding.myWebView.settings.javaScriptEnabled = true
        binding.myWebView.settings.loadsImagesAutomatically = true
        binding.myWebView.settings.domStorageEnabled = true
        binding.myWebView.settings.allowFileAccess = false
        binding.myWebView.settings.allowContentAccess = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            binding.button.visibility = View.GONE
            binding.textViewTop.visibility = View.GONE
            binding.textViewBottom.visibility = View.GONE
            binding.myWebView.visibility = View.VISIBLE
            binding.myWebView.loadUrl("https://valleycollege.edu")
            binding.backButton.visibility = View.VISIBLE
        }

        binding.backButton.setOnClickListener {
            binding.myWebView.visibility = View.GONE
            binding.backButton.visibility = View.GONE
            binding.textViewTop.visibility = View.VISIBLE
            binding.textViewBottom.visibility = View.VISIBLE
            binding.button.visibility = View.VISIBLE
        }
    }
}


