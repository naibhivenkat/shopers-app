package com.example.shopersapp

import com.example.shopersapp.CartActivity

import android.content.Intent
import com.example.shopersapp.models.Item
import com.example.shopersapp.network.ApiClient

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.shopersapp.network.ApiService

import com.example.shopersapp.network.fetchItems


class ShopItemsActivity : AppCompatActivity() {
    private lateinit var itemListView: ListView
    private val cartItems = mutableListOf<Item>()
    private var shopId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_items)

        shopId = intent.getIntExtra("shop_id", 0)
        itemListView = findViewById(R.id.itemListView)
        val cartBtn = findViewById<Button>(R.id.viewCartButton)

       fetchItems(shopId, this) { itemList ->
    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList.map { "${it.name} - ₹${it.price}" })
    itemListView.adapter = adapter
    itemListView.setOnItemClickListener { _, _, position, _ ->
        cartItems.add(itemList[position])
        Toast.makeText(this, "${itemList[position].name} added to cart", Toast.LENGTH_SHORT).show()
    }
}


        cartBtn.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("shop_id", shopId)
            intent.putParcelableArrayListExtra("cart_items", ArrayList(cartItems))
            startActivity(intent)
        }
    }
}
