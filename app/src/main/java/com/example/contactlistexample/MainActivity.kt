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
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()
    private val filteredList = mutableListOf<Contact>()
    private var isFilterEnabled = false // Tracks filter toggle state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val cbStatus = findViewById<CheckBox>(R.id.cbStatus)
        val addButton = findViewById<Button>(R.id.addButton)
        val switchFilter = findViewById<SwitchMaterial>(R.id.switchFilter)

        setupRecyclerView()

        addButton.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val status = cbStatus.isChecked

            if (name.isBlank() || phone.isBlank()) {
                Toast.makeText(this, "Please enter name and phone", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contact = Contact(name, phone, status)
            contactList.add(contact)

            if (!isFilterEnabled || status) {
                filteredList.add(contact)
                adapter.updateContacts(filteredList)
            }

            etName.text.clear()
            etPhone.text.clear()
            cbStatus.isChecked = false
        }

        switchFilter.setOnCheckedChangeListener { _, isChecked ->
            isFilterEnabled = isChecked
            updateFilteredList()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(filteredList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateFilteredList() {
        filteredList.clear()
        if (isFilterEnabled) {
            filteredList.addAll(contactList.filter { it.status }) // Only available contacts
        } else {
            filteredList.addAll(contactList) // All contacts
        }
        adapter.updateContacts(filteredList)
    }
}
