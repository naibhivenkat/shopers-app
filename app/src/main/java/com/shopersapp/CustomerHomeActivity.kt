package com.shopersapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.shopersapp.models.Shop
import com.shopersapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerHomeActivity : AppCompatActivity() {

    private lateinit var shopList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home)

        shopList = findViewById(R.id.shopList)

        ApiClient.apiService.getShops().enqueue(object : Callback<List<Shop>> {
            override fun onResponse(call: Call<List<Shop>>, response: Response<List<Shop>>) {
                if (response.isSuccessful) {
                    val shops = response.body() ?: listOf()
                    val adapter = ArrayAdapter(this@CustomerHomeActivity,
                        android.R.layout.simple_list_item_1,
                        shops.map { it.name })
                    shopList.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Shop>>, t: Throwable) {
                Toast.makeText(this@CustomerHomeActivity, "Failed to load shops", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
