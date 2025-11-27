package com.example.firebasechatdemo

import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(
    private val messageList: ArrayList<Message>
) : RecyclerView.Adapter<MessageAdapter.ChatViewHolder>() {

    private val itemSent = 1
    private val itemReceive = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }
    private val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messageList[position]


        holder.senderTextView.text = message.sender
        holder.messageTextView.text = message.text


        val messageParams = holder.messageTextView.layoutParams as ConstraintLayout.LayoutParams
        val senderParams = holder.senderTextView.layoutParams as ConstraintLayout.LayoutParams

        if (message.senderId == currentUserUid) {
            // Align RIGHT
            //params.startToStart = ConstraintLayout.LayoutParams.UNSET
            //params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            messageParams.startToStart = ConstraintLayout.LayoutParams.UNSET
            messageParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID

            senderParams.startToStart = ConstraintLayout.LayoutParams.UNSET
            senderParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID

            holder.messageTextView.setBackgroundResource(R.drawable.bg_me)
        } else {
            // Align LEFT
            //params.endToEnd = ConstraintLayout.LayoutParams.UNSET
            //params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            messageParams.endToEnd = ConstraintLayout.LayoutParams.UNSET
            messageParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID

            senderParams.endToEnd = ConstraintLayout.LayoutParams.UNSET
            senderParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID

            holder.messageTextView.setBackgroundResource(R.drawable.bg_other)
        }

        holder.messageTextView.layoutParams = messageParams
        //holder.messageTextView.text = message.text
        holder.senderTextView.layoutParams = senderParams

    }
        override fun getItemViewType(position: Int): Int {
            val currentMessage = messageList[position]
            return if (FirebaseAuth.getInstance().currentUser?.uid == currentMessage.senderId)
                itemSent else itemReceive
        }

        override fun getItemCount(): Int = messageList.size

        class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val senderTextView: TextView = itemView.findViewById(R.id.text_sender)
            val messageTextView: TextView = itemView.findViewById(R.id.text_message)
            val timeTextView: TextView = itemView.findViewById(R.id.text_time)
        }
    }
