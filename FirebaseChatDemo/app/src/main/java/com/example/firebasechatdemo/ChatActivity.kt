package com.example.firebasechatdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasechatdemo.databinding.ActivityChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference
    private lateinit var senderUid: String
    private lateinit var senderName: String
    private lateinit var adapter:MessageAdapter
    val chatRoom = "chat_room"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        messageList = ArrayList()
        binding.homeBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }


        senderUid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        senderName = FirebaseAuth.getInstance().currentUser?.displayName.orEmpty()

        supportActionBar?.title = "Chat Room"

        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        binding.chatRV.layoutManager = layoutManager

        adapter = MessageAdapter(
            messageList = messageList
        )

        this.adapter.also {binding.chatRV.adapter = it}

        mDbRef = FirebaseDatabase.getInstance().reference

        mDbRef.child("chats").child(chatRoom).child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(Message::class.java)
                        message?.let { messageList.add(it) }
                    }
                    adapter.notifyDataSetChanged()

                    binding.chatRV.post {
                        binding.chatRV.scrollToPosition(messageList.size - 1)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ChatActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            })
        binding.sendBtn.setOnClickListener {
            val messageText = binding.messageEditText.text.toString().trim()
            if (messageText.isEmpty()) return@setOnClickListener

            if (messageText.isNotEmpty()) {
                val messageObject = Message(text = messageText, senderId=senderUid,
                    sender = senderName)

                // Update senderRoom
                mDbRef.child("chats").child(chatRoom).child("messages")
                    .push().setValue(messageObject)

                binding.messageEditText.setText("")
            }
        }

    }
}
