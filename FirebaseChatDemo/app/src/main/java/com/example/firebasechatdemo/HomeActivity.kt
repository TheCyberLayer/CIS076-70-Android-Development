package com.example.firebasechatdemo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import androidx.core.widget.NestedScrollView
import kotlin.jvm.java

class HomeActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tv = TextView(this).apply { textSize = 18f }
        val logout = Button(this).apply {
            text = context.getString(R.string.log_out)
        }
        logout.setOnClickListener {
            auth.signOut()
            startActivity(/* intent = */ Intent(this, MainActivity::class.java))
            finish()
        }
        val openChat = Button(this).apply {text = context.getString(R.string.start)}
        
        val layout = NestedScrollView(this).apply {
            addView(
                LinearLayout(this@HomeActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(24, 24, 24, 24)
                    addView(tv)
                    addView(logout)
                    addView(openChat)
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

        openChat.setOnClickListener{
            val intent = Intent(this, ChatActivity::class.java).apply {
                putExtra("receiverUid", "RECEIVER_UID_HERE")
                putExtra("receiverName", "Receiver Name")
            }
            startActivity(intent)
        }
    }
}










