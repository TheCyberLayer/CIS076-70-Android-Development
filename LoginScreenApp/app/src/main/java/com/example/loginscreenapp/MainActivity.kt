package com.example.loginscreenapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import android.widget.EditText
import android.widget.Button

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
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if ((email.isEmpty()) || password.isEmpty()){
                if (email.isEmpty()) {
                    editTextEmail.error = "This field is required"
                } else {
                    editTextEmail.error = null
                }
                if (password.isEmpty()) {
                    editTextPassword.error = "This field is required"
                } else {
                    editTextPassword.error = null
                }

            } else {
                editTextEmail.text.clear()
                editTextPassword.text.clear()
                processLogin(email, password)
            }
        }
        val forgotButton = findViewById<Button>(R.id.forgotButton)

        forgotButton.setOnClickListener {
            processForgot()
        }
    }
    fun processLogin(email: String, password: String) {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
    }
    fun processForgot() {
        Toast.makeText(this,"Contact Your Administrator", Toast.LENGTH_LONG).show()
    }
}

