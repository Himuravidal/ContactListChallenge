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


        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val cbStatus = findViewById<CheckBox>(R.id.cbStatus)

        val addButton = findViewById<Button>(R.id.addButton)
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

            adapter.notifyItemInserted(contactList.size - 1)
        }

        setRecyclerViewAdapter(contactList)
    }

    private fun setRecyclerViewAdapter(contactList: List<Contact>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
