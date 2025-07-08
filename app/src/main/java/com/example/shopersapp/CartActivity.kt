package com.example.shopersapp


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import import com.example.shopersapp.network.ApiService
import import com.example.shopersapp.models.Item

class CartActivity : AppCompatActivity() {
    private var shopId: Int = 0
    private var cartItems = listOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val listView = findViewById<ListView>(R.id.cartListView)
        val confirmBtn = findViewById<Button>(R.id.confirmOrderButton)

        shopId = intent.getIntExtra("shop_id", 0)
        cartItems = intent.getParcelableArrayListExtra<Item>("cart_items") ?: listOf()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cartItems.map { "${it.name} - ₹${it.price}" })
        listView.adapter = adapter

        confirmBtn.setOnClickListener {
            ApiService.placeOrder(shopId, cartItems, this)
        }
    }
}
