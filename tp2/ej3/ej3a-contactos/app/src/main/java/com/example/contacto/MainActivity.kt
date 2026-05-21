package com.example.contacto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contacto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: ContactDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = ContactDatabaseHelper(this)
        val newContact1 = Contact(name = "Juan", phone = "1111")
        val newContact2 = Contact(name = "Pedro", phone = "2222")
        val newContact3 = Contact(name = "Agus", phone = "3333")
        val newContact4 = Contact(id = 1, name = "Juan2", phone = "9999")

        dbHelper.addContact(newContact1)
        dbHelper.addContact(newContact2)
        dbHelper.addContact(newContact3)

        dbHelper.updateContact(newContact4)

        dbHelper.deleteContact(2)

        val contacts = dbHelper.getAllContacts()

        val text = contacts.joinToString(separator = "\n") {
            "ID: ${it.id}, Name: ${it.name}, Phone: ${it.phone}"
        }

        binding.textView.text = text
    }
}