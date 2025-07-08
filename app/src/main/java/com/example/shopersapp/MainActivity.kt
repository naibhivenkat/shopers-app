package com.example.shopersapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity  // ✅ Needed for AppCompatActivity

class MainActivity : AppCompatActivity() {  // ✅ Correct superclass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
