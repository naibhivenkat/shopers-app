package com.example.shopersapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.shopersapp.network.ApiService
import com.shopersapp.models.Order

class ShopkeeperHomeActivity : AppCompatActivity() {
    private lateinit var orderListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopkeeper_home)

        orderListView = findViewById(R.id.orderListView)

        ApiService.fetchOrders(this) { orderList ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, orderList.map { "Order by ${it.customerName}" })
            orderListView.adapter = adapter

            orderListView.setOnItemClickListener { _, _, position, _ ->
                val order = orderList[position]
                ApiService.markOrderComplete(order.id, this)
            }
        }
    }
}
