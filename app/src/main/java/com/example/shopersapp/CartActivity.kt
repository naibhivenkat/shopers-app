package com.example.shopersapp

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.shopersapp.models.Item
import com.example.shopersapp.models.PlaceOrderRequest
import com.example.shopersapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity() {

    private lateinit var cartListView: ListView
    private lateinit var placeOrderBtn: Button
    private lateinit var cartItems: ArrayList<Item>
    private var shopId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartListView = findViewById(R.id.cartListView)
        placeOrderBtn = findViewById(R.id.placeOrderButton)

        shopId = intent.getIntExtra("shop_id", 0)
        cartItems = intent.getParcelableArrayListExtra("cart_items") ?: arrayListOf()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cartItems.map { "${it.name} - ₹${it.price}" })
        cartListView.adapter = adapter

        placeOrderBtn.setOnClickListener {
            placeOrder(shopId, cartItems, this)
        }
    }

    private fun placeOrder(shopId: Int, items: List<Item>, context: Context) {
        val request = PlaceOrderRequest(shopId, items)
        ApiClient.apiService.placeOrder(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Order placed successfully", Toast.LENGTH_SHORT).show()
                    finish() // close activity
                } else {
                    Toast.makeText(context, "Failed to place order", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
