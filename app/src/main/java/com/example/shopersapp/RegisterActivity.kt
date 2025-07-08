package com.example.shopersapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.shopersapp.network.ApiService

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val roleSpinner = findViewById<Spinner>(R.id.roleSpinner)
        val registerBtn = findViewById<Button>(R.id.registerButton)

        val roles = arrayOf("customer", "shopkeeper")
        roleSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)

        registerBtn.setOnClickListener {
            ApiService.register(
                username.text.toString(),
                password.text.toString(),
                roleSpinner.selectedItem.toString(),
                this
            )
        }
    }
}
