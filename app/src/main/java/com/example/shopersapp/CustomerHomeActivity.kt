package com.example.shopersapp


import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shopersapp.adapters.ShopAdapter
import com.example.shopersapp.models.Shop
import com.example.shopersapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerHomeActivity : AppCompatActivity() {

    private lateinit var shopListView: ListView
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home)

        shopListView = findViewById(R.id.shop_list_view)
        userId = intent.getIntExtra("user_id", -1)

        fetchShops()
    }

    private fun fetchShops() {
        ApiClient.apiService.getShops().enqueue(object : Callback<List<Shop>> {
            override fun onResponse(call: Call<List<Shop>>, response: Response<List<Shop>>) {
                if (response.isSuccessful) {
                    val shopList = response.body() ?: emptyList()
                    val adapter = ShopAdapter(this@CustomerHomeActivity, shopList)
                    shopListView.adapter = adapter

                    shopListView.setOnItemClickListener { _, _, position, _ ->
                        val selectedShop = shopList[position]
                        val intent = Intent(this@CustomerHomeActivity, ShopItemsActivity::class.java)
                        intent.putExtra("shop_id", selectedShop.id)
                        intent.putExtra("user_id", userId)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@CustomerHomeActivity, "Error fetching shops", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Shop>>, t: Throwable) {
                Toast.makeText(this@CustomerHomeActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
