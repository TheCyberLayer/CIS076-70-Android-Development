package com.example.helloapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val btnHello = findViewById<Button>(R.id.btnHello)

        btnHello.setOnClickListener {
            Toast.makeText(this, "Hello SBVC!",  Toast.LENGTH_LONG).show()
        }
        val btnEmail = findViewById<Button>(R.id.btnEmail)

        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto:".toUri()
            intent.putExtra(Intent.EXTRA_EMAIL,"")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            startActivity(intent)

        }

        val intent = Intent(Intent.ACTION_VIEW)
        val btnWebsite = findViewById<Button>(R.id.btnWebsite)

        btnWebsite.setOnClickListener {
            intent.data = "https://www.valleycollege.edu".toUri()
            startActivity(intent)
        }
    }
}