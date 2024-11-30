package com.example.contactlistexample

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistexample.adapter.ContactAdapter
import com.example.contactlistexample.data.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Form elements find
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val cbAvailable = findViewById<CheckBox>(R.id.cbAvailable)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val status = if (cbAvailable.isChecked) "Available" else "Busy"

            if (name.isBlank() || phone.isBlank()) {
                Toast.makeText(this, "Please enter name and phone", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create the contact object
            val contact = Contact(name, phone, status)
            // Add the contact to the list
            contactList.add(contact)

            // Notify the adapter that the item was inserted
            adapter.notifyItemInserted(contactList.size - 1)
        }

        // Initialize RecyclerView and Adapter
        setRecyclerViewAdapter(contactList)
    }

    private fun setRecyclerViewAdapter(contactList: List<Contact>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
