package com.example.shopersapp
import com.example.shopersapp.models.Order

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.shopersapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopkeeperHomeActivity : AppCompatActivity() {
    private lateinit var orderListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopkeeper_home)

        orderListView = findViewById(R.id.orderListView)

        ApiClient.apiService.getOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                val orders = response.body() ?: emptyList()
                val adapter = ArrayAdapter(this@ShopkeeperHomeActivity, android.R.layout.simple_list_item_1, orders.map { "Order by ${it.customerName}" })
                orderListView.adapter = adapter

                orderListView.setOnItemClickListener { _, _, position, _ ->
                    val order = orders[position]
                    ApiClient.apiService.completeOrder(order.id).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Toast.makeText(this@ShopkeeperHomeActivity, "Order marked as complete", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(this@ShopkeeperHomeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                Toast.makeText(this@ShopkeeperHomeActivity, "Failed to load orders", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
