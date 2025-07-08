package com.example.shopersapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ShopkeeperActivity : AppCompatActivity() {

    private val orders = listOf(
        "Order #1 by Alice: 2 x Milk, 1 x Bread",
        "Order #2 by Bob: 5 x Rice, 3 x Dal",
        "Order #3 by Charlie: 1 x Eggs, 2 x Apples"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopkeeper)

        val ordersListView = findViewById<ListView>(R.id.ordersListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, orders)
        ordersListView.adapter = adapter
    }
}
