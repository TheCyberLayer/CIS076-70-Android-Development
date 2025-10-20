package com.example.helloapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val btnForgot = findViewById<Button>(R.id.btnForgot)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val minLength = 8
            var isValid = true

            if (email.isEmpty()) {
                editTextEmail.error = "This field is required"
                isValid = false
            } else {
                editTextEmail.error = null
            }

            if (password.isEmpty()) {
                editTextPassword.error = "This field is required"
                isValid = false
            } else if (password.length < minLength) {
                editTextPassword.error = "This password must be 8 characters or more"
                isValid = false
                } else {
                    editTextPassword.error = null
                }

            if (isValid) {
                editTextEmail.text.clear()
                editTextPassword.text.clear()
                processLogin(email, password)
            }
        }
        btnForgot.setOnClickListener {
            processForgot()
        }
    }

        fun processLogin(email: String, password: String) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
        }

        fun processForgot() {
            Toast.makeText(this, "Contact Your Administrator", Toast.LENGTH_LONG).show()
        }
    }
