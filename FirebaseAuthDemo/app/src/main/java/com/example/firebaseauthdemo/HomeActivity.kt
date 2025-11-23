package com.example.firebaseauthdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import androidx.core.widget.NestedScrollView

class HomeActivity: AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this).apply { textSize = 18f }
        val logout = Button(this).apply {
            text = context.getString(R.string.log_out)
        }
            logout.setOnClickListener {
                auth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            val layout = NestedScrollView(this).apply {
                addView(
                    LinearLayout(this@HomeActivity).apply {
                        orientation = LinearLayout.VERTICAL
                        setPadding(24, 24, 24, 24)
                        addView(tv)
                        addView(logout)
                    }
                )
            }
            setContentView(layout)

            val user = auth.currentUser
            tv.text = if (user != null) {
                "Welcome!\n\nUID: ${user.uid}\nEmail: ${user.email}"
            } else {
                "No user logged in"
            }

        }
    }

