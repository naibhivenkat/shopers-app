package com.example.shopersapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CustomerActivity : AppCompatActivity() {

    private val shops = listOf(
        Pair(1, "Fresh Mart"),
        Pair(2, "Grocery King"),
        Pair(3, "Daily Needs")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        val shopListView = findViewById<ListView>(R.id.shopListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shops.map { it.second })
        shopListView.adapter = adapter

        shopListView.setOnItemClickListener { _, _, position, _ ->
            val shopId = shops[position].first
            val intent = Intent(this, ShopItemsActivity::class.java)
            intent.putExtra("shop_id", shopId)
            startActivity(intent)
        }
    }
}
