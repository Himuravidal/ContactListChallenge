package com.example.contactlistexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistexample.R
import com.example.contactlistexample.data.Contact

class ContactAdapter(private var contactList: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    fun updateContacts(newContacts: List<Contact>) {
        contactList = newContacts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contactList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        private val phoneTextView: TextView = itemView.findViewById(R.id.tvPhone)
        private val statusTextView: TextView = itemView.findViewById(R.id.tvStatus)

        fun bind(contact: Contact) {
            nameTextView.text = contact.name
            phoneTextView.text = contact.phone
            statusTextView.text = if (contact.status) "Available" else "Unavailable"
        }
    }
}